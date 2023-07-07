

var countElement = document.getElementById('count');
var decreaseBtn = document.getElementById('decreaseBtn');
var increaseBtn = document.getElementById('increaseBtn');

// 초기 개수 값 설정
var count = parseInt(countElement.textContent);

// 개수를 감소시키는 함수
function decreaseCount() {
    if (count > 1) {
        count--;
        countElement.textContent = count;
    }
}

// 개수를 증가시키는 함수
function increaseCount() {
    count++;
    countElement.textContent = count;
}

// 버튼에 이벤트 리스너 등록
decreaseBtn.addEventListener('click', decreaseCount);
increaseBtn.addEventListener('click', increaseCount);



