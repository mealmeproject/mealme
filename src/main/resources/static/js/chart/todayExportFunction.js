// 권장 섭취량 받기
export function getUserInfo(mealTime, callback, error){
    $.ajax({
        url : `/charts/todayChart`,
        type : 'get',
        dataType : 'json',
        data: mealTime,
        success : function (userInfo){
            if(callback){
                callback(userInfo);
            }
        },
        error : error
    });
}

export function getDailyList(callback, error){
    $.ajax({
        url : `/charts/daily`,
        type : 'get',
        dataType : 'json',
        success : function (dailyList){
            if(callback){
                callback(dailyList);
            }
        },
        error : error
    });
}

export function getWeeklyList(callback, error){
    $.ajax({
        url : `/charts/weekly`,
        type : 'get',
        dataType : 'json',
        success : function (weeklyList){
            if(callback){
                callback(weeklyList);
            }
        },
        error : error
    });
}

export function getMonthlyList(callback, error){
    $.ajax({
        url : `/charts/monthly`,
        type : 'get',
        dataType : 'json',
        success : function (monthlyList){
            if(callback){
                callback(monthlyList);
            }
        },
        error : error
    });
}