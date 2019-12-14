package prj3.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prj3.domain.ConceptNameDomain;
import prj3.domain.RoomConceptDetailDomain;
import prj3.domain.RoomConceptViewDomain;
import prj3.domain.RoomDetailDomain;
import prj3.domain.RoomViewDomain;
import prj3.vo.RoomConceptVO;
import prj3.vo.RoomRoomVO;

public class AdminRoomDAO {

	private static AdminRoomDAO arDAO;
	private static SqlSessionFactory ssf;
	
	public AdminRoomDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static AdminRoomDAO getInstance() {
		if(arDAO == null) {
			arDAO = new AdminRoomDAO();
			
		}//end if
		
		return arDAO;
		
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
	
	public List<RoomConceptViewDomain> selectConcept(){
		
		List<RoomConceptViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectConcept");
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
		
	}//selectConcept
	
	public List<RoomViewDomain> selectRoom(){
		
		List<RoomViewDomain> list = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			list = ss.selectList("selectRoom");
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
		
	}//selectConcept
	
	public boolean insertConcept(RoomConceptVO rcVO) throws SQLException{
		boolean flag = false;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.insert("insertConcept", rcVO)==1;
			
			ss.commit();
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//insertConcept
	
	public List<ConceptNameDomain> selectConceptName() throws SQLException{
		
		List<ConceptNameDomain> cndList = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			cndList = ss.selectList("conceptSelect");
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return cndList;
		
	}//selectConceptName
	
	public boolean insertRoom(RoomRoomVO rriVO) throws SQLException{
		boolean flag = false;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.insert("insertRoom", rriVO)==1;
			
			ss.commit();
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//insertConcept
	
	public RoomConceptDetailDomain selectConceptDetail(String concept_name)throws SQLException{
		
		RoomConceptDetailDomain rcdd = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			rcdd = ss.selectOne("selectConceptDetail", concept_name);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return rcdd;
	}//selectConceptDetail
	
	public RoomDetailDomain selectRoomDetail(String room_name)throws SQLException{
		
		RoomDetailDomain rdd = null;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			rdd = ss.selectOne("selectRoomDetail", room_name);
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return rdd;
	}//selectRoomDetail
	
	public boolean updateConcept(RoomConceptVO rcuVO)throws SQLException  {
		boolean flag = false;
//		System.out.println("11");
		try {
			SqlSession ss = getSessionFactory().openSession();
//			System.out.println("11-1");
			System.out.println(rcuVO);
			flag = ss.update("updateConcept", rcuVO) == 1;
			ss.commit();
			ss.close();
//			System.out.println("11-2");
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
//		System.out.println("22");
		return flag;
	}//updateConcept
	
	public boolean deleteConcept(String concept_name) throws SQLException {
		boolean flag = false;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.delete("deleteConcept", concept_name) == 1;
			
			ss.commit();
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//deleteConcept
	
	public boolean updateRoom(RoomRoomVO rrVO)throws SQLException  {
		boolean flag = false;
//		System.out.println("11");
		try {
			SqlSession ss = getSessionFactory().openSession();
//			System.out.println("11-1");
			System.out.println(rrVO);
			flag = ss.update("updateRoom", rrVO) == 1;
			ss.commit();
			ss.close();
//			System.out.println("11-2");
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
//		System.out.println("22");
		return flag;
	}//updateRoom
	
	public boolean deleteRoom(String room_name) throws SQLException {
		boolean flag = false;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			flag = ss.delete("deleteRoom", room_name) == 1;
			
			ss.commit();
			
			ss.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//deleteRoom
	
}//class













