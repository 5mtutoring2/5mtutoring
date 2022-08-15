package org.zerock.fmt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserServiceTests {

	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	
	@BeforeAll
	void beforeAll(){
		assertNotNull(this.userService);
		log.info("\t + this.userService : {}", this.userService);
	}
	
	@Test
	@Order(1)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void joinStudent() throws ServiceException {

		UserVO newUser = new UserVO("2", "1", "1", "1", "1010", "남자", "010", "st1", "st1", 
				null, null, null, null, null, null, null, null, null, null);
		if(this.userService.joinStudent(newUser)) {
			log.info("service:학생추가 성공"+newUser);
		} else {
			log.info("학생 회원가입 실패");
		}//if-else 
	}//joinStudent
}//end class
