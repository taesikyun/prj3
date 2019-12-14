package prj3.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import prj3.dao.AdminRoomDAO;
import prj3.domain.ConceptNameDomain;
import prj3.domain.RoomConceptDetailDomain;
import prj3.domain.RoomConceptViewDomain;
import prj3.domain.RoomDetailDomain;
import prj3.domain.RoomViewDomain;
import prj3.vo.RoomConceptVO;
import prj3.vo.RoomRoomVO;

public class AdminRoomService {

	public List<RoomConceptViewDomain> searchConcept(){
		List<RoomConceptViewDomain> list = null;
		
		AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
		
		list = arDAO.selectConcept();
		
		return list;
	}//searchConcept
	
	public List<RoomViewDomain> searchRoom(){
		List<RoomViewDomain> list = null;
		
		AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
		
		list = arDAO.selectRoom();
		
		return list;
	}//searchRoom
	
	
	
	public JSONObject addConcept(MultipartHttpServletRequest request) {
		
		boolean flag = false;
		JSONObject json = new JSONObject();
		RoomConceptVO rcVO = new RoomConceptVO();
		File file = null;
		File file_temp = null;
		
		MultipartFile multipartFile = request.getFile("image");
		String uploadPath = "C:/dev/workspace/zz_prj3/WebContent/uploadImage";
		
		try {
			if(!multipartFile.isEmpty()) {
				file = new File(uploadPath, multipartFile.getOriginalFilename());

				if(!file.exists()) {
					multipartFile.transferTo(file);
					rcVO.setImage(multipartFile.getOriginalFilename());
				}else {
					String temp = multipartFile.getOriginalFilename();
					int pos = temp.lastIndexOf(".");
					String ext = temp.substring(pos);
					
					String main = temp.substring(0, pos);
					
					temp = main+"_temp"+ext;
					file_temp = new File(uploadPath, temp);
					multipartFile.transferTo(file_temp);
					rcVO.setImage(temp);
				}//end else
				rcVO.setConcept_name(request.getParameter("concept_name"));
				rcVO.setConcept_name2(request.getParameter("concept_name2"));
				rcVO.setBrief_info(request.getParameter("brief_info"));
				rcVO.setInfo(request.getParameter("info"));
				
				AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
				flag = arDAO.insertConcept(rcVO);
				
			}//end if
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		json.put("result", flag);
		
		return json;
		
	}//addConcept
	
	public List<ConceptNameDomain> searchConceptName() {
		List<ConceptNameDomain> cndList = null;
		
		AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
		
		try {
			cndList = arDAO.selectConceptName();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		System.out.println(cndList);
		
		return cndList;
	}//searchConceptName
	
public JSONObject addRoom(MultipartHttpServletRequest request) {
		
		boolean flag = false;
		JSONObject json = new JSONObject();
		RoomRoomVO rriVO = new RoomRoomVO();
		File file = null;
		File file_temp = null;
		
		MultipartFile[] multipartFile = new MultipartFile[3] ;
		
		multipartFile[0] = request.getFile("image1");
		multipartFile[1] = request.getFile("image2");
		multipartFile[2] = request.getFile("image3");
		String uploadPath = "C:/dev/workspace/zz_prj3/WebContent/uploadImage";
		
		try {
			if(multipartFile.length != 0) {
				
				rriVO.setRoom_name(request.getParameter("room_name"));
				rriVO.setRoom_name2(request.getParameter("room_name2"));
				rriVO.setPerson_num(Integer.parseInt(request.getParameter("person_num")));
				rriVO.setInfo(request.getParameter("info"));
				rriVO.setBrief_info(request.getParameter("brief_info"));
				rriVO.setCharge(Integer.parseInt(request.getParameter("charge")));
				
				rriVO.setConcept_name(request.getParameter("concept_name"));
				
				for (int i = 0; i < multipartFile.length; i++) {
					file = new File(uploadPath, multipartFile[i].getOriginalFilename());

					if(!file.exists()) {
						multipartFile[i].transferTo(file);
						rriVO.setImage1(multipartFile[0].getOriginalFilename());
						rriVO.setImage2(multipartFile[1].getOriginalFilename());
						rriVO.setImage3(multipartFile[2].getOriginalFilename());
					}else {
						String temp = multipartFile[i].getOriginalFilename();
						int pos = temp.lastIndexOf(".");
						String ext = temp.substring(pos);
						
						String main = temp.substring(0, pos);
						
						temp = main+"_temp"+ext;
						file_temp = new File(uploadPath, temp);
						multipartFile[i].transferTo(file_temp);
						if(i==0) {
							rriVO.setImage1(temp);
						}else if(i==1) {
							rriVO.setImage2(temp);
						}else if(i==2) {
							rriVO.setImage3(temp);
						}
					}//end else
				}//end for
				
				AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
				flag = arDAO.insertRoom(rriVO);
				
			}//end if
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		json.put("result", flag);
		
		return json;
		
	}//addConcept
	
	public RoomConceptDetailDomain searchConceptDetail(String concept_name) {
		RoomConceptDetailDomain rcdd = null;
		
		AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
		
		try {
			rcdd = arDAO.selectConceptDetail(concept_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return rcdd;
	}//searchConceptDetail

	public RoomDetailDomain searchRoomDetail(String room_name) {
		RoomDetailDomain rdd = null;
		
		AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
		
		try {
			rdd = arDAO.selectRoomDetail(room_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return rdd;
	}//searchConceptDetail
	
	public JSONObject modifyConcept(MultipartHttpServletRequest request ) {
//		System.out.println("33");
		JSONObject json = new JSONObject();
		boolean flag = false;
		RoomConceptVO rcuVO = new RoomConceptVO();
		File file = null;
		String fileName="";
		int pos = 0;
		String ext = "";
		String main = "";
		
		MultipartFile multipartFile = request.getFile("image");
		String uploadPath = "C:/dev/workspace/zz_prj3/WebContent/uploadImage";
		
		try {
			if(!multipartFile.isEmpty()) {
				fileName = multipartFile.getOriginalFilename();
				file = new File(uploadPath, fileName);
			
				while(file.exists()) {//true일때 반복
					
	//				fileName = multipartFile.getOriginalFilename();
	//				System.out.println(fileName);
					pos = fileName.lastIndexOf(".");
					ext = fileName.substring(pos);
					main = fileName.substring(0, pos);
					
					fileName = main+"_temp"+ext;
	//				System.out.println(fileName);
					file = new File(uploadPath, fileName);
	//				System.out.println("1");
				}//end while
			
				multipartFile.transferTo(file);
				rcuVO.setImage(fileName);
			}//end if
			
			rcuVO.setConcept_name(request.getParameter("concept_name"));
			rcuVO.setConcept_name2(request.getParameter("concept_name2"));
			rcuVO.setBrief_info(request.getParameter("brief_info"));
			rcuVO.setInfo(request.getParameter("info"));
			
//			System.out.println(rcuVO);
			
			AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
			flag = arDAO.updateConcept(rcuVO);
				
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		json.put("result", flag);
//		System.out.println("44");
//		System.out.println(flag);
		return json;
	}//modifyConcept
	
	public JSONObject removeConcept(String concept_name) {
		JSONObject json = new JSONObject();
		boolean flag = false;
		
		AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
		try {
			flag = arDAO.deleteConcept(concept_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		json.put("result", flag);
		
		return json;
	}//removeConcept
	
	
	public JSONObject modifyRoom(MultipartHttpServletRequest request ) {
		System.out.println("33");
		JSONObject json = new JSONObject();
		boolean flag = false;
		RoomRoomVO rrVO = new RoomRoomVO();
		File file = null;
		String fileName="", ext = "", main = "";
		int pos = 0;
		MultipartFile[] multipartFile = null;
			
		multipartFile = new MultipartFile[3] ;
		multipartFile[0] = request.getFile("image1");
		multipartFile[1] = request.getFile("image2");
		multipartFile[2] = request.getFile("image3");
		String uploadPath = "C:/dev/workspace/zz_prj3/WebContent/uploadImage";
		
		try {
			if(multipartFile.length != 0) {
				
				for (int i = 0; i < multipartFile.length; i++) {
					fileName = multipartFile[i].getOriginalFilename();
					System.out.println(fileName);
					if(!"".equals(fileName) && fileName != null) {
						file = new File(uploadPath, fileName);
	
						while(file.exists()) {//true일때 반복
							
	//						fileName = multipartFile.getOriginalFilename();
	//						System.out.println(fileName);
							pos = fileName.lastIndexOf(".");
							ext = fileName.substring(pos); 
							main = fileName.substring(0, pos);
							
							fileName = main+"_temp"+ext;
	//						System.out.println(fileName);
							file = new File(uploadPath, fileName);
	//						System.out.println("1");
						}//end while
						
						multipartFile[i].transferTo(file);
						if(i==0) {
							rrVO.setImage1(fileName);
						}else if(i==1) {
							rrVO.setImage2(fileName);
						}else if(i==2) {
							rrVO.setImage3(fileName);
						}//end if
					}//end if
				}//end for
				
			}//end if	
			
			rrVO.setRoom_name(request.getParameter("room_name"));
			rrVO.setRoom_name2(request.getParameter("room_name2"));
			rrVO.setPerson_num(Integer.parseInt(request.getParameter("person_num")));
			rrVO.setInfo(request.getParameter("info"));
			rrVO.setBrief_info(request.getParameter("brief_info"));
			rrVO.setCharge(Integer.parseInt(request.getParameter("charge")));
			
			rrVO.setConcept_name(request.getParameter("concept_name"));
			
//			System.out.println(rcuVO);
			
			AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
			flag = arDAO.updateRoom(rrVO);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		json.put("result", flag);
//		System.out.println("44");
//		System.out.println(flag);
		return json;
		
	}//modifyRoom
	
	public JSONObject removeRoom(String room_name) {
		JSONObject json = new JSONObject();
		boolean flag = false;
		
		AdminRoomDAO arDAO = AdminRoomDAO.getInstance();
		try {
			flag = arDAO.deleteRoom(room_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		json.put("result", flag);
		
		return json;
	}//removeRoom
	
}//class









