

let $input = $("#post-image");
let $imgList = $(".img-list");
// console.log($input);
const modal2 = document.querySelector('.modal2');

// file change이벤트로 미리보기 갱신하기
$input.on("change", function () {
    let files = this.files;
    //   console.log(files);

    // 길이 체크함수 (files, 원하는 길이)
    let newFiles = checkLength(files, 4);

    appendImg(newFiles);
});

// 클릭 이벤트로 이미지 지우고 미리보기 갱신하기
$imgList.on("click", function (e) {
    let name = $(e.target).data("name");
    removeImg(name);
    appendImg($input[0].files);
});

//미리보기 삭제 함수
function removeImg(name) {
    let dt = new DataTransfer();

    let files = $input[0].files;

    for (let i = 0; i < files.length; i++) {
        if (files[i].name !== name) {
            dt.items.add(files[i]);
        }
    }
    $input[0].files = dt.files;
}

//파일 길이 체크 함수(체크할 files객체, 제한할 길이)
function checkLength(files, num) {
    if (files.length > num) {
        alert(`파일은 최대 ${num}개까지만 첨부 가능합니다.`);
        // 최대 수를 넘으면 비어있는 files객체 반환
        return new DataTransfer().files;
    }
    return files;
}

// 이미지 미리보기 처리 함수
// 이미지 수가 4개보다 적으면 기본이미지로 대체함
function appendImg(files) {
    for (let i = 0; i < 4; i++) {
        if (i < files.length) {
            let src = URL.createObjectURL(files[i]);

            $imgList
                .eq(i)
                .css("background-image", `url(${src})`)
                .css("background-size", "cover")
                .data("name", `${files[i].name}`);

            $imgList.eq(i).addClass("x-box");
        } else {
            $imgList
                .eq(i)
                .css(
                    "background",
                    "url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIzNiIgaGVpZ2h0PSIzNiI+PGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48ZyBzdHJva2U9IiNCNUI1QjUiIHN0cm9rZS13aWR0aD0iMS41IiB0cmFuc2Zvcm09InRyYW5zbGF0ZSg0IDQpIj48cmVjdCB3aWR0aD0iMjgiIGhlaWdodD0iMjgiIHJ4PSIzLjUiLz48Y2lyY2xlIGN4PSI4LjU1NiIgY3k9IjguNTU2IiByPSIyLjMzMyIvPjxwYXRoIGQ9Ik0yOCAxOC42NjdsLTcuNzc3LTcuNzc4TDMuMTExIDI4Ii8+PC9nPjxwYXRoIGQ9Ik0wIDBoMzZ2MzZIMHoiLz48L2c+PC9zdmc+) no-repeat 50% #f2f2f2"
                )
                .data("name", null);
            $imgList.eq(i).removeClass("x-box");
        }
    }
}

//
// // 서머노틐`
$(document).ready(function() {
    $('#summernote').summernote({
        height: 419,
        lang: 'ko-KR',
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'italic', 'underline', 'clear']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['insert', ['link', 'picture', 'video']],
            ['para', ['ul', 'ol', 'paragraph']],
        ],
        placeholder:
            "<br>* 식단에 대해서 작성해주세요 ! <br>",
    });
});

// 모달

const modal = document.querySelector('.modal');
const addImgBoxes = document.querySelectorAll(".add-img-box");


addImgBoxes.forEach(function (box) {
    box.addEventListener("click", openModal);
});

function openModal(event) {
    event.stopPropagation();
    modal.setAttribute("style", "display: block;");
}


const closeModalButton = document.getElementById("close-modal");

closeModalButton.addEventListener('click', function(event) {
    event.stopPropagation();
    console.log("닫는다!");
    modal.setAttribute("style", "display: none;");
});


function showError(a, b, c){
    console.error(c);
}

// 음식 검색

$('#search-img-box').click(function() {
    let foodName = $('#food-search-input').val();
    console.log("검색 버튼 클릭 !")
    searchFood(foodName, showSearchFoodList, showError);
});

function selectData(target) {
    foodSelectInfo = target;
}

// 검색
function searchFood(foodName, callback, error){
    console.log("음식 검색중 !" + foodName + " 검색중~!");
    $.ajax({
        type : "get",
        url : `http://openapi.foodsafetykorea.go.kr/api/ce2cef45c4b245de8356/I2790/json/1/5/DESC_KOR=${foodName}`,
        dataType : 'json',
        success : function (result){
            if (callback){
                callback(result);
            }
        },
        error : error
    });
}


