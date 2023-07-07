import * as company from '../module/companyList.js'


let page = 1;
company.searchCompanyList({searchType:'', keyword:'', page : page}, companyList, paging, showError);




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
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.companyAddress1}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.companyEmail}</th>
                
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
	let page = $(this).data('page');
	console.log(page)
	company.searchCompanyList({searchType:searchType, keyword:searchKeyword, page : page}, companyList, paging, showError);
});



// 검색 조건에 따른 회원 조회
$('.btn-image').on('click', function (){
	let searchKeyword = $('#keyword').val();
	let searchType = $('.fSelect').val();
	let page = 1;
	company.searchCompanyList({keyword : searchKeyword, searchType : searchType, page: page}, companyList,paging, showError);

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
		company.searchCompanyList({keyword : searchKeyword, searchType : searchType, page: page}, companyList,paging, showError);
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

function showError(a, b, c){
	console.error(c);
}