package prj3.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prj3.domain.MainNoticeViewDomain;
import prj3.domain.MainQnaViewDomain;
import prj3.domain.MainReservationViewDomain;

public class AdminMainDAO {

	private static AdminMainDAO amDAO;
	private static SqlSessionFactory ssf;
	
	public AdminMainDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static AdminMainDAO getInstance() {
		if(amDAO == null) {
			amDAO = new AdminMainDAO();
			
		}//end if
		
		return amDAO;
		
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
	
	public List<MainReservationViewDomain> selectMainReservationList() throws SQLException{
		List<MainReservationViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectReservation3");
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectMainReservationList
	
	public List<MainNoticeViewDomain> selectMainNoticeList() throws SQLException{
		List<MainNoticeViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectNotice3");
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectMainNoticeList
	
	public List<MainQnaViewDomain> selectMainQnaList() throws SQLException{
		List<MainQnaViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectQna3");
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectMainQnaList
	
}//class
















