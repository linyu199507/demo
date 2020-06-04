/**
 * 根据时间戳获取字符串日期格式
 * @param {number} time
 */
    function ToDate(date){
    console.log(1111)
        var newDate = new Date(date);
        let year = newDate.getFullYear().toString()
        let month = newDate.getMonth() > 9 ? (newDate.getMonth()+1).toString() : "0" + (newDate.getMonth()+1).toString()
        let day = newDate.getDate() >10 ? newDate.getDate().toString() : "0" + newDate.getDate().toString()
        let hour = newDate.getHours() > 10 ? newDate.getHours().toString() : "0" + newDate.getHours().toString()
        let minutes = newDate.getMinutes() > 10 ? newDate.getMinutes().toString() : "0" + newDate.getMinutes().toString()
        let seconds = newDate.getSeconds() > 10 ? newDate.getSeconds().toString() : "0" + newDate.getSeconds().toString()
    console.log(1111)
        newDate = year+"-"+month+"-"+day+" "+hour+":"+minutes+":"+seconds
        return newDate
    }


export { ToDate }