

$(".all-wrap").on('click', '.review-sub', function(){
  let $currentReviewWrap = $(this).siblings('.review-wrap');
  if ($currentReviewWrap.css('display') === 'none') {
    $currentReviewWrap.css('display', 'block');
  } else {
    $currentReviewWrap.css('display', 'none');
  }
});

showList();
function showList() {

  console.log("hello!!");

  $.ajax({
    url: '/shop/productReviewListData',
    method: 'GET',
    dataType: 'json',
    success: function (result) {
      console.log(result);
      console.log(result.productReviewList);
      makeListHtml(result)
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

                        //  <div class="company-file">
                        //     <img src="${reviewList[i].fileUploadPath}" alt="기업이미지">
                        // </div>
    text += `
                        <!-- 상품일 경우 상품사진, 컨설팅일 경우 회사 대표 사진 -->
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
  $('.all-wrap').append(text);
  starValueShow();
}


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












