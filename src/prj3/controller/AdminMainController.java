package prj3.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import prj3.domain.MainNoticeViewDomain;
import prj3.domain.MainQnaViewDomain;
import prj3.domain.MainReservationViewDomain;
import prj3.service.AdminMainService;

@Controller
public class AdminMainController {

	@RequestMapping(value = "admin_main.do", method = GET)
	public String adminMain(Model model) {
	
		List<MainReservationViewDomain> reservationList = null;
		List<MainNoticeViewDomain> noticeList = null;
		List<MainQnaViewDomain> qnaList = null;
		
		AdminMainService ams = new AdminMainService();
		
		reservationList = ams.searchMainReservationList();
		noticeList = ams.searchMainNoticeList();
		qnaList = ams.searchMainQnaList();
		
		model.addAttribute("reservationView", reservationList);
		model.addAttribute("noticeView", noticeList);
		model.addAttribute("qnaView", qnaList);
		
		return "admin_main";
	}//adminMain
	
}//class
