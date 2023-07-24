export function searchOrderList(obj, callback, pageCallback,orderTotal, error){
    console.log("실행됏다!!!!!!!!!!!!!!!!!!!")
    console.log(obj.startDate);
    console.log(obj.endDate);
    $.ajax({
        url: `/admins/v1/searchOrderList/${obj.page}`,
        type: 'get',
        data: {keyword : obj.keyword, searchType : obj.searchType, startDate:obj.startDate, endDate:obj.endDate},
        dataType: 'json',
        success: function (map){
            console.log("실행!!!!!!!!!!!!!!1")
            console.log(map)
            if(callback){

                callback(map.orderList);
                pageCallback(map.pageVo);
                orderTotal(map);
            }

        },

        error: error
    });
}


export function deleteOrderList(checkBoxArr,confirmAlert,error) {
    console.log(JSON.stringify(checkBoxArr))
    // if(comfirmAlert) {
    $.ajax({
        url: '/admins/v1/deleteUserList',
        data: JSON.stringify(checkBoxArr),
        type: 'post',
        traditional: true,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (result) {

            console.log(result);
            alert("해당 상품이 정상적으로 삭제되었습니다.")
            location.reload();
        },
        error: error

    });

}

// export function orderStatusAjax(obj,error){
//     $.ajax({
//         url : `/admins/v1/modify`,
//         type : 'get',
//         data : obj,
//         success : function(){
//
//                 window.alert("상태가 변경되었습니다.")
//
//             },
//
//         error : error
//     });
// }

export function orderStatusAjax(obj,error) {

    console.log(JSON.stringify(obj.orderNumber))
    // if(comfirmAlert) {
    $.ajax({
        url: '/admins/v1/modify',
        data: JSON.stringify(obj),
        type: 'post',
        // traditional: true,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function () {

            window.alert("상태가 변경되었습니다.")

        },
        error: error

    });

}

