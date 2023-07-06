

export function searchCompanyList(obj, callback, pageCallback, error){
    // let searchKeyword = $('#keyword').val()
    $.ajax({
        url: `/admins/v1/searchCompanyList/${obj.page}`,
        type: 'get',
        data: {keyword : obj.keyword, searchType : obj.searchType},
        dataType: 'json',
        success: function (map){
            if(callback){
                callback(map.companyList);
                pageCallback(map.pageVo);
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
