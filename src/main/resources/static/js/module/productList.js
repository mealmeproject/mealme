export function searchProductList(obj, callback, pageCallback, error){
    $.ajax({
        url: `/admins/v1/searchProductList/${obj.page}`,
        type: 'get',
        data: {keyword : obj.keyword, searchType : obj.searchType, productType:obj.productType, startDate:obj.startDate, endDate:obj.endDate},
        dataType: 'json',
        success: function (map){
            console.log(map)
            if(callback){
                callback(map.productList);
                pageCallback(map.pageVo);
            }

        },

        error: error
    });
}

// export function modifyProduct(checkBoxArr,confirmAlert,error) {
//     console.log(JSON.stringify(checkBoxArr))
//     // if(comfirmAlert) {
//     $.ajax({
//         url: '/admins/v1/deleteUserList',
//         data: JSON.stringify(checkBoxArr),
//         type: 'post',
//         traditional: true,
//         contentType: "application/json; charset=utf-8",
//         dataType: "json",
//         success: function (result) {
//
//             console.log(result);
//             alert("해당글이 정상적으로 삭제되었습니다.")
//             location.reload();
//         },
//         error: error
//
//     });
//
// }

// export function displayFile(obj, callback, error){
//     $.ajax({
//         url : '/files/imgList',
//         type : 'get',
//         date : obj,
//         success: function (map){
//             if(callback){
//                 callback(map)
//             }
//         },
//         error : error
//     });
// }