export function getDailyOrder(callback,callbackOrder,error){
    $.ajax({
        url : `/admins/v1/dailyOrder`,
        type : 'get',
        dataType : 'json',
        success : function (order){
            if(callback){
                callback(order);
                callbackOrder(order);

            }
        },
        error : error
    });
}

export function getDailyPayment(callback,callbackOrder,error){
    $.ajax({
        url : `/admins/v1/dailyPayment`,
        type : 'get',
        dataType : 'json',
        success : function (order){
            if(callback){
                callback(order);
                callbackOrder(order);

            }
        },
        error : error
    });
}

export function getDailyRefund(callback,callbackOrder,error){
    $.ajax({
        url : `/admins/v1/dailyRefund`,
        type : 'get',
        dataType : 'json',
        success : function (order){
            if(callback){
                callback(order);
                callbackOrder(order);

            }
        },
        error : error
    });
}

export function getWeeklyChart(callback,error){
    $.ajax({
        url : `/admins/v1/weeklyChart`,
        type : 'get',
        dataType : 'json',
        success : function (order){
            if(callback){
                callback(order);


            }
        },
        error : error
    });
}


export function getWeeklyTotal(callback,error){
    $.ajax({
        url : `/admins/v1/weeklyTotal`,
        type : 'get',
        dataType : 'json',
        success : function (order){
            if(callback){
                callback(order);


            }
        },
        error : error
    });
}

export function getMonthlyTotal(callback,error){
    $.ajax({
        url : `/admins/v1/monthlyTotal`,
        type : 'get',
        dataType : 'json',
        success : function (order){
            if(callback){
                callback(order);


            }
        },
        error : error
    });
}

// export function getWeeklyPayment(callback,error){
//     $.ajax({
//         url : `/admins/v1/weeklyPayment`,
//         type : 'get',
//         dataType : 'json',
//         success : function (order){
//             if(callback){
//                 callback(order);
//
//
//             }
//         },
//         error : error
//     });
// }
