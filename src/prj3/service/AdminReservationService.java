package prj3.service;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;

import prj3.dao.AdminReservationDAO;
import prj3.domain.PastReservationDetailDomain;
import prj3.domain.PastReservationViewDomain;
import prj3.domain.RealtimeListDomain;
import prj3.domain.ReservationDetailDomain;
import prj3.domain.ReservationViewDomain;
import prj3.vo.IndexListVO;
import prj3.vo.PastReservationDetailVO;
import prj3.vo.SearchVO;

public class AdminReservationService {

	public List<ReservationViewDomain> searchReservationList(SearchVO sVO){
		List<ReservationViewDomain> list = null;
		
		AdminReservationDAO arDAO = AdminReservationDAO.getInstance();
		
		try {
			list = arDAO.selectReservationList(sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchReservationList
	
	public List<PastReservationViewDomain> searchPastReservationList(SearchVO sVO){
		List<PastReservationViewDomain> list = null;
		
		AdminReservationDAO arDAO = AdminReservationDAO.getInstance();
		
		try {
			list = arDAO.selectAllPastReservation(sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchPastReservationList
	
	/**
	 * 검색값을 받아서 검색값이 없다면 전체글의 갯수를 조회하고, 
	 * 검색 값이 있다면 검색값에 해당하는 글의 갯수를 조회하는 일. 
	 * @param sv
	 * @return
	 */
	public int totalCount(SearchVO sVO) {
		int totalCnt = 0;
		AdminReservationDAO arDAO = AdminReservationDAO.getInstance();
		try {
			totalCnt = arDAO.selectTotalCount(sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return totalCnt;

	}// totalCount
	
	/**
	 * 검색값을 받아서 검색값이 없다면 전체글의 갯수를 조회하고, 
	 * 검색 값이 있다면 검색값에 해당하는 글의 갯수를 조회하는 일. 
	 * @param sv
	 * @return
	 */
	public int totalPastCount(SearchVO sVO) {
		int totalCnt = 0;
		AdminReservationDAO arDAO = AdminReservationDAO.getInstance();
		try {
			totalCnt = arDAO.pastReservationTotalCount(sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		
		return totalCnt;
		
	}// totalCount
	
	/**
	 * 한 화면에 보여줄 게시물의 수
	 * @return
	 */
	public int pageScale() {
		int pageScale = 10;
		return pageScale;
	}// pageScale
	
	/**
	 * 총 페이지를 보여주기 위해 필요한 페이지 수
	 * @param pageScale 한 화면에 보여줄 게시물에 보여질 수
	 * @param totalCount 총 게시물의 수
	 * @return
	 */
	public int totalPage(int pageScale, int totalCount) {
		int totalPage = totalCount / pageScale;// 모든 게시물울 보여주기 위한 총 페이지 수
//		System.out.println(totalCount +"/"+ pageScale +"="+ totalPage);
		if (totalCount % pageScale != 0) {// pageScale로 딱 떨어지지 않으면 나머지 게시물을 보여주기 위해 1장 더 필요하다.
			totalPage++;
		} // end if
		return totalPage;
	}// totalPage
	
	/**
	 * 페이지의 시작번호 구하는 일
	 * @param pageScale
	 * @param currentPage
	 * @return
	 */
	public int startNum(int pageScale, int currentPage) {
		int startNum = currentPage * pageScale - pageScale + 1;

		return startNum;
		
	}// startNum
	
	/**
	 * 페이지의 끝번호 구하는 일
	 * @param pageScale
	 * @param startNum
	 * @return
	 */
	public int endNum(int pageScale, int startNum) {
		int endNum = startNum + pageScale - 1;

		return endNum;
		
	}// end num
	
	// 현재 게시판의 페이지 인덱스 설정
	public String indexList(IndexListVO ilVO) {
		int pagenumber; // 화면에 보여질 페이지 인덱스 수
		int startpage; // 화면에 보여질 시작페이지 번호
		int endpage; // 화면에 보여질 마지막페이지 번호
		int curpage; // 이동하고자 하는 페이지 번호
	
		String strList=""; // 리턴될 페이지 인덱스 리스트
	
		pagenumber = 10; // 한 화면의 페이지 인덱스 수 
	
		// 시작 페이지번호 구하기
		startpage = (( ilVO.getCurrentPage() - 1) / pagenumber) * pagenumber + 1;
	
		// 마지막 페이지번호 구하기
		endpage = (((startpage - 1) + pagenumber) / pagenumber) * pagenumber;
	
		// 총 페이지 수가 계산된 마지막페이지 번호보다 작을경우 
	
		// 총 페이지 수가 마지막페이지 번호가 됨
	
	
		if ( ilVO.getTotalPage() <= endpage){
			endpage = ilVO.getTotalPage();
		}//end if
	
		// 첫번째 페이지 인덱스 화면이 아닌경우
		if ( ilVO.getCurrentPage() > pagenumber) {
			curpage = startpage - 1; // 시작페이지 번호보다 1 적은 페이지로 이동
			strList = strList + "<li class='page-item'><a class='page-link' href="+ilVO.getUrl()+"?current_page="+curpage+" aria-label='Previous'>\r\n" + 
					"		        <span aria-hidden='true'>&laquo;</span></a></li>";
		}else{                                        
			strList = strList + "<li class='page-item'>\r\n" + 
					"		      <a class='page-link' href='#' aria-label='Previous'>\r\n" + 
					"		        <span aria-hidden='true'>&laquo;</span>\r\n" + 
					"		      </a>\r\n" + 
					"			 </li>";
		}//end else
	
	//	strList = strList + " ... ";
	
		// 시작페이지 번호부터 마지막페이지 번호까지 화면에 표시
		curpage = startpage;
	
		while (curpage <= endpage){
			if (curpage == ilVO.getCurrentPage()) {
				strList = strList + "<li class='page-item'><a class='page-link' title='현재페이지'>"+ilVO.getCurrentPage()+"</a></li>";
			} else {
				strList = strList + "<li class='page-item'><a class='page-link' href="+ilVO.getUrl()+"?current_page="+curpage+">"+curpage+"</a></li>";
			}//end else
	
			curpage++;
		}//end while
	
	//	strList = strList + " ... ";
	
		// 뒤에 페이지가 더 있는경우
		if ( ilVO.getTotalPage() > endpage) {
			curpage = endpage + 1; 
			strList = strList + "<li class='page-item'>\r\n" + 
								"	<a class='page-link'  href="+ilVO.getUrl()+"?current_page="+curpage+" aria-label='Next'>\r\n" + 
								"		<span aria-hidden='true'>&raquo;</span>\r\n" + 
								"	</a>\r\n" + 
								"</li>";
		}else{
			strList = strList + "<li class='page-item'>\r\n" + 
								"	<a class='page-link' href='#' aria-label='Next'>\r\n" + 
								"		<span aria-hidden='true'>&raquo;</span>\r\n" + 
								"	</a>\r\n" + 
								"</li>";
		}//end else
	
		return strList;
	
	}//indexList
	
	public ReservationDetailDomain searchReservationDetail(String reservation_num){
		ReservationDetailDomain rdd = null;
		AdminReservationDAO arDAO = AdminReservationDAO.getInstance();
		
		try {
			rdd = arDAO.selectReservationDetail(reservation_num);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return rdd;
	}//searchReservationDetail
	
	public PastReservationDetailDomain searchPastReservationDetail(PastReservationDetailVO prdVO){
		PastReservationDetailDomain prdd = null;
		AdminReservationDAO arDAO = AdminReservationDAO.getInstance();
		
		try {
			prdd = arDAO.selectPastReservationDetail(prdVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return prdd;
	}//searchPastReservationDetail
	
	public List<RealtimeListDomain> searchRealtime(String reservation_num){
		List<RealtimeListDomain> realtimeList = null;
		
		AdminReservationDAO arDAO = AdminReservationDAO.getInstance();
		try {
			realtimeList = arDAO.selectRealtime(reservation_num);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return realtimeList;
	}//searchRealtime
	
	public JSONObject modifypayStatus(String reservation_num) {
		JSONObject json = new JSONObject();
		boolean flag = false;
		
		AdminReservationDAO arDAO = AdminReservationDAO.getInstance();
		try {
			if(arDAO.updateReservation(reservation_num)&&arDAO.updateDeposit(reservation_num)) {
				
//				System.out.println("세번째flag"+arDAO.updateReservation(reservation_num));
//				System.out.println("네번째flag"+arDAO.updateDeposit(reservation_num));
				flag = true;
			}//end if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
//		System.out.println("다섯번째flag"+flag);
		json.put("result", flag);
		
		return json;
	}//modifyDeposit
	
}//class









