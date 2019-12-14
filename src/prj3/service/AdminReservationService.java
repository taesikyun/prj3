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
	 * �˻����� �޾Ƽ� �˻����� ���ٸ� ��ü���� ������ ��ȸ�ϰ�, 
	 * �˻� ���� �ִٸ� �˻����� �ش��ϴ� ���� ������ ��ȸ�ϴ� ��. 
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
	 * �˻����� �޾Ƽ� �˻����� ���ٸ� ��ü���� ������ ��ȸ�ϰ�, 
	 * �˻� ���� �ִٸ� �˻����� �ش��ϴ� ���� ������ ��ȸ�ϴ� ��. 
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
	 * �� ȭ�鿡 ������ �Խù��� ��
	 * @return
	 */
	public int pageScale() {
		int pageScale = 10;
		return pageScale;
	}// pageScale
	
	/**
	 * �� �������� �����ֱ� ���� �ʿ��� ������ ��
	 * @param pageScale �� ȭ�鿡 ������ �Խù��� ������ ��
	 * @param totalCount �� �Խù��� ��
	 * @return
	 */
	public int totalPage(int pageScale, int totalCount) {
		int totalPage = totalCount / pageScale;// ��� �Խù��� �����ֱ� ���� �� ������ ��
//		System.out.println(totalCount +"/"+ pageScale +"="+ totalPage);
		if (totalCount % pageScale != 0) {// pageScale�� �� �������� ������ ������ �Խù��� �����ֱ� ���� 1�� �� �ʿ��ϴ�.
			totalPage++;
		} // end if
		return totalPage;
	}// totalPage
	
	/**
	 * �������� ���۹�ȣ ���ϴ� ��
	 * @param pageScale
	 * @param currentPage
	 * @return
	 */
	public int startNum(int pageScale, int currentPage) {
		int startNum = currentPage * pageScale - pageScale + 1;

		return startNum;
		
	}// startNum
	
	/**
	 * �������� ����ȣ ���ϴ� ��
	 * @param pageScale
	 * @param startNum
	 * @return
	 */
	public int endNum(int pageScale, int startNum) {
		int endNum = startNum + pageScale - 1;

		return endNum;
		
	}// end num
	
	// ���� �Խ����� ������ �ε��� ����
	public String indexList(IndexListVO ilVO) {
		int pagenumber; // ȭ�鿡 ������ ������ �ε��� ��
		int startpage; // ȭ�鿡 ������ ���������� ��ȣ
		int endpage; // ȭ�鿡 ������ ������������ ��ȣ
		int curpage; // �̵��ϰ��� �ϴ� ������ ��ȣ
	
		String strList=""; // ���ϵ� ������ �ε��� ����Ʈ
	
		pagenumber = 10; // �� ȭ���� ������ �ε��� �� 
	
		// ���� ��������ȣ ���ϱ�
		startpage = (( ilVO.getCurrentPage() - 1) / pagenumber) * pagenumber + 1;
	
		// ������ ��������ȣ ���ϱ�
		endpage = (((startpage - 1) + pagenumber) / pagenumber) * pagenumber;
	
		// �� ������ ���� ���� ������������ ��ȣ���� ������� 
	
		// �� ������ ���� ������������ ��ȣ�� ��
	
	
		if ( ilVO.getTotalPage() <= endpage){
			endpage = ilVO.getTotalPage();
		}//end if
	
		// ù��° ������ �ε��� ȭ���� �ƴѰ��
		if ( ilVO.getCurrentPage() > pagenumber) {
			curpage = startpage - 1; // ���������� ��ȣ���� 1 ���� �������� �̵�
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
	
		// ���������� ��ȣ���� ������������ ��ȣ���� ȭ�鿡 ǥ��
		curpage = startpage;
	
		while (curpage <= endpage){
			if (curpage == ilVO.getCurrentPage()) {
				strList = strList + "<li class='page-item'><a class='page-link' title='����������'>"+ilVO.getCurrentPage()+"</a></li>";
			} else {
				strList = strList + "<li class='page-item'><a class='page-link' href="+ilVO.getUrl()+"?current_page="+curpage+">"+curpage+"</a></li>";
			}//end else
	
			curpage++;
		}//end while
	
	//	strList = strList + " ... ";
	
		// �ڿ� �������� �� �ִ°��
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
				
//				System.out.println("����°flag"+arDAO.updateReservation(reservation_num));
//				System.out.println("�׹�°flag"+arDAO.updateDeposit(reservation_num));
				flag = true;
			}//end if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
//		System.out.println("�ټ���°flag"+flag);
		json.put("result", flag);
		
		return json;
	}//modifyDeposit
	
}//class









