$(".cart__bigorderbtn").on("click", function() {
    console.log("click!");
    console.log($(".priceResultNumber").text().trim());
    console.log($(".orderName").text());
    console.log($(".userId").val());
    console.log($(".userPhoneNumber").val());
    console.log($(".userEmail").val());
    console.log( $("#address").val() + " " + $("#address2").val());
    console.log($("#address3").val());


    let $cartList = $('.cart__list__detail');
    let orderDto = {};
    let orderDtoList = [];

    $cartList.each((i, product) =>{
       let productNumber = $(product).data('number');
       let orderCount = $(product).data('count');

        orderDto.productNumber = productNumber;
        orderDto.orderCount = orderCount;
        orderDto.orderConditionCode = 1;

        orderDtoList.push({...orderDto});

    });

    let orderName = '';

    if($(".orderName").length == 1){
        orderName = $(".orderName").text();
    }else if($(".orderName").length > 1){
        orderName = $(".orderName").eq(0).text() + ' 외 ' + ($(".orderName").length - 1) + '개';
    }


    IMP.init("imp67568217"); // 가맹점 식별코드를 매개변수로 넘겨준다.

    IMP.request_pay(
        {
            pg: "kakaopay.TC0ONETIME", // kakaopay.{상점아이디(CID)}
            pay_method: "card", // 생략가
            merchant_uid: "merchant" + new Date().getTime(), // 상점에서 생성한 고유 주문번호
            name: orderName, // 상품 구매 이름
            amount: $(".priceResultNumber").text().trim(), // 가격
            buyer_email: $(".userEmail").val(),
            buyer_id: $(".userId").val(),
            buyer_addr: $("#address").val() + " " + $("#address2").val(),
            buyer_postcode: $("#address3").val(),
            buyer_tel: $(".userPhoneNumber").val()
        },
        function(rsp) {
            console.log(rsp);
            console.log(rsp.buyer_addr);
            console.log(rsp.buyer_email);
            console.log(rsp.buyer_id);
            console.log(rsp.buyer_postcode);
            console.log(rsp.buyer_tel);
            console.log(rsp.name); // 상품 이름
            console.log(rsp.merchant_uid); // 주문 고유 번호

            if (rsp.success) {
                let shippingVo = {
                    shoppingName: orderName,
                    shippingAddress1: $("#address").val(),
                    shippingAddress2 : $("#address2").val(),
                    addressNumber: $("#address3").val().trim(),
                    orderDtoList : orderDtoList
                };

                console.log(shippingVo);

                $.ajax({
                    url: '/shops/payList',
                    type: 'post',
                    data: JSON.stringify(shippingVo),
                    contentType: "application/json; charset=utf-8",
                    success: function(result) {
                        console.log("success");
                        console.log(result)

                        // shoppingFinish 페이지로 이동할 때 필요한 orderNumber를 get방식 파라미터로 생성
                        let params = '';
                        result.forEach(obj => {
                            params += `orderNumber=${obj.orderNumber}&`;
                        });

                        //마지막 &를 삭제
                        params.slice(0,-1);

                        window.location.href = "/shop/shoppingFinish?" + params;
                    }
                });
            } else {
                console.log("else");
                window.location.href = "/shop/shoppingPayInfoError";
            }
        }
    );
});






$("#checkbox").on("click", function() {
    var isChecked = $(this).is(":checked");

    if (isChecked) {
        loadUserInfo();
    } else {
        $("input").val(""); // 체크 해제된 경우에 필요한 로직을 추가하세요
    }
});

function loadUserInfo() {
    $.ajax({
        url: "/shops/userInfo", // 사용자 정보를 가져올 API 엔드포인트 URL
        method: "GET",
        success: function(result) {
            $('#recipient').val(result.userName);
            $('#address').val(result.userAddress1);
            $('#address2').val(result.userAddress2);
            $('#address3').val(result.userAddressNumber);

            let phoneNumber = result.userPhoneNumber;

            $('#phone').val(phoneNumber.substring(3));
        },
        error: function(error) {
            console.log("Error:", error);
            console.log(loadUserInfo);
            console.log(renderUserInfo);
        }
    });
}

function renderUserInfo(result) {
    let text = '';

    result.forEach(user => {
        text += `
            <div class="address">
                <form>
                    <div class="form-group">
                        <label for="recipient">받는 분</label>
                        <input type="text" id="recipient" name="recipient" required value="${user.userName}">
                    </div>
                    <div class="form-group">
                        <label for="address">주소</label>
                        <input type="text" id="address3" name="address3" required placeholder="우편번호">
                        <input type="text" id="address" name="address" required placeholder="기본주소">
                        <button class="btn-address">우편번호 찾기</button>
                    </div>
                    <input type="text" id="address2" name="address2" required placeholder="상세주소">
                    <div class="form-group">
                        <label for="phone">휴대전화</label>
                        <select name="selectItem" class="select">
                            <option label="010"></option>
                            <option label="011"></option>
                            <option label="016"></option>
                            <option label="017"></option>
                            <option label="018"></option>
                            <option label="019"></option>
                        </select>
                        <input type="tel" id="phone" name="phone" required value="${user.phone}">
                    </div>
                    <div class="form-group">
                        <label for="telephone">전화번호</label>
                        <input type="tel" id="telephone" name="telephone" value="${user.telephone}">
                    </div>
                </form>
            </div>`;
    });

    $('.address > form').html(text);
}




function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }


                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('address3').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("address2").focus();
            }
        }
    }).open();
};
