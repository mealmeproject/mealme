
// function starValue() {
//   // DB에서 가져온 숫자
//   const reviewGrade = 2;
//
//   const starSpan = $('.star span');
//   const totalStars = starSpan.text().length;
//
//   // DB에서 가져온 숫자에 해당하는 별들만 색을 gold로 변경
//   // starSpan.html('★'.repeat(reviewGrade));
//
//   const widthPercentage = (reviewGrade / totalStars) * 100;
//   starSpan.css('width', `${widthPercentage}%`);
// }

// $(document).ready(function() {
//   starValue();
// });

// $(".review-sub").on('click', function(){
//   if ($('.review-wrap').css('display') === 'none') {
//     $('.review-wrap').css('display', 'block');
//   } else if ($('.review-wrap').css('display') === 'block') {
//     $('.review-wrap').css('display', 'none');
//   }
// });

$(".all-wrap").on('click', '.review-sub', function(){
  let $currentReviewWrap = $(this).siblings('.review-wrap');
  // 클릭한 .review-sub 요소와 연결된 .review-wrap 요소만 토글하여 보이도록 처리합니다.
  if ($currentReviewWrap.css('display') === 'none') {
    $currentReviewWrap.css('display', 'block');
  } else {
    $currentReviewWrap.css('display', 'none');
  }
});

showList();
function showList() {

  console.log("aaaaaaaaaa");

  $.ajax({
    url: '/company/consultingReviewListData',
    method: 'GET',
    dataType: 'json',
    success: function (result) {
      console.log(result);
      console.log(result.consultingReviewList);
      makeListHtml(result)
    },
    error: function (xhr, status, error) {
      console.log('에러 발생:', error);
    }
  });
}




function makeListHtml(result){
  let text = '';
  let reviewList = result.consultingReviewList;

  for(let i=0; i<reviewList.length; i++){
    text += `
      <div class="review-consulting">
                <input type="hidden" class="listReviewNumber" value="${reviewList[i].reviewNumber}">
                
                    <div class="review-sub">
                    `;

                        //  <div class="company-file">
                        //     <img src="${reviewList[i].fileUploadPath}" alt="기업이미지">
                        // </div>
    text += `
                        <!-- 상품일 경우 상품사진, 컨설팅일 경우 회사 대표 사진 -->
                        <div class="ranking-wrap">
                            <div class="review-date">${reviewList[i].consultingRequestDate}</div>
                            <div class="consulting-comment">${reviewList[i].consultingComment}
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
  window.location.href = '/company/consultingReviewModify?reviewNumber=' + reviewNumber;
};

function remove(event){
  console.log('------------');
  let reviewNumber = $(event.target).data('remove');
  window.location.href = '/company/consultingReviewRemove?reviewNumber=' + reviewNumber;
};



















