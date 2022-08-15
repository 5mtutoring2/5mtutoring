package org.zerock.fmt.service;

import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

public interface UserService {

	public abstract boolean joinStudent(UserVO newStudent) throws ServiceException;
	
}//end 
