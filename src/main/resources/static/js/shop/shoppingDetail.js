//
// document.querySelectorAll('.button').forEach(button => {
//
//     button.addEventListener('click', e => {
//         button.classList.toggle('liked');
//         if(button.classList.contains('liked')) {
//             gsap.fromTo(button, {
//                 '--hand-rotate': 8
//             }, {
//                 ease: 'none',
//                 keyframes: [{
//                     '--hand-rotate': -45,
//                     duration: .16,
//                     ease: 'none'
//                 }, {
//                     '--hand-rotate': 15,
//                     duration: .12,
//                     ease: 'none'
//                 }, {
//                     '--hand-rotate': 0,
//                     duration: .2,
//                     ease: 'none',
//                     clearProps: true
//                 }]
//             });
//         }
//     })
//
// });




// const starRatingContainer = document.querySelector('#starRating .rating');
// const averageRating = parseInt(starRatingContainer.dataset.rating);
//
// let starsHtml = '';
// for (let i = 0; i < 5; i++) {
//     if (i < averageRating) {
//         starsHtml += '<span class="star filled">&#9733;</span>';
//     } else {
//         starsHtml += '<span class="star">&#9733;</span>';
//     }
// }
//
// starRatingContainer.innerHTML = starsHtml;

//****************shoppingCart*************///

// function addToCart() {
//     const productNumber = document.getElementById("productNumber").value;
//     const userNumber = document.getElementById("userNumber").value;
// }
//
//
//
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


$('.rating').each(function() {
    fillStars(this);
});

function fillStars(stars) {
    let rating = $(stars).data('grade');
    let star = $(stars).find('.star');

    const filledStars = Math.floor(rating);
    const decimalPart = rating % 1;

    star.each(function(index) {
        const star = $(this);

        if (index < filledStars) {
            star.addClass('filled');
            star.text('★');
        }  else if (index === filledStars && decimalPart > 0) {
        const percentage = decimalPart * 100;
        const gradient = `linear-gradient(to right, gold ${percentage}%, transparent ${percentage}%)`;
        star.css('background-image', gradient);
        star.css('background-clip', 'text');
        star.css('-webkit-background-clip', 'text');
        star.css('color', 'transparent');
        star.text('★');
        } else {
            star.removeClass('filled');
            star.text('☆');
        }
    });
}
