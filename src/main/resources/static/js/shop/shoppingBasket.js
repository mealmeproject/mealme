
$('.select-all').on('change', function (){
    if($(this).is(':checked')){
        $('.detail-checkbox').prop('checked', true);
    }else{
        $('.detail-checkbox').prop('checked', false);
    }
});


$('.cart__list__optionbtn').on('click', function (){
    let $checkBox = $('.detail-checkbox:checked');
    let cartNumArr = [];

    for(let i=0; i<$checkBox.length; i++){
        let value = $checkBox.eq(i).val();
        cartNumArr.push(value)
    }

    console.log(cartNumArr);

    $.ajax({
        url : '/shops/deleteCart',
        type : 'delete',
        traditional : true,
        data : {cartNumbers : cartNumArr},
        success : function (){
            console.log("성공!")

            $checkBox.closest('.cart__list__detail').detach();
            $('.select-all').prop('checked', false);
        }
    });
});








/* 수량버튼 */

// 가격 합산
function calculateTotalPrice() {
    let totalPrice = 0;
    $(".price1").each(function() {
        let price = parseFloat($(this).text().replace(/[^0-9.-]+/g, ""));
        totalPrice += price;
    });
    $(".priceResult").text(totalPrice.toLocaleString() + '원');
}


    $(".plus_btn").on("click", function() {
        let quantity = parseInt($(this).siblings(".quantity").text()); // 현재 수량 값 가져오기
        quantity++; // 수량 증가
        $(this).siblings(".quantity").text(quantity.toLocaleString() + '개'); // 증가된 수량 값 업데이트
        $(this).siblings(".quantity").attr("value", quantity); // 버튼 요소의 형제인 span의 value 속성에 증가된 수량 값 업데이트

        // 가격 업데이트
        // let price = parseFloat($(this).closest("tr").find(".price1").text().replace(/[^0-9.-]+/g, ""));
        let price = $(this).closest("tr").find('.price1').data('price');
        let totalPrice = price * quantity;
        $(this).closest("tr").find(".price1").text(totalPrice.toLocaleString() + '원');


        // 총 가격 업데이트
        calculateTotalPrice();

        let cartNumber = $(this).closest('.cart__list__detail').find('.detail-checkbox').val();

        $.ajax({
            url: '/shops/updateCartPlus',
            type: 'POST',
            data: { cartCount: quantity, cartNumber : cartNumber },
            success: function() {
                console.log("성공했다");
            },
            error: function() {
                console.log("실패했다");
            }
        });
    });

    $(".minus_btn").on("click", function() {
        let quantity = parseInt($(this).siblings(".quantity").text()); // 현재 수량 값 가져오기
        if (quantity > 1) {
            quantity--; // 수량 감소
            $(this).siblings(".quantity").text(quantity.toLocaleString() + '개'); // 감소된 수량 값 업데이트
            $(this).siblings(".quantity").attr("value", quantity); // 버튼 요소의 형제인 span의 value 속성에 감소된 수량 값 업데이트

            // 가격 업데이트
            // let price = parseFloat($(this).closest("tr").find(".price1").text().replace(/[^0-9.-]+/g, ""));
            let price = $(this).closest("tr").find('.price1').data('price');
            // let totalPrice = price - (price / (quantity + 1));
            let totalPrice = price * quantity;
            $(this).closest("tr").find(".price1").text(totalPrice.toLocaleString() + '원'); // 가격 업데이트
        }

        // 총 가격 업데이트
        calculateTotalPrice();

        $.ajax({
            url: '/shops/updateCartMinus',
            type: 'POST',
            data: { cartCount: quantity },
            success: function() {
                console.log("성공했다");
            },
            error: function() {
                console.log("실패했다");
            }
        });
    });






