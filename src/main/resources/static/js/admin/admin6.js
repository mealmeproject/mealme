import * as product from '../module/productList.js';
let page = 1;
let startDate = $('#datepicker1').val();
let endDate = $('#datepicker2').val();
let productType = $('#productType').val();
product.searchProductList({searchType:'', keyword:'', page : page, productType: productType, startDate: startDate, endDate:endDate }, productList, paging,productTotal, showError);

$(function() {
  //input을 datepicker로 선언
  $("#datepicker1,#datepicker2").datepicker({
      dateFormat: 'yy-mm-dd' //달력 날짜 형태
      ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
      ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
      ,changeYear: true //option값 년 선택 가능
      ,changeMonth: true //option값  월 선택 가능                
      ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
      ,buttonImage: "https://kr.seaicons.com/wp-content/uploads/2022/05/calendar-icon-1.png" //버튼 이미지 경로
      ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
      ,buttonText: "선택" //버튼 호버 텍스트              
      ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
      ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
      ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
      ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
      ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
      ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
      ,maxDate: "+0d" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
  });                    
  
  //초기값을 오늘 날짜로 설정해줘야 합니다.
  $('#datepicker1,#datepicker2').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
});




$(document).ready(function() {
	$("#cbx_chkAll").click(function() {
		if($("#cbx_chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
		else $("input[name=chk]").prop("checked", false);
	});

	$("input[name=chk]").click(function() {
		var total = $("input[name=chk]").length;
		var checked = $("input[name=chk]:checked").length;

		if(total != checked) $("#cbx_chkAll").prop("checked", false);
		else $("#cbx_chkAll").prop("checked", true); 
	});
});

// let productNumber = $(".checkbox").val();
// product.displayFile({productNumber : productNumber}, productList, showError)

//전체 상품 리스트
function productList(map){
    console.log(map);
    let list ='';

    map.forEach(u => {
        // let fileName = file.fileUploadPath + '/' + file.fileUuid + '_' + file.fileName;
        list += `
		    <tr class="product-num" >
                  <th scope="col">
                    <input type="checkbox" name="chk" id="chk"  value="${u.productNumber}">
                  </th>
                  <th scope="col" style="font-size:13px; font-weight: 10;">${u.productRegisterDate}</th>
                  <th scope="col" style="font-size:13px; font-weight: 10;">${u.productNumber}</th>

                  <th scope="col" style="font-size:13px;font-weight: 10;">${u.productSeller}</th>
                  <th scope="col" style="font-size:13px;font-weight: 10;">${u.categoryName}</th>
                  <th scope="col" style="font-size:13px; padding: 8px;font-weight: 10; " >
                    <div class="gGoods gMedium" style="z-index: 0;">
                      <div class="mOpen">
                          <span class="frame eOpenOver" find="gGoods">
                            <img src="/upload/${u.fileUploadPath}/${u.fileUuid}_${u.fileName}"  data-number="${u.fileNumber}" data-name="${u.fileName}" width="44" height="44" alt="">
                          </span>
<!--                          <div class="open" style="top: 20%; left: 20%; width: 145px; display: none;">-->
<!--                              <div class="wrap">-->
<!--                                  <ul class="default">-->
<!--                                      <li><a href="" class="eProductDetail" product_no="10">상품 상세보기</a></li>-->
<!--                                   </ul>   -->
<!--                              </div>-->
<!--                          </div>-->
                      </div>
                      <p style="text-align: left; overflow: hidden; margin-left: 55px; font-weight: 10;"><a href="/admin/registChange" onclick="handleClick(event)" target="_blank" title="새창 열림" class="txtLink eProductDetail" id="modify" value="${u.productNumber}" data-product-number="${u.productNumber}" product_no="10">${u.productName}</a>
                      </p>
                      
                                                      </div>
                  </th>
                  <th scope="col" style="font-size:13px; font-weight: 10;">${u.productPrice}</th>
               
		`;

        list += `</tr>`;
    });
    $('.check').html(list);
}
// function handleClick(event){
//     event.preventDefault();
//     alert("상품 수정 페이지로 이동합니다!")
// }

function handleClick(event) {
    event.preventDefault();

    var productNumber = event.target.getAttribute('data-product-number');

    // AJAX 요청 보내기
    $.ajax({
        url: '/admins/v1/registChange',
        type: 'GET',
        data: { productNumber: productNumber },
        success: function(response) {

            console.log('요청이 성공적으로 완료되었습니다.');
            console.log(response);
        },
        error: function(xhr, status, error) {

            console.log('요청이 실패하였습니다.');
            console.log(error);
        }
    });
}

//페이징 처리
function paging(pageInfo){
    console.log("===========================");
    console.log(pageInfo);
    let page ='';
    if(pageInfo.prev){
        page += `
		
		 
		 	<a href="" data-page=${pageInfo.startPage-1} class="arrow prev"></a>
		`}
    for(let i = pageInfo.startPage; i<=pageInfo.endPage; i++){
        if(i == pageInfo.criteria.page){
            page += `
				<a href="#" data-page="${i}" class="active">${i}</a>
			`
        }else{
            page += `
				<a href="#" data-page="${i}">${i}</a>
			`
        }
    }
    if(pageInfo.next) {
        page += `
			<a href="" data-page=${pageInfo.endPage + 1} class="arrow next""></a>
		`
    }

    $(".page_nation").html(page);
}

//상품 수
function productTotal(total){
    console.log(total);
    $('.productTotal').text(total.productTotal);
    $('.searchTotal').text(total.searchTotal);
}

//페이징 클릭 이벤트
$('.page_nation').on('click','a', function (e){
    e.preventDefault();
    let searchKeyword = $('#keyword').val();
    let searchType = $('.fSelect').val();
    let page = $(this).data('page');
    let productType = $('#productType').val();
    let startDate = $('#datepicker1').val();
    let endDate = $('#datepicker2').val();
    console.log(page)
    product.searchProductList({searchType:searchType, keyword:searchKeyword, page : page, productType:productType, startDate:startDate, endDate:endDate }, productList, paging, productTotal,showError);
});

// 검색 조건에 따른 회원 조회
$('.search_user').on('click', function (){
    let searchKeyword = $('#keyword').val();
    let searchType = $('.fSelect').val();
    let page = 1;
    let productType = $('#productType').val();

    let startDate = $('#datepicker1').val();
    let endDate = $('#datepicker2').val();
    product.searchProductList({searchType:searchType, keyword:searchKeyword, page : page, productType:productType, startDate:startDate, endDate:endDate }, productList, paging,productTotal, showError);

    // $('#keyword').val('');

})

// 검색 input칸 엔터 이벤트
$('#keyword').on('keydown', function (e){
    console.log('++++++++++++++++++++++++++++');
    console.log(e.code);
    console.log(e.keyCode);
    if(e.keyCode == 13){
        console.log('Enter');
        let searchKeyword = $('#keyword').val();
        let searchType = $('.fSelect').val();
        let page = 1;
        let productType = $('#productType').val();

        let startDate = $('#datepicker1').val();
        let endDate = $('#datepicker2').val();
        product.searchProductList({searchType:searchType, keyword:searchKeyword, page : page,productType:productType, startDate:startDate, endDate:endDate }, productList, paging,productTotal, showError);
    }
});

$(".changeBtn").on('click',function (){
    let productNumber = productModify();
    // let productNumber = $('#chk').val();
    console.log("!@#@!#@!#@!#@!#@!#@!#@!#@!#@!#!@#@!#@!");
   console.log(productNumber);
    console.log("!@#@!#@!#@!#@!#@!#@!#@!#@!#@!#!@#@!#@!");

    window.location.href = '/admin/registChange?productNumber=' + productNumber;





});



function productChangeBtn(){
    var checkBoxArr = [];
    $("input:checkbox[name=chk]:checked").each(function (){
        checkBoxArr.push($(this).val());
        console.log("================================");
        console.log(checkBoxArr);
        console.log("================================");

    });
    console.log(checkBoxArr)

    if(checkBoxArr==""){
        alert("수정할 상품을 선택해주세요.");
        return false;
    }

    // var confirmAlert = confirm("정말로 삭제하시겠습니까?");
    // user.deleteUserList({checkBoxArr: checkBoxArr ,confirmAlert, showError});
    return checkBoxArr;
}

function productModify(){
    let productNumber = $("input:checkbox[name=chk]:checked").val();
    if(productNumber==""){
        alert("수정할 상품을 선택해주세요.");
        return false;
    }
    console.log("!@#@!#@!#@!#@!#@!#@!#@!#@!#@!#!@#@!#@!");

    console.log(productNumber);
    console.log("!@#@!#@!#@!#@!#@!#@!#@!#@!#@!#!@#@!#@!");

    return productNumber;
}



function showError(a, b, c){
    console.error(c);
}