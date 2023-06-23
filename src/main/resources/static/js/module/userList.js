


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

export function searchUserList(obj, callback, error){
    let searchKeyword = $('#keyword').val()
    $.ajax({
        url: '/admins/v1/searchUserList',
        type: 'get',
        data: obj,
        dataType: 'json',
        success: function (map){
            if(callback){
                callback(map)
            }
        },
        error: error
    });
}