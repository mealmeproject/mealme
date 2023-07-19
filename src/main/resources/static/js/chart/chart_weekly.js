import * as weekly from './todayExportFunction.js';

// 일간으로 색상 변경, 월간 색상 죽이기
changedMonthly();
changedWeekly();

function changedWeekly(){
  $('.weekly').css('backgroundColor', 'rgb(255, 139, 38)');
  $('.weekly').css('color', '#fff');
}

function changedMonthly(){
  $('.monthly').css('backgroundColor', '#fff');
  $('.monthly').css('color', 'rgb(255, 139, 38)');
}

monthlyMouseover();
monthlyMouseout();
function monthlyMouseover() {
  $('.monthly').mouseover(function() {
    $(this).css('backgroundColor', 'rgb(255, 139, 38)');
    $(this).css('color', '#fff');
  });
}
function monthlyMouseout() {
  $('.monthly').mouseout(function() {
    $(this).css('backgroundColor', '#fff');
    $(this).css('color', 'rgb(255, 139, 38)');
  });
}

// 오늘 날짜 기준으로 값 받아오기
let currentDate = new Date();

// 현재 주의 월요일 날짜를 가져오기
let currentWeekMonday = currentDate.getDate() - currentDate.getDay() + 1;
let firstMonday = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentWeekMonday);

// 5주 동안의 월요일 날짜 배열을 생성합니다.
let mondayArray = [];
let mondayMonthDay = [];
for (let i = 0; i < 5; i++) {
  let date = new Date(firstMonday.getTime());
  date.setDate(firstMonday.getDate() - (7 * i));

  let year = date.getFullYear();
  let month = String(date.getMonth() + 1).padStart(2, '0');
  let day = String(date.getDate()).padStart(2, '0');

  let formattedDate = year + '-' + month + '-' + day;
  mondayArray.push(formattedDate);

  let MonthDayDate = month + '-' + day;
  mondayMonthDay.push(MonthDayDate);
}
console.log(mondayArray);
console.log(mondayMonthDay)


// 이번 주의 일요일 날짜 가져오기
let currentWeekSunday = currentDate.getDate() - currentDate.getDay() + 7;
let firstSunday = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentWeekSunday);

// 5주 동안의 월요일 날짜 배열 생성
let sundaysArray = [];
let sundayMonthDay = [];
for (let i = 0; i < 5; i++) {
  let date = new Date(firstSunday.getTime());
  date.setDate(firstSunday.getDate() - (7 * i));

  let year = date.getFullYear();
  let month = String(date.getMonth() + 1).padStart(2, '0');
  let day = String(date.getDate()).padStart(2, '0');

  let formattedDate = year + '-' + month + '-' + day;
  sundaysArray.push(formattedDate);

  let MonthDayDate = month + '-' + day;
  sundayMonthDay.push(MonthDayDate);
}
console.log(sundaysArray);
console.log(sundayMonthDay);

