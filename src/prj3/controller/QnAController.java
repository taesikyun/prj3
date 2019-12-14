package prj3.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import prj3.domain.QnABoardDetailDomain;
import prj3.domain.QnAListDomain;
import prj3.service.QnAService;
import prj3.vo.IndexListVO;
import prj3.vo.QnAAddRpVO;
import prj3.vo.QnAModifyVO;
import prj3.vo.QnAWriteVO;
import prj3.vo.RpModifyVO;
import prj3.vo.SearchVO;


@Controller
public class QnAController {

	@RequestMapping(value="qna_list.do",method =GET)
	public String searchQnABoard(HttpServletRequest request,Model model) {

		String currentPage = request.getParameter("page");
		String field = request.getParameter("field");
		String keyword = request.getParameter("keyword");
		
		int tempPage=1;
		if( currentPage != null ) {//��ȿ�� ����
			tempPage=Integer.parseInt(currentPage);
		}//end if
		
		QnAService qs = new QnAService();
		int pageScale=qs.pageScale(); //�� ȭ�鿡 ������ �� �� 
		int startNum=qs.startNum(pageScale, tempPage);// ���۹�ȣ
		int endNum=qs.endNum(pageScale, startNum); //�� ��ȣ
		SearchVO sVO = new SearchVO(field,keyword,tempPage,startNum,endNum);
		int totalCount=qs.selectTotalCount(sVO); // ��ü �� ��ȸ
		int totalPage=qs.totalPage(pageScale, totalCount); //�� ������ ��
		String indexList="";
		
		//�ε��� ����Ʈ ����
		if(field==null&&keyword==null) {
			 indexList=qs.indexList(new IndexListVO(tempPage, totalPage, "qna_list.do?"));
			
		}else {
			 indexList=qs.indexList(new IndexListVO(tempPage, totalPage, "qna_list.do?field="+field+"&keyword="+keyword+"&"));
		}

		 List<QnAListDomain> list = qs.searchAllQnA(sVO);
		 model.addAttribute("list", list);
		 model.addAttribute("totalPage", totalPage);
		 //view���� ���� ����� �� �ֵ��� scope��ü�� ���� ����
		 model.addAttribute("indexList", indexList);
		 return "board/qna_list";
	}//searchBoard
	
	@RequestMapping(value="qna_post.do",method= {GET,POST})
	public String searchQnADetail(int q_num,Model model) {

		QnAService qs = new QnAService();
		QnABoardDetailDomain qbdd = qs.searchDetailQnA(q_num);
		model.addAttribute("qbdd",qbdd);
		return "board/qna_post";
	}//searchQnADetail
	
	@RequestMapping(value="write_form.do",method=GET)
	public String writeForm() {
	
		return "board/write_form";
	}//searchQnADetail
	/**
	 * @param session
	 * @param qwVO
	 * @return
	 */
	@RequestMapping(value="write_post.do",method=POST)
	@ResponseBody
	public String writeProcess(HttpSession session,QnAWriteVO qwVO) {
		JSONObject json = null;
		QnAService qs = new QnAService();
		 json = qs.insertQnAPost(qwVO);
		return json.toJSONString();
	}
	@RequestMapping(value="delete_post.do",method=GET)
	@ResponseBody
	public String deleteProcess(int q_num) {
		JSONObject json=null;
		QnAService qs = new QnAService();
		json = qs.deletePostQnA(q_num);
		return json.toJSONString();
	}//deleteProcess
	@RequestMapping(value="modify_form.do",method=POST)
	public String modifyForm() {
		
		return "board/modify_form";
	}//modifyForm
	@RequestMapping(value="modify_process.do",method=POST)
	@ResponseBody
	public String modifyProcess(QnAModifyVO qVo) {
		JSONObject json = null;
		QnAService qs = new QnAService();
		json =qs.updatePostQnA(qVo);
		return json.toJSONString();
	}//modifyProcess
	
	@RequestMapping(value="addRp.do" , method=POST)
	@ResponseBody
	public String addRp_process(QnAAddRpVO qarVO) {
		JSONObject json = null;
		QnAService qs = new QnAService();
		json = qs.updateQnARp(qarVO);
		
		return json.toJSONString();
	}//addRp_process
	@RequestMapping(value="rp_modify.do", method=POST)
	@ResponseBody
	public String rpModifyProcess(RpModifyVO rmVO) {
		JSONObject json = null;
		QnAService qs = new QnAService();
		json = qs.replyModify(rmVO);
		
		return  json.toJSONString();
	}//rpModifyProcess
}//class
