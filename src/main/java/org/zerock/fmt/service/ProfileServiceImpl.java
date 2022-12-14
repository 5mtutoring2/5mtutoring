package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.domain.ProfileDTO;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.UserProfileVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.FaqMapper;
import org.zerock.fmt.mapper.ProfileMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//프로필 서비스 계층
@Log4j2
@NoArgsConstructor

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Setter(onMethod_= @Autowired)
	private ProfileMapper mapper;
	
	
	//1. 프로필 사진 정보 조회
	@Override
	public List<ProfileVO> getProfile(String user_email) throws ServiceException {
		log.info("\t+ 1. \"{}\"의 프로필 사진 정보 조회", user_email);
		
		try { return this.mapper.selectProfile(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }// try-catch
	}// getProfile
	
	//2. 프로필 사진 등록
	@Override
	public boolean createProfile(ProfileDTO dto) throws ServiceException {
		log.info("\t+ 2. \"{}\"의 프로필 사진 등록", dto.getUser_email());
		
		try { return this.mapper.insertProfile(dto); } 
		catch (DAOException e) { throw new ServiceException(e); }// try-catch
	}// createProfile
	
	//3. 프로필 사진 수정
	@Override
	public boolean modifyProfile(ProfileDTO dto) throws ServiceException {
		log.info("\t+ 3. \"{}\"의 프로필 사진 수정", dto.getUser_email());
		
		try { return this.mapper.updateProfile(dto); } 
		catch (DAOException e) { throw new ServiceException(e); }// try-catch
	}// modifyProfile
	
	//4. 유저 닉네임 조회
	@Override
	public String getUserNick(String user_email) throws ServiceException {		
		try { return this.mapper.selectUserNick(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }// try-catch
	}// getUserNick
	
	//5. 튜터 닉네임 조회
	@Override
	public String getTutorNick(Integer tp_number) throws ServiceException {
		try { return this.mapper.selectTutorNick(tp_number); } 
		catch (DAOException e) { throw new ServiceException(e); }// try-catch
	}// getTutorNick
	
	//6. 튜터 이메일 조회
	@Override
	public String getTutorEmail(Integer tp_number) throws ServiceException {
		try { return this.mapper.selectTutorEmail(tp_number); } 
		catch (DAOException e) { throw new ServiceException(e); }// try-catch
	}// getTutorEmail
	
	//7. 유저 닉네임, 사진정보 조회
	@Override
	public List<UserProfileVO> getUserNaP(String user_email) throws ServiceException {
		try { return this.mapper.selectUserNaP(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }// try-catch
	}//getUserNaP
	
}// end class


















