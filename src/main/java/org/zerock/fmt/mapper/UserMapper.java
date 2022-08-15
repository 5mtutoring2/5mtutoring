package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.UserException;

public interface UserMapper {

	//*** select 
	//전체 회원조회 - 어드민(회원관리용)
	@Select("SELECT * FROM tbl_user")
	public abstract List<UserVO> selectAllUser() throws UserException;
	
	//회원 정보조회
	@Select("SELECT * FROM tbl_user WHERE user_email=#{user_email}")
	public abstract UserVO userInfo(@Param("user_email") String user_email) throws UserException;
	
	//*** insert 
	//학생 가입
	public abstract boolean joinStudent(UserVO newStudent) throws UserException;

	//튜터 가입 
	public abstract boolean joinTutor(UserVO newTutor) throws UserException;
	
	
	//*** update 
	//정보변경 - 마이페이지(정보수정용)
	public abstract boolean updateUser(UserVO user) throws UserException;
	
	//정보변경 - 어드민(튜터회원 승인용)
	public abstract boolean approveTutor(UserVO tutor) throws UserException;
	
	//회원탈퇴 - 마이페이지(정지)
	public abstract boolean stopUser(UserVO user) throws UserException;
	
}//end 