weekly.getWeeklyList(showWeekly, showError)
function showWeekly(weeklyList){
  console.log(weeklyList)

  let kcalList = [];
  let carbohydrateList = [];
  let proteinList = [];
  let fatList = [];
  let sugarsList = [];
  let sodiumList = [];
  let cholesterolList = [];
  let fattyAcidList = [];
  let transFatList = [];
  let countMealTimeList = [];

  let kcalTotal = 0;
  let carbohydrateTotal = 0;
  let proteinTotal = 0;
  let fatTotal = 0;
  let sugarsTotal = 0;
  let sodiumTotal = 0;
  let cholesterolTotal = 0;
  let fattyAcidTotal = 0;
  let transFatTotal = 0;
  let countMealTimeTotal = 0;



  for (let i = 0; i < mondayArray.length; i++) {
    let formattedDate = mondayArray[i];
    let foundColumn = false;

    for (let j = 0; j < weeklyList.dailyTotalVo.length; j++) {
      let mealWeek = weeklyList.dailyTotalVo[j].mealDate;

      if (formattedDate === mealWeek) {
        // console.log("성공@@@@@@@@@@@@");
        kcalList.push(weeklyList.dailyTotalVo[j].totalFoodKcal);
        carbohydrateList.push(weeklyList.dailyTotalVo[j].totalFoodCarbohydrate);
        proteinList.push(weeklyList.dailyTotalVo[j].totalFoodProtein);
        fatList.push(weeklyList.dailyTotalVo[j].totalFoodFat);
        sugarsList.push(weeklyList.dailyTotalVo[j].totalFoodSugars);
        sodiumList.push(weeklyList.dailyTotalVo[j].totalFoodSodium);
        cholesterolList.push(weeklyList.dailyTotalVo[j].totalFoodCholesterol);
        fattyAcidList.push(weeklyList.dailyTotalVo[j].totalFoodFattyAcid);
        transFatList.push(weeklyList.dailyTotalVo[j].totalFoodTransFat);
        countMealTimeList.push(weeklyList.dailyTotalVo[j].countMealTime);

        foundColumn = true;
        break;
      }
    }
    if (!foundColumn) {
      // console.log("실패@@@@@@@@@@@@");
      kcalList.push(0);
      carbohydrateList.push(0);
      proteinList.push(0);
      fatList.push(0);
      sugarsList.push(0);
      sodiumList.push(0);
      cholesterolList.push(0);
      fattyAcidList.push(0);
      transFatList.push(0);
      countMealTimeList.push(0);
    }
  }

  for (let i = 0; i<5; i++){
    kcalTotal += kcalList[i];
    carbohydrateTotal += carbohydrateList[i];
    proteinTotal += proteinList[i];
    fatTotal += fatList[i];
    sugarsTotal += sugarsList[i];
    sodiumTotal += sodiumList[i];
    cholesterolTotal += cholesterolList[i];
    fattyAcidTotal += fattyAcidList[i];
    transFatTotal += transFatList[i];
    countMealTimeTotal += countMealTimeList[i];
  }

  console.log(kcalTotal);
  console.log(fatTotal);
  console.log(countMealTimeTotal);



  console.log(kcalList);
  // console.log(carbohydrateList);
  // console.log(proteinList);
  console.log(fatList);
  // console.log(sugarsList);
  // console.log(sodiumList);
  // console.log(cholesterolList);
  // console.log(fattyAcidList);
  // console.log(transFatList);
  console.log(countMealTimeList);

//  값 넣기 시작
// *****바 차트
//  한 주의 일간 평균 탄단지 섭취 그람수
  const data = {
    labels: [mondayMonthDay[4]+' ~ '+sundayMonthDay[4], mondayMonthDay[3]+' ~ '+sundayMonthDay[3], mondayMonthDay[2]+' ~ '+sundayMonthDay[2], mondayMonthDay[1]+' ~ '+sundayMonthDay[1], '이번주'],
    datasets: [{
      label: '탄수화물',
      data: [carbohydrateList[4]/countMealTimeList[4], carbohydrateList[3]/countMealTimeList[3], carbohydrateList[2]/countMealTimeList[2], carbohydrateList[1]/countMealTimeList[1], carbohydrateList[0]/countMealTimeList[0]],
      backgroundColor: [
        'rgba(32, 178, 170, 0.2)'
      ],
      borderColor: [
        'rgba(32, 178, 170, 1)'
      ],
      borderWidth: 1
    },{
      label: '단백질',
      data: [proteinList[4]/countMealTimeList[4], proteinList[3]/countMealTimeList[3], proteinList[2]/countMealTimeList[2], proteinList[1]/countMealTimeList[1], proteinList[0]/countMealTimeList[0]],
      backgroundColor: [
        'rgba(255, 26, 104, 0.2)'
      ],
      borderColor: [
        'rgba(255, 26, 104, 1)'
      ],
      borderWidth: 1
    },{
      label: '지방',
      data: [fatList[4]/countMealTimeList[4], fatList[3]/countMealTimeList[3], fatList[2]/countMealTimeList[2], fatList[1]/countMealTimeList[1], fatList[0]/countMealTimeList[0]],
      backgroundColor: [
        'rgba(255, 206, 86, 0.2)'
      ],
      borderColor: [
        'rgba(255, 206, 86, 1)'
      ],
      borderWidth: 1
    }
    ]
  };

// config
  const config = {
    type: 'bar',
    data,
    options: {
      borderRadius: 5,
      barPercentage: 0.7,
      scales: {
        x:{
          stacked: true,
        },
        y: {
          stacked: true,
          beginAtZero: true
        }
      }
    }
  };

// render init block
  const barChart = new Chart(
      document.getElementById('bar-chart'),
      config
  );

  console.log(carbohydrateTotal*4/countMealTimeTotal + "look!!!");

  // *****도넛 차트
  const dataDonut = {
    // labels: ['탄수화물', '단백질', '지방'],
    datasets: [{
      label: '영양소',
      data: [Math.round(carbohydrateTotal*4/countMealTimeTotal), Math.round(proteinTotal*4/countMealTimeTotal), Math.round(fatTotal*9/countMealTimeTotal)],
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

  const centerText = {
    id :'centerText',
    afterDatasetDraw(chart, args, options){
      const{ctx, chartArea: {left, right, top, buttom, width, height} } = chart;

      let text = Math.round(kcalTotal/countMealTimeTotal);
      // 칼로리 값을 변수에 저장
      if (isNaN(text)) {
        text = 0;
      }
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
  const configDonut = {
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
  const myChartDonut = new Chart(
      document.getElementById('doughnut-chart'),
      configDonut
// const configDonut임
  );

// 탄단지 그래프 바

  let recommendCarbohydrateNum = $('.recommendCarbohydrate-num').text();
  let recommendProteinNum= $('.recommendProtein-num').text();
  let recommendFatNum= $('.recommendFat-num').text();

  if (recommendCarbohydrateNum <= 0){
    $('.recommendCarbohydrate-num').text(0);
  }

  if (recommendProteinNum <= 0){
    $('.recommendProtein-num').text(0);
  }

  if (recommendFatNum <= 0){
    $('.recommendFat-num').text(0);
  }



  let textCarbohydrate ='';
  textCarbohydrate = Math.round(carbohydrateTotal/countMealTimeTotal);
  // $('.taken-carbohydrate-gram').text(textCarbohydrate);
  if (isNaN(textCarbohydrate)) {
    $('.taken-carbohydrate-gram').text(0);
  }else {
    $('.taken-carbohydrate-gram').text(textCarbohydrate);
  }

  let textProtein ='';
  textProtein = Math.round(proteinTotal/countMealTimeTotal);
  // $('.taken-protein-gram').text(textProtein);
  if (isNaN(textProtein)) {
    $('.taken-protein-gram').text(0);
  }else {
    $('.taken-protein-gram').text(textProtein);
  }

  let textFat ='';
  textFat = Math.round(fatTotal/countMealTimeTotal);
  // $('.taken-fat-gram').text(textFat);
  if (isNaN(textFat)) {
    $('.taken-fat-gram').text(0);
  }else {
    $('.taken-fat-gram').text(textFat);
  }

  graph1();
  function graph1(){
    let graph1Width = ((carbohydrateTotal/countMealTimeTotal)/recommendCarbohydrateNum) *100;
    $('.graph1-span').animate({
      width: graph1Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  graph2();
  function graph2(){
    let graph2Width = ((proteinTotal/countMealTimeTotal)/recommendProteinNum) *100;
    $('.graph2-span').animate({
      width: graph2Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  graph3();
  function graph3(){
    let graph3Width = ((fatTotal/countMealTimeTotal)/recommendFatNum) *100;
    $('.graph3-span').animate({
      width: graph3Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  //총 섭취 그람수의 탄단지 % 비교
  let monthCarbohydrateKcal = carbohydrateTotal*4;
  let monthProteinKcal = proteinTotal*4;
  let monthFatKcal = fatTotal*9;
  let monthTotalKal = monthCarbohydrateKcal + monthProteinKcal + monthFatKcal;



  // let monthlyCarbohydratePercent = Math.round((monthCarbohydrateKcal/monthTotalKal) * 100);
  let monthlyProteinPercent = Math.round((monthProteinKcal/monthTotalKal) * 100);
  let monthlyFatPercent = Math.round((monthFatKcal/monthTotalKal) * 100);
  let monthlyCarbohydratePercent = 100-(monthlyProteinPercent+monthlyFatPercent);

  if (isNaN(monthlyCarbohydratePercent)) {
    $('.carbohydrate-percent-num').text(0);
  } else {
    $('.carbohydrate-percent-num').text(monthlyCarbohydratePercent);
  }

  if (isNaN(monthlyProteinPercent)) {
    $('.protein-percent-num').text(0);
  } else {
    $('.protein-percent-num').text(monthlyProteinPercent);
  }

  if (isNaN(monthlyFatPercent)) {
    $('.fat-percent-num').text(0);
  } else {
    $('.fat-percent-num').text(monthlyFatPercent);
  }

// ***** 전체 영양소
// setup
  const dataNutri = {
    labels: ['탄수화물(g)', '단백질(g)', '지방(g)', '당류(g)', '나트륨(mg)', '콜레스테롤(mg)', '포화지방산(g)', '트랜스지방(g)'],
    datasets: [{
      label: '섭취 영양성분',
      data: [Math.round(carbohydrateTotal/countMealTimeTotal), Math.round(proteinTotal/countMealTimeTotal), Math.round(fatTotal/countMealTimeTotal),
        Math.round(sugarsTotal/countMealTimeTotal), Math.round(sodiumTotal/countMealTimeTotal), Math.round(cholesterolTotal/countMealTimeTotal),
        Math.round(fattyAcidTotal/countMealTimeTotal),Math.round(transFatTotal/countMealTimeTotal)],
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
  const configNutri = {
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
  const myChartNutri = new Chart(
      document.getElementById('nutrient-chart'),
      configNutri
  );
}


function showError(a, b, c){
  console.log(c);
}




















