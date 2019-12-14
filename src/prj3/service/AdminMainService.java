package prj3.service;

import java.sql.SQLException;
import java.util.List;

import prj3.dao.AdminMainDAO;
import prj3.domain.MainNoticeViewDomain;
import prj3.domain.MainQnaViewDomain;
import prj3.domain.MainReservationViewDomain;

public class AdminMainService {

	public List<MainReservationViewDomain> searchMainReservationList(){
		List<MainReservationViewDomain> list = null;
		
		AdminMainDAO amDAO = AdminMainDAO.getInstance();
		
		try {
			list = amDAO.selectMainReservationList();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchMemberList
	
	public List<MainNoticeViewDomain> searchMainNoticeList(){
		List<MainNoticeViewDomain> list = null;
		MainNoticeViewDomain mnvd = null;
		String tempSubject = "";
		
		AdminMainDAO amDAO = AdminMainDAO.getInstance();
		
		try {
			list = amDAO.selectMainNoticeList();
			mnvd = new MainNoticeViewDomain();
			
			for (int i = 0; i < list.size(); i++) {
				mnvd = list.get(i); 
				tempSubject = mnvd.getN_subject();
				
				if (tempSubject.length() > 20) {
					tempSubject = tempSubject.substring(0, 20).concat("...");
					System.out.println(tempSubject);
				}//end if
				
				mnvd.setN_subject(tempSubject);
				list.set(i, mnvd);
				
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchMainNoticeList
	
	public List<MainQnaViewDomain> searchMainQnaList(){
		List<MainQnaViewDomain> list = null;
		MainQnaViewDomain mqvd = null;
		String tempSubject = "";
		AdminMainDAO amDAO = AdminMainDAO.getInstance();
		
		try {
			list = amDAO.selectMainQnaList();
			mqvd = new MainQnaViewDomain();
			
			for (int i = 0; i < list.size(); i++) {
				mqvd = list.get(i);
				tempSubject = mqvd.getQ_subject();
				
				if (tempSubject.length() > 20) {
					tempSubject = tempSubject.substring(0, 20).concat("...");
//					System.out.println(tempSubject);
				}//end if
				
				mqvd.setQ_subject(tempSubject);;
				list.set(i, mqvd);
				
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchMainQnaList
	
}//class









