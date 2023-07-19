import * as shop from '../module/shopModule.js';

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



// 찜하기
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






// shop.getAllList(makeList, 'allList');

$('#selectItem').on('change', function (){
    let endPoint = $(this).val();
    shop.getAllList(makeList, endPoint);
});

function makeList(result){

    let text = '';
    result.forEach(product => {
        text += `
            <li>
                <div class="shopping-list">
                    <ul>
                        <li class="shoppingItem">
                            <div class="shoppingItemContainer">
                                <a href="#" onclick="redirectToShoppingDetail(${product.productNumber})">
                                    <img src="/img/이미지 예시.jpg" class="itemImg">
                                    </a>
                                    <div class="shoppingDesc">
                                        <div class="top">
                                            <p class="title">${product.productName}</p>
                                            <div class="price-container">
                                                <span class="price">${product.productPrice}원</span>
                                            </div>
                                            <div id="starRating">
                                                <div class="product-item">
                                                    <div class="rating" data-rating=${product.averageRating}">
                                                    `




        for(let i=0; i<5; i++){
            if(i < product.averageRating){
                text += `<span class="star filled">&#9733;</span>`;
            }else{
                text += `<span class="star">&#9733;</span>`;
            }
        }

        text +=                                     `</div>
                                                </div>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </li>
        `;
    })

    $('#productList > ul').html(text);
}





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