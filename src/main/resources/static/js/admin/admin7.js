import * as register from '../module/registProduct.js';

// 대분류 화면에 띄우기

register.categoryViewList(categoryView, showError);

function categoryView(cate){
  console.log("***********************************");
  console.log(cate);

  let list = '';

  cate.forEach(c => {
    list += `
      
                                                   
       <li class="_next">
         (대분류) ${c.categoryName} &gt;
        <input type="hidden" name="categoryCode1" class="_category_no" value="${c.categoryCode1}">
       </li>
    `;
  });
  $('._category_1').html(list);
}

//중분류 띄우기
function showCategoryList(list){
  let text ='';

  list.forEach(c => {
    text+= `
        <li class=" _next">(중분류) ${c.categoryName} &gt;
        <input type="hidden" name="categoryCode2" class="_category_no" value="${c.categoryCode2}" ></li>
    `;
  });
  $("._category_2").html(text);

}


//대분류 클릭 이벤트
$("._category_1").on('click', '._next',function (){
     let cateNum = $(this).find('._category_no').val();
     let categoryName = $(this).text().trim().replace('(중분류)','');
     console.log(cateNum)
     register.categoryName(cateNum,showCategoryList,showError);

  $("._category_1 ._next.selected").removeClass('selected');
     $(this).addClass('selected');

  $("._product_category_checked").html(`
    <input type="hidden" name="category_product[group1][${cateNum}]" class="_selected_category_no" value="${cateNum}">
    <span class="txtLight txtLess" id="topCate" style="font-size: 12px;">${categoryName}</span>
    <a href="#none" class="btnNormal _remove_selected_category"><span><em class="icoDel"></em> 삭제</span></a>
  `);

  $(this).addClass('selected');


});

$("._category_2").on('click', '._next',function (){
  let cateNum = $(this).find('._category_no').val();
  let categoryName = $(this).text().trim();
  let existingText = $("._product_category_checked").find('#topCate').text().trim();

  // 중분류 이름을 추가한 새로운 텍스트 생성
  let newText = existingText ? `${existingText} ${categoryName}` : categoryName;

  $("._category_2 ._next.selected").removeClass('selected');
  $(this).addClass('selected');

  $("._product_category_checked").html(`
    <input type="hidden" name="category_product[group1][${cateNum}]" class="_selected_category_no" value="${cateNum}">
    <span class="txtLight txtLess" id="middleCate" style="font-size: 12px;">${newText}</span>
    <a href="#none" class="btnNormal _remove_selected_category"><span><em class="icoDel"></em> 삭제</span></a>
  `);

  $("._product_category_checked").find('#middleCate').remove();

  let existedText = $("._category_1 ._next.selected").text().trim().replace('(중분류)','');

  let Text =  `
    <span class="txtLight txtLess" style="font-size: 12px;">${existedText} ${categoryName}</span>
  `;

  $("._product_category_checked").prepend(Text);

  $("._product_category_checked ._selected_category_no").val(cateNum);


  $(this).addClass('selected');

});

//선택된 분류 삭제
$(document).on('click', '._remove_selected_category', function() {
  $(this).closest('._product_category_checked').find('.txtLight').text('');
  $(this).closest('._product_category_checked').find('._selected_category_no').remove();
  // $(this).closest('._category_1 ._next.selected').removeClass('selected');
  // $(this).closest('._product_category_checked').removeClass('selected');
  $(this).closest("td").find('li').removeClass("selected");
});


// //선택된 상품분류
// function selectCategory(select){
//   const text = `
//     <input type="hidden" name="category_product[group1][25]" class="_selected_category_no" value="${select.categoryCode1}">
//     <span class="txtLight txtLess" style="font-size: 14px;">(대분류) ${select.categoryName} </span>
//     <a href="#none" class="btnNormal _remove_selected_category"><span><em class="icoDel"></em> 삭제</span></a>
//   `;
//
//   $('._product_category_checked').html(text);
// }

