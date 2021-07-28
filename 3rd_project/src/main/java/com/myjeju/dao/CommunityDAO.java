package com.myjeju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myjeju.vo.CommunityVO;
import com.myjeju.vo.NoticeVO;

@Repository
public class CommunityDAO {
	

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static String namespace = "mapper.community";
	
	//자유게시판 글쓰기
	public int getFreeWrite(CommunityVO vo) {
		return sqlSession.insert(namespace+".free_write", vo);
	}
	
	//자유게시판 총 게시글 수
	public int getFreeTotalCount() {
		return sqlSession.selectOne(namespace+".free_total_count");
	}
	
	//자유게시판 페이징 처리
	public ArrayList<CommunityVO> getFreeList(int start, int end){
		Map<String, String> param = new HashMap<String, String>();
		param.put("start", String.valueOf(start));
		param.put("end", String.valueOf(end));
		List<CommunityVO> list = sqlSession.selectList(namespace+".free_list", param);
		
		return (ArrayList<CommunityVO>)list;
	}	
	
	//자유게시판 해당 글 내용 가져오기
	public CommunityVO getFreeContent(String fid) {
		return sqlSession.selectOne(namespace+".free_content", fid);
	}
	
	//자유게시판 댓글 달기
	public int getCommentResult(CommunityVO vo) {		
		return sqlSession.insert(namespace+".comment", vo);
	}
	
	//자유게시판 댓글 가져오기
	public ArrayList<CommunityVO> getFreeComment(String fid) {
		List<CommunityVO> list = sqlSession.selectList(namespace+".free_comment", fid);
		
		return (ArrayList<CommunityVO>)list;
	}
	
	//기존에 저장된 파일 가져오기
	public String getFileResult(String fid) {
		return sqlSession.selectOne(namespace+".file_result", fid);
	}
	
	//파일포함 --> 업데이트
	public int getFileYesUpdate(CommunityVO vo) {
		return sqlSession.update(namespace+".yes_update", vo);
	}
	
	//파일미포함 --> 업데이트
	public int getFileNoUpdate(CommunityVO vo) {
		return sqlSession.update(namespace+".no_update", vo);
	}
	
	//자유게시판 댓글 삭제하기
	public int getCommentDelete(String cid) {
		return sqlSession.delete(namespace+".comment_delete", cid);
	}
	
	//자유게시판 게시글 삭제하기
	public int getFreeBoardDelete(String fid) {
		return sqlSession.delete(namespace+".free_board_delete", fid);
	}
	
	//자유게시판 조회수
	public int getHitUp(String fid) {
		return sqlSession.update(namespace+".hit", fid);
	}
	
	///////////////////////////////////////////////////////////////////////
	
	//요청게시판 글쓰기
	public int getRequestWrite(CommunityVO vo) {
		return sqlSession.insert(namespace+".request_write", vo);
	}
	//요청게시판 총 게시글 수
		public int getRequestTotalCount() {
			return sqlSession.selectOne(namespace+".request_total_count");
	}		
	//요청게시판 페이징 처리
	public ArrayList<CommunityVO> getRequestList(int start, int end){
		Map<String, String> param = new HashMap<String, String>();
		param.put("start", String.valueOf(start));
		param.put("end", String.valueOf(end));
		List<CommunityVO> list = sqlSession.selectList(namespace+".request_list", param);
		
		return (ArrayList<CommunityVO>)list;
	}	
	//요청게시판 비밀번호 확인
	public int getBoardPass(String rid, String pass){
		Map<String, String> param = new HashMap<String, String>();
		param.put("rid", rid);
		param.put("pass", pass);
		return sqlSession.selectOne(namespace+".board_pass", param);
	}	
	//요청게시판 상세보기
	public CommunityVO getRequestContent(String rid) {
		return sqlSession.selectOne(namespace+".request_content", rid);
	}
	
	//공지사항 리스트
	public ArrayList<NoticeVO> getNoticeList() {
		List<NoticeVO> list = sqlSession.selectList(namespace+".notice_list");		
		return (ArrayList<NoticeVO>)list;
	}
	//공지사항 조회수
	public int getNoticeCount(String nid) {
		return sqlSession.update(namespace+".notice_count", nid);
	}
}
