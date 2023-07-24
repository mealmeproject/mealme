let queryString = location.search;

let urlParams = new URLSearchParams(queryString);

let login = urlParams.get('login');

if(login == 'failed'){
    alert('로그인에 실패하였습니다. 다시 시도해주세요.');
}

if(login == 'invalidUserType'){
    alert('잘못된 사용자 유형입니다.');
}
