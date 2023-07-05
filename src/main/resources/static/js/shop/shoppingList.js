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





////////////////////////////////////////////////////////////////////////////////////




// function changeSort() {
//     let selectedValue = document.getElementById('selectItem').value;
//
//     // AJAX 요청을 보냅니다
//     $.ajax({
//         url: '/shop/shoppingList', // 데이터를 가져올 URL
//         type: 'GET', // 요청 방식 (GET, POST 등)
//         dataType: 'json', // 데이터 타입을 JSON으로 설정합니다
//         data: { selectedValue: selectedValue }, // 선택된 값도 함께 전달합니다.
//         success: function (response) {
//             // 요청이 성공했을 때 실행될 콜백 함수
//             // 서버에서 반환된 JSON 데이터를 이용하여 웹 페이지를 업데이트합니다.
//             processResponse(response);
//         },
//         error: function (xhr, status, error) {
//             // 요청이 실패했을 때 실행될 콜백 함수
//             console.log(xhr, status, error);
//         },
//     });
// }


//    // AJAX 요청을 보냅니다
//     $.ajax({
//         url: '/shop/shoppingList', // 데이터를 가져올 URL
//         type: 'GET', // 요청 방식 (GET, POST 등)
//         dataType: 'json', // 데이터 타입을 JSON으로 설정합니다
//         data: {selectedValue: selectedValue}, // 선택된 값도 함께 전달합니다.
//         success: function (response) {
//             // 요청이 성공했을 때 실행될 콜백 함수
//             // 서버에서 반환된 JSON 데이터를 이용하여 웹 페이지를 업데이트합니다.
//             processResponse(response);
//         },
//         error: function (xhr, status, error) {
//             // 요청이 실패했을 때 실행될 콜백 함수
//             console.log(xhr, status, error);
//         }
//     });
//
//     // JavaScript 코드
//     function processResponse(response) {
//         let map = response; // JSON 데이터를 직접 전달받아 사용
//
//         let productList = ''; // productList HTML 문자열 초기화
//
//         Object.values(map).forEach(products => {
//             products.forEach(p => {
//                 productList += `
//                     <li>
//                         <div class="shopping-list">
//                             <ul>
//                                 <li class="shoppingItem">
//                                     <div class="shoppingItemContainer">
//                                         <a href="/shop/shoppingDetail">
//                                             <img src="/img/이미지 예시.jpg" class="itemImg">
//                                             <div class="shoppingDesc">
//                                                 <div class="top">
//                                                     <p class="title">${p.productName}</p>
//                                                     <div class="price-container">
//                                                         <span class="price">${p.productPrice}원</span>
//                                                     </div>
//                                                     <button class="button dark">
//                                                         <div class="hand">
//                                                             <div class="thumb"></div>
//                                                         </div>
//                                                         <span>찜하기</span>
//                                                     </button>
//                                                 </div>
//                                             </div>
//                                         </a>
//                                     </div>
//                                 </li>
//                             </ul>
//                         </div>
//                     </li>
//                 `;
//             });
//         });
// console.log(productList);
//         // productList 요소를 선택하고, 업데이트된 HTML을 삽입합니다.
//         $('#productList').html(productList);
//
// };


// function allPrice() {
//     let selectedValue = document.getElementById('selectItem').value;
//
//     $.ajax({
//         url: '/shops/allList',
//         type: 'GET',
//         dataType: 'json',
//         data: { selectedValue: selectedValue },
//         success: function(response) {
//             // 서버에서 반환된 JSON 데이터를 이용하여 웹 페이지를 업데이트합니다.
//             allPrice(response);
//         },
//         error: function(xhr, status, error) {
//             console.log(xhr, status, error);
//         }
//     });
//     $('#selectItem').html(allPrice);
// }

shop.getAllList(makeList, 'allList');

$('#selectItem').on('change', function (){
    let endPoint = $(this).val();
    shop.getAllList(makeList, endPoint);
});

function makeList(result){

    let text = '';

    result.forEach(product => {
        text += `
            <li">
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
                                            <button class="button dark">
                                                <div class="hand">
                                                    <div class="thumb"></div>
                                                </div>
                                                <span>찜하기</span>
                                            </button>
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











