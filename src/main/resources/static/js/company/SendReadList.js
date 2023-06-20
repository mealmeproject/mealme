// "listAll" 체크박스 클릭 이벤트 처리
var listAllCheckbox = document.getElementById("listAll");
listAllCheckbox.addEventListener("change", function() {
    var checkboxes = document.querySelectorAll(
        "input[type='checkbox']:not(#listAll)"
    );
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = listAllCheckbox.checked;
    });
});






// 페이지 번호 클릭 시 이벤트 처리
document.addEventListener("DOMContentLoaded", function () {
    var pageLinks = document.querySelectorAll(".pagination a");

    pageLinks.forEach(function (link) {
        link.addEventListener("click", function (event) {
            event.preventDefault();

            // 선택된 페이지 표시 변경
            pageLinks.forEach(function (page) {
                page.classList.remove("active");
            });
            this.classList.add("active");

            // 페이지 이동 또는 데이터 로드 로직 추가
            // ...

            // 페이지 내용 업데이트
            updatePageContent();
        });
    });
});

// 페이지 내용 업데이트 함수
function updatePageContent() {
    // 페이지 내용 업데이트 로직 추가
    // ...
}



// JavaScript 코드
document.addEventListener('DOMContentLoaded', function() {
    // th 요소 선택
    var th = document.querySelector('.title');

    // 클릭 이벤트 리스너 추가
    th.addEventListener('click', function() {
        // 내용 변경
        th.innerHTML = '<a href="#">드래프트</a>';
    });
});








// Get the modal
const modal = document.getElementById("myModal");

// Get the <span> element that closes the modal
const closeBtn = document.getElementsByClassName("close")[0];

// Get all modal trigger links
const modalTriggers = document.getElementsByClassName("modal-trigger");

// When the user clicks on a modal trigger link, open the modal
for (let i = 0; i < modalTriggers.length; i++) {
    modalTriggers[i].onclick = function () {
        const title = modalTriggers[i].getAttribute("data-modal-title");
        document.getElementById("modal-title").textContent = title;
        modal.style.display = "block";
    };
}

// When the user clicks on <span> (x), close the modal
closeBtn.onclick = function () {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};









// 모달창 나가면 textarea가 초기화
const closeButton = document.querySelector(".close");

// Get the textarea element
const textarea = document.getElementById("SendBox");

// When the modal close button is clicked, reset the textarea value
closeButton.addEventListener("click", function() {
    textarea.value = "";
});





$(document).ready(function () {
    $("#summernote").summernote({
        height: 150,
        lang: "ko-KR",
        toolbar: [
            ["style", ["style"]],
            ["font", ["bold", "italic", "underline", "clear"]],
            ["fontname", ["fontname"]],
            ["color", ["color"]],
            ['insert', ['link', 'picture', 'video']],
            ["para", ["ul", "ol", "paragraph"]],
        ],
    });
});


