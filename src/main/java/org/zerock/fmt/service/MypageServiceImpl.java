package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaFaq;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.MypageMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//마이페이지
@Log4j2
@NoArgsConstructor

@Service
public class MypageServiceImpl implements MypageService {
	
	@Setter(onMethod_= @Autowired)
	private MypageMapper mapper;


	//1. 기본정보 조회
	@Override
	public UserVO getUserInfo(String user_email) throws ServiceException {
		log.trace("getUserInfo() 기본정보 조회");
		
		try { return this.mapper.selectUser(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }

	}// getList()
	
	//2. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	@Override
	public List<QuestionBardVO> getAllMyQuestionList(CriteriaFaq cri) throws ServiceException {
		log.trace("getAllMyQuestionList() 나의 질문글 목록 조회");
		
		try { return this.mapper.selectAllMyQuestionList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getAllMyQuestionList

	//3. 나의 질문글 목록 총 개수 획득
	@Override
	public int getMyQuestionTotalAmount() throws ServiceException {
		log.trace("getMyQuestionTotalAmount() 나의 질문글 목록 총 개수 조회");
		
		try { return this.mapper.getMyQuestionTotalAmount(); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getMyQuestionTotalAmount
	
}// end class


















