import * as info from './todayExportFunction.js';

let todayPhoto = '';
const modal = document.querySelector('.modal');
let mealImgBoxes;
const closeModalButton = document.getElementById("close-modal");


// 도넛 차트
let dataDonut = {
  // labels: ['탄수화물', '단백질', '지방'],
  datasets: [{
    label: '영양소',
    data: [30*2, 20, 20],
    backgroundColor: [
      'rgba(32, 178, 170, 0.2)',
      'rgba(255, 26, 104, 0.2)',
      'rgba(255, 206, 86, 0.2)'
    ],
    borderColor: [
      'rgba(32, 178, 170, 1)',
      'rgba(255, 26, 104, 1)',
      'rgba(255, 206, 86, 1)'
    ],
    borderWidth: 1
  }]
};

// 도넛 차트 안에 칼로리 텍스트
let centerText = {
  id :'centerText',
  afterDatasetDraw(chart, args, options){
    const{ctx, chartArea: {left, right, top, buttom, width, height} } = chart;

    let text = $('.today-kcal-num').text();
    // 칼로리 값을 변수에 저장
    ctx.save();
    // console.log(top);
    ctx.font = '900 60px Nanum Gothic';
    ctx.fillStyle = 'rgba(50, 50, 50, 0.8)';
    ctx.textAlign = 'center';
    ctx.fillText(text, width/2 , height/2 + top-20);
    ctx.restore();

    ctx.save();
    // console.log(top);
    ctx.font = '900 30px Nanum Gothic';
    ctx.fillStyle = 'rgba(50, 50, 50, 0.8)';
    ctx.textAlign = 'center';
    ctx.fillText('kcal', width/2 , height/2 + top+40);
    ctx.restore();

  }
}

// config
let configDonut = {
  type: 'doughnut',
  data: dataDonut,
  // const dataDonut의 값을 넣어준것임
  options: {
    cutout:'90%',
    borderRadius:20
  },
  plugins:[centerText]
};

// render init block
let myChartDonut = new Chart(
  document.getElementById('doughnut-chart'),
  configDonut
// const configDonut임
);


// 영양소 표
let dataNutri = {
  labels: ['탄수화물(g)', '단백질(g)', '지방(g)', '당류(g)', '나트륨(mg)', '콜레스테롤(mg)', '포화지방산(g)', '트랜스지방(g)'],
  datasets: [{
    label: '섭취 영양성분',
    data: [18, 12, 6, 9, 12, 3, 9,10],
    backgroundColor: [
      'rgba(255, 139, 38, 0.2)',
      'rgba(255, 139, 38, 0.2)',
      'rgba(255, 139, 38, 0.2)',
      'rgba(255, 139, 38, 0.2)',
      'rgba(255, 139, 38, 0.2)',
      'rgba(255, 139, 38, 0.2)',
      'rgba(255, 139, 38, 0.2)',
      'rgba(255, 139, 38, 0.2)'

    ],
    borderColor: [
      'rgba(255, 139, 38, 1)',
      'rgba(255, 139, 38, 1)',
      'rgba(255, 139, 38, 1)',
      'rgba(255, 139, 38, 1)',
      'rgba(255, 139, 38, 1)',
      'rgba(255, 139, 38, 1)',
      'rgba(255, 139, 38, 1)',
      'rgba(255, 139, 38, 1)'
    ],
    borderWidth: 1,
    borderRadius: 5,
    barPercentage: 0.8
  }]
};

// config
let configNutri = {
  type: 'bar',
  data: dataNutri,
  options: {
    indexAxis: 'y',
    scales: {
      y: {
        beginAtZero: true,
        gride: {
          display: false,
          drawBorder: false
        }
      }
    }
  }
};

// render init block
let myChartNutri = new Chart(
  document.getElementById('nutrient-chart'),
  configNutri
);

