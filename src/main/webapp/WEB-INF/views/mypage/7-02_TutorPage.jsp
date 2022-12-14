<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="path" value="${pageContext.request.contextPath}" />


<!doctype html>
<html lang="ko">

<head>
  <!-- ======= HTML <head : CSS / Google Font / Favicons ======= -->
  <jsp:include page="../htmlHead.jsp" flush="true" />
  <!-- =======mypage 공통 CSS=================================== -->
  <link href="${path}/resources/css/mypage.css" rel="stylesheet">

  <title>기본 정보(튜터)</title>

  <script>
    $(function () {
      let modifyResult = '${_USERMODIFYRESULT_}';

      if (modifyResult === '회원정보 수정 성공') {
        Swal.fire({
          icon: 'success',
          text: '수정 완료!',
          confirmButtonText: '확인'
        })
      }
      
      //휴대전화 *처리
      let phone = '${_USERINFO_.user_phone}';
      
      let front = phone.substr(0, 3);
      let hiden = phone.substr(3, 4);
      let star = hiden.replaceAll(hiden, '****');
      let back = phone.substr(7, 4);

      let hidenPhone = front + '-' + star + '-' + back;
      
      let user_phone = document.querySelector("#user_phone");
      user_phone.value = hidenPhone;
    })// jq
  </script>

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
              class="list-group-item list-group-item-action px-lg-4 fw-bold">기본
              정보</a></c:if>
          <c:if test="${__LOGIN_USER__.user_group eq 'Tutor'}"><a href="/mypage/tutorPage"
              class="list-group-item list-group-item-action px-lg-4 fw-bold">기본
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

          <a href="/mypage/unregister" class="list-group-item list-group-item-action px-lg-4">회원
            탈퇴</a>
        </div>
      </div><!-- End main nav -->

      <div id="contents" class="col-lg-9">

        <h3 class="fw-bold">기본정보</h3>

        <!-- FROM -->
        <div class="container card p-4 bg-card">
          <form action="/mypage/tutorPageModify" method="post" id="userInfo" enctype="multipart/form-data" novalidate>
            <div class="row">

              <div class="col-4">
                <input type="file" name="file_name" id="file_name" class="d-none file_name" accept="image/*">
                <div id="profileImg" class="ratio ratio-1x1 rounded-circle profileImg">
                  <c:if test="${_USER_PROFILE_ eq 'false'}"> <img src="${path}/resources/img/profile.png"> </c:if>
                  <c:if test="${_USER_PROFILE_ eq 'true'}"> <img
                      src="<spring:url value='/profile/${_USERINFO_.user_nick}_profile.png'/>" />
                  </c:if>
                </div>
              </div>

              <div class="col-4">
                <input type="hidden" name="user_email" value="${_USERINFO_.user_email}">
                <label for="user_nick">닉네임</label>
                <input type="text" class="form-control mb-3" id="user_nick" name="user_nick" readonly
                  value="${_USERINFO_.user_nick}">
                <label for="user_name">이름</label>
                <input type="text" class="form-control mb-3" id="user_name" name="user_name" readonly
                  value="${_USERINFO_.user_name}">
                <label for="user_gender">성별</label>
                <input type="text" class="form-control mb-3" id="user_gender" name="user_gender" readonly
                  value="${_USERINFO_.user_gender}">
                <label for="user_phone">휴대전화</label>
                <input type="tel" class="form-control mb-3" id="user_phone" name="user_phone" readonly
                  value="${_USERINFO_.user_phone}">

              </div>

              <div class="col-4" style="position: relative;">

                <label for="user_pw">현재 비밀번호</label>
                <input type="password" class="form-control" id="user_pw" name="user_pw" required
                  placeholder="비밀번호를 입력해 주세요">
                <p id=check_1 class="text-danger invisible">비밀번호가 일치하지 않습니다.</p>

                <label for="user_newPw">비밀번호 변경</label>
                <input type="password" class="form-control mb-3" id="user_newPw" name="user_newPw" required
                  placeholder="새로운 비밀번호를 입력해 주세요">
                <label for="user_newPw2">비밀번호 확인</label>
                <input type="password" class="form-control" id="user_newPw2" name="user_newPw2" required>
                <p id=check_2 class="text-danger invisible">비밀번호가 일치하지 않습니다.</p>

                <button type="button" class="btn bg-blue" style="position: absolute; right: 20px; bottom: 5px;"
                  onclick="pwCheck()">
                  저장</button>
              </div>
            </div>
          </form>

        </div>
        <!--End main contents card(박스)-->

        <!-- TO -->

      </div><!-- End main contents -->

    </div><!-- End Section -->

  </section><!-- End main Section -->



  <!-- ======= footer + js  ======= -->
  <jsp:include page="../footer.jsp" flush="true" />
  <!-- ========유효성 체크========= -->
  <script src="${path}/resources/js/7-01_mypage.js"></script>
</body>

</html>