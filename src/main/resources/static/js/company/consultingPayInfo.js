function reviewLink(consultingRequestNumber){
    console.log("++++++");
    console.log(consultingRequestNumber);
    window.location.href = '/company/consultingReview?consultingRequestNumber=' + consultingRequestNumber;
}

// $(".order-history-table").on('click', '.review-button', function(){
//     console.log("%%%%%여기를 봐");
//     let RequestNumber = $(this).parents('.consulting-list').find('.consulting-requestNumber').val();
//     // $(this)는 review-button임 여기서 parents를 타고 해당 button태그를 빠져나와야 함
//     // 아에 tr로 빠져나와서 tr의 자식요소 중에서 찾는 게 쉬움 따라서 find('.consulting-requestNumber')사용
//     console.log(RequestNumber);
// });

// 페이징 이동 aTag색상 처리
pageTag();
function pageTag(){
    $('.active1').css('background', 'none');
    $('.active1').css('color', 'rgb(255, 139, 38)');
}