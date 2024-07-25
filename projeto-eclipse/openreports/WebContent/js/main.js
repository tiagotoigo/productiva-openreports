function main(){
	
	/**
	 * Cria efeito de imagem
	 * params: valor = Id que recebe o click para executar o efeito
	 * exemplo: onclick="aumentaDiv(this)"
	 */
	aumentaDiv = function(valor){
		
		$("#"+valor.id).animate({ 
           width: "70%",
		   height: "90%",
        }, 500 );
		
		$("#"+valor.id).mouseout(function(){
			$("#"+valor.id).animate({ 
           width: "4%",
		   height: "8%",
        }, 500 );
		})

	}
	
	/**
	 * 
	 * @param {Object} clickId: Elemente que receberat o Click, 
	 * @param {Object} divOutras: Criar uma div em qualquer lugar do html e colocar um id. Passar este id como parametro
	 * Exemplo: onclick="imgSlide(this, 'NovaID')"
	 */
	imgSlide = function(clickId, divOutras){
		var janela = '<div id="janela"  style="background-color:#F7F7F7;margin-left:20%;margin-top:5%; width:530px; height:390px; position:absolute; diplay:none; border:1px #EAEAEA solid;" >'
					+'<div style="text-align:right"><img id="fecharBt" src="img/ico_fechar.png" style="cursor:pointer" title="Fechar"></div>'
					+'<div style=";padding:5px;text-align:center; width:516px; height:360px;"><iframe style="border:1px #EAEAEA solid" frameborder="0"  width="100%" height="100%" scrolling="no" src="http://www.google.com.br"></iframe></div></div>';
		
		//$('#conteudo').css({ position:"absolute"});
		$('#'+divOutras).hide();
		$('#'+divOutras).html('');
		$('#'+divOutras).html(janela);          
		
		$('#fecharBt').click(function(){			
			$("#janela").hide("slow");
			$('#'+divOutras).hide("slow");
			$('#'+divOutras).html('');			
		})
		
		$('#'+divOutras).show("slow");	
		$("#janela").show("slow");	
	}
	
}
