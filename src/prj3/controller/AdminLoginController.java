package prj3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import prj3.service.AdminLoginService;
import prj3.vo.LoginVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.json.simple.JSONObject;



@Controller
@SessionAttributes("admin_id")
public class AdminLoginController {

	@RequestMapping(value="/admin_login_form.do", method= GET)
	public String adminLogin() {
//		System.out.println("11");
		
		return "admin_login";
	}//adminLogin
	
	@RequestMapping(value="admin_login_process.do", method=POST)
	@ResponseBody
	public String adminLoginProcess(LoginVO lv, Model model) {
		
		AdminLoginService als = new AdminLoginService();
//		LoginDomain ld = null;
		JSONObject json = null;
		json = als.matchAdmin(lv);
		
//		System.out.println(json);
		boolean flag = (boolean) json.get("result");
		if(flag) {
			model.addAttribute("admin_id", lv.getAdmin_id());
		}//end if
		
//		System.out.println("22");
		return json.toJSONString();
	}//adminLoginProcess
	
//	@RequestMapping(value="move_main_form.do", method=GET)
//	public String adminSuccess(Model model) {
//		LoginDomain ld = null;
//		AdminLoginService als = new AdminLoginService();
//		
//		ld = als.searchAdmin();
//		
//		model.addAttribute("admin_id", ld.getAdmin_id());
////		System.out.println("33");
//		return "admin_main";
//	}//adminSuccess
	
	@RequestMapping(value="admin_logout.do", method=GET)
	public String adminLogout(SessionStatus ss) {
		
		ss.setComplete();
		
		return "admin_login";
	}//adminSuccess
	
}//class
