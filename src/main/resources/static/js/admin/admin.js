// 예제 띄우기
// https://www.chartjs.org/docs/latest/getting-started/

// const ctx = document.getElementById('myChart');

  // new Chart(ctx, {
  //   type: 'bar',
  //   data: {
  //     labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
  //     datasets: [{
  //       label: '# of Votes',
  //       data: [12, 19, 3, 5, 2, 3],
  //       borderWidth: 1
  //     }]
  //   },
  //   options: {
  //     scales: {
  //       y: {
  //         beginAtZero: true
  //       }
  //     }
  //   }
  // });

  // Line 차트로 띄우기

  var ctx = document.getElementById('myChart');

  var data = {
    labels: ['Label 1', 'Label 2', 'Label 3', 'Label 4', 'Label 4','Label 4','Label 4'],
    datasets: [
      {
        label: 'Dataset 1',
        data: [10, 20, 38,40],
        borderColor: 'red',
        backgroundColor: 'transparent',
      },
      {
        label: 'Dataset 2',
        data: [30, 20, 14,50],
        borderColor: 'blue',
        backgroundColor: 'transparent',
      },
      {
        label: 'Dataset 3',
        data: [20, 70, 7, 30],
        borderColor: 'yellow',
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
    },
  });


