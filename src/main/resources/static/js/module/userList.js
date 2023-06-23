


export function showUserList(callback, error){
    $.ajax({
        url: '/admins/v1/user',
        type: 'get',
        dataType: 'json',
        success: function (map){
            if(callback){
                callback(map);
            }
        },
        error: error
    });
}

export function searchUserList(keyword, callback, error){
    let searchKeyword = $('#keyword').val()
    $.ajax({
        url: '/admin/v1/searchUserList',
        type: 'get',
        data: { keyword: searchKeyword},
        dataType: 'json',
        success: function (map){
            if(callback){
                callback(map)
            }
        },
        error: error
    });
}