package org.zerock.fmt.domain;

import java.sql.Date;

import lombok.Value;

@Value
public class StudentVO {

	private String user_email;
	private String user_pw;
	private String user_nick;
	private String user_name;
	private String user_gender;
	private Date user_birth;
	private String user_phone;
	private Integer hands_wallet;
	
	private String st_school;
	private String st_grade;

}//end class
