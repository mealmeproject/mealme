import * as daily from '../module/daily.js';
import {getDailyRefund} from "../module/daily.js";


daily.getDailyOrder(chartDate,dailyOrder,showError);

chartDate();

function chartDate() {
  let today = new Date();
  let todayDates = [];
  for(let i = 0; i < 4; i++){
    let date = new Date(today);



    date.setDate(date.getDate()-i);

    let year = date.getFullYear();
    let month = String(date.getMonth()+1).padStart(2,'0');
    let day = String(date.getDate()).padStart(2,'0');

    let formattedDate = month + '월' + day + '일';
    todayDates.push(formattedDate);
    console.log(formattedDate);



  }


  console.log(todayDates);

  let dayOfWeek = today.getDay();
  let weekdays = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
  let todayIs = weekdays[dayOfWeek];
  document.getElementById("weekDay").textContent = todayIs;
  $('#dailyCountTitle1').text(todayDates[3]);
  $('#dailyCountTitle2').text(todayDates[2]);
  $('#dailyCountTitle3').text(todayDates[1]);
  $('.dailyCountTitleToday').text(todayDates[0]);
  $('.todayDayIs').text(todayDates[0]);

}


// 주문
function dailyOrder(order) {
  console.log(order);
  $('.dailyCountOrderPrice1').text(order[0].totalPrice);
  $('.dailyCountOrderPrice2').text(order[1].totalPrice);
  $('.dailyCountOrderPrice3').text(order[2].totalPrice);
  $('.dailyCountOrderPrice4').text(order[3].totalPrice);
 console.log(order[3].totalOrderCount);

  $('.dailyCountOrderCount1').text(order[0].orderNumberCount);
  $('.dailyCountOrderCount2').text(order[1].orderNumberCount);
  $('.dailyCountOrderCount3').text(order[2].orderNumberCount);
  $('.dailyCountOrderCount4').text(order[3].orderNumberCount);


}

daily.getDailyOrder(chartDate,dailyOrder,showError);

