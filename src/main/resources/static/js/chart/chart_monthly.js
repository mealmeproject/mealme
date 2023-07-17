import * as monthly from './todayExportFunction.js';

// 오늘의 날짜를 기준으로 달 뽑기
let currentDate = new Date();
let sixMonthsAgo = new Date(currentDate.getFullYear(), currentDate.getMonth() - 6, 1);

let monthlyArray = [];
while (sixMonthsAgo <= currentDate) {
  let year = sixMonthsAgo.getFullYear();
  let month = String(sixMonthsAgo.getMonth() + 1).padStart(2, '0');
  let formattedDate = `${year}-${month}`;

  monthlyArray.unshift(formattedDate);

  sixMonthsAgo.setMonth(sixMonthsAgo.getMonth() + 1);
}

console.log(monthlyArray);

monthly.getMonthlyList(showMonthly, showError)
function showMonthly(monthlyList){
  console.log(monthlyList);

  let kcalList = new Array(7);
  let carbohydrateList = new Array(7);
  let proteinList = new Array(7);
  let fatList = new Array(7);
  let sugarsList = new Array(7);
  let sodiumList = new Array(7);
  let cholesterolList = new Array(7);
  let fattyAcidList = new Array(7);
  let transFatList = new Array(7);
  let countMealTimeList = new Array(7);

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


  for (let i = 0; i < monthlyArray.length; i++){
    for (let j = 0; j < monthlyList.dailyTotalVo.length; j++){
      let formattedDate = monthlyArray[i];
      let mealDay = monthlyList.dailyTotalVo[j].mealDate;
      if (formattedDate === mealDay) {
        kcalList[i] = monthlyList.dailyTotalVo[j].totalFoodKcal;
        carbohydrateList[i] = monthlyList.dailyTotalVo[j].totalFoodCarbohydrate;
        proteinList[i] = monthlyList.dailyTotalVo[j].totalFoodProtein;
        fatList[i] = monthlyList.dailyTotalVo[j].totalFoodFat;
        sugarsList[i] = monthlyList.dailyTotalVo[j].totalFoodSugars;
        sodiumList[i] = monthlyList.dailyTotalVo[j].totalFoodSodium;
        cholesterolList[i] = monthlyList.dailyTotalVo[j].totalFoodCholesterol;
        fattyAcidList[i] = monthlyList.dailyTotalVo[j].totalFoodFattyAcid;
        transFatList[i] = monthlyList.dailyTotalVo[j].totalFoodTransFat;
        countMealTimeList[i] = monthlyList.dailyTotalVo[j].countMealTime;
        break;
      }else {
        kcalList[i] = 0;
        carbohydrateList[i] = 0;
        proteinList[i] = 0;
        fatList[i] = 0;
        sugarsList[i] = 0;
        sodiumList[i] = 0;
        cholesterolList[i] = 0;
        fattyAcidList[i] = 0;
        transFatList[i] = 0;
        countMealTimeList[i] = 0;
      }
    }
  }
  // console.log(kcalList);
  // console.log(carbohydrateList);
  // console.log(countMealTimeList);
  for (let i = 0; i<7; i++){
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
  // console.log(countMealTimeTotal);
  // console.log(proteinTotal);
  // console.log(kcalTotal);


  const data = {
    labels: [monthlyArray[6], monthlyArray[5], monthlyArray[4], monthlyArray[3], monthlyArray[2], monthlyArray[1], monthlyArray[0]],
    datasets: [{
      label: '탄수화물',
      data: [carbohydrateList[6]/countMealTimeList[6], carbohydrateList[5]/countMealTimeList[5], carbohydrateList[4]/countMealTimeList[4], carbohydrateList[3]/countMealTimeList[3],
        carbohydrateList[2]/countMealTimeList[2], carbohydrateList[1]/countMealTimeList[1], carbohydrateList[0]/countMealTimeList[0]],
      backgroundColor: [
        'rgba(32, 178, 170, 0.2)'
      ],
      borderColor: [
        'rgba(32, 178, 170, 1)'
      ],
      borderWidth: 1
    },{
      label: '단백질',
      data: [proteinList[6]/countMealTimeList[6], proteinList[5]/countMealTimeList[5], proteinList[4]/countMealTimeList[4], proteinList[3]/countMealTimeList[3],
        proteinList[2]/countMealTimeList[2], proteinList[1]/countMealTimeList[1], proteinList[0]/countMealTimeList[0]],
      // data 숫자 칸에서 사칙연산 가능!!
      // 칼로리 계산 : 각자 받은 영양소 g에 탄수*4, 단백*4, 지방*9
      backgroundColor: [
        'rgba(255, 26, 104, 0.2)'
      ],
      borderColor: [
        'rgba(255, 26, 104, 1)'
      ],
      borderWidth: 1
    },{
      label: '지방',
      data: [fatList[6]/countMealTimeList[6], fatList[5]/countMealTimeList[5], fatList[4]/countMealTimeList[4], fatList[3]/countMealTimeList[3],
        fatList[2]/countMealTimeList[2], fatList[1]/countMealTimeList[1], fatList[0]/countMealTimeList[0]],
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

  let recommendCarbohydrateNum = $('.recommendCarbohydrate-num').text();
  let recommendProteinNum= $('.recommendProtein-num').text();
  let recommendFatNum= $('.recommendFat-num').text();

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
  let sixMonthCarbohydrateKcal = carbohydrateTotal*4;
  let sixMonthProteinKcal = proteinTotal*4;
  let sixMonthFatKcal = fatTotal*9;
  let sixMonthTotalKcal = sixMonthCarbohydrateKcal+sixMonthProteinKcal+sixMonthFatKcal;

  // let sixMonthCarbohydratePercent = Math.round((sixMonthCarbohydrateKcal/sixMonthTotalKcal) * 100);
  let sixMonthProteinPercent = Math.round((sixMonthProteinKcal/sixMonthTotalKcal) * 100);
  let sixMonthFatPercent = Math.round((sixMonthFatKcal/sixMonthTotalKcal) * 100);
  let sixMonthCarbohydratePercent = 100-(sixMonthProteinPercent+sixMonthFatPercent);

  if (isNaN(sixMonthCarbohydratePercent)) {
    $('.carbohydrate-percent-num').text(0);
  } else {
    $('.carbohydrate-percent-num').text(sixMonthCarbohydratePercent);
  }

  if (isNaN(sixMonthProteinPercent)) {
    $('.protein-percent-num').text(0);
  } else {
    $('.protein-percent-num').text(sixMonthProteinPercent);
  }

  if (isNaN(sixMonthFatPercent)) {
    $('.fat-percent-num').text(0);
  } else {
    $('.fat-percent-num').text(sixMonthFatPercent);
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













