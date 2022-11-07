

CREATE FUNCTION fn_teste (
	a INT,
	b INT
)
RETURNS INT
LANGUAGE SQL
DETERMINISTIC
BEGIN
	RETURN a * b;
END


/*
    1 - Mostre o valor de 10 produtos com desconto de 5% (funcao de venda em atacado)
*/
CREATE FUNCTION `func_aplica_desconto`(
	`preco` DECIMAL(5,2)
)
RETURNS decimal(5,2)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
BEGIN
	DECLARE valorFinal DECIMAL(5,2);
    set valorFinal = (preco * 0.95); 
	RETURN valorFinal;
END

select nome, func_aplica_desconto(preco) as desconto from produtos;


/*
    2 - Mostre o valor de um pedido se ele for feito 3 vezes, somando os valores e dando desconto de 10%
*/

CREATE FUNCTION `desconto_pedido`(
	`valor` DECIMAL(5,2)
)
RETURNS DECIMAL(5,2)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
BEGIN
	DECLARE desconto DECIMAL(5,2);
	SET desconto = ((valor-(valor*0.95)*3);
	RETURN desconto;
END