//주간 주문,결제,환불
function weeklyTotal(order){
  let avgPrice = (order.weeklyOrder[0].totalPrice + order.weeklyOrder[1].totalPrice + order.weeklyOrder[2].totalPrice + order.weeklyOrder[3].totalPrice + order.weeklyOrder[4].totalPrice + order.weeklyOrder[5].totalPrice + order.weeklyOrder[6].totalPrice)/7;
  $('.weeklyCountOrderPriceAvg').text(Math.round(avgPrice));
  let avgCount = (order.weeklyOrder[0].orderNumberCount + order.weeklyOrder[1].orderNumberCount + order.weeklyOrder[2].orderNumberCount + order.weeklyOrder[3].orderNumberCount + order.weeklyOrder[4].orderNumberCount + order.weeklyOrder[5].orderNumberCount + order.weeklyOrder[6].orderNumberCount)/7;
  $('.weeklyCountOrderCountAvg').text(Math.round(avgCount));

  let avgPrice2 = (order.weeklyPayment[0].totalPrice + order.weeklyPayment[1].totalPrice + order.weeklyPayment[2].totalPrice + order.weeklyPayment[3].totalPrice + order.weeklyPayment[4].totalPrice + order.weeklyPayment[5].totalPrice + order.weeklyPayment[6].totalPrice)/7;
  $('.weeklyCountPayedPriceAvg').text(Math.round(avgPrice2));
  let avgCount2 = (order.weeklyPayment[0].orderNumberCount + order.weeklyPayment[1].orderNumberCount + order.weeklyPayment[2].orderNumberCount + order.weeklyPayment[3].orderNumberCount + order.weeklyPayment[4].orderNumberCount + order.weeklyPayment[5].orderNumberCount + order.weeklyPayment[6].orderNumberCount)/7;
  $('.weeklyCountPayedCountAvg').text(Math.round(avgCount2));

  let avgPrice3 = (order.weeklyRefund[0].totalPrice + order.weeklyRefund[1].totalPrice + order.weeklyRefund[2].totalPrice + order.weeklyRefund[3].totalPrice + order.weeklyRefund[4].totalPrice + order.weeklyRefund[5].totalPrice + order.weeklyRefund[6].totalPrice)/7;
  $('.weeklyCountRefundPriceAvg').text(Math.round(avgPrice3));
  let avgCount3 = (order.weeklyRefund[0].orderNumberCount + order.weeklyRefund[1].orderNumberCount + order.weeklyRefund[2].orderNumberCount + order.weeklyRefund[3].orderNumberCount + order.weeklyRefund[4].orderNumberCount + order.weeklyRefund[5].orderNumberCount + order.weeklyRefund[6].orderNumberCount)/7;
  $('.weeklyCountRefundCountAvg').text(Math.round(avgCount3));

  let totalPrice = order.weeklyOrder[0].totalPrice + order.weeklyOrder[1].totalPrice + order.weeklyOrder[2].totalPrice + order.weeklyOrder[3].totalPrice + order.weeklyOrder[4].totalPrice + order.weeklyOrder[5].totalPrice + order.weeklyOrder[6].totalPrice;
  let totalCount = order.weeklyOrder[0].orderNumberCount + order.weeklyOrder[1].orderNumberCount + order.weeklyOrder[2].orderNumberCount + order.weeklyOrder[3].orderNumberCount + order.weeklyOrder[4].orderNumberCount + order.weeklyOrder[5].orderNumberCount + order.weeklyOrder[6].orderNumberCount;
  $('.weeklyCountOrderPriceTotal').text(totalPrice);
  $('.weeklyCountOrderCountTotal').text(totalCount);

  let totalPrice2 = order.weeklyPayment[0].totalPrice + order.weeklyPayment[1].totalPrice + order.weeklyPayment[2].totalPrice + order.weeklyPayment[3].totalPrice + order.weeklyPayment[4].totalPrice + order.weeklyPayment[5].totalPrice + order.weeklyPayment[6].totalPrice;
  let totalCount2 = order.weeklyPayment[0].orderNumberCount + order.weeklyPayment[1].orderNumberCount + order.weeklyPayment[2].orderNumberCount + order.weeklyPayment[3].orderNumberCount + order.weeklyPayment[4].orderNumberCount + order.weeklyPayment[5].orderNumberCount + order.weeklyPayment[6].orderNumberCount;
  $('.weeklyCountPayedPriceTotal').text(totalPrice2);
  $('.weeklyCountPayedCountTotal').text(totalCount2);

  let totalPrice3 = order.weeklyRefund[0].totalPrice + order.weeklyRefund[1].totalPrice + order.weeklyRefund[2].totalPrice + order.weeklyRefund[3].totalPrice + order.weeklyRefund[4].totalPrice + order.weeklyRefund[5].totalPrice + order.weeklyRefund[6].totalPrice;
  let totalCount3 = order.weeklyRefund[0].orderNumberCount + order.weeklyRefund[1].orderNumberCount + order.weeklyRefund[2].orderNumberCount + order.weeklyRefund[3].orderNumberCount + order.weeklyRefund[4].orderNumberCount + order.weeklyRefund[5].orderNumberCount + order.weeklyRefund[6].orderNumberCount;
  $('.weeklyCountRefundPriceTotal').text(totalPrice3);
  $('.weeklyCountRefundCountTotal').text(totalCount3);
}

daily.getWeeklyTotal(weeklyTotal,showError);