var foodArray;
var foodSelectInfo;
var foodSelectInfoResult;
function showSearchFoodList(map){
    console.log("음식 리스트 뽑음 !");
    console.log(map);
    foodArray = map.I2790.row;
    console.log(foodArray);
    let newSearchResults = "";
    map.I2790.row.forEach(r => {
        newSearchResults += `
              <li>
                  <div class="search-food-box">
                      <div class="food-number" style="display: none">${r.FOOD_CD}</div>
                      <h2 class="search-food-name">${r.DESC_KOR}</h2>
                      <div class="search-food-weight">
                          <span class="category-name">음식량:</span><span class="food-data">${r.SERVING_SIZE}</span>g
                      </div>
                      <div class="search-food-kcal">
                          <span class="category-name">칼로리:</span><span class="food-data">${r.NUTR_CONT1}</span>kcal
                      </div>
                      <div class="search-food-carbohydrate">
                          <span class="category-name">탄수화물:</span><span class="food-data">${r.NUTR_CONT2}</span>g
                      </div>
                      <div class="search-food-protein">
                          <span class="category-name">단백질:</span><span class="food-data">${r.NUTR_CONT3}</span>g
                      </div>
                      <div class="search-food-fat">
                          <span class="category-name">지방:</span><span class="food-data">${r.NUTR_CONT4}</span>g
                      </div>
                      <div class="search-food-sugars">
                          <span class="category-name">당류:</span><span class="food-data">${r.NUTR_CONT5}</span>g
                      </div>
                      <div class="search-food-sodium">
                          <span class="category-name">나트륨:</span><span class="food-data">${r.NUTR_CONT6}</span>mg
                      </div>
                      <div class="search-food-cholesterol">
                          <span class="category-name">콜레스테롤:</span><span class="food-data">${r.NUTR_CONT7}</span>mg
                      </div>
                      <div class="search-food-fatty-acid">
                          <span class="category-name">포화지방산:</span><span class="food-data">${r.NUTR_CONT8}</span>g
                      </div>
                      <div class="search-food-trans-fat">
                          <span class="category-name">트랜스지방:</span><span class="food-data">${r.NUTR_CONT9}</span>g
                      </div>
                      <div id="${r.NUM}" class="select-box" onclick="selectData(${r.NUM})"> <!--data-food-data="${r}" -->
                          이 음식이 맞아요 !
                      </div>
                  </div>
              </li>
        `;

    });
    $('.search-food-list-ul').html(newSearchResults);


    // 모달2

    const selectBox = document.querySelectorAll(".select-box");


    selectBox.forEach(function(box) {
        box.addEventListener("click", openModal2);
    });

    // 이거
    function openModal2() {
        modal2.setAttribute("style", "display: block;");
    }


    const closeModalButton2 = document.getElementById("close-modal2");

    closeModalButton2.addEventListener('click', function(event) {
        event.stopPropagation();
        console.log("닫는다!");
        modal2.setAttribute("style", "display: none;");

    });


}

// 먹은 음식 등록하기
$(document).ready(function() {
    $('.done-img-box').click(function() {
        console.log('done-img-box click event')
        const inputValue = document.getElementById("numberInput").value;
        console.log('=======================')
        foodSelectInfoResult = foodArray.find(food => food.NUM == foodSelectInfo);
        foodSelectInfoResult.count = inputValue;
        // function 써야함
        // 메인에 한줄 추가하는 funtion
        console.log("음식 등록 !");
        addEatFood(foodSelectInfoResult);
        // foodSelectInfoResult 에 선택된 음식 + count값 저장
        modal2.setAttribute("style", "display: none;");
        modal.setAttribute("style", "display: none;");
    });
});


// 시간입력 현재시간 표시
const now = new Date();
const currentYear = now.getFullYear();
const currentMonth = (now.getMonth() + 1).toString().padStart(2, '0');
const currentDay = now.getDate().toString().padStart(2, '0');
const currentHour = now.getHours().toString().padStart(2, '0');
const currentMinute = now.getMinutes().toString().padStart(2, '0');

const formattedDateTime = `${currentYear}-${currentMonth}-${currentDay}T${currentHour}:${currentMinute}`;

document.getElementById('datetime-input').value = formattedDateTime;









// 인분 숫자만 입력받기
const numberInput = document.getElementById('numberInput');

numberInput.addEventListener('input', function() {
    this.value = this.value.replace(/[^0-9.]/g, '');
});





function addEatFood(map) {
    console.log(map);
    let foodName = map.DESC_KOR;
    let servingSize = parseFloat(map.SERVING_SIZE);
    let calorie = parseFloat(map.NUTR_CONT1);
    let carbohydrate = parseFloat(map.NUTR_CONT2);
    let protein = parseFloat(map.NUTR_CONT3);
    let fat = parseFloat(map.NUTR_CONT4);
    let sugars = parseFloat(map.NUTR_CONT5);
    let sodium = parseFloat(map.NUTR_CONT6);
    let cholesterol = parseFloat(map.NUTR_CONT7);
    let fattyAcid = parseFloat(map.NUTR_CONT8);
    let transFat = parseFloat(map.NUTR_CONT9);
    let count = parseInt(map.count);

    let totalWeight = servingSize * count;
    let totalCalorie = calorie * count;
    let totalCarbohydrate = carbohydrate * count;
    let totalProtein = protein * count;
    let totalFat = fat * count;
    let totalSugars = sugars * count;
    let totalSodium = sodium * count;
    let totalCholesterol = cholesterol * count;
    let totalFattyAcid = fattyAcid * count;
    let totalTransFat = transFat * count;


    let text = `
    <li class="food-detail">
        <div class="food-name">${foodName}</div>
        <div class="food-weight">${totalWeight}g</div>
        <div class="food-kcal">${totalCalorie}kcal</div>
        <span class="delete-btn">×</span>
        <input type="hidden" name="foodName" value="${foodName}">
        <input type="hidden" name="foodServing" value="${count}">
        <input type="hidden" name="foodWeight" value="${totalWeight}">
        <input type="hidden" name="foodKcal" value="${totalCalorie}">
        <input type="hidden" name="foodCarbohydrate" value="${totalCarbohydrate}">
        <input type="hidden" name="foodProtein" value="${totalProtein}">
        <input type="hidden" name="foodFat" value="${totalFat}">
        <input type="hidden" name="foodSugars" value="${totalSugars}">
        <input type="hidden" name="foodSodium" value="${totalSodium}">
        <input type="hidden" name="foodCholesterol" value="${totalCholesterol}">
        <input type="hidden" name="foodFattyAcid" value="${totalFattyAcid}">
        <input type="hidden" name="foodTransFat" value="${totalTransFat}">
    </li>  
    `;

    $('.food-list-ul').append(text);

    text = ``;

    $(document).ready(function() {
        $('.delete-btn').click(function() {
            $(this).parent('.food-detail').remove();
        });
    });

}