function showError(a, b, c){
  console.error(c);
}

let $fileInput = $('#file-input');
let $fileList = $('.file-list');
let $cnt = $('.cnt');

// 서머노트
$(document).ready(function () {
  $("#summernote").summernote({
    height: 500,
    lang: "ko-KR",
    toolbar: [
      ["style", ["style"]],
      ["font", ["bold", "italic", "underline", "clear"]],
      ["fontname", ["fontname"]],
      ["color", ["color"]],
      ['insert', ['link', 'picture', 'video']],
      ["para", ["ul", "ol", "paragraph"]],
    ],
    placeholder:
      "<br><br>* 양식에 맞춰서 작성해주세요 ! <br>양식에 맞지 않을시 거절 될 수 있습니다. <br><br><br> 필수작성사항 <br> 1) 성분 : <br> 2) 상품 사진 : 첨부파일로 첨부가능 <br> 3) 상품에 대한 상세 설명 : ",
  });
});


// 파일 넣었을시 파일 담기 
let $tmpInput = $('#file-input');
  
$tmpInput.on('change', function () {


  // $('.upload-box').css("display", "none");
    $('.file-list').css("display", "flex");


  let files = this.files;
  inputProce(files);

  $fileList.html('');

  //3개를 넘기면 초기화 처리
  if(files.length > 3){
    let dt = new DataTransfer();
    files = dt.files;
    alert("파일은 최대 3개 까지만 첨부 가능합니다.")
    $cnt.text(files.length);
    return;
  }
  
  for(let i=0; i<files.length; i++){
    let src = URL.createObjectURL(files[i]);
    
    $fileList.append(`
        <li>
          <div class="show-img-box">
            <img src=${src} class="preview-image">
          </div>
          <div class="btn-box">
            <button type='button' class='img-cancel-btn' data-name='${files[i].name}'>삭제</button>
          </div>
        </li>
    `);
  }
  $cnt.text(files.length);

  if(files.length == 3){
    $('.upload-box').css("display", "none");
  }
 
});



function inputProce(target){
  let files = target;
  console.log(files)

  let $input = $('.input');

  for (let i = 0; i < 3; i++) {
    console.log($input[i]);
    if (i >= files.length) {
      let dt = new DataTransfer();

      $input[i].files = dt.files;
    } else {
      let dt = new DataTransfer();
      dt.items.add(files[i]);

      $input[i].files = dt.files;
    }
    $cnt.text(files.length);
  }};


$('.img-controller-box').on('click','.img-cancel-btn', function() {
  // console.log($tmpInput[0].files)

   // 버튼의 부모의 부모를 삭제
  $(this).parent().parent().remove();
  let files =  $tmpInput[0].files;
  let fileName = $(this).data('name');
  let dt = new DataTransfer();

  for(let i=0; i<files.length; i++){
      if(files[i].name !== fileName)   {
        dt.items.add(files[i]);
      }
  }
  
  files = dt.files;
  $fileInput[0].files = files;

  length = $('.preview-image').length;

  if(length == 0){
    $('.upload-box').css("display", "block");
    $('.file-list').css("display", "none");
  }
  
  if(length>0 && length<3 ){
    $('.upload-box').css("display", "block");
    $('.file-list').css("display", "flex");
  }

  if(length ==3){
    $('.upload-box').css("display", "none");
  }
  
  console.log('==================================');
  console.log(files);
  console.log('==================================');
  inputProce(files);
  $cnt.text(files.length);
  checkEnd();
});

let $uploadBox = $('.upload-box');

$uploadBox.on('click', function () {
  console.log(this);
  $fileInput.trigger("click");
});

// 등록버튼
$(".submit-btn").on("click", () => {

   alert("상품 등록이 완료되었습니다.");
  window.location.href = "/admin/productList";

});

// 취소버튼
$(".cancel-btn").on("click", () => {
  window.location.href = "/admin/adminMain";
});