//월간 주문,결제,환불
function monthlyTotal(order){
  let sum = 0;
  let sum2 = 0;
  let sum3 = 0;
  let sum4 = 0;
  let sum5 = 0;
  let sum6 = 0;
  for (let i = 0; i < 30; i++) {
    sum += order.monthlyOrder[i].totalPrice;
    sum2 += order.monthlyOrder[i].orderNumberCount;
    sum3 += order.monthlyPayment[i].totalPrice;
    sum4 += order.monthlyPayment[i].orderNumberCount;
    sum5 += order.monthlyRefund[i].totalPrice;
    sum6 += order.monthlyRefund[i].orderNumberCount;
  }
  console.log(sum);
  let avgPrice = sum/30;
  $('.monthlyCountOrderPriceAvg').text(Math.round(avgPrice));
  let avgCount = sum2/30;
  $('.monthlyCountOrderCountAvg').text(Math.round(avgCount));

  let avgPrice2 = sum3/30;
  $('.monthlyCountPayedPriceAvg').text(Math.round(avgPrice2));
  let avgCount2 = sum4/30;
  $('.monthlyCountPayedCountAvg').text(Math.round(avgCount2));

  let avgPrice3 = sum5/30;
  $('.monthlyCountRefundPriceAvg').text(Math.round(avgPrice3));
  let avgCount3 = sum6/30;
  $('.monthlyCountRefundCountAvg').text(Math.round(avgCount3));

  let totalPrice = sum;
  let totalCount = sum2;
  $('.monthlyCountOrderPriceTotal').text(totalPrice);
  $('.monthlyCountOrderCountTotal').text(totalCount);

  let totalPrice2 = sum3;
  let totalCount2 = sum4;
  $('.monthlyCountPayedPriceTotal').text(totalPrice2);
  $('.monthlyCountPayedCountTotal').text(totalCount2);

  let totalPrice3 = sum5;
  let totalCount3 = sum6;
  $('.monthlyCountRefundPriceTotal').text(totalPrice3);
  $('.monthlyCountRefundCountTotal').text(totalCount3);
}

daily.getMonthlyTotal(monthlyTotal,showError);

//결제
monthlyCountRefundPriceTotal
function dailyPayment(order) {
  console.log(order);
  $('.dailyCountPayedPrice1').text(order[0].totalPrice);
  $('.dailyCountPayedPrice2').text(order[1].totalPrice);
  $('.dailyCountPayedPrice3').text(order[2].totalPrice);
  $('.dailyCountPayedPrice4').text(order[3].totalPrice);


  $('.dailyCountPayedCount1').text(order[0].orderNumberCount);
  $('.dailyCountPayedCount2').text(order[1].orderNumberCount);
  $('.dailyCountPayedCount3').text(order[2].orderNumberCount);
  $('.dailyCountPayedCount4').text(order[3].orderNumberCount);



}

daily.getDailyPayment(chartDate,dailyPayment,showError);

//주간 결제



//환불

function dailyRefund(order) {
  console.log(order);
  $('.dailyCountRefundPrice1').text(order[0].totalPrice);
  $('.dailyCountRefundPrice2').text(order[1].totalPrice);
  $('.dailyCountRefundPrice3').text(order[2].totalPrice);
  $('.dailyCountRefundPrice4').text(order[3].totalPrice);


  $('.dailyCountRefundCount1').text(order[0].orderNumberCount);
  $('.dailyCountRefundCount2').text(order[1].orderNumberCount);
  $('.dailyCountRefundCount3').text(order[2].orderNumberCount);
  $('.dailyCountRefundCount4').text(order[3].orderNumberCount);


}

daily.getDailyRefund(chartDate,dailyRefund,showError);

// 상태에 따른 개수 뽑기
function statusCount(status){
console.log(status)
  $('#preparing').text(status[0].orderCount);
  $('#shipping').text(status[1].orderCount);
  $('#cancel-count').text(status[2].orderCount);
  $('#change-count').text(status[3].orderCount);
  $('#refund-count').text(status[4].orderCount);

}

daily.getStatusCount(statusCount,showError);

//일반,기업 회원 조회 및 총합
function userTotal(user){
  console.log(user)
  $('#n-user').text(user.userCount);
  $('#c-user').text(user.companyCount);
  $('#user-total').text(user.totalCount);

}
daily.getUserTotal(userTotal,showError);





