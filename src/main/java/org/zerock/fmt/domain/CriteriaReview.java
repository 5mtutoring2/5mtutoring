package org.zerock.fmt.domain;

import lombok.Data;

@Data
public class CriteriaReview {
	
	private int currPage = 1;		//현재 페이지 번호
	private int amount = 5;			//각 페이지에서 보여줄 게시물의 개수 
	private int pagesPerPage =1; 	//pagination의 길이 
	
	private Integer tp_number;

}
