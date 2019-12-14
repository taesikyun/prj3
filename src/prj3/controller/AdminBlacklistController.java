package prj3.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import prj3.domain.BlacklistViewDomain;
import prj3.domain.WarningTypeDomain;
import prj3.service.AdminBlacklistService;
import prj3.vo.IndexListVO;
import prj3.vo.InsertBLacklistVO;
import prj3.vo.SearchVO;


@Controller
public class AdminBlacklistController {
	
	@RequestMapping(value = "admin_blacklist.do", method = GET)
	public String adminBlacklist(SearchVO sVO, @RequestParam(required = false, defaultValue = "1")String current_page, Model model) {
		List<BlacklistViewDomain> blacklist = null;
		AdminBlacklistService abs = new AdminBlacklistService();
		//indexList���� �����ϴ� url�� current_page�� ��ȸ�� ���Ǵ� sVO�� �̸��� �ٸ��Ƿ� current_page�� �Ķ���ͷ� �ް�, sVO�� �����Ͽ� ��ȸ
		sVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = abs.totalCount(sVO);
		int pageScale = abs.pageScale();
		int totalPage = abs.totalPage(pageScale, totalCount);
		int startNum = abs.startNum(pageScale, sVO.getCurrentPage());
		int endNum = abs.endNum(pageScale, startNum);
		
		//���� ������ ��ȸ�� ���� �� �ְ� VO�� ����
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
//		System.out.println("------------------------------------------"+sVO);
		
		IndexListVO ilVO = new IndexListVO(sVO.getCurrentPage(), totalPage, "admin_blacklist.do");
		
		String indexList = abs.indexList(ilVO);
		
		blacklist = abs.searchBlacklistList(sVO);
		
		model.addAttribute("blacklist_list", blacklist);
		model.addAttribute("indexList", indexList);
		
		return "admin_blacklist";
		
	}//adminBlacklist
	
	@RequestMapping(value = "move_blacklist_add_form.do", method = GET)
	public String addBlacklistForm(String user_id, Model model) {
		
		AdminBlacklistService abs = new AdminBlacklistService();
		List<WarningTypeDomain> list = null;
		
		list = abs.searchWarningType();
//		System.out.println(list.get(1));
		model.addAttribute("warningList", list);
		
		return "admin_blacklist_add";
		
	}//addBlacklistForm
	
	@RequestMapping(value = "add_blacklist_process.do", method = POST)
	@ResponseBody
	public String addBlacklistProcess(InsertBLacklistVO ibVO) {
		System.out.println(ibVO);
		JSONObject json = null;
		AdminBlacklistService abs = new AdminBlacklistService();
		
		json = abs.addBlacklist(ibVO);
		
		return json.toJSONString();
	}//addBlacklistProcess
	
}//class













