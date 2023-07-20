import * as company from '../module/companyList.js'


let productType = $('#productType').val();
console.log(productType);
let page = 1;
company.searchCompanyList({searchType:'', keyword:'', productType: productType, page : page}, companyList, paging, buttonChange,companyCount,showError);





// 전체 기업 리스트

function companyList(map){
	console.log(map);
	let list ='';

	map.forEach(u => {
		list += `
		  
                <tr class="company-num" data-num="${u.companyNumber}">
                  <th>
                  <input type="checkbox" name="chk" value="${u.companyNumber}">
                  </th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.companyRegistrationNumber}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">
                    <a href="">${u.companyName}</a>
                  </th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.companyId}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.companyCallNumber}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.companyCeoName}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10; overflow: hidden;">${u.companyAddress1}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.companyEmail}</th>
                  <input type="hidden" name="status" value="${u.companyStatus}">
                  
                  

                  
                
		`;

		list += `</tr>`;
	});
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

//페이징 클릭 이벤트
$('.page_nation').on('click','a', function (e){
	e.preventDefault();
	let searchKeyword = $('#keyword').val();
	let searchType = $('.fSelect').val();
	let productType = $('#productType').val();
	console.log("=======================");
	console.log(productType);
	let page = $(this).data('page');
	console.log(page)
	company.searchCompanyList({searchType:searchType, keyword:searchKeyword,productType: productType, page : page}, companyList,paging,buttonChange,companyCount,showError);

});


// 기업 수
function companyCount(count){
	console.log(count);
	$('.companyTotal').text(count.companyTotal);
	$('.searchTotal').text(count.searchTotal);
}

// 검색 조건에 따른 회원 조회
$('.btn-image').on('click', function (){
	let searchKeyword = $('#keyword').val();
	let searchType = $('.fSelect').val();
	let productType = $('#productType').val();
	let page = 1;
	company.searchCompanyList({keyword : searchKeyword, searchType : searchType,productType: productType, page: page}, companyList,paging,buttonChange,companyCount,showError);

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
		let productType = $('#productType').val();
		let page = 1;
		company.searchCompanyList({keyword : searchKeyword, searchType : searchType,productType: productType, page: page}, companyList,paging,buttonChange, companyCount,showError);
	}
});


// 체크박스 선택
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

// 상태변경

$('.leftSide').on('click','.btnNormal', function() {
	let companyStatus = $('#productType').val();

	let companyNumber = productDeleteBtn();
	// let conditionName = $(".status-select").text();
	// let orderNumber2 = $(this).closest('tr').find('input[type="checkbox"]').val();
	// let orderStatus = $(".orderStatus").text();
	console.log(companyNumber);
	let searchKeyword = $('#keyword').val();
	let searchType = $('.fSelect').val();
	let productType = $('#productType').val();
	let page = 1;



	company.searchCompanyList({searchType:searchType, keyword:searchKeyword,productType: productType, page : page}, companyList,paging, buttonChange,companyCount,showError);

	company.orderStatusAjax({companyNumber: companyNumber, companyStatus: companyStatus},showError);
	company.searchCompanyList({searchType:searchType, keyword:searchKeyword, productType: productType, page : page}, companyList,paging, buttonChange,companyCount,showError);

});

//수정, 취소 버튼
function buttonChange(){
	let statusNumber = $('#productType').val();
	if(statusNumber == 1){
		$('.leftSide').html(`
			<button type="button" class="btnNormal">
            승인
           </button>
		`)
	} else{
		$('.leftSide').html(`
			<button type="button" class="btnNormal">
            취소
           </button>
		`)
	}


}
//
// $('#btnCancel').on('click', function() {
// 	let companyStatus = '0';
// 	let companyNumber = productDeleteBtn();
// 	// let conditionName = $(".status-select").text();
// 	// let orderNumber2 = $(this).closest('tr').find('input[type="checkbox"]').val();
// 	// let orderStatus = $(".orderStatus").text();
// 	console.log(companyNumber);
// 	let searchKeyword = $('#keyword').val();
// 	let searchType = $('.fSelect').val();
// 	let productType = $('#productType').val();
// 	let page = 1;
//
//
//
// 	company.searchCompanyList({searchType:searchType, keyword:searchKeyword,productType: productType, page : page}, companyList,paging, buttonChange,companyCount,showError);
//
// 	company.changeStatusAjax({companyNumber: companyNumber, companyStatus: companyStatus},showError);
// 	company.searchCompanyList({searchType:searchType, keyword:searchKeyword,productType: productType, page : page}, companyList, paging,buttonChange,companyCount,showError);
//
// });

function productDeleteBtn() {
	var companyNumber = [];

	$("input:checkbox[name=chk]:checked").each(function () {
		companyNumber.push($(this).val());
		console.log("================================");
		console.log(companyNumber);
		console.log("================================");

	});
	console.log(companyNumber)

	if (companyNumber == "") {
		alert("수정할 기업을 선택해주세요.");
		return false;
	}

	return companyNumber;
}

function showError(a, b, c){
	console.error(c);
}