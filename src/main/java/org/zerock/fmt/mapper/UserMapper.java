package org.zerock.fmt.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;

public interface UserMapper {

	//-------------- SELECT 
	//학생회원조회 - 어드민(회원관리용)
	public abstract List<UserVO> selectAllUser(CriteriaAdmin cri) throws DAOException;

	//총 회원 수 
	public abstract int userCount(CriteriaAdmin cri) throws DAOException; 

	//회원 정보조회 + 손들기 개수 조회
	@Select("SELECT * FROM tbl_user WHERE user_email=#{user_email}")
	public abstract UserVO selectUser(@Param("user_email") String user_email) throws DAOException;

	//이메일 중복체크 
	@Select("SELECT COUNT(*) FROM tbl_user WHERE user_email = #{user_email}")
	public abstract int findEmailCheck(@Param("user_email")String userEmail) throws DAOException;
	
	//닉네임 중복검사 
	@Select("SELECT COUNT(*) FROM tbl_user WHERE USER_NICK = #{user_nick}")
	public abstract int selectNicCheck(@Param("user_nick")String newNick) throws DAOException;
	
	//로그인
	@Select("SELECT * FROM tbl_user WHERE user_email = #{user_eamil} AND pass='Accept'")
	public abstract UserVO loginEmail(@Param("user_eamil")String user_email) throws DAOException;
	
	//튜터 승인 대기 리스트
	public abstract List<UserVO> selectWaitTutor(CriteriaAdmin cri) throws DAOException;
	
	//튜터 승인 총 인원수 
	@Select("SELECT count(*) FROM tbl_user WHERE pass='Wait'")
	public abstract int waitTutorCount() throws DAOException;
	
	//이메일찾기 
	public abstract String FindEmailreturnString(String user_phone) throws DAOException;

	
	//-------------- INSERT 
	//학생 가입
	public abstract int insertStudent(UserDTO newStudent) throws DAOException;

	//튜터 가입 
	public abstract int insertTutor(UserDTO newTutor) throws DAOException;
	
	
	//-------------- KAKAO
	// 카카오정보 있는지 확인
	public abstract HashMap<String,Object> kakaoCheck(HashMap<String,Object> paramMap);
	
	// 카카오정보로 로그인
	public abstract UserVO kakaoLogin(HashMap<String,Object> paramMap);
	
	// 카카오 정보 수정
	public abstract int updateKakao(HashMap<String,Object> paramMap);
	
	// 카카오 회원가입(학생)
	public abstract int kakaoRegister(HashMap<String,Object> paramMap);
	
	// 카카오 회원가입(튜터)
	public abstract int kakaoRegisterTu(HashMap<String,Object> paramMap);
	
	
	//-------------- UPDATE 
	//정보변경 - 마이페이지(정보수정용)
	public abstract int updateUser(UserDTO user) throws DAOException;
	
	//정보변경 - 어드민(튜터회원 승인용)
	@Update("UPDATE tbl_user SET pass='Accept' WHERE user_email=#{user_email}")
	public abstract int updateTutorPass(@Param("user_email")String user_email) throws DAOException;
	
	//회원탈퇴 - 마이페이지(정지)
	public abstract int updateUserStop(@Param("user_email")String user_email) throws DAOException;
	
	//비밀번호 변경(비밀번호찾기용)
	public abstract int updatePW(UserDTO dto) throws DAOException;
	
	
	//-------------- 손들기 UPDATE 
	
	//손들기 구매(학생) + 획득(튜터) -> 개수 추가
	public abstract int updateHandGet(@Param("h_count") Integer h_count,
										@Param("user_email")String user_email) throws DAOException;
	//손들기 사용-> 개수 차감
	public abstract int updateHandUse(@Param("h_count") Integer h_count,
										@Param("user_email")String user_email) throws DAOException;
	
	
	
	
	//-------------- DELETE 
	//회원삭제가 아니라 정지 변경이므로 삭제SQL 없음
	
}//end 
