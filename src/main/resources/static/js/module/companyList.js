

export function searchCompanyList(obj, callback, pageCallback, btnCallback, error){
    // let searchKeyword = $('#keyword').val()
    $.ajax({
        url: `/admins/v1/searchCompanyList/${obj.page}`,
        type: 'get',
        data: {keyword : obj.keyword, searchType : obj.searchType, productType : obj.productType},
        dataType: 'json',
        success: function (map){
            if(callback){
                callback(map.companyList);
                pageCallback(map.pageVo);
                btnCallback(map.companyList);
            }

        },

        error: error
    });
}

export function deleteUserList(checkBoxArr,confirmAlert,error) {
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
            alert("해당글이 정상적으로 삭제되었습니다.")
            location.reload();
        },
        error: error

    });

}

export function orderStatusAjax(obj,error) {

    console.log(JSON.stringify(obj.companyNumber))
    // if(comfirmAlert) {
    $.ajax({
        url: '/admins/v1/modifyStatus',
        data: JSON.stringify(obj),
        type: 'post',
        traditional: true,
        contentType: "application/json; charset=utf-8",
        // dataType: "json",
        success: function () {

            window.alert("처리되었습니다.")
            location.reload();

        },
        error: error

    });

}

export function changeStatusAjax(obj,error) {

    console.log(JSON.stringify(obj.companyNumber))
    // if(comfirmAlert) {
    $.ajax({
        url: '/admins/v1/modifyStatus2',
        data: JSON.stringify(obj),
        type: 'post',
        traditional: true,
        contentType: "application/json; charset=utf-8",
        // dataType: "json",
        success: function () {

            window.alert("승인이 취소되었습니다.")

        },
        error: error

    });

}