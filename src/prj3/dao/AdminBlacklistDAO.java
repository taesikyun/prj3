package prj3.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prj3.domain.BlacklistViewDomain;
import prj3.domain.WarningTypeDomain;
import prj3.vo.InsertBLacklistVO;
import prj3.vo.SearchVO;

public class AdminBlacklistDAO {

	private static AdminBlacklistDAO aaDAO;
	private static SqlSessionFactory ssf;
	
	public AdminBlacklistDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static AdminBlacklistDAO getInstance() {
		if(aaDAO == null) {
			aaDAO = new AdminBlacklistDAO();
			
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
	
	public List<BlacklistViewDomain> selectBlacklistList(SearchVO sVO) throws SQLException{
		List<BlacklistViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectAllBlacklist", sVO);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectAccountList
	
	public int selectTotalCount(SearchVO sVO) throws SQLException {
		int cnt = 0;
		
		try {
			//MyBatis Handler 얻기
			SqlSession ss = getSessionFactory().openSession();
			
			//쿼리 수행 후 결과 받기
			cnt = ss.selectOne("memberTotalCount", sVO);
			
			//MyBatis Handler 끊기
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return cnt;
		
	}// selectTotalCount
	
	public List<WarningTypeDomain> selectWarningType() throws SQLException{
		List<WarningTypeDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectWarningType");
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
		
	}//selectWarningType
	
	public boolean insertBlacklist(InsertBLacklistVO ibVO)throws SQLException{
		
		boolean flag = false;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.insert("insertBlacklist", ibVO) == 1;
			
			ss.commit();
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//insertBlacklist
	
//	public boolean deleteAccount(String bank)throws SQLException{
//		
//		boolean flag = false;
////		System.out.println(bank);
//		try {
//			SqlSession ss = getSessionFactory().openSession();
//			
//			flag = ss.delete("deleteAccount", bank) == 1;
////			System.out.println(flag);
//			ss.commit();
//			
//			ss.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}//end catch
//		
//		return flag;
//	}//insertAccount
	
}//class
















