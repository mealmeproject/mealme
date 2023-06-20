var menuDetails = document.getElementById("menuDetails");
var summary = menuDetails.querySelector(".summary");

summary.addEventListener("click", function() {
    if (menuDetails.open) {
        // 닫을 때이미지를 변경하는 로직
        summary.style.backgroundImage = "url(../img/close.png')";
    } else{
        // 열 때 이미지를 변경하는 로직
        summary.style.backgroundImage = "url('../img/open.png')";
    }
});



// 메뉴바 누르면 색상변경
$(".reviewContainer-menu-list").on("click", function () {
    // console.log(this);
    let $tagA = $(this).children();
    // console.log($tagA);
    $(".reviewContainer-menu-list>a").removeClass("reviewContainer-active");
    $tagA.toggleClass("header-active");
});






var tabBtn = $("#tab-btn > ul > li");     //각각의 버튼을 변수에 저장
var tabCont = $("#tab-cont > div");       //각각의 콘텐츠를 변수에 저장

//컨텐츠 내용을 숨겨주세요!
tabCont.hide().eq(0).show();

tabBtn.click(function(){
    var target = $(this);         //버튼의 타겟(순서)을 변수에 저장
    var index = target.index();   //버튼의 순서를 변수에 저장
    tabBtn.removeClass("active");    //버튼의 클래스를 삭제
    target.addClass("active");       //타겟의 클래스를 추가
    tabCont.css("display","none");
    tabCont.eq(index).css("display","block");
});