//차트
daily.getWeeklyChart(weeklyChart,showError);
function weeklyChart(order) {
  console.log(order);
  let today = new Date();
  let todayDates = [];
  for (let i = 0; i < 7; i++) {
    let date = new Date(today);
    date.setDate(date.getDate() - i);

    let year = date.getFullYear();
    let month = String(date.getMonth() + 1).padStart(2, '0');
    let day = String(date.getDate()).padStart(2, '0');

    let formattedDate = month + '월' + day + '일';
    todayDates.push(formattedDate);
  }
  var ctx = document.getElementById('myChart');

  var data = {

    labels: [todayDates[6], todayDates[5], todayDates[4], todayDates[3], todayDates[2], todayDates[1], todayDates[0]],
    datasets: [
      {
        label: '주문',
        data: [order.weeklyOrder[0].totalPrice, order.weeklyOrder[1].totalPrice, order.weeklyOrder[2].totalPrice, order.weeklyOrder[3].totalPrice, order.weeklyOrder[4].totalPrice, order.weeklyOrder[5].totalPrice, order.weeklyOrder[6].totalPrice],
        borderColor: 'rgba(32, 178, 170, 0.7)',
        backgroundColor: 'transparent',
      },
      {
        label: '결제',
        data: [order.weeklyPayment[0].totalPrice, order.weeklyPayment[1].totalPrice, order.weeklyPayment[2].totalPrice, order.weeklyPayment[3].totalPrice, order.weeklyPayment[4].totalPrice, order.weeklyPayment[5].totalPrice, order.weeklyPayment[6].totalPrice],
        borderColor: 'rgba(255, 26, 104, 0.7)',
        backgroundColor: 'transparent',
      },
      {
        label: '환불',
        data: [order.weeklyRefund[0].totalPrice, order.weeklyRefund[1].totalPrice, order.weeklyRefund[2].totalPrice, order.weeklyRefund[3].totalPrice, order.weeklyRefund[4].totalPrice, order.weeklyRefund[5].totalPrice, order.weeklyRefund[6].totalPrice],
        borderColor: 'rgba(255, 206, 86, 0.7)',
        backgroundColor: 'transparent',
      }
      // Add more datasets if needed
    ],
  };

  new Chart(ctx, {
    type: 'line',
    data: data,
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: 'top',
        },
        title: {
          display: true

        },
      },
      scales:{
        x:{
          ticks:{
            font:{
              size: 10,
            }
          }
        }
      }
    },

  });
}


//결제 차트

//
// daily.getWeeklyPayment(weeklyPayment,showError);
// function weeklyPayment(order) {
//   let today = new Date();
//   let todayDates = [];
//   for (let i = 0; i < 7; i++) {
//     let date = new Date(today);
//     date.setDate(date.getDate() - i);
//
//     let year = date.getFullYear();
//     let month = String(date.getMonth() + 1).padStart(2, '0');
//     let day = String(date.getDate()).padStart(2, '0');
//
//     let formattedDate = month + '월' + day + '일';
//     todayDates.push(formattedDate);
//   }
//   var ctx = document.getElementById('myChart');
//
//   var data = {
//
//     labels: [todayDates[6], todayDates[5], todayDates[4], todayDates[3], todayDates[2], todayDates[1], todayDates[0]],
//     datasets: [
//       {
//         label: 'Dataset 1',
//         data: [],
//         borderColor: 'red',
//         backgroundColor: 'transparent',
//       },
//       {
//         label: 'Dataset 2',
//         data: [order[0].totalPrice, order[1].totalPrice, order[2].totalPrice, order[3].totalPrice, order[4].totalPrice, order[5].totalPrice, order[6].totalPrice],
//         borderColor: 'blue',
//         backgroundColor: 'transparent',
//       },
//       {
//         label: 'Dataset 3',
//         data: [20, 70, 7, 30, 20, 38, 40],
//         borderColor: 'yellow',
//         backgroundColor: 'transparent',
//       }
//       // Add more datasets if needed
//     ],
//   };
//
//   new Chart(ctx, {
//     type: 'line',
//     data: data,
//     options: {
//       responsive: true,
//       plugins: {
//         legend: {
//           position: 'top',
//         },
//         title: {
//           display: true
//
//         },
//       },
//     },
//   });
// }
//




function showError(a, b, c){
  console.log(c);
}