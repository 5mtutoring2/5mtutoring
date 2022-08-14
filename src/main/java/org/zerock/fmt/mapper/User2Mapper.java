package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.StudentVO;
import org.zerock.fmt.domain.UserVO2;
import org.zerock.fmt.domain.UserVO_Stop;
import org.zerock.fmt.domain.UserVO_tutor;
import org.zerock.fmt.exception.UserException;

public interface User2Mapper {

	//select 
	//전체 회원조회 - 어드민(회원관리용)
	@Select("SELECT * FROM tbl_user2")
	public abstract List<UserVO2> selectAllUser() throws UserException;
	
	
	//회원 정보조회
	@Select("SELECT * FROM tbl_user2 WHERE user_email=#{user_email}")
	public abstract UserVO2 userInfo(@Param("user_email") String user_email) throws UserException;
	
	
	//insert 
	
//	public abstract boolean insertUser(UserVO2 newUSER) throws UserException;
	
	//회원가입(학생)
	public abstract boolean joinStudent(StudentVO newStudent) throws UserException;
	
	
	//update 
	//정보변경 - 마이페이지(정보수정용)
	public abstract boolean updateUser(UserVO2 userVO) throws UserException;
	
	//정보변경 - 어드민(튜터회원 승인용)
	public abstract boolean approveTutor(UserVO_tutor tutor) throws UserException;
	
	//회원탈퇴 - 마이페이지(정지)
	public abstract boolean stopUser(UserVO_Stop user) throws UserException;
	
}//end 
