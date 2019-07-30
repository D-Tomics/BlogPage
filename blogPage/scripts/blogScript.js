var open = false;

function openNav() {
	openAndCloseNav("200px");
}

function closeNav() {
	openAndCloseNav("0px");
}


function openAndCloseNav(width) {
	document.getElementById("menu").style.width = width;
}