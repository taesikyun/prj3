package prj3.service;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;

import prj3.dao.AdminAccountDAO;
import prj3.domain.AccountViewDomain;
import prj3.vo.AccountVO;

public class AdminAccountService {

	public List<AccountViewDomain> searchAccountList(){
		List<AccountViewDomain> list = null;
		
		AdminAccountDAO aaDAO = AdminAccountDAO.getInstance();
		
		try {
			list = aaDAO.selectAccountList();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//searchAccountList
	
	public JSONObject addAccount(AccountVO aVO) {
		JSONObject json = new JSONObject();
		boolean flag = false;
		
		AdminAccountDAO aaDAO = AdminAccountDAO.getInstance();
		
		try {
			flag = aaDAO.insertAccount(aVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		json.put("result", flag);
		
		return json;
		
	}//addAccount
	
	public JSONObject removeAccount(String bank) {
		JSONObject json = new JSONObject();
		boolean flag = false;
		
		AdminAccountDAO aaDAO = AdminAccountDAO.getInstance();
		
		try {
			flag = aaDAO.deleteAccount(bank);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		json.put("result", flag);
		
		return json;
		
	}//removeAccount
	
}//class









