package br.com.productiva.engine;

public class Constantes {

	public static final int TIPO_ENTIDADE_CLIENTE = 1;
	public static final int TIPO_ENTIDADE_AGENCIA = 2;
	public static final int TIPO_ENTIDADE_CONTATO = 4;
	
	public static final int SITUACAO_ENTIDADE_2 = 2;
	public static final int SITUACAO_ENTIDADE_3 = 3;
	
	public static final int TIPO_EMPRESA_ATIVA = 1;
	
	public static final int TIPO_NEGOCIACAO_ATIVA = 1;
	public static final int TIPO_NEGOCIACAO_INATIVA = 2;
	public static final int TIPO_NEGOCIACAO_CANCELADA = 3;
	public static final int TIPO_NEGOCIACAO_PENDENTE = 4;
	
	public static final char SITUACA_NEGOCIACAO_CANCELADA = '3';
	public static final char SITUACA_NEGOCIACAO_PENDENTE = '4';
	
	public static final int TIPO_PAGAMENTO_BV_PELO_FATURAMENTO = 1;
	public static final int TABELA_PRECO_CANAL_PADRAO = 1;
	public static final int TABELA_PRECO_SITUACAO_PADRAO = 2;
	
	public static final char SIM = 'S';
	public static final char NAO = 'N';
	
	public static final String VALOR_SIM = "S";
	public static final String VALOR_NAO = "N";
	
	public static final String VALOR_ZERO = "0,00";
	public static final String ZERO = "0";
	public static final int    IZERO = 0;
	public static final int    IUM = 1;
	public static final int    IDOIS = 2;
	public static final String UM = "1";
	public static final String DOIS = "2";
	public static final String TRES = "3";

	public static final String FALSE = "false";
	public static final String TRUE = "true";
	
	public static final String 	HORA_ZERO = "00:00:00";
	
	public static final int TIPO_TABELA_PRECO_ESPECIFICA = 1;
	public static final int TIPO_TABELA_PRECO_ESPECIAL_PERIODO = 2;
	public static final int TIPO_TABELA_PRECO_VIGENTE_PERIODO = 3;
	
	public static final int SITUACAO_COMERCIAL_1 = 1;
	public static final int SITUACAO_COMERCIAL_2 = 2; 
	public static final int SITUACAO_COMERCIAL_3 = 3;
	
	public static final String COMERCIAL_AVISARVENDER_S = "S";
	
	public static final int SITUACAO_FINANCEIRA_2 = 2;
	public static final String FINANCEIRO_AVISARVENDER_S = "S";
	
	public static final int TIPO_CONTRATO_1 = 1;
	public static final int TIPO_CONTRATO_3 = 3;
	
	public static final String CONTRATO_ESTILO_CANCELADO = "contrato_cancelado";
	public static final String CONTRATO_ESTILO_NORMAL = "contrato_normal";
	
	public static final char FORMA_CONTRATO_1 = '1';
	public static final char FORMA_CONTRATO_3 = '3';
	
	public static final String ENTIDADE_BLOQUEADA_S = "S";

	/**
	 * Nome da função javascript para a carga da tela
	 */
	public static final String NOME_FUNCAO_JAVASCRIPT = "carregarPagina";
	
	/**
	 * Nome da função javascript que verifica se existe algum item selecionado
	 */
	public static final String NOME_FUNCAO_VERIFICA_SELECIONADOS = 
			"existeElementoSelecionado";
	
	public static final String DIRETORIO_RELATORIOS = "/relatorio/jasper/";
	public static final int PARM_PROC_GERAR_NUMERO_ZERO = 0;
	public static final int PARAMETRO_PROC_GERAR_NUMERO = 1;
	public static final int CANAL_MESTRE = 1;
	public static final String TIPO_DESCONTO_DESCONTO = "1";
	public static final String TIPO_CLIENTE_SITUACAO_PROSPECT = "3";
	public static final String TIPO_CLIENTE_SITUACAO_INATIVO = "2";
	public static final String TIPO_CLIENTE_SIT_COMERC_VENDA = "2";
	public static final String TIPO_CLIENTE_SIT_FINANC_VENDA = "2";
	
	public static final String TIPO_VENCIMENTO_CTR_NORMAL = "1";
	public static final String TIPO_VENCIMENTO_CTR_FATURADO = "2";
	public static final String TIPO_VENCIMENTO_CTR_CANCELADO = "3";
	
	public static final String SITUACAO_DESC_VENCIMENTO_NORMAL = "Não Enviado Para Faturamento";
	public static final String SITUACAO_DESC_VENCIMENTO_FATURADO = "Enviado Para Faturamento";
	public static final String SITUACAO_DESC_VENCIMENTO_CANCELADO = "Vencimento Cancelado";
	public static final String SITUACAO_DESC_VENCIMENTO_VENCIDO = "Envio Para Faturamento Vencido";
	
	public static final int TIPO_ORIGEM_COMERCIAL_CLIENTE = 1;
	public static final int TIPO_ORIGEM_COMERCIAL_AGENCIA = 2;
	public static final int TIPO_ORIGEM_COMERCIAL_PROGRAMA = 3;
	public static final int TIPO_ORIGEM_COMERCIAL_PATROCINIO = 4;
	
	public static final String REPASSE_QUALEMPRESA_VENDA = "Venda";
	public static final String REPASSE_QUALEMPRESA_FATURAMENTO = "Faturamento";
	public static final String REPASSE_QUALEMPRESA_EXIBICAO = "Exibição";
	public static final String REPASSE_QUALEMPRESA_CLIENTE = "Cliente";
	public static final String REPASSE_QUALEMPRESA_OUTRA = "Outra";
	
