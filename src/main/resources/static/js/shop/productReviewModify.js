
const drawStar = (target) => {
  $('.star span').css('width', `${target.value * 20}%`);
  console.log(`${target.value}`);
  
  const starValue = $('.star-value');
  starValue.val(target.value);
}

// 서머노트
$(document).ready(function () {
  $("#summernote").summernote({
    height: 300,
    lang: "ko-KR",
    toolbar: [
      ["style", ["style"]],
      ["font", ["bold", "italic", "underline", "clear"]],
      ["fontname", ["fontname"]],
      ["color", ["color"]],
      ['insert', ['link', 'picture', 'video']],
      ["para", ["ul", "ol", "paragraph"]],
    ],
    placeholder:
      "다른 고객님에게 도움이 되도록 자세한 평가를 부탁드려요.",
  });
});

starValue();
function starValue() {
  // DB에서 가져온 숫자
  const reviewGrade = $('.star-value').val();
  console.log(reviewGrade);

  const starSpan = $('.star span');
  const totalStars = starSpan.text().length;

  // DB에서 가져온 숫자에 해당하는 별들만 색을 gold로 변경
  // starSpan.html('★'.repeat(reviewGrade));

  const widthPercentage = (reviewGrade / totalStars) * 100;
  starSpan.css('width', `${widthPercentage}%`);
}

// 서머노트 꾸미기
$(document).ready(function() {
  summerNote();
});
function summerNote(){
  $('.note-modal-footer').css('height', `52px`);
}

$('.cancel').click(function() {
  window.location.href = '/shop/productReviewList';
});

console.log('수정페이지');