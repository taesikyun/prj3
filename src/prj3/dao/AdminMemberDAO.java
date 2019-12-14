package prj3.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prj3.domain.MemberDetailDomain;
import prj3.domain.MemberReservationDomain;
import prj3.domain.MemberViewDomain;
import prj3.vo.SearchVO;

public class AdminMemberDAO {

	private static AdminMemberDAO amDAO;
	private static SqlSessionFactory ssf;
	
	public AdminMemberDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static AdminMemberDAO getInstance() {
		if(amDAO == null) {
			amDAO = new AdminMemberDAO();
			
		}//end if
		
		return amDAO;
		
	}//getInstance
	
	public SqlSessionFactory getSessionFactory() throws IOException {
		
		if(ssf == null) {
			//1. ������ xml�� ��Ʈ������ ����
			Reader reader = null;
			
			try {
				reader = Resources.getResourceAsReader("prj3/dao/mybatis_config.xml");
				
				//2. SqlSessionFactoryBuilder ����
				SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
				
				//3. SqlSessionFactory ���
				ssf = ssfb.build(reader);
				
			}finally {
				if(reader != null) {reader.close();}
				
			}//end finally
			
		}//end if
		
		return ssf;
		
	}//getSessionFactory
	
	public List<MemberViewDomain> selectMemberList(SearchVO sVO) throws SQLException{
		List<MemberViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectAllMember", sVO);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectMemberList
	
	public int selectTotalCount(SearchVO sVO) throws SQLException {
		int cnt = 0;
		
		try {
			//MyBatis Handler ���
			SqlSession ss = getSessionFactory().openSession();
			
			//���� ���� �� ��� �ޱ�
			cnt = ss.selectOne("selectTotalCount", sVO);
			
			//MyBatis Handler ����
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return cnt;
		
	}// selectTotalCount
	
	public MemberDetailDomain selectMemberDetail(String user_id)throws SQLException{
		
		MemberDetailDomain mdd = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			mdd = ss.selectOne("selectMemberDetail", user_id);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return mdd;
		
	}//selectMemberDetail
	
	public List<MemberReservationDomain> selectMemberReservation(String user_id)throws SQLException{
		List<MemberReservationDomain> mrd = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			mrd = ss.selectList("selectUserReservation", user_id);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return mrd;
		
	}//selectMemberReservation
	
}//class
