// datepicker 날짜 이동
$(function() {
  //input을 datepicker로 선언
  $("#datepicker").datepicker({
      dateFormat: 'yy-mm-dd' //달력 날짜 형태
      ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
      ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
      ,changeYear: true //option값 년 선택 가능
      ,changeMonth: true //option값  월 선택 가능                
      ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
      // ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
      ,buttonImage: "https://kr.seaicons.com/wp-content/uploads/2022/05/calendar-icon-1.png" //버튼 이미지 경로
      // ,buttonImage: "https://cdn.icon-icons.com/icons2/3485/PNG/512/calendar_time_event_date_icon_220247.png" //버튼 이미지 경로
      ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
      ,buttonText: "선택" //버튼 호버 텍스트              
      ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
      ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
      ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
      ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
      ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
      ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
      ,maxDate: "+0D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
  });                    
  
  // //초기값을 오늘 날짜로 설정
  // $('#datepicker').datepicker('setDate', 'today');
  $('#datepicker').datepicker('setDate', '.hasDatepicker'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
  // console.log($('.hasDatepicker').val() + " hasDate");
});

// 데이트 피커 값 뽑기
$(function() {
  $("#datepicker").datepicker();
  // 오늘 날짜로 지정된 val 값
  // console.log($('#datepicker').val());
});

// 날짜 변경시 바뀌는 val 값
$("#datepicker").on('change', function(){
  // console.log($('#datepicker').val());
  info.getUserInfo({mealTime : $('#datepicker').val()}, showUser, showError);
});

// 탭 이동
// 사진 클릭 시  오늘 먹은 음식 사진 보임
$('.today-food-photo-text').on("click", function () {
  $('.img').css('color', 'rgba(50, 50, 50, 0.9)');
  $('.img-text').css('color', 'rgba(50, 50, 50, 0.9)');
  
  $('.chart').css('color', 'rgba(160, 160, 160, 0.5)');
  $('.chart-text').css('color', 'rgba(160, 160, 160, 0.5)');

  $('.chart-all-wrap').css('display', 'none');
  $('.photo-all-wrap').css('display', 'flex');
});

// 차트 클릭 시 오늘 먹은 음식 차트 보임
$('.today-food-chart-text').on("click", function () {
  $('.chart').css('color', 'rgba(50, 50, 50, 0.9)');
  $('.chart-text').css('color', 'rgba(50, 50, 50, 0.9)');
  
  $('.img').css('color', 'rgba(160, 160, 160, 0.5)');
  $('.img-text').css('color', 'rgba(160, 160, 160, 0.5)');

  $('.photo-all-wrap').css('display', 'none');
  $('.chart-all-wrap').css('display', 'flex');
});

// $(function() {})은 문서(DOM)가 완전히 로드되었을 때 내부의 코드를 실행하는 역할
// datepicker의 val값이 바로 뜨도록 함수를 설정해줌
$(function (){
  console.log($('#datepicker').val())
//  controller에서 보낸 회원의 정보 받기
  info.getUserInfo({mealTime : $('#datepicker').val()}, showUser, showError)
})


function showUser(userInfo){
  // console.log(userInfo);
  // console.log(userInfo.recommendVo.userComment);
  // console.log(userInfo.calcNutrientUtil);

  // let test ='';
  // test = userInfo.recommendVo.userComment;
  let textGoal = '';
    textGoal += `<div class="goal">✨ 나의 목표</div>
            <div class="goal-content">${userInfo.recommendVo.userComment}</div>`;
  $('.goal-wrap').html(textGoal);
  // $('.goal-content').text(test);
  // $('.goal-content').text(us
  // Comment);

  let textKcal = '';
  textKcal += `<div class="today-kcal-num">${userInfo.calcNutrientUtil.kcalSum}</div>
              <div class="today-kcal-wrap-sub">
              <div class="today-kcal-slide">/</div>
              <div class="today-kcal-total"">${userInfo.recommendVo.recommendKcal}</div>
              <div class="today-kcal-unit">kcal</div>`
  $('.today-kcal-num-wrap').html(textKcal);

  // $('.today-kcal-num').text(userInfo.calcNutrientUtil.kcalSum);
  // $('.today-kcal-total').text(userInfo.recommendVo.recommendKcal);


  if (userInfo.todayKcalSumVo != null){
    $('.breakfast-kcal-num').text(userInfo.todayKcalSumVo.breakfastTotal);
    $('.lunch-kcal-num').text(userInfo.todayKcalSumVo.lunchTotal);
    $('.dinner-kcal-num').text(userInfo.todayKcalSumVo.dinnerTotal);
    $('.snack-kcal-num').text(userInfo.todayKcalSumVo.snackTotal);
  }else {
    $('.breakfast-kcal-num').text(0);
    $('.lunch-kcal-num').text(0);
    $('.dinner-kcal-num').text(0);
    $('.snack-kcal-num').text(0);
  }


  // $('.breakfast-kcal-num').text(userInfo.todayKcalSumVo.breakfastTotal);
  // $('.lunch-kcal-num').text(userInfo.todayKcalSumVo.lunchTotal);
  // $('.dinner-kcal-num').text(userInfo.todayKcalSumVo.dinnerTotal);
  // $('.snack-kcal-num').text(userInfo.todayKcalSumVo.snackTotal);





  let eatKcal = $('.today-kcal-num').text();
  let dayKcal = $('.today-kcal-total').text();

// 섭취 칼로리 퍼센트 숫자 계산
  kcalPercent();
  function kcalPercent() {
    let targetNumber = Math.round((eatKcal/dayKcal) * 100); // 애니메이션의 최종 값
    let duration = 500;                                 // 애니메이션의 지속 시간 (밀리초)
    let startNumber = 0;                                 // 애니메이션의 시작 값
    let intervalTime = 20;                               // 애니메이션의 간격 시간 (밀리초)

    let step = (targetNumber - startNumber) / (duration / intervalTime);
    let currentNumber = startNumber;

    let interval = setInterval(function() {
      currentNumber += step;
      if (currentNumber >= targetNumber) {
        currentNumber = targetNumber; // 최종 값에 도달하면 currentNumber를 targetNumber로 설정
        $('.kcal-comment-percent').text(Math.round(currentNumber));
        clearInterval(interval);
      }
      else {
        $('.kcal-comment-percent').text(Math.ceil(currentNumber)); // 소수점 자리 올림으로 표시
      }
    }, intervalTime);
  }

// 섭취 칼로리 퍼센트 바
  barPercent();
  function barPercent(){
    var width = (eatKcal/dayKcal) * 100;   // 사칙연산해서 변수로 넓이 저장
    $('.kcal-comment-bar-span').animate({
      width: width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  let textCarbohydrate = '';
  textCarbohydrate += `<div class="sumCarbohydrateNum">${userInfo.calcNutrientUtil.carbohydrateSum}</div>
                <div>/</div>
                <div class="recommendCarbohydrateNum">${userInfo.recommendVo.recommendCarbohydrate}</div>
                <div>g</div>`
  $('.carbohydrate-gram').html(textCarbohydrate);

  let textProtein = '';
  textProtein += `<div class="sumProteinNum">${userInfo.calcNutrientUtil.proteinSum}</div>
                <div>/</div>
                <div class="recommendProteinNum">${userInfo.recommendVo.recommendProtein}</div>
                <div>g</div>`
  $('.protein-gram').html(textProtein);

  let textFat ='';
  textFat += `<div class="sumFatNum">${userInfo.calcNutrientUtil.fatSum}</div>
                <div>/</div>
                <div class="recommendFatNum">${userInfo.recommendVo.recommendFat}</div>
                <div>g</div>`
  $('.fat-gram').html(textFat);

  // 탄단지 bar 계산, kcal별 섭취량 계산

  let sumCarbohydrateNum = $('.sumCarbohydrateNum').text();
  let recommendCarbohydrateNum = $('.recommendCarbohydrateNum').text();
  let sumProteinNum = $('.sumProteinNum').text();
  let recommendProteinNum = $('.recommendProteinNum').text();
  let sumFatNum = $('.sumProteinNum').text();
  let recommendFatNum = $('.recommendFatNum').text();

  graph1();
  function graph1(){
    let graph1Width = (sumCarbohydrateNum/recommendCarbohydrateNum) *100;
    $('.graph1-span').animate({
      width: graph1Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  graph2();
  function graph2(){
    let graph2Width = (sumProteinNum/recommendProteinNum) *100;
    $('.graph2-span').animate({
      width: graph2Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  graph3();
  function graph3(){
    let graph3Width = (sumFatNum/recommendFatNum) *100;
    $('.graph3-span').animate({
      width: graph3Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }



// 칼로리에 따른 탄단지 비율
//   console.log($('.today-kcal-num').text());
  // let todayKcal = $('.today-kcal-num').text();
  let sumCarbohydrateKcal = $('.sumCarbohydrateNum').text()*4;
  // console.log(sumCarbohydrateKcal);
  let sumProteinKcal= $('.sumProteinNum').text()*4;
  let sumFatKcal = $('.sumFatNum').text()*9;
  let sumTotalKcal = $('.sumCarbohydrateNum').text()*4 + $('.sumProteinNum').text()*4 + $('.sumFatNum').text()*9;
  // let sumCarbohydratePercent = Math.round((sumCarbohydrateKcal/sumTotalKcal) * 100);
  let sumProteinPercent = Math.round((sumProteinKcal/sumTotalKcal) * 100);
  let sumFatPercent = Math.round((sumFatKcal/sumTotalKcal) * 100);
  let sumCarbohydratePercent = 100-(sumProteinPercent+sumFatPercent);

  if (isNaN(sumCarbohydratePercent)) {
    $('.carbohydrate-percent-num').text(0);
  } else {
    $('.carbohydrate-percent-num').text(sumCarbohydratePercent);
  }

  if (isNaN(sumProteinPercent)) {
    $('.protein-percent-num').text(0);
  } else {
    $('.protein-percent-num').text(sumProteinPercent);
  }

  if (isNaN(sumFatPercent)) {
    $('.fat-percent-num').text(0);
  } else {
    $('.fat-percent-num').text(sumFatPercent);
  }


  // 도넛 차트 값 채우기
  // console.log(myChartDonut)
  // console.log(myChartDonut.data.datasets);
  // console.log(myChartDonut.data.datasets[0].data);

  // let dataArr = myChartDonut.data.datasets[0].data;
  // console.log(myChartDonut.data.datasets[0].data);
  // 도넛차트 날짜 이동하면 값 바꿔주기
  for(let i=0; i<3; i++){
    myChartDonut.data.datasets[0].data.pop();
  }
  myChartDonut.update();

  myChartDonut.data.datasets[0].data.push(userInfo.calcNutrientUtil.carbohydrateSum);
  myChartDonut.data.datasets[0].data.push(userInfo.calcNutrientUtil.proteinSum);
  myChartDonut.data.datasets[0].data.push(userInfo.calcNutrientUtil.fatSum);

  myChartDonut.update();

//  총 영양소 바 차트 날짜이동하면 값 바꿔주기기
  for(let i=0; i<8; i++){
    myChartNutri.data.datasets[0].data.pop();
  }
  myChartNutri.update();

  myChartNutri.data.datasets[0].data.push(userInfo.calcNutrientUtil.carbohydrateSum);
  myChartNutri.data.datasets[0].data.push(userInfo.calcNutrientUtil.proteinSum);
  myChartNutri.data.datasets[0].data.push(userInfo.calcNutrientUtil.fatSum);
  myChartNutri.data.datasets[0].data.push(userInfo.calcNutrientUtil.sugarsSum);
  myChartNutri.data.datasets[0].data.push(userInfo.calcNutrientUtil.sodiumSum);
  myChartNutri.data.datasets[0].data.push(userInfo.calcNutrientUtil.cholesterolSum);
  myChartNutri.data.datasets[0].data.push(userInfo.calcNutrientUtil.fattyAcidSum);
  myChartNutri.data.datasets[0].data.push(userInfo.calcNutrientUtil.transFatSum);

  myChartNutri.update();


  // 하루 식사 이미지 받아오기


//  오늘 하루 사진 ========================================================================================================================================================

  // 날짜로 받아온 식단 리스트 html 만드는 펑션
  todayMealList(userInfo);
}

function showError(a, b, c){
  console.log(c);
}


// 모달창 html
function showMeal(map){
  console.log('모달창 map띄우기');
  console.log(map);

  let text = '';

  //
  text += `
                            <div class="modal-header">
                                <div class="modal-header-text">
                                    <h2>${map.mealTime}</h2>
                                    <h2>${map.boardTitle}</h2>    
                                </div>
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
                         `;
                map.files.forEach(f => {
                    text += `
                                    <li><img src="/upload/${f.fileUploadPath}/${f.fileUuid}_${f.fileName}"></li>
              `;
                });

              
                    text += `
                                    </ul>
                                    <div class="bullets">
               `;
  let count = 1;

  map.files.forEach(f => {
    text += `
    <label for="slide${count}"> </label>
  `;
    count++;
  });
                text += `
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
                                        <div class="board-content">
                                            ${map.boardContent}
                                        </div>
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
                                        <a href="/meal/mealDelete?boardNumber=${map.boardNumber}">
                                            <div class="delete-btn">삭제</div>
                                        </a>
                                    </div>
                                </div>
                            </div>
        `;

  $('.modal-container').html(text);

}

$('.meal-img-list').on('click', '.meal-img-box' , function (){
  let boardNumber = $(this).data('boardnumber');

  console.log(boardNumber);
  openModal(boardNumber);
})


function todayMealList(userInfo){
  // 날짜를 바꾸면 값이 쌓이므로 todayPhoto = '';을 통해 쌓이는 값을 초기화 시킴
  console.log(userInfo)
  todayPhoto = '';
  userInfo.mealList.forEach(m => {
    todayPhoto += `<li class="meal-img-box" data-boardnumber="${m.boardNumber}" >
                            <div class="meal-img">
                                <div class="imgbox" >
                                    <img src="/upload/${m.fileUploadPath}/${m.fileUuid}_${m.fileName}"  alt="" />
                                </div>
                                <div class="meal-info">
                                    <div class="meal-eat-time">
                                        <h2>${m.mealTime}</h2>
                                    </div>
                                    <div class="meal-eat-kcal">
                                        <h2>${m.mealTotalKcal}kcal</h2>
                                    </div>
                                </div>
                            </div>
                        </li>`;
  });
  new Promise(function(resolve, reject) {
    $('.meal-img-list').html(todayPhoto);
    resolve();
  }).then(function() {
    mealImgBoxes = document.querySelectorAll(".meal-img-box");
  //  이건 잘 줬니?
  console.log("================================!")
  });
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


closeModalButton.addEventListener('click', function(event) {
  event.stopPropagation();
  console.log("닫는다!");
  modal.setAttribute("style", "display: none;");
});


function openModal(boardNumber) {
  console.log("11111111111111111111111111111111");
  // event.stopPropagation();
  console.log("boardNumber는 이거 ! " + boardNumber);
  mealRead(boardNumber, showMeal, showError);
  modal.setAttribute("style", "display: block;");
}