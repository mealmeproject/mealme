import * as user from '../module/userList.js';
// const userNumber = $('.user-num').val();
user.showUserList(userList, showError);
// const searchKeyword = $('#keyword').val();


// 전체 회원 리스트

function userList(map){
	console.log(map);
	let list ='';

	map.forEach(u => {
		list += `
		   <tr class="user-num" data-num="${u.userNumber}">
                  <th>
                    <input type="checkbox" name="chk" value="${u.userNumber}">
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

// 검색 조건에 따른 회원 조회
$('.btn-image').on('click', function (){
	let searchKeyword = $('#keyword').val();
	user.searchUserList({keyword : searchKeyword}, userList, showError);

})



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