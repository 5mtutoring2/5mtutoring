package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.TutorPageDTO;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.exception.DAOException;

public interface TutorMapper {

	// 튜터페이지 정보 
	public abstract TutorPageVO selectAllTInfo(@Param("tp_number") Integer tp_number) throws DAOException;
	
	// 튜터카드 정보 (최신순 12개행 정렬)
	public abstract List<TutorPageVO> selectRecentTCard() throws DAOException;
	
	// 튜터페이지 소개 정보 입력 (경력, 소개제목, 소개내용)
	public abstract Integer insertIntroInfo(TutorPageDTO tutorPagedto) throws DAOException;
	
	// 튜터페이지 소개 정보 수정 
	public abstract Integer updateTInfo(TutorPageDTO tutorPagedto) throws DAOException;
	
} // end interface
