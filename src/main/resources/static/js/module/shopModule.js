
export function getAllList(callback, endPoint){
    $.ajax({
        url : `/shops/${endPoint}`,
        type : 'get',
        dataType : 'json',
        success : function (result){
            callback(result);
        },
        error : function (a,b,c){
            console.error(c);
        }
    })
}
