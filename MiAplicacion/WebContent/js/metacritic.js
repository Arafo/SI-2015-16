/**
 * 
 */

$(document).ready(function () {
	
	var green = 61;
	var yellow = 40;
	
	for (i=1; i<=2; i++) {
		 var metaElement = document.getElementById("metascore" + i);
		 var score = metaElement.textContent;
		 
		 if (score >= yellow && score < green) {
			 metaElement.style.backgroundColor = "#FC3";
		 }
		 else if (score < yellow){
			 metaElement.style.backgroundColor = "#F00";
		 }
	}
});