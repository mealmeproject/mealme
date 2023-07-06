function starValue() {
  // DB에서 가져온 숫자
  const reviewGrade = 2;

  const starSpan = $('.star span');
  const totalStars = starSpan.text().length;

  // DB에서 가져온 숫자에 해당하는 별들만 색을 gold로 변경
  // starSpan.html('★'.repeat(reviewGrade));

  const widthPercentage = (reviewGrade / totalStars) * 100;
  starSpan.css('width', `${widthPercentage}%`);
}

$(document).ready(function() {
  starValue();
});



