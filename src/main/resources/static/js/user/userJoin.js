$(function () {
    //input을 datepicker로 선언
    $("#datepicker").datepicker({
        dateFormat: "yy-mm-dd", //달력 날짜 형태
        showOtherMonths: true, //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        showMonthAfterYear: true, // 월- 년 순서가아닌 년도 - 월 순서
        changeYear: true, //option값 년 선택 가능
        changeMonth: true, //option값  월 선택 가능
        showOn: "both", //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
        buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif", //버튼 이미지 경로
        buttonImageOnly: true, //버튼 이미지만 깔끔하게 보이게함
        buttonText: "선택", //버튼 호버 텍스트
        yearSuffix: "년", //달력의 년도 부분 뒤 텍스트
        monthNamesShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"], //달력의 월 부분 텍스트
        monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"], //달력의 월 부분 Tooltip
        dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"], //달력의 요일 텍스트
        dayNames: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"], //달력의 요일 Tooltip
        minDate: "-100Y", //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        maxDate: "+0D", //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
    });

    //초기값을 오늘 날짜로 설정해줘야 합니다.
    $("#datepicker").datepicker("setDate", "today"); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
});

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ""; // 주소 변수
            var extraAddr = ""; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === "R") {
                // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else {
                // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === "R") {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== "" && data.apartment === "Y") {
                    extraAddr += extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== "") {
                    extraAddr = " (" + extraAddr + ")";
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;
            } else {
                document.getElementById("extraAddress").value = "";
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("adressNumber").value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        },
    }).open();
}

var userIdInput = document.getElementById("userId");
$('.id-check').on("click", function() {
    var userId = userIdInput.value;
    if (userId.trim() !== "") {
        checkDuplicateId(userId);
    }
});

// 아이디 중복체크
function checkDuplicateId(userId) {
    // const data = { userId: userId };
    // alert(userId)
    $.ajax({
        url: "/user/checkUserId",
        type: "post",
        // data: JSON.stringify(data),
        data: { userId: userId },
        success: function(response) {
            if (response.checkId) {
                console.log("중복된 아이디 입니다.");
                document.getElementById("duplicateIdError").style.display = "block";
                document.getElementById("duplicateIdPass").style.display = "none";
                alert("중복된 아이디입니다. ");
            } else {
                console.log("사용 가능한 아이디 입니다. ");
                document.getElementById("duplicateIdError").style.display = "none";
                document.getElementById("duplicateIdPass").style.display = "block";
                alert("사용 가능한 아이디입니다. ");
            }
        },
        error: function(xhr, status, error) {
            console.log("아이디 중복검사 에러");
        }
    });
}

// 비밀번호 일치
var passwordInput = document.getElementById("userPassword");
var confirmPasswordInput = document.getElementById("confirmPassword");
var passwordMismatchError = document.getElementById("passwordMismatchError");
var passwordMismatchPass = document.getElementById("passwordMismatchPass");
var passwordCheck1 = document.getElementById("passwordCheck1");
var passwordCheck2 = document.getElementById("passwordCheck2");
var passwordCheck3 = document.getElementById("passwordCheck3");

passwordInput.addEventListener("input", checkPasswordMatch);

confirmPasswordInput.addEventListener("input", checkPasswordMatch);

function checkPasswordMatch() {
    var password = passwordInput.value;
    var confirmPassword = confirmPasswordInput.value;

    var pw = $("#userPassword").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if (password === confirmPassword){
        passwordMismatchError.style.display = "none";
        passwordMismatchPass.style.display = "block";
    } else {
        passwordMismatchError.style.display = "block";
        passwordMismatchPass.style.display = "none";
    }
    if(pw.length < 8 || pw.length > 20){
        passwordCheck1.style.display = "block";
    }else if(pw.search(/\s/) != -1){
        passwordCheck1.style.display = "none";
        passwordCheck2.style.display = "block";
    }else if(num < 0 || eng < 0 || spe < 0 ){
        passwordCheck2.style.display = "none";
        passwordCheck1.style.display = "none";
        passwordCheck3.style.display = "block";
    }else {
        passwordCheck2.style.display = "none";
        passwordCheck1.style.display = "none";
        passwordCheck3.style.display = "none";
        console.log("통과");
        return true;
    }
}


