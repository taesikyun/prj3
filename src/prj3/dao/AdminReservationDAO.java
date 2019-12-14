package prj3.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prj3.domain.PastReservationDetailDomain;
import prj3.domain.PastReservationViewDomain;
import prj3.domain.RealtimeListDomain;
import prj3.domain.ReservationDetailDomain;
import prj3.domain.ReservationViewDomain;
import prj3.vo.PastReservationDetailVO;
import prj3.vo.SearchVO;

public class AdminReservationDAO {

	private static AdminReservationDAO arDAO;
	private static SqlSessionFactory ssf;
	
	public AdminReservationDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static AdminReservationDAO getInstance() {
		if(arDAO == null) {
			arDAO = new AdminReservationDAO();
			
		}//end if
		
		return arDAO;
		
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
	
	public List<ReservationViewDomain> selectReservationList(SearchVO sVO) throws SQLException{
		List<ReservationViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectAllReservation", sVO);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectAccountList
	
	public List<PastReservationViewDomain> selectAllPastReservation(SearchVO sVO) throws SQLException{
		List<PastReservationViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectAllPastReservation", sVO);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectAllPastReservation
	
	public int selectTotalCount(SearchVO sVO) throws SQLException {
		int cnt = 0;
		
		try {
			//MyBatis Handler ���
			SqlSession ss = getSessionFactory().openSession();
			
			//���� ���� �� ��� �ޱ�
			cnt = ss.selectOne("reservationTotalCount", sVO);
			
			//MyBatis Handler ����
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return cnt;
		
	}// selectTotalCount
	
	public int pastReservationTotalCount(SearchVO sVO) throws SQLException {
		int cnt = 0;
		
		try {
			//MyBatis Handler ���
			SqlSession ss = getSessionFactory().openSession();
			
			//���� ���� �� ��� �ޱ�
			cnt = ss.selectOne("pastReservationTotalCount", sVO);
			
			//MyBatis Handler ����
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return cnt;
		
	}// pastReservationTotalCount
	
	public ReservationDetailDomain selectReservationDetail(String reservation_num) throws SQLException{
		ReservationDetailDomain rdd = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			rdd = ss.selectOne("selectDetailReservation", reservation_num);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return rdd;
	}//selectReservationDetail
	
	public PastReservationDetailDomain selectPastReservationDetail(PastReservationDetailVO prdVO) throws SQLException{
		PastReservationDetailDomain prdd = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			prdd = ss.selectOne("selectDetailPastReservation", prdVO);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return prdd;
	}//selectPastReservationDetail
	
	public List<RealtimeListDomain> selectRealtime(String reservation_num) throws SQLException{
		List<RealtimeListDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectRealtime", reservation_num);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}//selectRealtime
	
	public boolean updateReservation(String reservation_num)throws SQLException {
		boolean flag = false;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.update("updateReservation", reservation_num)==1;
//			System.out.println("ù��° flag"+flag);
			ss.commit();
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//updateReservation
	
	public boolean updateDeposit(String reservation_num) throws SQLException {
		boolean flag = false;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.update("updateDeposit", reservation_num)==1;
//			System.out.println("�ι�° flag"+flag);
			ss.commit();
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//updateDeposit
	
}//class
















