

/*
1) Criar uma procedure para cada tabela que seja possível inserir dados em cada tabela, exemplo:


CREATE PROCEDURE faculdades_insert(idx int,nomex varchar(65),emailx varchar(60),
localizacaox varchar(80),municipiox varchar(45),estadox varchar(45))

 BEGIN

  insert into faculdades(id_faculdade,nome,email,localizacao,municipio,estado)

values(idx,nomex,emailx,localizacaox,municipiox,estadox);

END //

DELIMITER ;

CALL faculdades_insert(10,'HUICODE ACADEMY','huicodesocial@gmail.com','Rua dos Bandeirantes 3-33','Bauru','São Paulo');
*/

/* Insert tabela produto */
CREATE PROCEDURE `proc_insert_produto`(
	IN `codigoProduto` INT,
	IN `nome` VARCHAR(150),
	IN `quantidade` INT,
	IN `preco` DECIMAL(5,2)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	INSERT INTO produtos (codigoProduto, nome, quantidade, preco) VALUES (codigoProduto, nome, quantidade, preco);
END

CALL proc_insert_produto(6, 'berinjela', 20, 25.00);

/* Insert tabela tipo_produto */
CREATE PROCEDURE `proc_insert_tipo_produto`(
	IN `codigo` INT,
	IN `descricao` VARCHAR(150)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	INSERT INTO tipo_produto (id, codigo, descricao) VALUES (id, codigo, descricao);
	SELECT "Dados inseridos com sucesso" AS MESSAGE,COUNT(*) AS COUNT_ALL_TYPE FROM tipo_produto;
END


/* Insert table Funcionario */
CREATE PROCEDURE `proc_insert_funcionario`(
	IN `codigoFuncionario` INT,
	IN `nome` VARCHAR(150),
	IN `telefone` INT,
	IN `endereco` VARCHAR(150),
	IN `documento` CHAR(11),
	IN `salario` DECIMAL(5,2)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	INSERT INTO funcionario (codigoFuncionario, nome, telefone, endereco, documento, salario) 
	VALUES (codigoFuncionario, nome, telefone, endereco , documento, salario);
END


/* Insert table Cliente */

CREATE PROCEDURE `proc_create_client`(
	IN `codigoCliente` INT,
	IN `nome` VARCHAR(150),
	IN `telefone` INT,
	IN `estado_civil` VARCHAR(150),
	IN `numero_filhos` INT,
	IN `endereco` VARCHAR(250),
	IN `documento` CHAR(11)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	INSERT INTO cliente (codigoCliente, nome, telefone, estado_civil, numero_filhos, endereco, documento) 
	VALUES (codigoCliente, nome, telefone, estado_civil, numero_filhos, endereco, documento);
END

CALL proc_create_client(324, "Leonardo Silva", 992460976, 'viuvo', 2, 'Rua Jernano Abreu', '23567834098')


CREATE PROCEDURE `proc_insert_pedidos`(
	IN `codigo_pedido` INT,
	IN `codigo_produto` INT,
	IN `codigo_cliente` INT,
	IN `codigo_funcionario` INT,
	IN `valor` DECIMAL(5,2),
	IN `observacao` VARCHAR(250)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
/*
  id serial not null,
    codigo_pedido INTEGER,
    codigo_produto INTEGER,
    codigo_cliente INTEGER,
    codigo_funcionario INTEGER,
    valor DECIMAL(5,2),
    observacao VARCHAR(250),
    PRIMARY KEY (id),
    CONSTRAINT codigo_produto FOREIGN KEY (id) REFERENCES produtos(id),
    CONSTRAINT codigo_cliente FOREIGN KEY (id) REFERENCES cliente(id),
    CONSTRAINT codigo_funcionario FOREIGN KEY (id) REFERENCES funcionario(id)
);
*/
	INSERT INTO pedidos (codigo_pedido, codigo_produto, codigo_cliente, codigo_funcionario, valor, observacao) 
	VALUES (codigo_pedido, codigo_produto, codigo_cliente, codigo_funcionario, valor, observacao);
END

CALL proc_insert_pedidos(3, 3, 4, 5, 20.50, 'Xis');



/*
    2) Converter os scripts de SELECT já feitos em Procedimentos Armazenados.
*/

/* SELECT JOIN -
 Apresente as informações de todos os pedidos incluindo o nome do Cliente e do Funcionário;
SELECT pedidos.valor, pedidos.observacao
FROM ((pedidos
INNER JOIN cliente ON cliente.id = pedidos.codigo_cliente)
INNER JOIN funcionario ON funcionario.id = pedidos.codigo_funcionario);
 */

 CREATE PROCEDURE `proc_info_pedidos`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	SELECT pedidos.valor, pedidos.observacao
	FROM ((pedidos
	INNER JOIN cliente ON cliente.id = pedidos.codigo_cliente)
	INNER JOIN funcionario ON funcionario.id = pedidos.codigo_funcionario);
END

CALL proc_info_pedidos();

/* Busque o nome dos funcionários e o último pedido que ele participou 
(mesmo se não participou de nenhuma, deve aparecer no SELECT);
select funcionario.nome as nome_funcionarios, funcionario.codigoFuncionario from  
(funcionario inner join pedidos on pedidos.codigo_funcionario = funcionario.id);
*/
CREATE PROCEDURE `proc_funcionario_pedido`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	SELECT funcionario.nome as nome_funcionarios, funcionario.codigoFuncionario, pedidos.observacao  from  
	(funcionario inner join pedidos on pedidos.codigo_funcionario = funcionario.id);
END

CALL proc_funcionario_pedido();



/* Para cada funcionário que participou de uma venda mostre o nome, o código, e o salário acrescido em 5% 
para cada pedido que ele participa. */
CREATE PROCEDURE `proc_info_salario_pedido_funcionario`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	select funcionario.nome as nome_funcionarios, funcionario.codigoFuncionario, count(pedidos.codigo_funcionario) as numero_pedidos,
		funcionario.salario as salario, salario+count(pedidos.codigo_funcionario)*0.05 as acrescimo from 
		funcionario inner join pedidos on pedidos.codigo_funcionario = funcionario.id 
		GROUP by funcionario.salario , pedidos.codigo_funcionario;
END

CALL proc_info_salario_pedido_funcionario();


/* Faça uma visão que exiba, para cada CPF e nome de cliente os pedidos que ele fez juntamente com o valor. */
CREATE PROCEDURE `proc_info_cliente`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	select cliente.documento as CPF, cliente.nome as Nome, produtos.nome as produto, pedidos.valor from ((pedidos
	INNER JOIN cliente ON cliente.id = pedidos.codigo_cliente)
	INNER JOIN produtos ON produtos.id = pedidos.codigo_produto);
END

CALL proc_info_cliente()



/*
Criar um SELECT com CONCAT para os nomes. CONCAT(string | coluna);
*/
CREATE PROCEDURE `proc_concat_tables_by_id`(
	IN `table_select` VARCHAR(150),
	IN `id_select` INT
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	case
		when table_select = 'cliente' then
			SELECT CONCAT('Nome do cliente: ', nome) as descricao FROM cliente WHERE id = id_select;
		when table_select = 'funcionario' then
			SELECT CONCAT('Nome do funcionario: ', nome) as descricao FROM funcionario WHERE id = id_select;
		when table_select = 'produtos' then
			SELECT CONCAT('Produto: ', nome) as Produto_mais_vendido FROM produtos WHERE id = id_select;
		ELSE SELECT 'Table or id not found';
	END case;
END

CALL proc_concat_tables_by_id('cliente', 2)
CALL proc_concat_tables_by_id('funcionario', 4)
CALL proc_concat_tables_by_id('produtos', 1)
