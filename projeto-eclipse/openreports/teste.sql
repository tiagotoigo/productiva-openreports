NumberFormat

select Nome as "Nome",Latitude as "Latitude", Icone as "Ícone" ,
ano as "Ano", Valor as "Valor (R$)" from bolsa_familia

update bolsa_familia set icone = 'ponto3.png'


 CREATE FUNCTION getValor(v integer) RETURNS VARCHAR
   LANGUAGE JAVA DETERMINISTIC NO SQL
   EXTERNAL NAME 'CLASSPATH:br.com.productiva.engine.UtilString.getValorComMilhar'

 SELECT getValor(valor) FROM bolsa_familia