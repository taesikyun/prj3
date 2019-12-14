package prj3.service;

import java.sql.SQLException;
import java.util.List;

import prj3.dao.AdminMemberDAO;
import prj3.domain.MemberDetailDomain;
import prj3.domain.MemberReservationDomain;
import prj3.domain.MemberViewDomain;
import prj3.vo.IndexListVO;
import prj3.vo.SearchVO;

public class AdminMemberService {

	public List<MemberViewDomain> searchMemberList(SearchVO sVO){
		List<MemberViewDomain> list = null;
		
		AdminMemberDAO amDAO = AdminMemberDAO.getInstance();
		
		try {
			list = amDAO.selectMemberList(sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchMemberList
	
	/**
	 * �˻����� �޾Ƽ� �˻����� ���ٸ� ��ü���� ������ ��ȸ�ϰ�, 
	 * �˻� ���� �ִٸ� �˻����� �ش��ϴ� ���� ������ ��ȸ�ϴ� ��. 
	 * @param sv
	 * @return
	 */
	public int totalCount(SearchVO sVO) {
		int totalCnt = 0;
		AdminMemberDAO amDAO = AdminMemberDAO.getInstance();
		try {
			totalCnt = amDAO.selectTotalCount(sVO);
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
	
	public MemberDetailDomain searchMemberDetail(String user_id) {
		
		MemberDetailDomain mdd = null;
		
		AdminMemberDAO amDAO = AdminMemberDAO.getInstance();
		
		try {
			mdd = amDAO.selectMemberDetail(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return mdd;
		
	}//searchMemberDetail
	
	public List<MemberReservationDomain> searchMemberReservation(String user_id) {
		
		List<MemberReservationDomain> mrd = null;
		
		AdminMemberDAO amDAO = AdminMemberDAO.getInstance();
		
		try {
			mrd = amDAO.selectMemberReservation(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return mrd;
		
	}//searchMemberReservation
	
}//class









