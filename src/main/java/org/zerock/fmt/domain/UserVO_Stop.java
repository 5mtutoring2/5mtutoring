package org.zerock.fmt.domain;

import lombok.Value;


@Value
//활동정지용
public class UserVO_Stop {

	private String user_email;
	private String user_status;		//상태 

}//end class
