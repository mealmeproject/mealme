
makeTotalPrice();

function makeTotalPrice(){
    let $price = $('.product-price__hidden');
    let totalPrice = 0;

    $price.each((i,p) => {
        totalPrice += parseInt($(p).val());
    });

    $('#total-price').text(totalPrice.toLocaleString());
}

$('.finish-shopping').on('click', function (){
   window.location.href = '/shop/shoppingList'
});