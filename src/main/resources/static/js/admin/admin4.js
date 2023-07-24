import * as order from '../module/orderList.js';
let page = 1;
let startDate = $('#datepicker1').val();
console.log(startDate);
let endDate = $('#datepicker2').val();
console.log(endDate);
let sysdate = new Date();
let date = '1900-01-01';

order.searchOrderList({searchType:'', keyword:'', page : page, startDate: date, endDate: sysdate}, orderList, paging,orderTotal, showError);

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
function orderList(map){
    console.log(map);
    let list ='';

    map.forEach(u => {
        // let fileName = file.fileUploadPath + '/' + file.fileUuid + '_' + file.fileName;
        list += `
		   <tr>
                 <th>
                   <input type="checkbox" name="chk" class="chk" value="${u.orderNumber}">
                 </th>
                 <th scope="col" style="font-size: 13px; font-weight: 10;">${u.orderDate}</th>
             <th scope="col" style="font-size: 13px; font-weight: 10;">${u.orderNumber}</th>
                 <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userName}</th>
                 <th scope="col" style="font-size: 13px; font-weight: 10;">${u.productName}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.productPrice * u.orderCount}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.orderCount}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;" class="orderStatus">${u.conditionCodeName}
          
                  </th>
                </tr>
               
		`;

    });
    $('.check').html(list);
}

function orderName(map){
    let list ='';
    list += `
		   <tr>
                 <th>
                   <input type="checkbox" name="chk" class="chk" value="${map.orderNumber}">
                 </th>
                 <th scope="col" style="font-size: 13px; font-weight: 10;">${map.orderDate}</th>
             <th scope="col" style="font-size: 13px; font-weight: 10;">${map.orderNumber}</th>
                 <th scope="col" style="font-size: 13px; font-weight: 10;">${map.userName}</th>
                 <th scope="col" style="font-size: 13px; font-weight: 10;">${map.productSeller}</th>
                 <th scope="col" style="font-size: 13px; font-weight: 10;">${map.productName}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${map.productPrice * map.orderCount}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${map.orderCount}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;" class="orderStatus">${map.conditionCodeName}
          
                  </th>
                </tr>
               
		`;

    $('.check').html(list);
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

let conditionNum;
let conditionName;

// 상태 변경
$(".status-select").on('change', function(){
  conditionName = $(this).text();
  conditionNum = $(this).val();
})

//주문 수
function orderTotal(total){
    console.log(total);
    $('.orderTotal').text(total.orderTotal);
    $('.searchTotal').text(total.searchTotal);
}

$('.status').on('click', function() {
    let conditionNum = $(".status-select").val();
    let orderNumber = productDeleteBtn();
    // let conditionName = $(".status-select").text();
    // let orderNumber2 = $(this).closest('tr').find('input[type="checkbox"]').val();
    // let orderStatus = $(".orderStatus").text();
    console.log(orderNumber);
    let searchKeyword = $('#keyword').val();
    let searchType = $('.fSelect').val();
    let page = 1;


    let startDate = $('#datepicker1').val();
    let endDate = $('#datepicker2').val();
    order.searchOrderList({searchType:searchType, keyword:searchKeyword, page : page, startDate: startDate, endDate:endDate }, orderList, paging,orderTotal, showError);

 order.orderStatusAjax({orderNumber: orderNumber, orderConditionCode: conditionNum},showError)

 });


// $('.status').on('click', function(){
//     order.
// })


//페이징 클릭 이벤트
$('.page_nation').on('click','a', function (e){
    e.preventDefault();
    let searchKeyword = $('#keyword').val();
    let searchType = $('.fSelect').val();
    let page = $(this).data('page');
    let startDate = $('#datepicker1').val();
    let endDate = $('#datepicker2').val();
    console.log(page)
    order.searchOrderList({searchType:searchType, keyword:searchKeyword, page : page, startDate: startDate, endDate:endDate }, orderList, paging,orderTotal, showError);
});

// 검색 조건에 따른 회원 조회
$('.search_user').on('click', function (){
    let searchKeyword = $('#keyword').val();
    let searchType = $('.fSelect').val();
    let page = 1;


    let startDate = $('#datepicker1').val();
    let endDate = $('#datepicker2').val();
    order.searchOrderList({searchType:searchType, keyword:searchKeyword, page : page, startDate: startDate, endDate:endDate }, orderList, paging, orderTotal,showError);

    // $('#keyword').val('');

});

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

        let startDate = $('#datepicker1').val();
        let endDate = $('#datepicker2').val();
        order.searchOrderList({searchType:searchType, keyword:searchKeyword, page : page, startDate: startDate, endDate:endDate }, orderList, paging,orderTotal, showError);
    }
});

