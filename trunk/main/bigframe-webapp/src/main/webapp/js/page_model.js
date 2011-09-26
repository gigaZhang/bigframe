function turnPage(pageId){
    var reg=/^[0-9]*[1-9][0-9]*$/;
    if(!reg.test(pageId)){
		return;
    }
	document.getElementById("toPage").value = pageId;
	var P = P || {};
	if(P.search == undefined){
		P.search = function(){
			document.forms[0].submit();
		}
	}
	P.search();
}
function pageKeyPress(event,el){
  var keyCode =  event.keyCode ||event.which; 
  if(keyCode== 13) 
  	turnPage(el.value);
}
