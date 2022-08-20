package org.zerock.fmt.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		})
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UserMapperTests {

	@Setter(onMethod_= {@Autowired})
	private UserMapper mapper;

	@BeforeAll
	void beforeAll() {
		assertNotNull(this.mapper);
		log.info("\t+ mapper : {}", this.mapper);
	}
	
	
	@Test
	@Order(1)
	@DisplayName("selectAllUser")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectAllUser() throws DAOException {
		log.info("selectAllUser");
		List<UserVO> list = this.mapper.selectAllUser();
		list.forEach(log::info);
		
	}//selectAllUser
	
	
	@Test
	@Order(2)
	@DisplayName("  selectUser  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectUser() throws DAOException {
		log.trace("selectUser:특정 회원 조회");
		
		UserVO user = this.mapper.selectUser("STemail_4");
		log.info("\t + user : {}", user);
		
	}//userInfo
	
	@Test
	@Order(3)
	@DisplayName("insertStudent 학생회원가입 ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void insertStudent() throws DAOException {
		
		UserDTO newStudent = new UserDTO("STemail", "pass1","nick1","name1","19900430","여자","01000000000",
										"고등학생","3학년",null,null,null,null);
		if(this.mapper.insertStudent(newStudent) == 1) {
			log.info("학생회원가입완료");
		} else log.info("회원가입안됨");
		
	}//joinStudent
	
	@Test
	@Order(4)
	@DisplayName("insertTutor 튜터회원가입")
	@Timeout(value = 5, unit =TimeUnit.SECONDS)
	void insertTutor() throws DAOException {
		
		UserDTO newTutor = new UserDTO("newTT1","pw1-1","nick1-1","nameTT","19900000","남자","01011111111",
										null,null, "재학중","국어","file:name",null);
		Integer insertResult = this.mapper.insertTutor(newTutor);
		if(insertResult==1) {
			log.info("\t + insertResult : {}", insertResult);
			log.info("튜터 가입 완료 -> 메일 송신 필요");
		} else log.info("회원가입안됨");
		
	}//joinTutor
	
	@Test
	@Order(5)
	@DisplayName("updateUser 마이페이지 정보수정")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateUser() throws DAOException {
		
		UserDTO user = new UserDTO("STemail_5","pw변경","nick변경","name변경",null,null,null,
				null,null, null, null, null ,null);
		Integer updateResult = this.mapper.updateUser(user);
		if(updateResult==1) {
			log.info("\t + updateResult : {}", updateResult);
			log.info("회원정보 수정완료");
		}//if
	}//updateUser
	
	@Test
	@Order(6)
	@DisplayName("updateTutorPass 튜터 가입 승인")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateTutorPass() throws DAOException {
		
//		UserDTO tutor = new UserDTO("TTemail_4","pw","nick","name",null,null,null,
//									null,null, null, null, null ,null,null);
		String user_email = "TTemail_4";
		Integer updateResult = this.mapper.updateTutorPass(user_email);
		if(updateResult==1) {
			log.info("\t + updateResult : {}", updateResult);
			log.info("튜터 승인 완료");
		} else log.info("테스트실패");
	}//approveTutor
	
	@Test
	@Order(7)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void stopUser() throws DAOException {

//		UserDTO user = new UserDTO("STemail_5","pw변경","nick변경","name변경",null,null,null,
//				null,null, null, null, null ,null,null);
		String user_email = "STemail_4";
		Integer updateResult = this.mapper.updateUserStop(user_email);
		
		if(updateResult==1) {
			log.info("\t updateResult : {}", updateResult);
			log.info("활동정지 완료");
		} else log.info("테스트실패");
		
	}//stopUser
}//end class
