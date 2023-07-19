// function handleRating(rating) {
//     var fillStars = document.querySelector(".star-ratings-fill");
//     var fillWidth = (rating / 5) * 100 + "%";
//     fillStars.style.width = fillWidth;
// };

function handleRating(rating) {
    var fillStars = rating.querySelector(".star-ratings-fill");
    var fillWidth = (rating.textContent / 5) * 100 + "%";
    fillStars.style.width = fillWidth;
};

var ratingsList = document.querySelectorAll(".rating");
ratingsList.forEach(function(ratingItem) {
    handleRating(ratingItem);
});

pageTage();
function pageTage(){
    $('.active1').css('background','none');
    $('.active1').css('color','rgb(255,139,38)');

}

console.log('연결됐다');

// ratingForEach();
//
// function ratingForEach(){
//     let  starRatings = document.querySelectorAll('.star_rating');
//
//     starRatings.forEach(function (s){
//             fillStars(s);
//         }
//     );
// }
//
//
// function fillStars(stars) {
//     let reviewGrade =stars.getAttribute('data-grade');
//
//     let star = $(stars).find('.star');
//
//     const filledStars = Math.floor(reviewGrade);
//     const decimalPart = reviewGrade % 1;
//
//     star.each(function (index) {
//         const star = $(this);
//
//         if (index < filledStars) {
//             star.addClass('filled');
//             star.text('★');
//         } else if (index === filledStars && decimalPart > 0) {
//             star.css('background-image', `linear-gradient(to right, gold ${decimalPart * 100}%, rgba(0, 0, 0, 0) ${decimalPart * 100}%)`);
//         } else {
//             star.removeClass('filled');
//         }
//     });
// }

