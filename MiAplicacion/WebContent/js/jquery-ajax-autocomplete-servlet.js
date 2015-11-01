$(function() {
	
	// Text Box 1
	$("#autocomplete1").autocomplete({
		source : "autocomplete-servlet", // AJAX: url --> web.xml
		focus : function(event, ui) {
			$("#autocomplete1").val(ui.item.nombre);
			return false;
		},
		select : function(event, ui) {
			$("#autocomplete1").val(ui.item.nombre);
			return false;
		}
	}).autocomplete("instance")._renderItem = function(ul, item) {
		var image = '<img src='+ item.ruta_imagen +' style="float: left; margin-right: 6px;" width="44" height="64">';
		
		var stars = "";
		for (i = 0; i < item.puntuacion; i++) {
			stars = stars + '<span class="glyphicon glyphicon-star"></span>';
		}
		
		var nombre = item.nombre;
		var puntuacion = "Puntuacion: " + stars;
		var duracion = "Duración: " + item.duracion + " min";
		
		return $("<li>").append("<a>" + image + "</a>" + 
				"<a><strong>" + nombre + "</strong><br>" +
				puntuacion.fontsize(1) + "<br>" +
				duracion.fontsize(1) + "</a>").appendTo(ul);
	};
	
	
	// Text Box 2
	$("#autocomplete2").autocomplete({
		minLength : 0,
		source : "autocomplete-servlet", // AJAX: url --> web.xml
		focus : function(event, ui) {
			$("#autocomplete2").val(ui.item.nombre);
			return false;
		},
		select : function(event, ui) {
			$("#autocomplete2").val(ui.item.nombre);
			return false;
		}
	}).autocomplete("instance")._renderItem = function(ul, item) {
		return $("<li>").append(
				"<a>" + item.nombre + "<br> Puntuacion:" + item.puntuacion +
						", Duración: " + item.duracion + "<br> Fecha de emision: " +
						item.fecha_emision + "</a>").appendTo(ul);
	};
});