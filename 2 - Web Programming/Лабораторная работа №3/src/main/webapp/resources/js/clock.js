window.onload = function () {
    var date = new Date();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    if (hours < 10) hours = '0' + hours;
    if (minutes < 10) minutes = '0' + minutes;
    if (seconds < 10) seconds = '0' + seconds;
    var month = date.getMonth() + 1;
    var fullDate = "Текущая дата: " + date.getDate() + "." + month + "." + date.getFullYear();
    var clock = "Текущее время: " + hours + ":" + minutes + ":" + seconds;
    document.getElementById("clock").innerHTML = clock;
    document.getElementById("date").innerHTML = fullDate;
    setInterval(() => window.setTimeout(function () {
        var date = new Date();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        if (hours < 10) hours = '0' + hours;
        if (minutes < 10) minutes = '0' + minutes;
        if (seconds < 10) seconds = '0' + seconds;
        var month = date.getMonth() + 1;
        var fullDate = "Текущая дата: " + date.getDate() + "." + month + "." + date.getFullYear();
        var clock = "Текущее время: " + hours + ":" + minutes + ":" + seconds;
        document.getElementById("clock").innerHTML = clock;
        document.getElementById("date").innerHTML = fullDate;
    }), 11000);
}

