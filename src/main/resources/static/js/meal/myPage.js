const modal = document.querySelector('.modal');
const mealImgBoxes = document.querySelectorAll(".meal-img-box");


let page = 1;

function showMeal(map){
    console.log(map);

    let text = '';

    //
        text += `
                            <div class="modal-header">
                                <h2>${map.mealTime}</h2>
                                <hr class="service-name-hr" />
                            </div>
                            <div class="modal-main">
                                <!-- 이미지 -->
                                <div class="day-img-box slider">
                                    <input type="radio" name="slide" id="slide1" checked>
                                    <input type="radio" name="slide" id="slide2">
                                    <input type="radio" name="slide" id="slide3">
                                    <input type="radio" name="slide" id="slide4">
                                    <ul id="imgholder" class="imgs">
                                        <li><img src="/img/sample1.jpg"></li>
                                        <li><img src="/img/sample2.jpg"></li>
                                        <li><img src="/img/sample3.jpg"></li>
                                        <li><img src="/img/sample4.jpg"></li>
                                    </ul>
                                    <div class="bullets">
                                        <label for="slide1">&nbsp;</label>
                                        <label for="slide2">&nbsp;</label>
                                        <label for="slide3">&nbsp;</label>
                                        <label for="slide4">&nbsp;</label>
                                    </div>
                                </div>
                                <!-- 이미지 끝-->
                                <div class="day-detail">
                                    <div class="day-detail1">
                 `;
        if(map.mealCodeNumber == 10){
            text += `                                        
                          <div class="meal-time meal-time-checked"><span class="material-symbols-outlined meal-icon-span ">wb_twilight</span>아침</div>
`;
        }else if(map.mealCodeNumber == 20){
            text += `    
                          <div class="meal-time meal-time-checked"><span class="material-symbols-outlined meal-icon-span">sunny</span>점심</div>

            `;
        }else if(map.mealCodeNumber == 30){
            text += `
                          <div class="meal-time meal-time-checked"><span class="material-symbols-outlined meal-icon-span">dark_mode</span>저녁</div>
            `;
        }else if(map.mealCodeNumber == 40){
            text += `
                          <div class="meal-time meal-time-checked"><span class="material-symbols-outlined meal-icon-span">icecream</span>간식</div>
            `;
        };
        text += `
                                    </div>
                                    <div class="day-detail2">
                `;
        if (Array.isArray(map.foodList)) {
            map.foodList.forEach(f => {
                text += `
                                       <div class="day-food-detail">
                                         <div class="day-food-name">${f.foodName}</div>
                                         <div class="day-food-weight">${f.foodWeight}g</div>
                                         <div class="day-food-kcal">${f.foodKcal}kcal</div>
                                       </div>
                                     `;
                                         });
                                     };
        text += `
                                    <div class="day-detail3">
                                        총 칼로리 : &nbsp<div class="detail3-kcal">${map.mealTotalKcal}kcal</div>
                                    </div>
                                    <div class="day-detail4">
                                        <div class="modify-btn">수정</div>
                                        <div class="delete-btn">삭제</div>
                                    </div>
                                </div>
                            </div>
        `;
    //

    $('.modal-container').html(text);
}

mealImgBoxes.forEach(function (box) {
    box.addEventListener("click", openModal);
});

function openModal(event) {
    event.stopPropagation();
    const boardNumber = event.currentTarget.dataset.boardNumber;
    console.log("boardNumber는 이거 ! " + boardNumber);
    mealRead(boardNumber, showMeal, showError);
    modal.setAttribute("style", "display: block;");
}


const closeModalButton = document.getElementById("close-modal");

closeModalButton.addEventListener('click', function(event) {
    event.stopPropagation();
    console.log("닫는다!");
    modal.setAttribute("style", "display: none;");
});


// 식단 리스트 가져오기

