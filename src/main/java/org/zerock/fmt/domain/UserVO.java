package org.zerock.fmt.domain;

import java.sql.Date;

import lombok.Value;

@Value
public class UserVO {

	private String user_email;
	private String user_pw;
	private String user_nick;
	private String user_name;
	private String user_birth;
	private String user_gender;
	private String user_phone;
	
	private String st_school;		//학교 
	private String st_grade;		//학년

	private String tt_school;		//재학or졸업
	private String tt_subject;		//과목
	private String tt_certificate; 	//첨부파일
	private String tt_depart;		//학과

	private String user_group;		//학생or튜터 신분
	private String pass;			//승인허가
	private String user_status;		//활동상태
	private Integer hands_wallet;	//손들기
	private Date user_join;			//가입일
	private Date withdrawal_date;	//정지일(탈퇴일)
	
	
	

	
}//end class
