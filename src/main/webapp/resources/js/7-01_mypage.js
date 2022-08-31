/* ============================= */
/*      기본정보(7-01,7-02)      */   
/* ============================= */

/* ============================= */
/*      파일첨부 미리보기         */   
/* ============================= */
const realFile = document.querySelector(".file_name");
const profileImg = document.querySelector(".profileImg");

// 첨부파일 가져오는 function
function getProfileImgFile(e){
    const uploadFiles = [];
    const files = e.currentTarget.files;

    [...files].forEach(file => {

        //두개 이상 올리면 return
        if([...files].length >=2){
            return;
        }//if

        //이미지 파일이 아니면 경고창뜨고 return처리
        if (!file.type.match("image/.*")){
            Swal.fire({
                icon: 'warning',
                title: '이미지 파일을 업로드해 주세요.'
            })
            return;
        }// if

        uploadFiles.push(file);

        const reader = new FileReader();
        reader.onload = e => {
            const prieview = createElement(e, file);
            profileImg.appendChild(prieview);
        };

        reader.readAsDataURL(file);

    });

}// getProfileImgFile

//<img>태그 생성 funtion
function createElement(e, file){
    const img = document.createElement('img');
    img.setAttribute('src', e.target.result);
    img.setAttribute('date-file', file.name);
    
    profileImg.appendChild(img);
}// createElement()

// 프로필 사진 클릭 = input.file 클릭
profileImg.addEventListener('click', () => realFile.click());

//사진 변경
realFile.addEventListener('change', getProfileImgFile)


/* ============================= */
/*    비밀번호 변경 유효성        */   
/* ============================= */

function check() {
    let userForm = document.querySelector("#userInfo")

    // const datapw = document.querySelector("#hiddenpw").value;
    // const pass0 = document.querySelector("#user_Oldpw").value;    //현재 비밀번호
    const pwcheck = document.querySelector("#pwcheck").value;
    const pass1 = document.querySelector("#user_newPw").value;    //새로운 비밀번호
    const pass2 = document.querySelector("#user_pw").value;       //새로운 비밀번호 확인(이값으로 파라미터 값 제출)

    const check1 = document.querySelector("#check_1").classList;
    const check2 = document.querySelector("#check_2").classList;

    //현재 비밀번호 유효성
    // if (pass0 === datapw) {
    //     check1.add("invisible");
    //     check1.remove("visible");
    // } else {
    //     check1.add("visible");
    //     check1.remove("invisible");
    //     // 틀리면 제출 막음
    //     return false;
    // }// if
    if (pwcheck === 'true') {
        check1.add("invisible");
        check1.remove("visible");
    } else if (pwcheck === 'false') {
        check1.add("visible");
        check1.remove("invisible");
        // 틀리면 제출 막음
        return false;
    }// if


    // 변경할비밀번호와 비밀번호 확인 유효성
    if ((pass1 === pass2) && (pass1 !== '') && (pass2 !== '')) {
        check2.add("invisible");
        check2.remove("visible");
    } else {
        check2.add("visible");
        check2.remove("invisible");

        // 틀리면 제출 막음
        return false;
    }// check()
    
    // 저장 버튼 누르면 확인
    Swal.fire({
        icon: 'warning',
        title: '기본정보 수정',
        showDenyButton: true,
        confirmButtonText: '확인',
        denyButtonText: `취소`,
        text: '수정하시겠습니까?',
    }).then((result) => {
        // 저장확인 누르면 저장완료 창!
        if (result.isConfirmed) {
            Swal.fire({
                icon: 'success',
                text: '수정 완료!', 
                confirmButtonText: '확인'
            }).then(function(e) {
                userForm.submit();
            })// Swal.fire-then
        }// if

    })// Swal.fire-then

}//check()