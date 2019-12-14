package prj3.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prj3.domain.AccountViewDomain;
import prj3.vo.AccountVO;

public class AdminAccountDAO {

	private static AdminAccountDAO aaDAO;
	private static SqlSessionFactory ssf;
	
	public AdminAccountDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static AdminAccountDAO getInstance() {
		if(aaDAO == null) {
			aaDAO = new AdminAccountDAO();
			
		}//end if
		
		return aaDAO;
		
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
	
	public List<AccountViewDomain> selectAccountList() throws SQLException{
		List<AccountViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectAllAccount");
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectAccountList
	
	public boolean insertAccount(AccountVO aVO)throws SQLException{
		
		boolean flag = false;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.insert("insertAccount", aVO) == 1;
			
			ss.commit();
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//insertAccount
	
	public boolean deleteAccount(String bank)throws SQLException{
		
		boolean flag = false;
//		System.out.println(bank);
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.delete("deleteAccount", bank) == 1;
//			System.out.println(flag);
			ss.commit();
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//insertAccount
	
}//class
