$(".deleteBtn").on('click',function (){
    let checkBoxArr = productDeleteBtn();
    var confirmAlert = confirm("정말로 삭제하시겠습니까?");

    // let productNumber = $('#chk').val();
    console.log("!@#@!#@!#@!#@!#@!#@!#@!#@!#@!#!@#@!#@!");
    console.log(checkBoxArr);
    console.log("!@#@!#@!#@!#@!#@!#@!#@!#@!#@!#!@#@!#@!");

    order.deleteOrderList({checkBoxArr: checkBoxArr},confirmAlert ,showError);

    // order.findOrderList(orderList ,showError);




});



function productDeleteBtn(){
    var orderNumber = [];

    $("input:checkbox[name=chk]:checked").each(function (){
        orderNumber.push($(this).val());
        console.log("================================");
        console.log(orderNumber);
        console.log("================================");

    });
    console.log(orderNumber)

    if(orderNumber==""){
        alert("수정할 상품을 선택해주세요.");
        return false;
    }

    // var confirmAlert = confirm("정말로 삭제하시겠습니까?");
    // user.deleteUserList({checkBoxArr: checkBoxArr ,confirmAlert, showError});
    return orderNumber;
}

// function productModify(){
//     let productNumber = $("input:checkbox[name=chk]:checked").val();
//     if(productNumber==""){
//         alert("수정할 상품을 선택해주세요.");
//         return false;
//     }
//     console.log("!@#@!#@!#@!#@!#@!#@!#@!#@!#@!#!@#@!#@!");
//
//     console.log(productNumber);
//     console.log("!@#@!#@!#@!#@!#@!#@!#@!#@!#@!#!@#@!#@!");
//
//     return productNumber;
// }




function showError(a, b, c){
    console.error(c);
}






//
// $('.status').on('click', function() {
//     var selectedValue = $('#changeStatus').val(); // 선택한 값 가져오기
//
//     $.ajax({
//         url: `/admins/v1/modi`, // 서버에서 데이터를 가져올 엔드포인트 설정
//         method: 'GET',
//         data: { status: selectedValue }, // 선택한 값 전달
//         success: function(response) {
//             // 서버로부터 받은 데이터를 처리하는 작업
//            list(response); // orderList 함수에 받은 데이터를 전달
//         },
//         error: function(xhr, status, error) {
//             // 요청이 실패했을 때의 동작
//         }
//     });
// });
//
// function list(map) {
//     var list = '';
//
//     map.forEach(function(u) {
//         list += `
//       <tr>
//         <th>
//           <input type="checkbox" name="chk" value="${u.orderNumber}">
//         </th>
//         <th scope="col" style="font-size: 13px; font-weight: 10;">${u.orderDate}</th>
//         <th scope="col" style="font-size: 13px; font-weight: 10;">${u.orderNumber}</th>
//         <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userName}</th>
//         <th scope="col" style="font-size: 13px; font-weight: 10;">${u.productSeller}</th>
//         <th scope="col" style="font-size: 13px; font-weight: 10;">${u.productName}</th>
//         <th scope="col" style="font-size: 13px; font-weight: 10;">${u.productPrice * u.orderCount}</th>
//         <th scope="col" style="font-size: 13px; font-weight: 10;">${u.orderCount}</th>
//         <th scope="col" style="font-size: 13px; font-weight: 10;" class="orderStatus">${u.conditionCodeName}</th>
//       </tr>`;
//     });
//
//     $('.check').html(list);
// }
//
//
//     $('.status').on("click", function () {
//         let orderStatus = $(".check").find('.orderStatus').text();
//         let orderConditionCode = $('.status-select').val();
//         let conditionCodeName = $('.status-select').text();
//         $.ajax({
//             type: 'get',
//             url: '/admins/v1/modifyName',
//             data: {orderConditionCode : orderConditionCode},
//             success: function (result) {
//                 console.log(result.conditionName);
//                 // $(".check").find('.orderStatus').text(result.conditionCodeName);
//                 // let text = result.conditionName;
//                 click(result.conditionName);
//             }
//         });
//     });


// function click() {
//     $('.check').on("click", ".chk", function () {
//
//         // console.log($(this).closest('tr').find('.orderStatus').text().trim());
//        $(this).closest('tr').find('.orderStatus').text();
//     })
// }


// let text = "sfsdfsdf";
//
// $('.check').on("click", ".chk", function () {
//     // console.log($(this).closest('tr').find('.orderStatus').text().trim());
//     $(this).closest('tr').find('.orderStatus').text(text);
// })