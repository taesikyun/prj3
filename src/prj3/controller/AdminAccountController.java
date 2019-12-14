package prj3.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import prj3.domain.AccountViewDomain;
import prj3.service.AdminAccountService;
import prj3.vo.AccountVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;


@Controller
public class AdminAccountController {
	
	@RequestMapping(value = "admin_account.do", method = GET)
	public String adminAccount(Model model) {
		List<AccountViewDomain> list = null;
		
		AdminAccountService aas = new AdminAccountService();
		
		list = aas.searchAccountList();
		
		model.addAttribute("account_list", list);
		
		return "admin_account";
		
	}//adminAccount
	
	@RequestMapping(value = "move_add_account_form.do", method = GET)
	public String addAccountForm() {
		
		return "admin_account_add";
		
	}//adminAccount
	
	@RequestMapping(value = "add_account_process.do", method = POST)
	@ResponseBody
	public String AccountWriteProcess(AccountVO aVO) {
		
		JSONObject json = null;
		AdminAccountService aas = new AdminAccountService();
		
		json = aas.addAccount(aVO);
		
		return json.toJSONString();
		
	}//adminAccount
	
	@RequestMapping(value = "remove_account_process.do", method = POST)
	@ResponseBody
	public String AccountRemoveProcess(String bank) {
		
		JSONObject json = null;
		AdminAccountService aas = new AdminAccountService();
		
		json = aas.removeAccount(bank);
		
		return json.toJSONString();
		
	}//adminAccount
	
}//class













