

/*************************************************************************************************

			EXERCICIO CASE WHEN

*************************************************************************************************/

/*
1) CRIE UMA NOVA COLUNA PARA A TABELA DE PRODUTOS CHAMADA “CUSTO
BENEFÍCIO”. APÓS ISSO CASO O PRODUTO TENHA O VALOR MENOR QUE
200, INSIRA NA COLUNA NOVA “BARATO”, CASO MAIOR QUE 1000 “MÉDIO”,
CASO MAIOR QUE 3000 “CARO”.
*/

ALTER TABLE produtos ADD COLUMN custo_beneficio VARCHAR(150);

CREATE PROCEDURE `proc_update_custo_beneficio`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	
    /* valor para comparação */
	DECLARE valor FLOAT(7,2) DEFAULT 0;
	
    /* determina fim do loop */
    declare fimloop INT DEFAULT 0;
	
    /* Declara o cursor */
	DECLARE meucursor CURSOR FOR SELECT preco FROM produtos;

    /* condicao de parada do loop */
	DECLARE CONTINUE handler FOR NOT FOUND SET fimloop = 1;
	
	OPEN meucursor;
	
	while(fimloop != 1)do
		fetch meucursor INTO valor;
			case 
				when valor <= 100.0 then
					UPDATE produtos SET custo_beneficio = "BARATO" WHERE preco <= 100.0;
				when valor > 100.0 && valor < 200.0 then
					UPDATE produtos SET custo_beneficio = "MEDIO" WHERE preco > 100.0 && preco < 200.0;
				when valor >= 200.0 then
					UPDATE produtos SET custo_beneficio = "CARO" WHERE preco >= 200.0;
				ELSE SELECT 'value not found';
			END case;
	END while;
	SELECT * FROM produtos;
END

CALL proc_update_custo_beneficio()

/*
2) CRIE UMA NOVA COLUNA NA TABELA CLIENTES CHAMADA “STATUS”. APÓS
ISSO VERIFIQUE SE O CLIENTE JÁ FEZ PEDIDOS. CASO TENHA FEITO UM
PEDIDO, INSIRA NA NOVA COLUNA “PAGADOR”, CASO NÃO TENHA FEITO
NENHUM “NOVO”, CASO TENHA FEITO 2 OU MAIS “FIDELIZADO”.
*/

ALTER TABLE cliente ADD COLUMN status VARCHAR(150);

/* Select utilizado para encontrar quantidade de pedidos */
SELECT cliente.nome, cliente.id AS id_cliente,  cliente.status, count(pedidos.codigo_cliente) AS Qtde_pedidos FROM cliente
LEFT JOIN pedidos ON (pedidos.codigo_cliente = cliente.id)
GROUP BY cliente.id;

/* Procedure criada para realizar a query */
CREATE PROCEDURE `proc_check_status_cliente`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	/* valor para comparação */
	DECLARE check_count_pedidos int DEFAULT 0;
	DECLARE check_nome_cliente nVARCHAR(200);
	DECLARE check_id_cliente int;
	
    /* determina fim do loop */
   declare fimloop INT DEFAULT 0;
	
    /* Declara o cursor */
	DECLARE cursor_status CURSOR FOR 
		SELECT cliente.id, count(pedidos.codigo_cliente) AS qtde_pedidos FROM cliente
		LEFT JOIN pedidos ON (pedidos.codigo_cliente = cliente.id)
		GROUP BY cliente.id;

    /* condicao de parada do loop */
	DECLARE CONTINUE handler FOR NOT FOUND SET fimloop = 1;
	
	OPEN cursor_status;
	
		while(fimloop != 1)do
		
		fetch NEXT from cursor_status INTO check_id_cliente, check_count_pedidos;
		
		case
			when check_count_pedidos = 0 then
				UPDATE cliente SET cliente.status = "NOVO" WHERE id = check_id_cliente;
			when check_count_pedidos = 1 then
				UPDATE cliente SET cliente.status = "PAGADOR" WHERE id = check_id_cliente;
			when check_count_pedidos >= 2 then
				UPDATE cliente SET cliente.status = "FIDELIZADO" WHERE id = check_id_cliente;
			ELSE SELECT 'value not found';
		END case;
			
	END while;
	
	close cursor_status;
	
