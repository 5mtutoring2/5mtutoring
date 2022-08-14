package org.zerock.fmt.domain;

import java.sql.Date;

import lombok.Value;

@Value

//어드민 - 회원정보 담는용(개인정보는 UserVO연결따로) 
public class userVO_Admin {

	private String user_email;
	private String nick_name;
	private Date user_join;			//가입일
	private Date withdrawal_date;	//탈퇴일
	
}//end class

