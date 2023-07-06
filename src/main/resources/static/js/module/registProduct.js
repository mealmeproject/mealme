
export function categoryViewList(callback,error){
    $.ajax({
        url: '/admins/v1/categoryList',
        type:'get',
        dataType:'json',
        // data: obj,
        success: function (result){
            console.log(result);
            if (callback){
                callback(result);
            }
        },
        error: error
    });

}

export function categoryName(cateNum,callback,error){
    $.ajax({
        url: `/admins/v1/categoryName`,
        type:'get',
        dataType:'json',
        data: {categoryCode1 : cateNum},
        success: function (result){
            console.log("+++++++++++++++++++++++++++++++")
            console.log(result)
            if(callback){
                callback(result);
            }
        },
        error : error
    });
}