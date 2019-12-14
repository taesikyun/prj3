package prj3.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import prj3.domain.ConceptNameDomain;
import prj3.domain.RoomConceptDetailDomain;
import prj3.domain.RoomConceptViewDomain;
import prj3.domain.RoomDetailDomain;
import prj3.domain.RoomViewDomain;
import prj3.service.AdminRoomService;

@Controller
public class AdminRoomController {

	@RequestMapping(value = "admin_room.do", method = GET)
	public String adminRoom(Model model) {
		
		AdminRoomService ars = new AdminRoomService();
		List<RoomViewDomain> listRoom = null;
		List<RoomConceptViewDomain> listConcept = null;
		
		listRoom = ars.searchRoom();
		listConcept = ars.searchConcept();
		
		model.addAttribute("listRoom", listRoom);
		model.addAttribute("listConcept", listConcept);
		
		return "admin_room";
		
	}//adminRoom
	
	@RequestMapping(value = "move_add_room_form.do", method = GET)//룸 추가 폼으로 이동
	public String roomWriteForm(Model model) {
		AdminRoomService ars = new AdminRoomService();
		List<ConceptNameDomain> cndList = ars.searchConceptName();//카테고리 추가
		
		model.addAttribute("conceptName", cndList);
		return "admin_room_add";
	}//
	
	@RequestMapping(value = "move_add_concept_form.do", method = GET)//컨셉 추가 폼으로 이동
	public String conceptWriteForm() {
		
		return "admin_room_concept_add";
	}//
	
	
	@RequestMapping(value = "add_room_process.do", method = POST)//룸 추가 폼에서 입력한 룸 내용을 db에 넣기
	@ResponseBody
	public String roomWriteProcess(MultipartHttpServletRequest request)throws IOException {
		AdminRoomService ars = new AdminRoomService();
		JSONObject json = null;
		
		json = ars.addRoom(request);
		
//		MultipartFile multipartFile = request.getFile("image");
//		String uploadPath = "C:/dev/workspace/zz_prj3/WebContent/images";
//		
//		if(!multipartFile.isEmpty()) {
//			File file = new File(uploadPath, multipartFile.getOriginalFilename());
//			multipartFile.transferTo(file);
//			
//			model.addAttribute("concept_name", request.getParameter("concept_name"));
//			model.addAttribute("concept_name2", request.getParameter("concept_name2"));
//			model.addAttribute("brief_info", request.getParameter("brief_info"));
//			model.addAttribute("info", request.getParameter("info"));
//			model.addAttribute("fileName", multipartFile.getOriginalFilename());			
//			
//			return "test";
//			
//		}//end if           "admin_room_add"
		
		return json.toJSONString();
		
	}//
	
	@RequestMapping(value = "add_concept_process.do", method = POST)//컨셉 추가 폼에서 입력한 컨셉 내용을 db에 넣기
	@ResponseBody
	public String conceptWriteProcess(MultipartHttpServletRequest request)throws IOException {
		
		AdminRoomService ars = new AdminRoomService();
		JSONObject json = null;
		
		json = ars.addConcept(request);
		
//		MultipartFile multipartFile = request.getFile("image");
//		String uploadPath = "C:/dev/workspace/zz_prj3/WebContent/images";
//		
//		if(!multipartFile.isEmpty()) {
//			File file = new File(uploadPath, multipartFile.getOriginalFilename());
//			multipartFile.transferTo(file);
//			
//			model.addAttribute("concept_name", request.getParameter("concept_name"));
//			model.addAttribute("concept_name2", request.getParameter("concept_name2"));
//			model.addAttribute("brief_info", request.getParameter("brief_info"));
//			model.addAttribute("info", request.getParameter("info"));
//			model.addAttribute("fileName", multipartFile.getOriginalFilename());			
//			
//			return "test";
//			
//		}//end if
		
		return json.toJSONString();
	}//
	
	@RequestMapping(value = "open_detail_concept.do", method = GET)//상세설명 클릭 시 컨셉 상세정보 보여주기
	public String searchConceptDetail(String concept_name, Model model) {
		
		AdminRoomService ars = new AdminRoomService();
		
		RoomConceptDetailDomain rcdd = ars.searchConceptDetail(concept_name);
		model.addAttribute("concept_detail", rcdd);
		
		return "admin_room_concept_detail";
	}//searchConceptDetail
	
	@RequestMapping(value = "open_detail_room.do", method = GET)//상세설명 클릭 시 룸 상세정보 보여주기
	public String searchRoomDetail(String room_name, Model model) {
		
		AdminRoomService ars = new AdminRoomService();

		List<ConceptNameDomain> cndList = ars.searchConceptName();//카테고리 추가
		RoomDetailDomain rdd = ars.searchRoomDetail(room_name);

		model.addAttribute("room_detail", rdd);
		model.addAttribute("conceptName", cndList);
		
		return "admin_room_detail";
	}//searchRoomDetail
	
	@RequestMapping(value = "update_concept_process.do", method = POST)
	@ResponseBody
	public String modifyConcept(MultipartHttpServletRequest request) {
		JSONObject json = null;
//		System.out.println("55");
		AdminRoomService ars = new AdminRoomService();
		json = ars.modifyConcept(request);
//		System.out.println("66");
		return json.toJSONString();
	}//modifyConcept
	
	@RequestMapping(value = "delete_concept_process.do", method = POST)
	@ResponseBody
	public String removeConcept(String concept_name) {
		JSONObject json = null;
//		System.out.println("55");
		AdminRoomService ars = new AdminRoomService();
		json = ars.removeConcept(concept_name);
//		System.out.println("66");
		return json.toJSONString();
	}//modifyConcept
	
	
	@RequestMapping(value = "update_room_process.do", method = POST)
	@ResponseBody
	public String modifyRoom(MultipartHttpServletRequest request) {
		JSONObject json = null;
//		System.out.println("55");
		AdminRoomService ars = new AdminRoomService();
		json = ars.modifyRoom(request);
//		System.out.println("66");
		return json.toJSONString();
	}//modifyConcept
	
	@RequestMapping(value = "delete_room_process.do", method = POST)
	@ResponseBody
	public String removeRoom(String room_name) {
		JSONObject json = null;
//		System.out.println("55");
		AdminRoomService ars = new AdminRoomService();
		json = ars.removeRoom(room_name);
//		System.out.println("66");
		return json.toJSONString();
	}//modifyConcept
	
	
	
}//class











