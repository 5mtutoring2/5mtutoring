package org.zerock.fmt.domain;

import java.util.Date;

import lombok.Value;

@Value
public class CommentVO3 {	//mypage에 사용
	
	private Integer cm_number;		// 댓글 번호
	private Integer a_number;		// 답변게시글 번호 (fk, null 허용)
	private Integer fb_number;		// 커뮤니티글 번호 (fk, null 허용)
	
	private String user_email;

	private String cm_content;		
	private Date regdate;			
	private Date updateDate;

} // end class