END


CALL proc_check_status_cliente()

/*
3) CRIE UMA NOVA COLUNA NA TABELA PEDIDO CHAMADA “LUCRO”. APÓS
ISSO VERIFIQUE O VALOR DO PEDIDO, CASO O PEDIDO TENHA SIDO IGUAL
OU MENOR QUE 500, INSIRA O VALOR DE LUCRO DE 10%, CASO IGUAL OU
MENOR QUE 1000, LUCRO DE 14%, CASO IGUAL OU MENOR QUE 2000,
LUCRO DE 20%, CASO MAIOR QUE 2001 O LUCRO É DE 25%.
*/

ALTER TABLE pedidos ADD COLUMN LUCRO decimal(7, 2);

/* Criado Function para calcular o aumento */
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


/* Procedure que aplica valor no Lucro

OBS: NA MINHA TABELA OS VALORES ERAM MENORES DO QUE NO EXERCICIO ENTAO ALTEREI PARA:
VALOR < R$ 10.0 LUCRO DE 10%
VALOR >= R$ 10.0 && VALOR < R$ 15.0 LUCRO DE 14%
VALOR < R$ 15.0  && VALOR <= R$ 20.0 LUCRO DE 20%
VALOR > R$ 20.0 LUCRO DE 25%

 */
CREATE PROCEDURE `proc_lucro_pedidos`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	/* valor para comparação */
	DECLARE valor_pedido DECIMAL(7,2);
	DECLARE id_pedido int;
	
    /* determina fim do loop */
   declare fimloop INT DEFAULT 0;
	
    /* Declara o cursor */
	DECLARE cursor_lucro_pedido CURSOR FOR SELECT pedidos.id, pedidos.valor FROM pedidos;

    /* condicao de parada do loop */
	DECLARE CONTINUE handler FOR NOT FOUND SET fimloop = 1;
	
	OPEN cursor_lucro_pedido;
	
		while(fimloop != 1)do
		
		fetch NEXT from cursor_lucro_pedido INTO id_pedido, valor_pedido;
		
		case
			when valor_pedido <= 10.0 then
				UPDATE pedidos SET pedidos.LUCRO = aplica_aumento(valor_pedido, 10)  WHERE id = id_pedido;
			when valor_pedido > 10.0 && valor_pedido <= 15.0 then
				UPDATE pedidos SET pedidos.LUCRO = aplica_aumento(valor_pedido, 14)  WHERE id = id_pedido;
			when valor_pedido > 15.0 && valor_pedido <= 20.0 then
				UPDATE pedidos SET pedidos.LUCRO = aplica_aumento(valor_pedido, 20)  WHERE id = id_pedido;
			when valor_pedido > 20.0 then
				UPDATE pedidos SET pedidos.LUCRO = aplica_aumento(valor_pedido, 25)  WHERE id = id_pedido;
			ELSE SELECT 'value not found';
		END case;
			
	END while;
	
	close cursor_lucro_pedido;
END

CALL proc_lucro_pedidos()


/*************************************************************************************************

			EXERCICIO IF ELSE

*************************************************************************************************/

CREATE PROCEDURE `proc_calcula`(
	IN `valor` INT,
	IN `porcentagem` DECIMAL(20,6)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	DECLARE final DECIMAL(7,2);
	DECLARE texto VARCHAR(150);
	SET final = valor + (porcentagem*(valor/100));
	
	if final < 20.0 then 
		select "Valor menor que 20";
	elseif final > 20.0 && final <= 100.0 then 
		select "Valor entre 20 e 100";
	else
		select "Valor acima das condicoes";
	END if;
		
END


call proc_calcula(100, 1.0);