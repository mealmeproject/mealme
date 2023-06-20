let slides = document.querySelector('.main1-slide-ul');
let slideImg = document.querySelectorAll('.main1-slide-ul li');
let currentIdx = 0;
let slideCount = slideImg.length;
let prev = document.querySelector('.prev'); //이전 버튼
let next = document.querySelector('.next'); //다음 버튼
let slideWidth = 1200; //슬라이드이미지 넓이
let slideMargin = 0; //슬라이드 끼리의 마진값

makeClone(); // 처음이미지와 마지막 이미지 복사 함수
initfunction(); //슬라이드 넓이와 위치값 초기화 함수

function makeClone() {
    let cloneSlide_first = slideImg[0].cloneNode(true);
    let cloneSlide_last = slides.lastElementChild.cloneNode(true);
    slides.append(cloneSlide_first);
    slides.insertBefore(cloneSlide_last, slides.firstElementChild);
}
function initfunction() {
    slides.style.width = (slideWidth + slideMargin) * (slideCount + 2) + 'px';
    slides.style.left = -(slideWidth + slideMargin) + 'px';
}

next.addEventListener('click', nextProcess);

setInterval(nextProcess, 3000);

function nextProcess(){
    //다음 버튼 눌렀을때
    if (currentIdx <= slideCount - 1) {
        //슬라이드이동
        slides.style.left = -(currentIdx + 2) * (slideWidth + slideMargin) + 'px';
        slides.style.transition = `${0.5}s ease-out`; //이동 속도
    }
    if (currentIdx === slideCount - 1) {
        //마지막 슬라이드 일때
        setTimeout(function () {
            //0.5초동안 복사한 첫번째 이미지에서, 진짜 첫번째 위치로 이동
            slides.style.left = -(slideWidth + slideMargin) + 'px';
            slides.style.transition = `${0}s ease-out`;
        }, 500);
        currentIdx = -1;
    }
    currentIdx += 1;
}
prev.addEventListener('click', function () {
    //이전 버튼 눌렀을때
    console.log(currentIdx);
    if (currentIdx >= 0) {
        slides.style.left = -currentIdx * (slideWidth + slideMargin) + 'px';
        slides.style.transition = `${0.5}s ease-out`;
    }
    if (currentIdx === 0) {
        setTimeout(function () {
            slides.style.left = -slideCount * (slideWidth + slideMargin) + 'px';
            slides.style.transition = `${0}s ease-out`;
        }, 500);
        currentIdx = slideCount;
    }
    currentIdx -= 1;
});


let best = document.getElementById('best');
function mouseover(){
    best.setAttribute("src","../img/닭가슴살오버.png");
}

function mouseleave(){
    best.setAttribute("src","../../img/닭가슴살.png");
}

let best2 = document.getElementById('best2');
function mouseover2(){
    best2.setAttribute("src","../../img/샐러드오버.png");
}

function mouseleave2(){
    best2.setAttribute("src","../../img/샐러드,과일.png");
}


let best3 = document.getElementById('best3');
function mouseover3(){
    best3.setAttribute("src","../img/건강식품오버.png");
}

function mouseleave3(){
    best3.setAttribute("src","../img/건강식품.png");
}


let best4 = document.getElementById('best4');
function mouseover4(){
    best4.setAttribute("src","../img/음료오버.png");
}

function mouseleave4(){
    best4.setAttribute("src","../img/음료,차,프로틴.png");
}


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