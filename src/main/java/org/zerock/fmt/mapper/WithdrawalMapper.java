package org.zerock.fmt.mapper;

import java.util.List;

import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.DAOException;

public interface WithdrawalMapper {

	//	출금하기는 삭제(D) 없음 
	// => 마이페이지에서 출금 신청 & 목록 조회
	// => 관리자페이지에서 목록 조회 & 승인여부 수정

	
//  [C]  출금 신청
	public abstract Integer insertWithdrawal(WithdrawalDTO dto) throws DAOException;
	
	
//  [R]  출금 신청 내역 전체 조회 (내림차순) - 관리자
	public abstract List<WithdrawalVO> selectAllWithdrawalList(CriteriaAdmin cri) throws DAOException;
	
//	[R] 페이징 총 건수 - 관리자
	public abstract int countList(Integer w_num) throws DAOException;
	

//  [U]  승인 여부 수정
	public abstract Integer updateState(WithdrawalDTO dto) throws DAOException;
	
//	[R] 승인 여부 별 금액
	public abstract int totalDrowal(String approval) throws DAOException;
	
} // end interface
