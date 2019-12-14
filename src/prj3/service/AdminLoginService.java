package prj3.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import kr.co.sist.util.cipher.DataEncrypt;
import prj3.dao.AdminLoginDAO;
import prj3.domain.LoginDomain;
import prj3.vo.LoginVO;

public class AdminLoginService {

	
	public LoginDomain searchAdmin() {
		LoginDomain ld = null;
		
		AdminLoginDAO alDAO = AdminLoginDAO.getInstance();
		
		try {
			ld = alDAO.selectAdmin();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
//		System.out.println("service");
		
		return ld;
		
	}//matchAdmin
	
	public JSONObject matchAdmin(LoginVO lv) {
		JSONObject json = new JSONObject();
		boolean flag = false;
		
		String cipherTemp = "";
		cipherTemp = cipherText(lv);
				
		LoginDomain ld = searchAdmin();
//		System.out.println(ld.getAdmin_id());
//		System.out.println(ld.getPass());
		if(lv.getAdmin_id().equals(ld.getAdmin_id()) && cipherTemp.equals(ld.getPass())) {
			flag = true;
		
		}//end if
		
		json.put("result", flag);
		json.put("admin_id", lv.getAdmin_id());
		json.put("pass", lv.getPass());
		
		return json;
		
	}//matchAdmin
	
	public String cipherText(LoginVO lv) {
		String cipherText = "";
		
		try {
			cipherText = DataEncrypt.messageDigest("MD5", lv.getPass());
//			System.out.println(cipherText);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		return cipherText;
	}//cipherText
	
}//class









