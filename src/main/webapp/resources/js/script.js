
function targetHostRequired() {
    if (document.getElementById("targetHosts").readOnly === false) {
        return true;
    }
}

function targetSwitch() {
    if (document.getElementById("shareType").value === "NFS") {
        document.getElementById("targetHosts").disabled = false;
        document.getElementById("targetHosts").style.background = "white";
        document.getElementById("targetHosts").required = true;
    } else {
//        document.getElementById("targetHosts").value = null;
        document.getElementById("targetHosts").disabled = true;
        document.getElementById("targetHosts").style.background = "beige";
        document.getElementById("targetHosts").required = false;
    }
}

function startTime() {

    var mydate = new Date();
    var year = mydate.getFullYear();

    if (year < 1000)
        year += 1900;

    var day = mydate.getDay(); // Current Day of week - 2
    var month = mydate.getMonth(); // Current Month 2
    var daym = mydate.getDate(); // Current Date -24
//    var h = mydate.getHours(); //Hours
//    var m = mydate.getMinutes();//Minutes
//    var s = mydate.getSeconds();//Seconds
//    m = checkTime(m);
//    s = checkTime(s);

    var dayarray = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    var montharray = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    document.getElementById('mydate').innerHTML = dayarray[day] + ", " + montharray[month] + " " + daym + " " + year;

//    var t = setTimeout(function () {
//        startTime();
//    }, 500);

//    var today = new Date();
//    var d = today.getDay();
//    var month = today.getMonth();
//    var y = today.getFullYear();
//    var h = today.getHours();
//    var m = today.getMinutes();
//    var s = today.getSeconds();
//    m = checkTime(m);
//    s = checkTime(s);
//    var months_array = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
////    document.getElementById('mytime').innerHTML = d + " " + months_array[month] +" " + y + ", " + h + ":" + m + ":" + s;
//    document.getElementById('txt').innerHTML = h + ":" + m + ":" + s;
}

//function checkTime(i) {
//    if (i < 10) {
//        i = "0" + i;
//    }
//    return i;
//}

