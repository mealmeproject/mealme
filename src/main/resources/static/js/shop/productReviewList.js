$(function(){
  console.log('마이페이지 리뷰 페이지진입');
  let page = 1;
  showList(page);
})

$(".all-wrap").on('click', '.review-sub', function(){
  let $currentReviewWrap = $(this).siblings('.review-wrap');
  if ($currentReviewWrap.css('display') === 'none') {
    $currentReviewWrap.css('display', 'block');
  } else {
    $currentReviewWrap.css('display', 'none');
  }
});


function showList(page) {

  console.log("hello!!");

  $.ajax({
    url: '/shops/productReviewListData',
    method: 'GET',
    data : {"page": page},
    dataType: 'json',
    success: function (result) {
      console.log(result);
      console.log(result.productReviewList);
      console.log(result.pageVo);
      makeListHtml(result);
    },
    error: function (xhr, status, error) {
      console.log('에러 발생:', error);
    }
  });
}




function makeListHtml(result){
  let text = '';
  let reviewList = result.productReviewList;

  for(let i=0; i<reviewList.length; i++){
    text += `
      <div class="review-product">
                <input type="hidden" class="listReviewNumber" value="${reviewList[i].reviewNumber}">

                    <div class="review-sub">
                    `;

    text +=             `<div class="company-file">
                        <img src="${reviewList[i].fileUploadPath}" alt="상품이미지">
                        </div>`;
    text += `
                        <div class="ranking-wrap">
                            <div class="review-date">${reviewList[i].orderDate}</div>
                            <div class="product-name-wrap">
                            <div class="product-name">${reviewList[i].productName}
                            </div>
                            </div>
                            <!-- 상품일 경우 상품제목, 컨설팅일 경우  컨설팅 제목 -->
                            <div class="star-wrap">
                              <span class="star">
                                ★★★★★
                                <span class="star-span" data-star="${reviewList[i].reviewGrade}">★★★★★</span>
                                <input value="1" step="1" min="0" max="5">
                              </span>
                                <input type="hidden" name="reviewGrade" class="star-value" value="">
                                <!-- value에 DB에서 받아오는 값 넣어주기 -->
                            </div>
                            <!-- DB에서 DB의 값에 따라 별 채우기 -->

                        </div>


                    </div>
                    <div class="review-wrap">
                        <div class="summernote-box">
                            <div class="summernote-text">
                                ${reviewList[i].reviewContent}
                            </div>
                        </div>

                        <div class="button-wrap">
                            <button class="remove" data-remove="${reviewList[i].reviewNumber}" onclick="remove(event)">삭제</button>
                            <button class="modify" data-modify="${reviewList[i].reviewNumber}" onclick="modify(event)">수정</button>
                        </div>
                    </div>
                </div>
    `;
  }
  $('.list-wrap').html(text);
  starValueShow();

  // 리뷰 비동기 페이징처리하기
  let pageNum = '';
  let pageList = result.pageVo;

  console.log('페이지 리스트');
  console.log(pageList);

  pageNum += `<a class="arrow pprev" data-value="1"></a>`;
  if (pageList.prev == true){
    pageNum += `<a class="arrow prev" value="${pageList.startPage - 1}"></a>`;}

  for (let i = pageList.startPage; i <= pageList.endPage; i++) {
    if (pageList.criteriaCompany.page == i) {
      pageNum += `<a><div class="page-num active1">${i}</div></a>`;
    }
    else {
      pageNum += `<a><div class="page-num">${i}</div></a>`;
    }
  }

  if(pageList.next == true){pageNum += `<a class="arrow next" value="${pageList.endPage + 1}"></a>`;}
  pageNum += `<a class="arrow nnext" data-value="${pageList.realEnd}"></a>`;

  $('.page_nation').html(pageNum);

}


// 페이지 버튼 클릭시 이동처리
$('.page_nation').on("click", ".page-num", function (){
  page = $(this).text();
  showList(page);
});

$('.page_nation').on("click", ".prev", function (){
  page = $(this).val();
  showList(page);
});

$('.page_nation').on("click", ".pprev", function (){
  page = $(this).data('value');
  console.log(page);
  showList(page);
});

$('.page_nation').on("click", ".next", function (){
  page = $(this).val();
  showList(page);
});

$('.page_nation').on("click", ".nnext", function (){
  page = $(this).data('value');
  console.log(page);
  showList(page);
});

// 평가 값 채우기
function starValueShow() {
  let $span = $('.star-span');
  for(let i=0; i<$span.length; i++){
    let reviewGrade = $span.eq(i).data('star');
    let widthPercentage = (reviewGrade / 5) * 100;
    $span.eq(i).css('width', `${widthPercentage}%`);
  }
}

function modify(event){
  console.log("++++++");
  let reviewNumber = $(event.target).data('modify');
  window.location.href = '/shop/productReviewModify?reviewNumber=' + reviewNumber;
};

function remove(event){
  console.log('------------');
  let reviewNumber = $(event.target).data('remove');
  window.location.href = '/shop/productReviewRemove?reviewNumber=' + reviewNumber;
};

changeTab();
function changeTab(){
  $('.company-tab').css('background', 'none');
  $('.company-tab').css('color', 'rgb(255, 139, 38)');
  $('.shop-tab').css('background', 'rgb(255, 139, 38)');
  $('.shop-tab').css('color', '#fff');
}