	public static final int DIA31 = 31;
	public static final int DIA30 = 30;
	
	public static final char SITUACAO_VENCIMENTO_ABERTO = '1';
	public static final char SITUACAO_VENCIMENTO_FATURADO = '2';
	public static final char TIPO_INSERCAO_AVULSA = '1';
	public static final char TIPO_INSERCAO_CAMPANHA = '2';
	public static final char TIPO_INSERCAO_DE_CAMPANHA = '3';
	
	public static final char ORIGEM_INSERCAO = '1';
	public static final char VALOR_COMPENSADO_NAO = 'N';
	public static final char DATA_VEICULACAO_LIVRE_3 = '3';
	public static final char DATA_VEICULACAO_LIVRE_NAO = 'N';
	public static final String CALCULO_VALOR_VALORFIXO = "1 - Valor Fixo";
	public static final String CALCULO_VALOR_CONFORMEINSERCAO = "2 - Conforme Inserção";
	
	public static final char SITUACAO_INSERCAO_RESERVADO = '1';
	public static final char SITUACAO_INSERCAO_SEMESPACO = '2';
	public static final char SITUACAO_INSERCAO_ESTOURADO = '3';
	public static final char SITUACAO_INSERCAO_PENDENTE = '4';
	public static final char SITUACAO_INSERCAO_ABATIDO = '5';
	public static final char SITUACAO_INSERCAO_ROTEIRADO = '6';
	public static final char SITUACAO_INSERCAO_EXIBIDO = '7';
	public static final char SITUACAO_INSERCAO_FALHA = '8';
	public static final char SITUACAO_INSERCAO_COMPENSADO = '9';
	public static final char SITUACAO_INSERCAO_NOTADECREDITO = 'A';
	public static final char SITUACAO_INSERCAO_NAOCOMPENSADO = 'B';
	public static final char SITUACAO_INSERCAO_FALHADOPARCIAL = 'C';
	public static final char SITUACAO_INSERCAO_VEICULADOPARCIAL = 'D';
	public static final char SITUACAO_INSERCAO_FALHAPARCIAL = 'E';
	public static final char SITUACAO_INSERCAO_BLOQUEADO = 'F';
	public static final char SITUACAO_INSERCAO_SEMSALDO = 'G';
	public static final char SITUACAO_INSERCAO_SALDONAOVERIFICADO = 'H';
	public static final char SITUACAO_INSERCAO_ESPAÇONAOVERIFICADO = 'I';
	public static final char SITUACAO_INSERCAO_ESTOURONAOVERIFICADO = 'J';
	public static final char SITUACAO_INSERCAO_CREDITOEMESPACO = 'K';
	public static final char SITUACAO_INSERCAO_VETADO = 'V';
	public static final String TIPO_NEG_COTA = "4";
	public static final char TIPO_INSERCAO_1 = '1';
	
	public static String TIPO_DATAAPURACAOMETA_DATA_ESPECIFICA = "7";
	
	public static String VALOR_PARA_CALCULO_VALOR_ESPECIFICO = "4";
	
	public static String TIPO_COMISSAO_PERCENTUAL_FIXO = "1";
	
	public static final String N = "N";
	public static final String S = "S";
	public static final String TIPO_MENSSAGEM_ALERTA = "Atenção";
	public static final String TIPO_MENSSAGEM_ERRO   = "ERRO";
	
	public static final String WORKFLOW_CLASSE_CONTRATO_UTILIZACAO= "TfrmContratoUtilizacao";
	
	public static final char WORKFLOW_ACAO_A = 'A';
	
	public static final String DESCRICAO_CONFORME_META = "Conforme Meta";
	public static final String TIPO_ENCAIXE_3 = "3";
	public static final char TIPO_ENCAIXE_BREAK = '3';
	public static final char TIPO_ENCAIXE_ROTATIVO = '1';
	public static final char TIPO_ENCAIXE_ROTATIVONAFAIXA = '5';
	public static final String TIPO_ENCAIXE_DESC_ROTATIVONAFAIXA = "rotativofaixa";
	public static final String TIPO_ENCAIXE_DESC_ROTATIVO = "rotativo";
	public static final String TIPO_ENCAIXE_DESC_BREAK = "break";
	
	public static final char CONTARECEBER_SITUACAO_CANCELADO = '4';
	
	public static final char SITUACAO_FATURA_ABERTO    = 1;
	public static final char SITUACAO_FATURA_QUITADO   = 2;
	public static final char SITUACAO_FATURA_CANCELADO = 3;

	public static final String SITUACAO_DECRICAO_FATURA_ABERTO    = "Em Aberto";
	public static final String SITUACAO_DECRICAO_FATURA_QUITADO   = "Quitado";
	public static final String SITUACAO_DECRICAO_FATURA_CANCELADO = "Cancelado";	
	
	public static final char ROTA_SITUACAO_ATIVO = 'S';
	public static final Character ESPACO_CEDIDO_LOCAL = 'L';

	public static final String COR_TR_IMPAR = "tr_zebra_0";
	public static final String COR_TR_PAR = "tr_zebra_1";
	public static final String COR_CAMPANHA = "tr_campanha";
	public static final String COR_VERMELHO = "_vermelho";
	public static final String COR_AMARELO = "_amarelo";
	public static final String COR_ROXO = "_roxo";
	public static final String COR_VERDE = "_verde";
	public static final String COR_AZUL = "_azul";
	
}
