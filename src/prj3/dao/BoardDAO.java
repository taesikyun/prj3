package prj3.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prj3.domain.NoticeBoardDetailDomain;
import prj3.domain.NoticeListDomain;
import prj3.domain.QnABoardDetailDomain;
import prj3.domain.QnAListDomain;
import prj3.vo.NoticeModifyVO;
import prj3.vo.NoticeWriteVO;
import prj3.vo.QnAAddRpVO;
import prj3.vo.QnAModifyVO;
import prj3.vo.QnAWriteVO;
import prj3.vo.RpModifyVO;
import prj3.vo.SearchVO;



public class BoardDAO {

	private static BoardDAO bdDao;
	public static SqlSessionFactory ssf;
	
	private BoardDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}

	public static BoardDAO getInstance() {
		if(bdDao==null) {
			bdDao=new BoardDAO();
		}//end if
		return bdDao;
	}//getInstance
	
	public SqlSessionFactory getSessionFactory()throws IOException {//�̱��� //ssf�� ���� ��쿡�� ��������� �ִٸ� ��������� �ʴ´�.
		if(ssf==null) {
			
			Reader reader = null;
			try {
				//1.������ xml�� ��Ʈ������ ����
					reader = Resources.getResourceAsReader("prj3/dao/mybatis_config.xml");
				//2.SqlSessionFactoryBuilder ����
					SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
				//3.SqlSessionFactory ���
					ssf= ssfb.build(reader);
				
			}finally {
				if(reader!=null) {reader.close();}//end if
			}//end finally
			
		}//end if
		return ssf;
	}//getSessionFactory
	
	
	/**
	 * ��ü �Խù��� �� 
	 * @return
	 * @throws SQLException
	 */
	public int selectTotalCount(String mappedId,SearchVO sVO) throws SQLException{
		int cnt=0;
		try {
			SqlSession ss = getSessionFactory().openSession();
			if( sVO != null && sVO.getKeyword() != null && !"".equals(sVO.getKeyword())){
			cnt = ss.selectOne(mappedId,sVO);
			}else {
				cnt = ss.selectOne(mappedId);
			}
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return cnt;
	}//selectTotalCount
	
	//////////////////////////////////////////////////QNA/////////////////////////////////////////////////////////////////////
	
	
	
	public List<QnAListDomain> selectAllQnA(SearchVO sVO)throws SQLException{
		List<QnAListDomain> list = null;
		
		//3.Handler���
		try {
			SqlSession ss = getSessionFactory().openSession();
			
			
			if( sVO != null && sVO.getKeyword() != null && !"".equals(sVO.getKeyword())) {//�˻����� ������ �� 
			}
			list=ss.selectList("qnaList",sVO);
			ss.close();
		

		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return list;
		
	}//selectAllEmp
	
	public QnABoardDetailDomain selectDetailQnA(int q_num)throws SQLException{
		QnABoardDetailDomain qbdd = null;
		try {
			SqlSession ss = getSessionFactory().openSession();
			qbdd = ss.selectOne("qnaPost", q_num);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return qbdd;
	}//selectDetailQnA
	
	public int insertQnAPost(QnAWriteVO qwVO) {
		int flag = 0;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			flag=ss.insert("writePost", qwVO);
			ss.commit();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//endcatch
		
		
		return flag;
	}
	public int deletePostQnA(int q_num) {
		int flag =0;
		
			try {
			SqlSession ss = getSessionFactory().openSession();
				flag = ss.delete("deletePost",q_num);
				ss.commit();
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
			
		return flag;
	}//deletePostQnA
	public int updatePostQnA(QnAModifyVO qVo) {
		int flag=0;
		
		SqlSession ss;
		try {
			ss = getSessionFactory().openSession();
		flag = ss.update("updatePost",qVo);
		ss.commit();
		ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//updatePostQnA
	public int updateQnARp(QnAAddRpVO qarVO) {
		int flag=0;
		
		SqlSession ss;
		try {
			ss = getSessionFactory().openSession();
			flag = ss.update("updateRp",qarVO);
			ss.commit();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//updatePostQnA
	
	public int replyModify(RpModifyVO rmVO) {
		int cnt=0;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.update("rpModify",rmVO);
			ss.commit();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		
		return cnt=0;
	}//replyModify
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////Notice//////////////////////////////////////////////////////////////////////
	public List<NoticeListDomain> selectAllNotice(SearchVO sVO)throws SQLException{
		List<NoticeListDomain> list = null;
		
		//3.Handler���
			try {
				SqlSession ss = getSessionFactory().openSession();
				list=ss.selectList("noticeList",sVO); //parameterType�Ӽ��� �������� ���� ������ ���̵� �ִ´�.
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
		
		return list;
		
	}//selectAllNotice
	public NoticeBoardDetailDomain selectDetailNotice(int n_num)throws SQLException{
		NoticeBoardDetailDomain nbdd = null;
			try {
				SqlSession ss = getSessionFactory().openSession();
				nbdd = ss.selectOne("noticePost", n_num);
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
		return nbdd;
	}//selectDetailNotice
	

	public int insertNoticePost(NoticeWriteVO nwVO) {
		int flag = 0;
		try {
			SqlSession ss = getSessionFactory().openSession();
			flag=ss.insert("n_writePost", nwVO);
			ss.commit();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//endcatch
		
		
		return flag;
	}//inserNoticePost
	
	
	public int deletePostNotice(int q_num) {
		int flag =0;
		
			try {
			SqlSession ss = getSessionFactory().openSession();
				flag = ss.delete("n_deletePost",q_num);
				ss.commit();
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
			
		return flag;
	}//deletePostNotice
	public int updatePostNotice(NoticeModifyVO nmVO) {
		int flag=0;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
		flag = ss.update("n_updatePost",nmVO);
		ss.commit();
		ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//updatePostNotice
	
	
}//class
