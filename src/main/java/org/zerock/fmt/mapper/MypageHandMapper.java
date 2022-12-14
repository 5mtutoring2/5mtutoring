package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.AnswerVO2;
import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.UseHandQVO;
import org.zerock.fmt.domain.UseHandTVO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.DAOException;

//마이페이지(손들기 조회)
public interface MypageHandMapper {
	
//	============<<SELECT>>============
	//1-1. 손들기 사용 목록(질문하기) 조회 페이징 처리(학생)
	public abstract List<UseHandQVO> selectAllmyUsehandQList(CriteriaMyPage cri) throws DAOException;
	
	//1-2. 손들기 사용 목록(질문하기) 총 횟수 조회
	public abstract Integer getMyUsehandQTotalAmount(CriteriaMyPage cri) throws DAOException;
	
	//1-3. 손들기 사용 목록(과외받기) 조회 페이징 처리(학생)
	public abstract List<UseHandTVO> selectAllmyUsehandTList(CriteriaMyPage cri) throws DAOException;
	
	//1-4. 손들기 사용 목록(과외받기) 총 횟수 조회
	public abstract Integer getMyUsehandTTotalAmount(CriteriaMyPage cri) throws DAOException;

//	====================================
	//2-1. 손들기 구매 목록 조회 페이징처리(학생)
	public abstract List<BuyVO> myPageAllBuy(CriteriaMyPage cri) throws DAOException;
	
	//2-2. 손들기 구매 내역 총 수량
	public abstract Integer myPageBuyCount(CriteriaMyPage cri) throws DAOException;
	
	//2-3 손들기 구매내역 상세 조회
	public abstract BuyInfoVO selectBuyDetail(Integer b_number) throws DAOException;
	
//	====================================
	//3-1 손들기 출금 내역 목록 조회 페이징 처리(내림차순으로) 
	public abstract List<WithdrawalVO> selectAllMyWithdrawalList(CriteriaMyPage cri) throws DAOException;
	
	//3-2 손들기 출금 내역 총 수량
	public abstract Integer getMyWithdrawalTotalAmount(CriteriaMyPage cri) throws DAOException;
	
//	====================================
	//4-1 손들기 획득 내역 목록(질문하기) 조회 페이징 처리(튜터) 
	public abstract List<AnswerVO2> selectAllmyGetHandQList(CriteriaMyPage cri) throws DAOException;
	
	//4-2 손들기 획득 내역(질문하기) 총 횟수
	public abstract Integer getMyGetHandQTotalAmount(CriteriaMyPage cri) throws DAOException;
	
	
//	====================================
	//5. 튜터페이지 조회
	@Select("SELECT tp_number "
			+ "FROM tbl_tutor_page "
			+ "WHERE user_email = #{user_email}")
	public abstract Integer getTutorPageNum(String user_email) throws DAOException;
	
}// end interface
