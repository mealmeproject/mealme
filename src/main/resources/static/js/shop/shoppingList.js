let slideContainer = document.querySelector('.main1-slide-ul');

let slideImg = document.querySelectorAll('.main1-slide-ul li');
let currentIdx = 0;
let slideCount = slideImg.length;
let prev = document.querySelector('.prev'); // 이전 버튼
let next = document.querySelector('.next'); // 다음 버튼
let slideWidth = 1200; // 슬라이드 이미지 넓이
let slideMargin = 0; // 슬라이드 끼리의 마진값


makeClone();
initFunction();

function makeClone() {
    let cloneSlideFirst = slideImg[0].cloneNode(true);
    let cloneSlideLast = slideImg[slideCount - 1].cloneNode(true);
    slideContainer.appendChild(cloneSlideFirst);
    slideContainer.insertBefore(cloneSlideLast, slideImg[0]);
}

function initFunction() {
    slideContainer.style.width = (slideWidth + slideMargin) * (slideCount + 2) + 'px';
    slideContainer.style.left = -(slideWidth + slideMargin) + 'px';
}



next.addEventListener('click', nextProcess);

setInterval(nextProcess, 3000);

function nextProcess(){
    //다음 버튼 눌렀을때
    if (currentIdx <= slideCount - 1) {
        //슬라이드이동
        slideContainer.style.left = -(currentIdx + 2) * (slideWidth + slideMargin) + 'px';
        slideContainer.style.transition = `${0.5}s ease-out`; //이동 속도
    }
    if (currentIdx === slideCount - 1) {
        //마지막 슬라이드 일때
        setTimeout(function () {
            //0.5초동안 복사한 첫번째 이미지에서, 진짜 첫번째 위치로 이동
            slideContainer.style.left = -(slideWidth + slideMargin) + 'px';
            slideContainer.style.transition = `${0}s ease-out`;
        }, 500);
        currentIdx = -1;
    }
    currentIdx += 1;
}
prev.addEventListener('click', function () {
    //이전 버튼 눌렀을때
    console.log(currentIdx);
    if (currentIdx >= 0) {
        slideContainer.style.left = -currentIdx * (slideWidth + slideMargin) + 'px';
        slideContainer.style.transition = `${0.5}s ease-out`;
    }
    if (currentIdx === 0) {
        setTimeout(function () {
            slideContainer.style.left = -slideCount * (slideWidth + slideMargin) + 'px';
            slideContainer.style.transition = `${0}s ease-out`;
        }, 500);
        currentIdx = slideCount;
    }
    currentIdx -= 1;
});

//
// let best = document.getElementById('best');
// function mouseover(){
//     best.setAttribute("src","../img/닭가슴살오버.png");
// }
//
// function mouseleave(){
//     best.setAttribute("src","../../img/닭가슴살.png");
// }
//
// let best2 = document.getElementById('best2');
// function mouseover2(){
//     best2.setAttribute("src","../../img/샐러드오버.png");
// }
//
// function mouseleave2(){
//     best2.setAttribute("src","../../img/샐러드,과일.png");
// }
//
//
// let best3 = document.getElementById('best3');
// function mouseover3(){
//     best3.setAttribute("src","../img/건강식품오버.png");
// }
//
// function mouseleave3(){
//     best3.setAttribute("src","../img/건강식품.png");
// }
//
//
// let best4 = document.getElementById('best4');
// function mouseover4(){
//     best4.setAttribute("src","../img/음료오버.png");
// }
//
// function mouseleave4(){
//     best4.setAttribute("src","../img/음료,차,프로틴.png");
// }


document.querySelectorAll('.button').forEach(button => {

    button.addEventListener('click', e => {
        button.classList.toggle('liked');
        if(button.classList.contains('liked')) {
            gsap.fromTo(button, {
                '--hand-rotate': 8
            }, {
                ease: 'none',
                keyframes: [{
                    '--hand-rotate': -45,
                    duration: .16,
                    ease: 'none'
                }, {
                    '--hand-rotate': 15,
                    duration: .12,
                    ease: 'none'
                }, {
                    '--hand-rotate': 0,
                    duration: .2,
                    ease: 'none',
                    clearProps: true
                }]
            });
        }
    })

});


function toggleFoodList() {
    const foodList = document.getElementById('food-list');
    foodList.classList.toggle('show');
}



// 각 상품 별점에 대한 이벤트 처리
const ratingElements = document.querySelectorAll('.rating');
ratingElements.forEach(ratingElement => {
    const stars = ratingElement.querySelectorAll('.star');

    // 초기 평가 표시
    const initialRating = ratingElement.dataset.rating;
    setRating(initialRating);

    // 클릭 이벤트 처리
    stars.forEach(star => {
        star.addEventListener('click', () => {
            const ratingValue = star.dataset.value;
            setRating(ratingValue);
        });
    });

    // 별점 설정 함수
    function setRating(ratingValue) {
        stars.forEach(star => {
            if (star.dataset.value <= ratingValue) {
                star.classList.add('selected');
            } else {
                star.classList.remove('selected');
            }
        });
    }
});



$(document).ready(function() {
    // 음식 카테고리 클릭 이벤트
    $('#food').click(function() {
        var category = '음식';
        sendAjaxRequest(category);
    });

    // 보충제 카테고리 클릭 이벤트
    $('#supplement').click(function() {
        var category = '보충제';
        sendAjaxRequest(category);
    });

    // 영양제 카테고리 클릭 이벤트
    $('#nutrients').click(function() {
        var category = '영양제';
        sendAjaxRequest(category);
    });

    // Ajax 요청 함수
    function sendAjaxRequest(category) {
        $.ajax({
            type: 'GET',
            url: '/shop/shoppingList',
            data: { category: category },
            contentType: 'application/json',
            success: function(data) {
                $('#product_list').html(data);
            },
            error: function(request, status, error) {
                alert(error);
            }
        });
    }
});

$(document).ready(function() {
    // select 요소의 변경 이벤트를 감지합니다.
    $('#selectItem').change(function() {
        var selectedValue = $(this).val(); // 선택된 옵션의 값
        changeSort(selectedValue); // changeSort 함수에 선택된 값을 전달합니다.
    });
});

function changeSort(selectedValue) {
    // AJAX 요청을 보냅니다
    $.ajax({
        url: '/shop/shoppingList',
        type: 'GET',
        dataType: 'json',
        data:JSON.stringify(selectedValue),
        success: function(response) {

            $('#productList').json(response);
        },
        error: function(xhr, status, error) {
            // 요청이 실패했을 때 실행될 콜백 함수
            console.log(xhr, status, error);
        }
    });
}

function
