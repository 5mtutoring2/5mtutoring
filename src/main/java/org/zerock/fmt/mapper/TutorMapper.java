package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.TutorPageDTO;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.exception.DAOException;

public interface TutorMapper {

	// 튜터페이지 정보 
	public abstract TutorPageVO selectAllTInfo(@Param("num") String tp_number) throws DAOException;
	
	// 신규 튜터 카드정보 (최신순 12개행 정렬)
	public abstract List<TutorPageVO> selectRecentTCard() throws DAOException;
	
	// 추천 튜터 카드 정보 (과목 + 누적답변순 12개행 정렬)
	public abstract List<TutorPageVO> selectHighAnswerTcard(String subject) throws DAOException;
	
	// 추천 튜터 카드 정보 (과목 + 평점순 12개행 정렬)
	public abstract List<TutorPageVO> selectHighStarTcard(String subject) throws DAOException;
	
	// 튜터승인시 튜터페이지 추가
	public abstract Integer insertIntroInfo(String user_email) throws DAOException;
	
	// 튜터페이지 소개 정보 수정 
	public abstract Integer updateTInfo(TutorPageDTO tutorPagedto) throws DAOException;
	
	// 해당 튜터인지 확인
	public abstract String selectTEmail(int tp_number) throws DAOException;
	
	// 튜터페이지 번호 출력
	public abstract Integer selectTpNumber(String user_email) throws DAOException;
	
} // end interface
