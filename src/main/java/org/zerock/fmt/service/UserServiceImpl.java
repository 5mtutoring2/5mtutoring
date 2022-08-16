package org.zerock.fmt.service;

import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.UserMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class UserServiceImpl implements UserService, InitializingBean{

	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	

	@Override
	public boolean joinStudent(UserVO newStudent) throws ServiceException{
		
		try{ return this.userMapper.joinStudent(newStudent); }
		catch( Exception e ) { throw new ServiceException(e); }
		
	}


//------------------------------------------------------------
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("\t proxy 객체 만들어졌습니까");
		Objects.requireNonNull(this.userMapper);
		log.info("\t + userMapper : {}", this.userMapper);
		
	}//joinStudent
//------------------------------------------------------------
}//end class
