import * as daily from './todayExportFunction.js';

// 일간으로 색상 변경, 월간 색상 죽이기
changedMonthly();
changedDaily();

function changedDaily(){
  $('.daily').css('backgroundColor', 'rgb(255, 139, 38)');
  $('.daily').css('color', '#fff');
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

daily.getDailyList(showDaily, showError)
function showDaily(dailyList){
  console.log(dailyList)

  let today = new Date();
  let todayDates = [];

  for (let i = 0; i < 7; i++) {
    let date = new Date(today);
    date.setDate(date.getDate() - i);

    let year = date.getFullYear();
    let month = String(date.getMonth() + 1).padStart(2, '0');
    let day = String(date.getDate()).padStart(2, '0');

    let formattedDate = year + '-' + month + '-' + day;
    todayDates.push(formattedDate);
  }
  console.log(todayDates);

  let kcalSum= [0,0,0,0,0,0,0];
  let carbohydrateSum = [0,0,0,0,0,0,0];
  let proteinSum = [0,0,0,0,0,0,0];
  let fatSum = [0,0,0,0,0,0,0];
  let sugarsSum = [0,0,0,0,0,0,0];
  let sodiumSum = [0,0,0,0,0,0,0];
  let cholesterolSum = [0,0,0,0,0,0,0];
  let fattyAcidSum = [0,0,0,0,0,0,0];
  let transFatSum = [0,0,0,0,0,0,0];

  let kcalTotal = 0;
  let carbohydrateTotal = 0;
  let proteinTotal = 0;
  let fatTotal = 0;
  let sugarsTotal = 0;
  let sodiumTotal = 0;
  let cholesterolTotal = 0;
  let fattyAcidTotal = 0;
  let transFatTotal = 0;

  let registeredDateNum = 0;

  for (let i = 0; i<todayDates.length; i++){
    for (let j = 0; j < dailyList.dailyTotalVo.length; j++){

      let formattedDate = todayDates[i];
      let mealDay = dailyList.dailyTotalVo[j].mealDate;

        if (formattedDate === mealDay) {
          // console.log("성공@@@@@@@@@@@@")
          kcalSum[i] = dailyList.dailyTotalVo[j].totalFoodKcal;
          carbohydrateSum[i] = dailyList.dailyTotalVo[j].totalFoodCarbohydrate;
          proteinSum[i] = dailyList.dailyTotalVo[j].totalFoodProtein;
          fatSum[i] = dailyList.dailyTotalVo[j].totalFoodFat;
          sugarsSum[i] = dailyList.dailyTotalVo[j].totalFoodSugars;
          sodiumSum[i] = dailyList.dailyTotalVo[j].totalFoodSodium;
          cholesterolSum[i] = dailyList.dailyTotalVo[j].totalFoodCholesterol;
          fattyAcidSum[i] = dailyList.dailyTotalVo[j].totalFoodFattyAcid;
          transFatSum[i] = dailyList.dailyTotalVo[j].totalFoodTransFat;

          registeredDateNum += 1;

          console.log(kcalSum[0]);
          console.log(registeredDateNum+'$$$$$$$$');
        }
    }
  }
  console.log(kcalSum);    // 해당 위치에 넣어진 값인 1000이 뜸
  console.log(carbohydrateSum);
  console.log(proteinSum);
  console.log(fatSum);
  console.log(sugarsSum);
  console.log(sodiumSum);
  console.log(cholesterolSum);
  console.log(fattyAcidSum);
  console.log(transFatSum);
  console.log("$$$$$$$$$$$$$");

  for (let i = 0; i<7; i++){
    kcalTotal += kcalSum[i];
    carbohydrateTotal += carbohydrateSum[i];
    proteinTotal += proteinSum[i];
    fatTotal += fatSum[i];
    sugarsTotal += sugarsSum[i];
    sodiumTotal += sodiumSum[i];
    cholesterolTotal += cholesterolSum[i];
    fattyAcidTotal += fattyAcidSum[i];
    transFatTotal += transFatSum[i];
  }

  console.log(kcalTotal+'잘 들어가나');
  console.log(cholesterolTotal+'잘 들어가나');

  // *****바 차트
  // 일간 탄단지 섭취 그람수
  const data = {
    labels: [todayDates[6], todayDates[5], todayDates[4], todayDates[3], todayDates[2], todayDates[1], todayDates[0]],
    datasets: [{
      label: '탄수화물',
      data: [carbohydrateSum[6], carbohydrateSum[5], carbohydrateSum[4], carbohydrateSum[3], carbohydrateSum[2], carbohydrateSum[1], carbohydrateSum[0]],
      backgroundColor: [
        'rgba(32, 178, 170, 0.2)'
      ],
      borderColor: [
        'rgba(32, 178, 170, 1)'
      ],
      borderWidth: 1
    },{
      label: '단백질',
      data: [proteinSum[6], proteinSum[5], proteinSum[4], proteinSum[3], proteinSum[2], proteinSum[1], proteinSum[0]],
      backgroundColor: [
        'rgba(255, 26, 104, 0.2)'
      ],
      borderColor: [
        'rgba(255, 26, 104, 1)'
      ],
      borderWidth: 1
    },{
      label: '지방',
      data: [fatSum[6], fatSum[5], fatSum[4], fatSum[3], fatSum[2], fatSum[1], fatSum[0]],
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

  // *****도넛 차트
  //일간 섭취 칼로리의 평균값과 탄단지 평균값값
 const dataDonut = {
    // labels: ['탄수화물', '단백질', '지방'],
    datasets: [{
      label: '영양소',
      data: [Math.round(carbohydrateTotal*4/registeredDateNum), Math.round(proteinTotal*4/registeredDateNum), Math.round(fatTotal*9/registeredDateNum)],
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

      // Math.round((eatKcal/dayKcal) * 100)

      let text = Math.round(kcalTotal/registeredDateNum);
      if (isNaN(text)) {
        text = 0;
      }
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

  let recommendCarbohydrateNum = $('.recommendCarbohydrate-num').val();
  let recommendProteinNum= $('.recommendProtein-num').val();
  let recommendFatNum= $('.recommendFat-num').val();

  let textCarbohydrate ='';
  textCarbohydrate = Math.round(carbohydrateTotal/registeredDateNum);
  // $('.taken-carbohydrate-gram').text(textCarbohydrate);
  if (isNaN(textCarbohydrate)) {
    $('.taken-carbohydrate-gram').text(0);
  }else {
    $('.taken-carbohydrate-gram').text(textCarbohydrate);
  }

  let textProtein ='';
  textProtein = Math.round(proteinTotal/registeredDateNum);
  // $('.taken-protein-gram').text(textProtein);
  if (isNaN(textProtein)) {
    $('.taken-protein-gram').text(0);
  }else {
    $('.taken-protein-gram').text(textProtein);
  }

  let textFat ='';
  textFat = Math.round(fatTotal/registeredDateNum);
  // $('.taken-fat-gram').text(textFat);
  if (isNaN(textFat)) {
    $('.taken-fat-gram').text(0);
  }else {
    $('.taken-fat-gram').text(textFat);
  }



  graph1();
  function graph1(){
    let graph1Width = ((carbohydrateTotal/registeredDateNum)/recommendCarbohydrateNum) *100;
    $('.graph1-span').animate({
      width: graph1Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  graph2();
  function graph2(){
    let graph2Width = ((proteinTotal/registeredDateNum)/recommendProteinNum) *100;
    $('.graph2-span').animate({
      width: graph2Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  graph3();
  function graph3(){
    let graph3Width = ((fatTotal/registeredDateNum)/recommendFatNum) *100;
    $('.graph3-span').animate({
      width: graph3Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
    }, 500);
  }

  //총 섭취 칼로리의 탄단지 % 비교
  let weekCarbohydrateKcal = carbohydrateTotal*4;
  let weekProteinKcal = proteinTotal*4;
  let weekFatKcal = fatTotal*9;
  let weekCarbohydratePercent = Math.round((weekCarbohydrateKcal/kcalTotal) * 100);
  let weekProteinPercent = Math.round((weekProteinKcal/kcalTotal) * 100);
  let weekFatPercent = Math.round((weekFatKcal/kcalTotal) * 100);

  if (isNaN(weekCarbohydratePercent)) {
    $('.carbohydrate-percent-num').text(0);
  } else {
    $('.carbohydrate-percent-num').text(weekCarbohydratePercent);
  }

  if (isNaN(weekProteinPercent)) {
    $('.protein-percent-num').text(0);
  } else {
    $('.protein-percent-num').text(weekProteinPercent);
  }

  if (isNaN(weekFatPercent)) {
    $('.fat-percent-num').text(0);
  } else {
    $('.fat-percent-num').text(weekFatPercent);
  }



// ***** 전체 영양소
//  일주일 평균값
// setup
  const dataNutri = {
    labels: ['탄수화물(g)', '단백질(g)', '지방(g)', '당류(g)', '나트륨(mg)', '콜레스테롤(mg)', '포화지방산(g)', '트랜스지방(g)'],
    datasets: [{
      label: '섭취 영양성분',
      data: [Math.round(carbohydrateTotal/registeredDateNum), Math.round(proteinTotal/registeredDateNum), Math.round(fatTotal/registeredDateNum),
        Math.round(sugarsTotal/registeredDateNum), Math.round(sodiumTotal/registeredDateNum), Math.round(cholesterolTotal/registeredDateNum), Math.round(fattyAcidTotal/registeredDateNum),Math.round(transFatTotal/registeredDateNum)],
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









