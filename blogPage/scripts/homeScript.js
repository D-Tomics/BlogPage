var header = null;
window.onscroll = function(){scrollCheck()};

function onLoad() {
    if(checkCookie("loaded")) return;
    addCookie("loaded","true");
    document.getElementsByTagName("body")[0].classList.add("fadeIn");
}

function scrollCheck() {
    if(header == null)
        header = document.getElementById("main-header")
    if (window.pageYOffset >= window.innerHeight - 1) {
        header.style.width= "100%";
        header.style.opacity = "1";
    } else {
        header.style.width="0";
         header.style.opacity = "0";
    }
}

function checkCookie(name) {
    return accessCookie(name) != "";
}

function accessCookie(name) {
    var cookieName = name+"=";
    var allCookies = document.cookie.split(";");
    for(var i=0; i < allCookies.length; i++) {
        var temp = allCookies[i].trim();
        if(temp.indexOf(cookieName) == 0)
            return temp.substring(temp.indexOf(cookieName),temp.length);
    }
    return "";
}

function addCookie(name, value) {
    document.cookie = name+"="+value+";";
} 