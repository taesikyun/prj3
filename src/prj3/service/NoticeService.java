package prj3.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import prj3.dao.BoardDAO;
import prj3.domain.NoticeBoardDetailDomain;
import prj3.domain.NoticeListDomain;
import prj3.domain.QnABoardDetailDomain;
import prj3.vo.IndexListVO;
import prj3.vo.NoticeModifyVO;
import prj3.vo.NoticeWriteVO;
import prj3.vo.QnAModifyVO;
import prj3.vo.QnAWriteVO;
import prj3.vo.SearchVO;

public class NoticeService {
	
	/**
	 * �˻����� �޾Ƽ� �˻����� ���ٸ� ��ü ���� ������ ��ȸ�ϰ�, �˻����� �ִٸ� 
	 * �˻����� �ش��ϴ� ���� ������ ��ȸ�ϴ� ��.
	 * @param sVO
	 * @return
	 */
	public int selectTotalCount(SearchVO sVO) {
		int cnt = 0;
		String mappedId="noticeTotalCount";
		BoardDAO bDao = BoardDAO.getInstance();
		try {
			cnt = bDao.selectTotalCount(mappedId,sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return cnt;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public List<NoticeListDomain> searchAllNotice( SearchVO sVO){
		List<NoticeListDomain> noticeList= null;
		BoardDAO bDao = BoardDAO.getInstance();
		try {
			noticeList=bDao.selectAllNotice(sVO);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return noticeList;
	}//searchAllNotice
	public NoticeBoardDetailDomain searchDetailNotice(int num){
		NoticeBoardDetailDomain nbdd =null;
		BoardDAO bDao = BoardDAO.getInstance();
		
		try {
			nbdd = bDao.selectDetailNotice(num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return nbdd;
	}//searchDetailQnA
	
	public JSONObject insertNoticePost(NoticeWriteVO nwVO) {
		boolean flag = false;
		JSONObject json = new JSONObject();
		
		
		

		
			BoardDAO bDAO = BoardDAO.getInstance();
			

			flag = bDAO.insertNoticePost(nwVO)==1;
		
		json.put("result", flag);
		return json;
	}//insertNoticePost
	
	public JSONObject addFile(MultipartFile multipartFile) {
		JSONObject json = new JSONObject();
		boolean flag = false;
		File file = null;
		String uploadPath = "C:/dev/workspace/zz_prj3/WebContent/common/images/";
		
		try {
			if(!multipartFile.isEmpty()&&multipartFile.getName()!=null) {
				file = new File(uploadPath, multipartFile.getOriginalFilename());
				
				if(!file.exists()) {
					multipartFile.transferTo(file);
				}//end if
			}//end if
		
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		if(multipartFile.getOriginalFilename()!=null) {
			flag = true;
		}//end if
		json.put("result", flag);
		
		return json;
	}//addFile
	
	public JSONObject deletePostNotice(int n_num) {
		JSONObject json = new JSONObject();
		BoardDAO bDAO = BoardDAO.getInstance();
		boolean flag = bDAO.deletePostNotice(n_num)==1;
		json.put("result", flag);
		return json;
	}//deletePostNotice
	
	public JSONObject updatePostNotice(NoticeModifyVO nmVO) {
		boolean flag=false;
		JSONObject json = new JSONObject();
		
		BoardDAO bDAO = BoardDAO.getInstance();
		flag = bDAO.updatePostNotice(nmVO)==1;
		
		json.put("result", flag);
		
		return json;
	}//deletePostNotice
	
	
	
	
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
