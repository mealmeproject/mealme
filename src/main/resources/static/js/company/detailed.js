let company = $('.address').text();

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch(company, function(result, status) {

    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    }
});

// let ar= [];
//
// ar.push();


function handleRating(rating) {
    var fillStars = document.querySelector(".star-ratings-fill");
    var fillWidth = (rating / 5) * 100 + "%";
    fillStars.style.width = fillWidth;
};






// 이 부분에서 별점 데이터를 가져와서 변수에 할당하도록 수정해야 합니다.
// const reviewGrade = 3; // 예시로 4.5라 가정


ratingForEach();

function ratingForEach(){
let  starRatings = document.querySelectorAll('.star_rating');

    starRatings.forEach(function (s){
        fillStars(s);
        }
    );
}


function fillStars(stars) {
    let reviewGrade =stars.getAttribute('data-grade');

    let star = $(stars).find('.star');

    const filledStars = Math.floor(reviewGrade);
    const decimalPart = reviewGrade % 1;

    star.each(function (index) {
        const star = $(this);

        if (index < filledStars) {
            star.addClass('filled');
            star.text('★');
        } else if (index === filledStars && decimalPart > 0) {
            star.css('background-image', `linear-gradient(to right, gold ${decimalPart * 100}%, rgba(0, 0, 0, 0) ${decimalPart * 100}%)`);
        } else {
            star.removeClass('filled');
        }
    });
}


pageTage();
function pageTage(){
    $('.active1').css('background','none');
    $('.active1').css('color','rgb(255,139,38)');

}
