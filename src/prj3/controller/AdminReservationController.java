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

import prj3.domain.PastReservationDetailDomain;
import prj3.domain.PastReservationViewDomain;
import prj3.domain.RealtimeListDomain;
import prj3.domain.ReservationDetailDomain;
import prj3.domain.ReservationViewDomain;
import prj3.service.AdminReservationService;
import prj3.vo.IndexListVO;
import prj3.vo.PastReservationDetailVO;
import prj3.vo.SearchVO;


@Controller
public class AdminReservationController {
	
	@RequestMapping(value = "admin_reservation.do", method = GET)
	public String adminReservation(SearchVO sVO, @RequestParam(required = false, defaultValue = "1")String current_page, Model model) {
		List<ReservationViewDomain> list = null;
		AdminReservationService ars = new AdminReservationService();
		
		//indexList에서 제공하는 url인 current_page가 조회에 사용되는 sVO의 이름과 다르므로 current_page를 파라메터로 받고, sVO에 설정하여 조회
		sVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = ars.totalCount(sVO);
		int pageScale = ars.pageScale();
		int totalPage = ars.totalPage(pageScale, totalCount);
		int startNum = ars.startNum(pageScale, sVO.getCurrentPage());
		int endNum = ars.endNum(pageScale, startNum);
		
		//계산된 값으로 조회에 사용될 수 있게 VO에 설정
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		IndexListVO ilVO = new IndexListVO(sVO.getCurrentPage(), totalPage, "admin_reservation.do");
		String indexList = ars.indexList(ilVO);
//		System.out.println(ilVO);
		list = ars.searchReservationList(sVO);
		
		model.addAttribute("reservation_list", list);
		model.addAttribute("indexList", indexList);
		return "admin_reservation";
		
	}//adminReservation
	
	@RequestMapping(value = "move_reservation_detail_form.do", method = GET)
	public String memberReservationProcess(String reservation_num, Model model) {
		
		ReservationDetailDomain rdd = null;
		List<RealtimeListDomain> realtimeList = null;
//		System.out.println(reservation_num);
		AdminReservationService ams = new AdminReservationService();
		rdd = ams.searchReservationDetail(reservation_num);
		realtimeList = ams.searchRealtime(reservation_num);
//		System.out.println(rdd);
		
		model.addAttribute("reservationDetail", rdd);
		model.addAttribute("realtimeList", realtimeList);
		
		return "admin_reservation_detail";
	}//memberReservationProcess
	
	@RequestMapping(value = "pay_status_modify_process.do", method = POST)
	@ResponseBody
	public String payStatusModify(String reservation_num) {
		JSONObject json = null;
//		System.out.println("reservation_num : "+reservation_num);
		AdminReservationService ars = new AdminReservationService();
		json = ars.modifypayStatus(reservation_num);
//		System.out.println(json.get("result"));
		return json.toJSONString();
	}//payStatusModify
	
	@RequestMapping(value = "admin_reservation_past.do", method = GET)
	public String pastReservation(SearchVO sVO, @RequestParam(required = false, defaultValue = "1")String current_page, Model model) {
		
		List<PastReservationViewDomain> list = null;
		AdminReservationService ars = new AdminReservationService();
		
		//indexList에서 제공하는 url인 current_page가 조회에 사용되는 sVO의 이름과 다르므로 current_page를 파라메터로 받고, sVO에 설정하여 조회
		sVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = ars.totalPastCount(sVO);
		int pageScale = ars.pageScale();
		int totalPage = ars.totalPage(pageScale, totalCount);
		int startNum = ars.startNum(pageScale, sVO.getCurrentPage());
		int endNum = ars.endNum(pageScale, startNum);
		
		//계산된 값으로 조회에 사용될 수 있게 VO에 설정
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		IndexListVO ilVO = new IndexListVO(sVO.getCurrentPage(), totalPage, "admin_reservation_past.do");
		String indexList = ars.indexList(ilVO);
//		System.out.println(ilVO);
		list = ars.searchPastReservationList(sVO);
		
		model.addAttribute("pastReservation", list);
		model.addAttribute("indexList", indexList);
		
		return "admin_reservation_past";
		
	}//pastReservation
	
	@RequestMapping(value = "move_past_reservation_detail_form.do", method = GET)
	public String pastReservationDetailProcess(PastReservationDetailVO prdVO, Model model) {
		
		PastReservationDetailDomain prdd = null;
		AdminReservationService ams = new AdminReservationService();
		
		prdd = ams.searchPastReservationDetail(prdVO);
		
		model.addAttribute("pastReservationDetail", prdd);
		
		return "admin_past_reservation_detail";
	}//memberReservationProcess
	
}//class













