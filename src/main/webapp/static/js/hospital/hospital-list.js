function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {  
    window.onload = function() {
      oldonload();
      func();
    }
  }
}
function $(id){
	return document.getElementById(id);
}
function showProvince(){
	$("selectProvince").onclick = showAllProvince;
	selectProvince();
}
function showAllProvince(){
	$("allProvince").style.display = "block";
	$("selectProvince").style.color = "#CCCCCC";
	$("selectProvince").style.backgroundPosition = "189px -17px";
}
function hideAllProvince(){
	$("allProvince").style.display = "none";
	$("selectProvince").style.color = "#000000";
	$("selectProvince").style.backgroundPosition = "189px -2px";
}
function selectProvince(){
	var pro = $("allProvince").getElementsByTagName("li");
	var links;
	for(var i=0;i<pro.length;i++){
		links = pro[i].getElementsByTagName("a");
		for(var j=0;j<links.length;j++){
			links[j].onclick = function(){
				$("selectProvince").innerHTML = this.innerHTML;
				hideAllProvince();
			}
		}
	}
}
addLoadEvent(showProvince);