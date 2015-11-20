/**
 * 
 */

$(document).ready(function () {
    current_comment = "";
    
    /**
     * Boton de modificar
     */
	$(document).find("a[id^='edit_']").click(function() {
	    var num = this.id.split('_')[1];
	    var comment = $('#comentario_' + num);
	    
	    // Si la zona de texto de comentario esta deshabilitada
	    if (comment.is(':disabled')) {
	    	current_comment = comment.val();
	    	
		    // Deshabilita todas las areas de comentarios
		    $("textarea[id^='comentario_']").each(function() {
		    	var id = this.id.split('_')[1];
		    	var c = $('#comentario_' + id);
				disableTextarea(c, id);
		    });
	    	
		    enableTextarea(comment, num);
	    } 
	    // Si la zona de texto de comentario esta habilitada
	    else {
		    disableTextarea(comment, num);
	    }
	});
	
	/**
	 * Boton de enviar
	 */
	$(document).find("input[id^='sendb_']").click(function() {
	    var num = this.id.split('_')[1];
	    var button = $('#sendb_' + num);
	});
	
	/**
	 * Boton de cancelar
	 */
	$(document).find("input[id^='cancelb_']").click(function() {
	    var num = this.id.split('_')[1];
	    var button = $('#cancelb_' + num);
	    var comment = $('#comentario_' + num);
	    
	    // Si se ha modificado el texto, volver al original
    	if (current_comment !== comment.val())
    		comment.val(current_comment);

	    disableTextarea(comment, num)
	});
	
	/**
	 * Habilita la textarea de los comentarios
	 */
	function enableTextarea(comment, num) {
    	// Estilo CSS habilitado
    	comment.css( {
			'resize' : 'vertical',
			'overflow' : 'auto',
			'border' : '1px solid grey',
			'background-color': '#fff', 
	    });
    	
    	// Habilitar textarea
    	comment.prop( "disabled", false );
    	
    	// SOLUCION MAS ELEGANTE PERO DESHECHADA, en los botones
    	// creados en jQuery no funciona el evento click() porque lo
    	// que se tendria que usar on() que solo funciona para padres
    	// estaticos que no usamos :(
    	// Añadir botones para enviar y cancelar
        //var send_button = $('<a class="btn btn-success" id="sendb_' + num + '" style="margin: 4px 0 0 16px">Enviar</a>');
        //$('#comentariofila_' + num).append(send_button);
        //var cancel_button = $('<a class="btn btn-primary" id="cancelb_' + num + '" value="Cancelar" style="margin: 4px 0 0 6px">Cancelar</a>');
        //$('#comentariofila_' + num).append(cancel_button);
        
        $('#sendb_' + num).prop('type', 'submit');
        $('#cancelb_' + num).prop('type', 'submit');
	}
	
	/**
	 * Deshabilita la textarea de los comentarios
	 */
	function disableTextarea(comment, num) {
    	// Estilo CSS deshabilitado
    	comment.css( {
    		'resize' : 'none',
    		'overflow' : 'hidden',
    		'border' : 'none',
    		'background-color': '#f5f5f5', 
    	});
    	// Deshabilitar textarea
    	comment.prop( "disabled", true );	
    	
    	// Elimnar los botones de enviar y cancelar
    	//$('#sendb_' + num).remove();
    	//$('#cancelb_' + num).remove();
    	
        $('#sendb_' + num).prop('type', 'hidden');
        $('#cancelb_' + num).prop('type', 'hidden');
	}
	
});