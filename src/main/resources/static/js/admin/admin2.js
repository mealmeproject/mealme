import * as user from '../module/userList.js';
// const userNumber = $('.user-num').val();
// user.showUserList(userList, showError);
// const searchKeyword = $('#keyword').val();
let page = 1;
user.searchUserList({searchType:'', keyword:'', page : page}, userList, paging, showError);


// 전체 회원 리스트

function userList(map){
	console.log(map);
	let list ='';

	map.forEach(u => {
		list += `
		   <tr class="user-num" data-num="${u.userNumber}">
                  <th>
                    <input type="checkbox" name="chk" value="${u.userNumber}" data-idx="">
                  </th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userJoinDate}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userName}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userId}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userPhoneNumber}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userGender}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userBirth}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userAdress1}</th>
                  <th scope="col" style="font-size: 13px; font-weight: 10;">${u.userEmail}</th>
               
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
	user.searchUserList({searchType:searchType, keyword:searchKeyword, page : page}, userList, paging, showError);
});
			
				

// 검색 조건에 따른 회원 조회
$('.btn-image').on('click', function (){
	let searchKeyword = $('#keyword').val();
	let searchType = $('.fSelect').val();
	let page = 1;
	user.searchUserList({keyword : searchKeyword, searchType : searchType, page: page}, userList,paging, showError);

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
		user.searchUserList({keyword : searchKeyword, searchType : searchType, page: page}, userList,paging, showError);
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


//체크박스 삭제
// $(document).ready(function (){
// 	$(".deleteBtn").click(function (){
// 		let checkBoxArr = userDelete();
// 		user.deleteUserList({checkBoxArr: checkBoxArr });
// 	})
//
// })

$(".deleteBtn").on('click',function (){
	let checkBoxArr = userDelete();
	console.log(checkBoxArr);
	var confirmAlert = confirm("정말로 삭제하시겠습니까?");
	user.deleteUserList({checkBoxArr: checkBoxArr},confirmAlert ,showError);

	user.findUserList(userList ,showError);
});



function userDelete(){
	var checkBoxArr = [];
	$("input:checkbox[name=chk]:checked").each(function (){
		checkBoxArr.push($(this).val());
		console.log("================================");
		console.log(checkBoxArr);
		console.log("================================");

	});
	console.log(checkBoxArr)

	if(checkBoxArr==""){
		alert("삭제할 회원을 선택해주세요.");
		return false;
	}

	// var confirmAlert = confirm("정말로 삭제하시겠습니까?");
	// user.deleteUserList({checkBoxArr: checkBoxArr ,confirmAlert, showError});
	return checkBoxArr;
}



function showError(a, b, c){
	console.error(c);
}