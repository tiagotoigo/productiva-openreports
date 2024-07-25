
	var BOTAO_SIM, BOTAO_NAO,BOTAO_OK; 
	
	/**
	 * Função para Criar Mensagem de confirmação ou aviso;
	 * @param {Object} titulo: Tilulo que será mostrado no topo ja janela
	 * @param {Object} texto: Texto do conteúdo da caixa de mensagem 
	 * @param {Object} funcaoSim: função que o botão executará, caso esteja vazio não mostrará o botão
	 * @param {Object} funcaoNao: função que o botão executará, caso esteja vazio não mostrará o botão
	 * @param {Object} funcaoOk: função que o botão executará, caso esteja vazio não mostrará o botão
	 */	
   function showMsg(titulo, texto, funcaoSim, funcaoNao, funcaoOk){
   	
   	$.blockUI({ 
		message:montarTela(titulo,texto, funcaoSim, funcaoNao, funcaoOk),
		css:{ 
		 	background: '../imagem/fundoMensage.png', 
			border: '0px'
		 } ,
		overlayCSS:  { 
        	backgroundColor: '#F3F3F3' ,
			opacity:         0.5,
			cursor:'default' 
    	} 
	});
   }
	
	
   function fechar(){
		$.unblockUI();
   }
   
   function montarTela(titulo, texto, funcaoSim, funcaoNao, funcaoOk){
	if(funcaoSim == 'true'){
		BOTAO_SIM = '<input type="button" value="&nbsp;&nbsp;Sim&nbsp;&nbsp;" onclick="fechar();'+funcaoSim+'" class="botao" />'; 
	}else{BOTAO_SIM='';}
	if(funcaoNao == 'true'){
		BOTAO_NAO = '<input type="button" value="&nbsp;&nbsp;Não&nbsp;&nbsp;" onclick="fechar();'+funcaoNao+'" class="botao" />';
	}else{BOTAO_NAO = '';}
	if(funcaoOk == 'true'){ 
		BOTAO_OK = '<input type="button" value="&nbsp;&nbsp;OK&nbsp;&nbsp;"  onclick="fechar();'+funcaoOk+'" class="botao" />'; 
	}else{BOTAO_OK='';}
	
   return  texto;
		}
