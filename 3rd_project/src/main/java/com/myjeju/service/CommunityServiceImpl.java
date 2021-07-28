package com.myjeju.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjeju.dao.CommunityDAO;
import com.myjeju.vo.CommunityVO;

@Service("CommunityService")
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityDAO communityDAO;

	//자유게시판 글쓰기
	@Override
	public boolean getFreeWrite(CommunityVO vo) {
		int val = communityDAO.getFreeWrite(vo);
		
		boolean result = false;
		
		if ( val!=0 ) {
			result = true;
		}
		return result;
	}
	//자유게시판 총 갯수
	@Override
	public int getFreeTotalCount() {
		return communityDAO.getFreeTotalCount();
	}
	//자유게시판 리스트
	@Override
	public ArrayList<CommunityVO> getFreeList(int start, int end) {
		return communityDAO.getFreeList(start, end);
	}
	//자유게시판 상세보기
	@Override
	public  CommunityVO getFreeContent(String fid) {
		return communityDAO.getFreeContent(fid);
	}
	//자유게시판 댓글
	@Override
	public boolean getCommentResult(CommunityVO vo) {
		int val = communityDAO.getCommentResult(vo);
		
		boolean result = false;
		
		if ( val != 0 ) {
			result = true;
		}
		
		return result;
	}
	//자유게시판 댓글 가져오기
	@Override
	public ArrayList<CommunityVO> getFreeComment(String fid) {
		return communityDAO.getFreeComment(fid);
	}
	//자유게시판 파일이름가져오기
	@Override
	public String getFileResult(String fid) {
		return communityDAO.getFileResult(fid);
	}
	//자유게시판 파일o 업데이트
	@Override
	public  boolean getFileYesUpdate(CommunityVO vo) {
		int val = communityDAO.getFileYesUpdate(vo);
		
		boolean result = false;
		
		if ( val!= 0) {
			result = true;
		}
		
		return result;
	}
	//자유게시판 파일x 업데이트
	@Override
	public  boolean getFileNoUpdate(CommunityVO vo) {
		int val = communityDAO.getFileNoUpdate(vo);
		
		boolean result = false;
		
		if ( val!= 0) {
			result = true;
		}
		
		return result;
	}
	//자유게시판 댓글삭제
	@Override
	public boolean getCommentDelete(String cid) {
		int val = communityDAO.getCommentDelete(cid);

		boolean result = false;
		
		if ( val != 0) {
			result = true;
		}
		
		return result;
	}
	//자유게시판 삭제
	@Override
	public boolean getFreeBoardDelete(String fid) {
		int val = communityDAO.getFreeBoardDelete(fid);

		boolean result = false;
		
		if ( val != 0) {
			result = true;
		}
		
		return result;
	}
	
	//자유게시판 조회수
	public  int getHitUp(String fid) {
		return communityDAO.getHitUp(fid);
	}
	
	
	
	
	//요청게시판 글쓰기
	@Override
	public boolean getRequestWrite(CommunityVO vo) {
		int val = communityDAO.getRequestWrite(vo);

		boolean result = false;
		
		if ( val != 0) {
			result = true;
		}
		
		return result;
	}
	//요청게시판 총 갯수
	@Override
	public int getRequestTotalCount() {
		return communityDAO.getRequestTotalCount();
	}
	//요청게시판 리스트
	@Override
	public ArrayList<CommunityVO> getRequestList(int start, int end) {
		return communityDAO.getRequestList(start, end);
	}
	//요청게시판 비밀번호확인
	@Override
	public boolean getBoardPass(String rid, String pass) {
		int val = communityDAO.getBoardPass(rid, pass);

		boolean result = false;
		
		if ( val != 0) {
			result = true;
		}
		
		return result;
	}
	//요청게시판 상세보기
	@Override
	public  CommunityVO getRequestContent(String rid) {
		return communityDAO.getRequestContent(rid);
	}

}
