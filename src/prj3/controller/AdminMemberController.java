package prj3.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import prj3.domain.MemberDetailDomain;
import prj3.domain.MemberReservationDomain;
import prj3.domain.MemberViewDomain;
import prj3.service.AdminMemberService;
import prj3.vo.IndexListVO;
import prj3.vo.SearchVO;


@Controller
public class AdminMemberController {
	
	@RequestMapping(value = "admin_member.do", method = GET)
	public String adminmember(SearchVO sVO, @RequestParam(required = false, defaultValue = "1")String current_page, Model model) {
		List<MemberViewDomain> memberList = null;
		AdminMemberService ams = new AdminMemberService();
		
		//indexList에서 제공하는 url인 current_page가 조회에 사용되는 sVO의 이름과 다르므로 current_page를 파라메터로 받고, sVO에 설정하여 조회
		sVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = ams.totalCount(sVO);
		int pageScale = ams.pageScale();
		int totalPage = ams.totalPage(pageScale, totalCount);
		int startNum = ams.startNum(pageScale, sVO.getCurrentPage());
		int endNum = ams.endNum(pageScale, startNum);
		
		//계산된 값으로 조회에 사용될 수 있게 VO에 설정
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		IndexListVO ilVO = new IndexListVO(sVO.getCurrentPage(), totalPage, "admin_member.do");
		String indexList = ams.indexList(ilVO);
//		System.out.println(ilVO);
		memberList = ams.searchMemberList(sVO);
		
		
		model.addAttribute("memberList", memberList);
		
		model.addAttribute("indexList", indexList);
		
		return "admin_member";
		
	}//adminMember
	
	@RequestMapping(value = "move_member_detail_form.do", method = GET)
	public String memberDetailProcess(String user_id, Model model) {
		
		MemberDetailDomain mdd = null;
		List<MemberReservationDomain> mrd = null;
		
		AdminMemberService ams = new AdminMemberService();
		
		mdd = ams.searchMemberDetail(user_id);
		mrd = ams.searchMemberReservation(user_id);
		model.addAttribute("memberDetail", mdd);
		model.addAttribute("memberReservation", mrd); 
		
		return "admin_member_detail";
	}//memberDetailProcess
	
}//class













