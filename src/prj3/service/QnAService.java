package prj3.service;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.ui.Model;

import prj3.dao.BoardDAO;
import prj3.domain.QnABoardDetailDomain;
import prj3.domain.QnAListDomain;
import prj3.vo.IndexListVO;
import prj3.vo.QnAAddRpVO;
import prj3.vo.QnAModifyVO;
import prj3.vo.QnAWriteVO;
import prj3.vo.RpModifyVO;
import prj3.vo.SearchVO;


public class QnAService {
///////////////////////�ۿ°�////////////////////////////////////////
	
	/**
	 * �˻����� �޾Ƽ� �˻����� ���ٸ� ��ü ���� ������ ��ȸ�ϰ�, �˻����� �ִٸ� 
	 * �˻����� �ش��ϴ� ���� ������ ��ȸ�ϴ� ��.
	 * @param sVO
	 * @return
	 */
	public int selectTotalCount(SearchVO sVO) {
		int cnt = 0;
		String mappedId="qnaTotalCount";
		BoardDAO bDao = BoardDAO.getInstance();
		try {
			cnt = bDao.selectTotalCount(mappedId,sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return cnt;
	}
	
	/**
	 * �� ȭ�鿡 ������ �Խù��� ��
	 * @return
	 */
	public int pageScale() {
		int pageScale=10;
		return pageScale;
	}//pageScale

	/**
	 * �� �������� �����ֱ� ���� �ʿ��� ������ ��
	 * @param pageScale �� ȭ�鿡 ������ �Խù��� �� 
	 * @param totalCount �� �Խù��� �� 
	 * @return
	 */
	public int totalPage(int pageScale, int totalCount) {
	  	int totalPage= totalCount/pageScale ;//��� �Խù��� �����ֱ� ���� �� ������ ��
	  	
		if ( totalCount % pageScale !=0 ) {// pageScale�� �� �������� ������ ������ �Խù��� �����ֱ� ���� �� �� �� �ʿ��ϴ�.
			totalPage++;
		} //end if
		//int totalPage=(int)Math.ceil( (double) totalCount / pageScale );
		
		return totalPage;
	}//totalPage
	/**
	 * �������� ���۹�ȣ ���ϴ� ��
	 * @param pageScale
	 * @param currentPage
	 * @return
	 */
	public int startNum( int pageScale, int currentPage) {
		int startNum=currentPage*pageScale()-pageScale()+1;
			
		return startNum;
	}//startNum
	
	/**
	 * �������� ����ȣ ���ϴ� ��
	 * @param pageScale
	 * @param startNum
	 * @return
	 */
	public int endNum(int pageScale, int startNum) {
		int endNum=startNum+pageScale-1;
		return endNum;
	}//endNum
	
///////////////////////////////////////////////////////////////
	
	public List<QnAListDomain> searchAllQnA( SearchVO sVO){
		List<QnAListDomain> list= null;
		BoardDAO bDao = BoardDAO.getInstance();
		try {
			list=bDao.selectAllQnA(sVO);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		 
		
		return list;
	}//searchAllQnA
	public QnABoardDetailDomain searchDetailQnA(int num){
		QnABoardDetailDomain qbdd =null;
		BoardDAO bDao = BoardDAO.getInstance();
		
		try {
			qbdd = bDao.selectDetailQnA(num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return qbdd;
	}//searchDetailQnA
	
	public JSONObject insertQnAPost(QnAWriteVO qwVO) {
		JSONObject json = new JSONObject();
		
		BoardDAO bDAO = BoardDAO.getInstance();
		qwVO.setUser_id("������");
		boolean flag = bDAO.insertQnAPost(qwVO)==1;
		
		json.put("result", flag);
		
		return json;
	}//insertQnAPost
	
	public JSONObject deletePostQnA(int q_num) {
		JSONObject json = new JSONObject();
		BoardDAO bDAO = BoardDAO.getInstance();
		boolean flag = bDAO.deletePostQnA(q_num)==1;
		json.put("result" , flag);
		return json;
	}//deletePostQnA
	public JSONObject updatePostQnA(QnAModifyVO qVo) {
		JSONObject json = new JSONObject();
		BoardDAO bDAO = BoardDAO.getInstance();
		boolean flag = bDAO.updatePostQnA(qVo)==1;
		json.put("result", flag);
		return json;
	}//deletePostQnA
	
	public JSONObject updateQnARp(QnAAddRpVO qarVO) {
		JSONObject json = new JSONObject();
		BoardDAO bDAO = BoardDAO.getInstance();
		boolean flag = bDAO.updateQnARp(qarVO)==1;
		json.put("result", flag);
		return json;
	}//deletePostQnA
	
	public JSONObject replyModify(RpModifyVO rmVO) {
		JSONObject json = new JSONObject();
		BoardDAO bDAO = BoardDAO.getInstance();
		boolean flag =bDAO.replyModify(rmVO)==1;
		return json;
	}//replyModify 
	
	// ���� �Խ����� ������ �ε��� ����
public String indexList(IndexListVO ilVO) {
			int pagenumber; // ȭ�鿡 ������ ������ �ε��� ��
			int startpage; // ȭ�鿡 ������ ���������� ��ȣ
			int endpage; // ȭ�鿡 ������ ������������ ��ȣ
			int curpage; // �̵��ϰ��� �ϴ� ������ ��ȣ

			String strList=""; // ���ϵ� ������ �ε��� ����Ʈ

			pagenumber = 5; // �� ȭ���� ������ �ε��� �� 

			// ���� ��������ȣ ���ϱ�
			startpage = ((ilVO.getCurrentPage() - 1) / pagenumber) * pagenumber + 1;

			// ������ ��������ȣ ���ϱ�
			endpage = (((startpage - 1) + pagenumber) / pagenumber) * pagenumber;

			// �� ������ ���� ���� ������������ ��ȣ���� ������� 

			// �� ������ ���� ������������ ��ȣ�� ��


			if (ilVO.getTotalPage() <= endpage){
				endpage = ilVO.getTotalPage();
			}//end if

			// ù��° ������ �ε��� ȭ���� �ƴѰ��
			if ( ilVO.getCurrentPage() > pagenumber) {
				curpage = startpage - 1; // ���������� ��ȣ���� 1 ���� �������� �̵�														
				strList = strList + "<li class=\"page-item\"><a class=\"page-link text-dark\" href="+ilVO.getUrl()+"page="+curpage+" aria-label=\"Previous\">\r\n" + 
						"         <span aria-hidden=\"true\">&laquo;</span></a></li>"; 
			}else{
				strList = strList + "		    <li class=\"page-item\">\r\n" + 
										"		      <a class=\"page-link text-dark\" href=\"#\" aria-label=\"Previous\">\r\n" + 
										"		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + 
										"		      </a>\r\n" + 
										"		    </li>";
			}

			//strList = strList + " ... ";

			// ���������� ��ȣ���� ������������ ��ȣ���� ȭ�鿡 ǥ��
			curpage = startpage;

			while (curpage <= endpage){
				if (curpage == ilVO.getCurrentPage()) {
					strList = strList + "<li class=\"page-item\"><a class='page-link text-white  bg-secondary' title='����������'>"+ilVO.getCurrentPage()+"</a></li>";
				} else {
					strList = strList +"<li class=\"page-item\"><a class=\"page-link text-dark \" href="+ilVO.getUrl()+"page="+curpage+">"+curpage+"</a></li>";
				}//end else

				curpage++;
			}//end while

			//strList = strList + " ... ";

			// �ڿ� �������� �� �ִ°��
			if ( ilVO.getTotalPage() > endpage) {
				curpage = endpage + 1; 
				strList = strList + "<li class=\"page-item\">\r\n" + 
						"		      <a class=\"page-link text-dark \" href="+ilVO.getUrl()+"page="+curpage+" aria-label=\"Next\">\r\n" + 
								"		        <span aria-hidden=\"true\">&raquo;</span></a>";
			}else{
				strList = strList + "<li class=\"page-item\">\r\n" + 
						"		      <a class=\"page-link text-dark \" href=\"#\" aria-label=\"Next\">\r\n" + 
						"		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + 
						"		      </a>\r\n" + 
						"		    </li>";
			}//end else

			return strList;
		}//indexList

	
	
}//class
