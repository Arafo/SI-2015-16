/**
 * 
 */

$(document).ready(function () {
    
    /**
     * Boton de editar
     */
	$("#edit_button").click(function () {

    	if (this.value == "Editar") {
        	// Estilo CSS habilitado
    		$("#name").css( {'border' : '1px solid #CCC','background-color': '#fff', 'box-shadow': '0px 1px 1px rgba(0, 0, 0, 0.075) inset'});
    		$("#email").css( {'border' : '1px solid #CCC','background-color': '#fff', 'box-shadow': '0px 1px 1px rgba(0, 0, 0, 0.075) inset'});
    		$("#address").css( {'border' : '1px solid #CCC','background-color': '#fff', 'box-shadow': '0px 1px 1px rgba(0, 0, 0, 0.075) inset'});
    		$("#phone").css( {'border' : '1px solid #CCC','background-color': '#fff', 'box-shadow': '0px 1px 1px rgba(0, 0, 0, 0.075) inset'});
    		$("#bday").css( {'border' : '1px solid #CCC','background-color': '#fff', 'box-shadow': '0px 1px 1px rgba(0, 0, 0, 0.075) inset'});
    		$("#sex").css( {'border' : '1px solid #CCC','background-color': '#fff', 'box-shadow': '0px 1px 1px rgba(0, 0, 0, 0.075) inset'});

        	// Habilitar inputs
    		$("#name").prop( "disabled", false );    		
    		$("#email").prop( "disabled", false );
    		$("#address").prop( "disabled", false );
    		$("#phone").prop( "disabled", false );
    		$("#bday").prop( "disabled", false );
    		$("#sex").prop( "disabled", false );

            $('#send_button').prop('type', 'submit');
            $('#edit_button').prop('class', 'btn btn-warning');	
            $('#edit_button').prop('value', 'Cancelar');	
    	}	
    	else {
        	// Estilo CSS deshabilitado
    		$("#name").css( {'border' : 'none','background-color': '#fff', 'box-shadow': 'none'});
    		$("#email").css( {'border' : 'none','background-color': '#fff', 'box-shadow': 'none'});
    		$("#address").css( {'border' : 'none','background-color': '#fff', 'box-shadow': 'none'});
    		$("#phone").css( {'border' : 'none','background-color': '#fff', 'box-shadow': 'none'});
    		$("#bday").css( {'border' : 'none','background-color': '#fff', 'box-shadow': 'none'});
    		$("#sex").css( {'border' : 'none','background-color': '#fff', 'box-shadow': 'none'});

        	// Deshabilitar inputs
    		$("#name").prop( "disabled", true );
    		$("#email").prop( "disabled", true );
    		$("#address").prop( "disabled", true );
    		$("#phone").prop( "disabled", true );
    		$("#bday").prop( "disabled", true );
    		$("#sex").prop( "disabled", true );

            $('#send_button').prop('type', 'hidden');
            $('#edit_button').prop('class', 'btn btn-success');	
            $('#edit_button').prop('value', 'Editar');
	    }
	});
});