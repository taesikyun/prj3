package prj3.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prj3.domain.LoginDomain;

public class AdminLoginDAO {

	private static AdminLoginDAO alDAO;
	private static SqlSessionFactory ssf;
	
	public AdminLoginDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static AdminLoginDAO getInstance() {
		if(alDAO == null) {
			alDAO = new AdminLoginDAO();
			
		}//end if
		
		return alDAO;
		
	}//getInstance
	
	public SqlSessionFactory getSessionFactory() throws IOException {
		
		if(ssf == null) {
			//1. 설정용 xml을 스트림으로 연결
			Reader reader = null;
			
			try {
				reader = Resources.getResourceAsReader("prj3/dao/mybatis_config.xml");
				
				//2. SqlSessionFactoryBuilder 생성
				SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
				
				//3. SqlSessionFactory 얻기
				ssf = ssfb.build(reader);
				
			}finally {
				if(reader != null) {reader.close();}
				
			}//end finally
			
		}//end if
		
		return ssf;
		
	}//getSessionFactory
	
	public LoginDomain selectAdmin() throws SQLException{
		LoginDomain ld = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			ld = ss.selectOne("selectAdmin");
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
//		System.out.println(ld.getAdmin_id());
//		System.out.println(ld.getPass());
		
		return ld;
		
	}//selectAdmin
	
	
	
}//class
