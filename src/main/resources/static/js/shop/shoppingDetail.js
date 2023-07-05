

document.querySelectorAll('.button').forEach(button => {

    button.addEventListener('click', e => {
        button.classList.toggle('liked');
        if(button.classList.contains('liked')) {
            gsap.fromTo(button, {
                '--hand-rotate': 8
            }, {
                ease: 'none',
                keyframes: [{
                    '--hand-rotate': -45,
                    duration: .16,
                    ease: 'none'
                }, {
                    '--hand-rotate': 15,
                    duration: .12,
                    ease: 'none'
                }, {
                    '--hand-rotate': 0,
                    duration: .2,
                    ease: 'none',
                    clearProps: true
                }]
            });
        }
    })

});


function toggleFoodList() {
    const foodList = document.getElementById('food-list');
    foodList.classList.toggle('show');
}



// 각 상품 별점에 대한 이벤트 처리
const ratingElements = document.querySelectorAll('.rating');
ratingElements.forEach(ratingElement => {
    const stars = ratingElement.querySelectorAll('.star');

    // 초기 평가 표시
    const initialRating = ratingElement.dataset.rating;
    setRating(initialRating);

    // 클릭 이벤트 처리
    stars.forEach(star => {
        star.addEventListener('click', () => {
            const ratingValue = star.dataset.value;
            setRating(ratingValue);
        });
    });

    // 별점 설정 함수
    function setRating(ratingValue) {
        stars.forEach(star => {
            if (star.dataset.value <= ratingValue) {
                star.classList.add('selected');
            } else {
                star.classList.remove('selected');
            }
        });
    }
});

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

