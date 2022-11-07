

/*
1 - Escreva uma procedure onde faça a soma de dois números
*/

CREATE PROCEDURE `soma_numeros`(
	IN `num_1` INT,
	IN `num_2` INT
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
BEGIN
	SELECT num_1 + num_2;
END

CALL soma_numeros(5, 10);

/*
2 - Escreva uma procedure onde entre com quatro valores e calcule a média.
*/

CREATE PROCEDURE `media_numero`(
	IN `num_1` INT,
	IN `num_2` INT,
	IN `num_3` INT,
	IN `num_4` INT
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
    SELECT CAST(((num_1+num_2+num_3+num_4)/4) AS decimal(5,2));
END

CALL media_numero(3,5,7,8);

/*
3 - Escreva uma procedure se a soma das rendas dos clientes for menor do que 345,56 aplique um aumento de 5,14% para todos os
clientes. Caso contrário 10,23%.
*/

CREATE PROCEDURE `aplica_aumento`(
	OUT `resultado` VARCHAR(100)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	if (SELECT SUM(salario) FROM funcionario) < 345.86 then
		UPDATE funcionario set salario = salario * 5.14;
		set resultado = "aplicando aumento de 5,14%";
	ELSE 
		UPDATE funcionario set salario = salario * 10.23;
		set resultado = "aplicando aumento de 10,23%";
	end if;
	SELECT resultado;
END

CALL aplica_aumento(@valor);


/*
4 - Escreva uma procedure se o maior valor unitário do produto for
maior ou igual a 1200 aplique uma redução de 9,07% para todos os
produtos. Caso contrário uma redução de 4,02%.
*/

CREATE PROCEDURE `calc_valor_unitario_produto`(
	OUT `resultado` VARCHAR(100)
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	if (SELECT max(preco) FROM produtos) >= 3.99 then
	 UPDATE produtos set preco = preco * 90.9;
	 SET resultado = "redução de 9,07% preco = preco * 90.93";
	 else
	 UPDATE produtos set preco = preco * 95.98;
	 SET resultado = "redução de 4,02%.";
	 END if;
	 SELECT resultado;
END

CALL calc_valor_unitario_produto(@result);



/* Functions para exercicio 5 e 6 */
CREATE FUNCTION `aplica_aumento`(
	`renda` DECIMAL(7,2),
	`valor` DECIMAL(7,2)
)
RETURNS DECIMAL
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	DECLARE rendaFinal DECIMAL(7,2);
	SET rendaFinal = renda + (renda*(valor/100));
	RETURN rendaFinal;
END

SELECt aplica_aumento(1200.0, 9.5);


CREATE FUNCTION `aplica_reducao`(
	`renda` DECIMAL(7,2),
	`valor` DECIMAL(7,2)
)
RETURNS deCIMAL
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	DECLARE rendaFinal DECIMAL(7,2);
	SET rendaFinal = renda - (renda*(valor/100));
	RETURN rendaFinal;
END

SELECt aplica_reducao(1200.0, 9.5);

/*
5 - Escreva uma procedure onde entre com dois parâmetros, onde
primeiro indica o código do funcionario e o segundo indica o valor da
porcentagem de aumento
*/

CREATE PROCEDURE `aumento_salario_funcionario`(
	IN `codigo` INT,
	IN `valor` DECIMAL(5,2)
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	select id, aplica_aumento(salario, valor) from funcionario where id = codigo;
END

CALL aumento_salario_funcionario(4, 9.5)

/*
6 -  Escreva uma procedure onde entre com dois parâmetros, onde
primeiro indica o código do produto e o segundo indica o valor da
porcentagem de aumento.
*/

CREATE PROCEDURE `aumento_preco_produto`(
	IN `codigo` INT,
	IN `valor` DECIMAL(5,2)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	select id, aplica_aumento(preco, valor) from produtos where id = codigo;
END

CALL aumento_preco_produto(1, 0.95)