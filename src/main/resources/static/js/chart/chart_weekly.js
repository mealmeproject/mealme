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





// *****바 차트
const data = {
  labels: ['06.13', '06.14', '06.15', '06.16', '06.17', '06.18', '06.19'],
  datasets: [{
    label: '탄수화물',
    data: [40*4, 60*4, 40*4, 55*4, 50*4, 55*4, 50*4],
    backgroundColor: [
      'rgba(32, 178, 170, 0.2)'      
    ],
    borderColor: [
      'rgba(32, 178, 170, 1)'
    ],
    borderWidth: 1
  },{
    label: '단백질',
    data: [37*4, 33*4, 30*4, 28*4, 21*4, 55*4, 50*4],
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
    data: [18*9, 22*9, 23*9, 14*9, 25*9, 23*9, 14*9],
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
    data: [50, 30, 20],
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
  
    let text = 875*3;
    // 칼로리 값을 변수에 저장
    ctx.save();
    console.log(top);
    ctx.font = '900 60px Nanum Gothic';
    ctx.fillStyle = 'rgba(50, 50, 50, 0.8)';
    ctx.textAlign = 'center';
    ctx.fillText(text, width/2 , height/2 + top-20);
    ctx.restore();

    ctx.save();
    console.log(top);
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

graph1();
function graph1(){
  let graph1Width = (100/228) *100;
  $('.graph1-span').animate({
    width: graph1Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
  }, 500);
}

graph2();
function graph2(){
  let graph2Width = (60/137) *100;
  $('.graph2-span').animate({
    width: graph2Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
  }, 500);
}

graph3();
function graph3(){
  let graph3Width = (10/40) *100;
  $('.graph3-span').animate({
    width: graph3Width + '%'    // 저장한 변수에 % 붙여서 width 적용하기
  }, 500);
}

// ***** 전체 영양소
// setup 
const dataNutri = {
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

// Instantly assign Chart.js version
// const chartVersionNutri = document.getElementById('chartVersion');
// chartVersion.innerText = Chart.version;

// 탄단지 퍼센트바













