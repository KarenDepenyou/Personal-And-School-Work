var canvas,
	ctx,
	cloud,
	i, x;
	

function init(){
	canvas = document.getElementById("myCanvas");
	ctx = canvas.getContext('2d');
	drawCloud();
}

function drawCloud(){
	cloud = new Image();
	cloud.src = "cloud.png";
	cloud.onload = function(){
		ctx.drawImage(cloud, 10, 10, 100, 100);
	}
}

window.onload = init;
document.onkeydown = checkKey;
function checkKey(e) {
	e = e || window.event;
	if(e.keyCode == '38') {
		var element = document.getElementById("girl");
		element.style.bottom = parseInt(element.style.bottom) + 90 + '%';
		setTimeout(function(){down()}, 300);
	}
	if(e.keyCode == '39') {
		var element = document.getElementById("girl");
		element.style.left= parseInt(element.style.left) + 1 + '%';
	}
	if(e.keyCode == '37') {
		var element = document.getElementById("girl");
		element.style.left= parseInt(element.style.left) - 1 + '%';
	}
}
function down() {
	var element = document.getElementById("girl");
	element.style.bottom = parseInt(element.style.bottom) - 90 + '%';
}
$('.scroller').offset().left;



var carrots = [];
var miko = ("99px", "122px");
var enemy = [];
