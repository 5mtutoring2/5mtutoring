<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
	<!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
	<jsp:include page="../htmlHead.jsp" flush="true" />
	<!-- =======mypage 공통 CSS=================================== -->
	<link href="${path}/resources/css/mypage.css" rel="stylesheet">
	<!-- ======== 탈퇴하기 모달 ========= -->
	<script src="${path}/resources/js/7-08.js"></script>

	<title>회원탈퇴</title>
</head>

<body>
	<!-- ============= 공통 Header : 로그인 후 =================== -->
	<jsp:include page="../header_login.jsp" flush="true" />
	<!-- ========================================================= -->

	<!-- ======= Hero Section ======= -->

	<!-- ======= main Section ======= -->
	<section id="main" class="container">
		<div class="row">

			<div id="nav" class="col-lg-3">
				<ul class="list-group mb-2 bg-dark text-white">
					<li class="list-group-item bg-blue fw-bold fs-5 px-lg-4">마이페이지</li>
				</ul>
				<div class="list-group">
					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/studentPage"
							class="list-group-item list-group-item-action px-lg-4">기본
							정보</a></c:if>
					<c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorPage"
							class="list-group-item list-group-item-action px-lg-4">기본
							정보</a></c:if>

					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/studentHands/use?group=1"
							class="list-group-item list-group-item-action px-lg-4">손들기
							내역</a></c:if>
					<c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorHands/get?group=1"
							class="list-group-item list-group-item-action px-lg-4">손들기
							내역</a></c:if>

					<c:if test="${__LOGIN_USER__.user_group eq 'Student'}"><a href="/mypage/myQuestion"
							class="list-group-item list-group-item-action px-lg-4">나의
							질문글</a></c:if>

					<a href="/mypage/community/write" class="list-group-item list-group-item-action px-lg-4">나의
						커뮤니티</a>

					<a href="/mypage/qList" class="list-group-item list-group-item-action px-lg-4">1:1
						문의하기</a>

					<a href="/mypage/unregister" class="list-group-item list-group-item-action px-lg-4 fw-bold">회원
						탈퇴</a>
				</div>
			</div><!-- End main nav -->


			<div id="contents" class="col-lg-9">

				<h3 class="fw-bold">회원 탈퇴</h3>
				<br>

				<!-- FROM -->
			
				<form action="/mypage/unregisterConfirm" method="post" id="userInfo" enctype="multipart/form-data"></form>
					<div class="container card p-4" bg-card>
						<div class="content7-8">
							<h5>📝 신청하기 전 안내 사항을 꼭 확인해주세요.</h5>
							<br>
							<ul>
								<li>사용하고 계신 아이디 <strong>'${__LOGIN_USER__.user_email}'</strong> 는 <span class="text-danger">탈퇴할
										경우 재사용 및 복구가 불가능</span>합니다.<br> 본인과 타인이 모든 재사용 및 복구가 불가하오니 신중하게
									선택하시기 바랍니다.
								</li>
								<br>
								<li>탈퇴 후 회원정보 및 서비스 이용기록은 모두 삭제됩니다. <br> 삭제되는 내용을 확인하시고
									필요한 데이터는 미리 백업해주세요.
								</li>
								<br>
								<li>탈퇴 후에도 게시판에 등록된 게시물은 그대로 남아 있습니다. <br> 삭제를 원하시는
									게시글이 있다면 반드시 탈퇴 전 삭제하시기 바랍니다.
								</li>
							</ul>
						</div>

						<!-- <form action="/mypage/unregisterConfirm" method="post" id="userInfo" enctype="multipart/form-data"> -->
							<div class="d-flex flex-row align-items-center">
								<div class="col-6">
									<input class="form-check-input" type="checkbox" id="item1" required>
									<label class="form-check-label"> 안내 사항을 모두
										확인하였으며, 이에 동의합니다. </label>
								</div>

								<div class="col-1">
									<label for="inputPassword" class="form-label mb-0"><strong>비밀번호</strong></label>
								</div>
								<div class="col-3">
									<input type="password" id="user_pw" name="user_pw" 
										placeholder="현재 비밀번호를 입력해주세요." style="width:235px;" required>
								</div>

								<div class="col-2" style="margin-left:20px">

									
										<button class="unregisterBtn p-1 ms-2"
											onclick="withdrawalbtn()">탈퇴하기</button>
									
									<!-- <button type="submit" class="btn btn-dark ms-3" data-bs-toggle="modal" data-bs-target="#withdrawal">
									탈퇴하기
								</button> -->
								</div>
							</div>
						 <!-- </form>  -->
						

					</div>
					<!--End main contents card(박스)-->
				</form>
				

			</div>
			<!--회원탈퇴 contents box-->

		</div>
		<!--End Section-->

	</section>
	<!-- End main Section -->

	<!-- ======= End Hero Section ======= -->


	<!-- ============= 공통 footer + js ======================== -->
	<jsp:include page="../footer.jsp" flush="true" />
	<!-- ======================================================== -->
</body>

</html>