// //무한 스크롤 페이징
// $(window).on('scroll', function(){
//     console.log($(window).scrollTop());
// //  $(window).scrollTop() : 현재 브라우저의 스크롤 위치를 의미한다.
//     console.log(`document : ${$(document).height()}`)
// //    $(document).height() : 문서 전체의 높이를 의미한다.
//     console.log(`window : ${$(window).height()}`)
// //    $(window).height() : 브라우저 화면의 높이를 의미한다.
//
//     //현재 브라우저 스크롤의 위치 == 문서 높이 - 화면 높이      -> 문서 마지막에 스크롤이 도작했을 때
//     if(Math.round($(window).scrollTop()) == $(document).height() - $(window).height()){
//         console.log(++page);
//         // getListPage({userNumber : userNumber, page : page}, appendText, showError);
//     }
// });


function showError(a, b, c){
    console.error(c);
}

function mealRead(boardNumber, callback, error){
    console.log("mealRead 함수 실행 !");
    console.log(boardNumber + "boardNumber");
    $.ajax({
        type: "get",
        url : `/meal/myPage/${boardNumber}`,
        dataType: 'json',
        // data : {
        //     "boardNumber" : boardNumber
        // },
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : error
    });
}

// function showMeal(map){
//     // console.log(map);
//
//     let text = '';
//
//     // map.replyList.forEach(r => {
//         text += `
//         <div id="myModal" class="modal modal-hidden">
//                         <div class="modal-content">
//                             <div class="modal-header">
//                                 <span class="close" id="close-modal">&times;</span>
//                                 <h2>6월 15일 아침 8:00</h2>
//                                 <hr class="service-name-hr" />
//                             </div>
//                             <div class="modal-main">
//                                 <!-- 이미지 -->
//                                 <div class="day-img-box slider">
//                                     <input type="radio" name="slide" id="slide1" checked>
//                                     <input type="radio" name="slide" id="slide2">
//                                     <input type="radio" name="slide" id="slide3">
//                                     <input type="radio" name="slide" id="slide4">
//                                     <ul id="imgholder" class="imgs">
//                  `;
//                       map.replyList.forEach(r => {
//         text += `
//                                         <li><img th:src="@{/img/sample1.jpg}"></li>
//                                         <li><img th:src="@{/img/sample2.jpg}"></li>
//                                         <li><img th:src="@{/img/sample3.jpg}"></li>
//                                         <li><img th:src="@{/img/sample4.jpg}"></li>
//                   `;
//                       });
//
//         text += `
//                                     </ul>
//                                     <div class="bullets">
//                                         <label for="slide1">&nbsp;</label>
//                                         <label for="slide2">&nbsp;</label>
//                                         <label for="slide3">&nbsp;</label>
//                                         <label for="slide4">&nbsp;</label>
//                                     </div>
//                                 </div>
//                                 <!-- 이미지 끝-->
//                                 <div class="day-detail">
//                                     <div class="day-detail1">
//                                         <div class="meal-time meal-time-checked"><span class="material-symbols-outlined meal-icon-span ">wb_twilight</span>아침</div>
//                                         <div class="meal-time"><span class="material-symbols-outlined meal-icon-span">sunny</span>점심</div>
//                                         <div class="meal-time"><span class="material-symbols-outlined meal-icon-span">dark_mode</span>저녁</div>
//                                         <div class="meal-time"><span class="material-symbols-outlined meal-icon-span">icecream</span>간식</div>
//                                     </div>
//                                     <div class="day-detail2">
//                 `;
//         map.replyList.forEach(r => {
//             text += `
//                                         <div class="day-food-detail">
//                                             <div class="day-food-name">치킨</div>
//                                             <div class="day-food-weight">1220g</div>
//                                             <div class="day-food-kcal">4030kcal</div>
//                                         </div>
//                     `;
//         });
//                 text += `
//                                     </div>
//                                     <div class="day-detail3">
//                                         총 칼로리 : &nbsp<div class="detail3-kcal">3000kcal</div>
//                                     </div>
//                                     <div class="day-detail4">
//                                         <div class="modify-btn">수정</div>
//                                         <div class="delete-btn">삭제</div>
//                                     </div>
//                                 </div>
//                             </div>
//                         </div>
//                     </div>
//         `;
//     });
//
//     $('.modal-content').html(text);
// }


function showError(a, b, c){
    console.error(c);
}