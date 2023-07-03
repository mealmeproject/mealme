const modal = document.querySelector('.modal');
const mealImgBoxes = document.querySelectorAll(".meal-img-box");
const closeModalButton = document.getElementById("close-modal");


const userNumber = $('.board-num').val();
let page = 1;
// getListPage({userNumber : userNumber, page : page}, appendText, showError);


mealImgBoxes.forEach(function (box) {
    box.addEventListener("click", openModal);
});

function openModal(event) {
    event.stopPropagation();
    modal.setAttribute("style", "display: block;");
}

closeModalButton.addEventListener('click', function() {
    event.stopPropagation();
    console.log("닫는다!");
    modal.setAttribute("style", "display: none;");
});

function generateMealList(map) {
    let text = '';
    map.mealList.forEach(day => {
        text += `
      <li class="meal-list-oneday">
        <div class="meal-list-info">
          <div class="eat-date"><h2>${day.date}</h2></div>
          <div class="eat-kcal"><h2>${day.dayTotalKcal}</h2></div>
          <div class="eat-list-view-btn">
            <img th:src="@{/img/chevron_right.png}" alt="" />
          </div>
        </div>
        <ul class="meal-img-list">
    `;

        day.dayMealList.forEach(meal => {
            text += `
        <li class="meal-img-box">
          <div class="meal-img">
            <div class="imgbox">
              <img th:src="@{/img/${meal.image}}" alt="" />
            </div>
            <div class="meal-info">
              <div class="meal-eat-time"><h2>${meal.mealTime}</h2></div>
              <div class="meal-eat-kcal"><h2>${meal.mealTotalKcal}</h2></div>
            </div>
          </div>
        </li>
      `;
        });

        text += `
        </ul>
      </li>
    `;
    });

    return text;
}

// Example usage:
const mealListHTML = generateMealList();
console.log(mealListHTML);

// 식단 리스트 가져오기


function appendText(map){
    let text = '';

    map.replyList.forEach(r => {
        text += `
        <div class="reply" data-num="${r.replyNumber}">
          <div class="reply-box">
            <div class="reply-box__writer">${r.userId}</div>
            <div class="reply-box__content">${r.replyContent}</div>
          </div>
          <div class="reply-time">
            ${reply.timeForToday(r.replyRegisterDate == r.replyUpdateDate ? r.replyRegisterDate : r.replyUpdateDate)}
          </div>  
          <div class="reply-btn-box">
            `;

        if(r.userNumber == loginNumber){
            text += `    
                <span class="reply-btns"></span>
                <div class="reply-btns__box none">
                  <div class="reply-remove-btn">삭제</div>
                  <div class="reply-modify-btn">수정</div>
                </div>`;
        }

        text += `
            </div>
        </div>
        `;
    });

    $('.reply-list-wrap').append(text);
}



function getListPage(pageInfo, callback, error){
    $.ajax({
        url : `/meal/myPage/${pageInfo.userNumber}/${pageInfo.page}`,
        type : 'get',
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : error
    });
}

//무한 스크롤 페이징
$(window).on('scroll', function(){
    console.log($(window).scrollTop());
//  $(window).scrollTop() : 현재 브라우저의 스크롤 위치를 의미한다.
    console.log(`document : ${$(document).height()}`)
//    $(document).height() : 문서 전체의 높이를 의미한다.
    console.log(`window : ${$(window).height()}`)
//    $(window).height() : 브라우저 화면의 높이를 의미한다.

    //현재 브라우저 스크롤의 위치 == 문서 높이 - 화면 높이      -> 문서 마지막에 스크롤이 도작했을 때
    if(Math.round($(window).scrollTop()) == $(document).height() - $(window).height()){
        console.log(++page);
        // getListPage({userNumber : userNumber, page : page}, appendText, showError);
    }
});


function showError(a, b, c){
    console.error(c);
}



