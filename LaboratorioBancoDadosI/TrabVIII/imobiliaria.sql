/*
1 - Criar a modelagem (modelo relacional) Imobiliaria;

imovel
venda
aluguel
cliente
tipo_imovel
controle_interno_vendas


2 - Criar as tabelas que julgar necessárias para a construção da base de dados da empresa;

3 - Crie os comandos para apagar as tabelas inteiramente se necessário;

4 - Crie um comando para cada tabela para selecionar todos os dados;
*/


/* Criar as tabelas que julgar necessárias para a construção da base de dados da empresa; */

create database IF NOT EXISTS imobiliaria;

use imobiliaria;


create TABLE IF NOT EXISTS operacao (
    cod_operacao int not null AUTO_INCREMENT,
    tipo VARCHAR(150),
    data date,
    data_final date,
    PRIMARY KEY (cod_operacao)
);


create TABLE IF NOT EXISTS endereco (
    cod_endereco int not null AUTO_INCREMENT,
    numero INTEGER,
    rua VARCHAR(150),
    bairro VARCHAR(150),
    cidade VARCHAR(150),
    estado char(2),
    PRIMARY KEY (cod_endereco)
);


create TABLE IF NOT EXISTS tipo_imovel (
    cod_tipo int not null AUTO_INCREMENT,
    tipo VARCHAR(150),
    PRIMARY KEY (cod_tipo)
);


create TABLE IF NOT EXISTS imovel (
    cod_imovel int not null AUTO_INCREMENT,
    area INTEGER not null,
    detalhes VARCHAR(150),
    valor DECIMAL(10, 6),
    PRIMARY KEY (cod_imovel),
    cod_tipo INTEGER,
    cod_imov_endereco INTEGER,
    CONSTRAINT cod_tipo FOREIGN KEY (cod_tipo) REFERENCES tipo_imovel(cod_tipo),
    CONSTRAINT cod_imov_endereco FOREIGN KEY (cod_imov_endereco) REFERENCES endereco(cod_endereco)
);


create TABLE IF NOT EXISTS proprietario (
    cod_proprietario int not null AUTO_INCREMENT,
    nome VARCHAR(150),
    cpf char(11),
    telefone VARCHAR(20),
    fk_endereco INTEGER,
    PRIMARY KEY (cod_proprietario),
    CONSTRAINT fk_endereco FOREIGN KEY (fk_endereco) REFERENCES endereco(cod_endereco)
);


create TABLE IF NOT EXISTS cliente (
    cod_cliente int not null AUTO_INCREMENT,
    nome VARCHAR(150),
    cpf char(11),
    telefone VARCHAR(20),
    cod_endereco INTEGER,
    PRIMARY KEY (cod_cliente),
    CONSTRAINT cod_endereco FOREIGN KEY (cod_endereco) REFERENCES endereco(cod_endereco)
);


create TABLE IF NOT EXISTS venda_locacao (
    cod_venda_locacao int not null AUTO_INCREMENT,
    cod_operacao int,
    cod_imovel int,
    cod_proprietario int,
    cod_cliente int,
    PRIMARY KEY (cod_venda_locacao),
    CONSTRAINT cod_operacao FOREIGN KEY (cod_operacao) REFERENCES operacao(cod_operacao),
    CONSTRAINT cod_imovel FOREIGN KEY (cod_imovel) REFERENCES imovel(cod_imovel),
    CONSTRAINT cod_cliente FOREIGN KEY (cod_cliente) REFERENCES cliente(cod_cliente),
    CONSTRAINT cod_proprietario FOREIGN KEY (cod_proprietario) REFERENCES proprietario(cod_proprietario)
);


create TABLE IF NOT EXISTS controle_interno_vendas (
    cod_controle int(10) NOT NULL AUTO_INCREMENT,
    nome_cliente VARCHAR(100),
    nome_proprietario VARCHAR(100),
    valor DECIMAL(10, 6),
    PRIMARY KEY (cod_controle)
);


/* Crie os comandos para apagar as tabelas inteiramente se necessário; */

DROP TABLE venda_locacao;
DROP TABLE imovel;
DROP TABLE operacao;
DROP TABLE proprietario;
DROP TABLE cliente;
DROP TABLE endereco;
DROP TABLE tipo_imovel;
DROP TABLE controle_interno_vendas;


/* Crie um comando para cada tabela para selecionar todos os dados; */

select * from imovel;
select * from venda_locacao;
select * from operacao;
select * from proprietario;
select * from cliente;
select * from endereco;
select * from tipo_imovel;
select * from controle_interno_vendas;


/* Insira a quantidade de dados necessários para cada tabela (no mínimo 5 linhas para cada tabela); */

/* TABLE OPERACAO */
INSERT INTO operacao (tipo, data, data_final) VALUES ('aluguel', '2022-05-02', null);
INSERT INTO operacao (tipo, data, data_final) VALUES ('aluguel', '2022-05-12', '2022-05-12');
INSERT INTO operacao (tipo, data, data_final) VALUES ('aluguel', '2022-05-10', '2022-05-10');
INSERT INTO operacao (tipo, data, data_final) VALUES ('venda', '2022-05-12', null);
INSERT INTO operacao (tipo, data, data_final) VALUES ('venda', '2022-05-15', NULL);
INSERT INTO operacao (tipo, data, data_final) VALUES ('venda', '2022-05-20', NULL);


/* TABLE ENDERECO */
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (12, "Rua Abelardo Ferreira Gomes", "Jeronimo de Medeiros Prado", "Sobral", "CE");
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (340, "Rua Porto Velho", "Dom Bosco", "Ji-Parana", "RO");
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (102, "Rua Jose Benedito Petry", "Sertao do Maruim", "Sao Jose", "SC");
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (450, "Rua Rio Juruena", "Capao do Pequi", "Varzea Grande", "MT" );
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (588, "Via das Flores", "Pricuma", "Boa Vista", "RR" );
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (250, "3 Travessa Valdemar Lino Chaves", "Cidade Alta", "Caruaru", "PE");
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (122, "Duque de caxias", "Cruzeiro", "Passo Fundo", "RS");
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (55, "Av 7 de Setembro", "Centro", "Erechim", "RS");
INSERT INTO endereco (numero, rua, bairro, cidade, estado) VALUES (878, "Joaquim de Moura Faitao", "Aldo Ariole", "Erechim", "RS");


/* TABLE TIPO_IMOVEL */
INSERT INTO tipo_imovel (tipo) VALUES ('casa');
INSERT INTO tipo_imovel (tipo) VALUES ('apartamento');
INSERT INTO tipo_imovel (tipo) VALUES ('terreno');
INSERT INTO tipo_imovel (tipo) VALUES ('chacara');


/* TABLE CLIENTE */
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Carolina Ana Rafaela", "61268511617", "(88) 36035683", 1);
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Raquel Emily Giovana Ferreira", "20459940589", "(69) 985400658", 2);
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Sarah Sandra Corte Real", "35399702231", "(48) 999791103", 3);
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Sophie Maite Elza Novaes", "48414579418", "(65) 982368186", 4);
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Ayla Melissa Fogaca", "26483346964", "(95) 981855049", 5);
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Sophia Helena da Cruz", "42121600990","(81) 992418467", 6);
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Paulo Souza", "12748690476","(54) 996633451",7);
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Maria Jose", "34567821456","(47) 992418467", 8);
INSERT INTO cliente (nome, cpf, telefone, cod_endereco) VALUES ("Antonio P", "34567823426","(55) 992418467", 9);


/* TABLE PROPRIETARIO */
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Carolina Rafaela dos Santos", "25611967981", "(92) 998203119", 1);
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Livia Jaqueline Sara Aragao", "93636469479", "(82) 26007951", 2);
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Beatriz Fernanda Rocha", "30220194831", "(67) 993237246", 3);
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Danilo Julio Lorenzo Araujo", "72239825944", "(79) 998000086", 4);
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Samuel Martin Iago Teixeira", "71474259600", "(94) 26568225", 5);
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Nathan Felipe Santos", "88185530386", "(96) 988849691", 6);
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Maria Paula Santos", "55185530382", "(54) 988849691", 7);
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Nathan Santos", "99185530316", "(44) 988849691", 8);
INSERT INTO proprietario (nome, cpf, telefone, fk_endereco) VALUES ("Felipe Santos", "00185530386", "(55) 988849691", 9);



/* TABLE IMOVEL */
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco) VALUES (150, 'CASA ALTO PADRAO CIDADE NOVA 03 SUITES 02 VAGAS', 1.6500000, 1, 1);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco) VALUES (141, 'EDIFICIO NOUVE 354 APARTAMENTO 02 DORMITORIOS', 802.95180, 2, 2);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco) VALUES (1000, 'AREA RURAL 10 HECTARES A 5 KM DA CIDADE', 1.38000000, 3, 3);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco) VALUES (280, 'CHACARA PETROPOLIS', 1.90000000, 4, 4);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco) VALUES (150, 'CASA NA SANTA RITA', 700.00, 1, 2);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco) VALUES (200, 'CASA C/ 05 SUITES - DUPLICADO', 1.000, 1, 3);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco) VALUES (120, 'APARTAMENTO 1 DORMITORIO', 500, 1, 3);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco) VALUES (148, 'ED. MONTREAL 03 DORMITORIOS COM SUITE 02 VAGAS', 2.99900, 2, 5);


/* TABLE VENDA_LOCACAO */
INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (1, 2, 4, 5);
INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (4, 1, 2, 3);
INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (3, 1, 2, 5);
INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (5, 2, 4, 6);
INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (6, 3, 2, 2);
INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (1, 9, 7, 7);
INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (5, 10, 8, 9);
INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (3, 11, 9, 8);


/* 1) Dê exemplo de um comando utilizando subconsultas que utilize o operador = ou <,>, <= etc. */
SELECT cliente.nome, imovel.valor from venda_locacao
INNER JOIN cliente ON cliente.cod_cliente = venda_locacao.cod_cliente
INNER JOIN imovel ON imovel.cod_imovel = venda_locacao.cod_imovel
WHERE imovel.valor >=
(SELECT imovel.valor
FROM imovel
WHERE imovel.valor =  802.951800);


/* 2) Dê exemplo de um comando utilizando subconsultas (subqueries) que utilize o operador in. */
SELECT cliente.nome, endereco.rua from cliente
INNER JOIN endereco ON endereco.cod_endereco = cliente.cod_endereco
WHERE endereco.rua IN 
(SELECT endereco.rua FROM endereco WHERE LOWER(endereco.rua) LIKE '%rua%')


/* 3) Dê exemplo de um comando utilizando subconsultas que utilize o operador not in. */
SELECT cliente.nome, tipo_imovel.tipo from venda_locacao
INNER JOIN cliente ON cliente.cod_cliente = venda_locacao.cod_cliente
INNER JOIN imovel ON imovel.cod_imovel = venda_locacao.cod_imovel
INNER JOIN tipo_imovel ON tipo_imovel.cod_tipo = imovel.cod_tipo
WHERE  tipo_imovel.tipo NOT IN
(SELECT tipo_imovel.tipo FROM tipo_imovel WHERE LOWER(tipo_imovel.tipo) LIKE '%apartamento%')


/* 4) Dê exemplo de um comando utilizando subconsultas que utilize o operador exists. */
SELECT im.detalhes FROM imovel im
WHERE EXISTS 
(SELECT venda_locacao.cod_imovel FROM venda_locacao
WHERE im.cod_imovel = venda_locacao.cod_imovel)


/* 5) Dê exemplo de um comando utilizando subconsultas que utilize o operador not exists. */
SELECT im.detalhes FROM imovel im
WHERE NOT EXISTS 
(SELECT venda_locacao.cod_imovel FROM venda_locacao
WHERE im.cod_imovel = venda_locacao.cod_imovel)


/* 6) Dê exemplo de uma subconsulta utilizada dentro de um comando Update. */
UPDATE tipo_imovel SET tipo_imovel.tipo = "terrenos" FROM tipo_imovel
WHERE tipo_imovel.cod_tipo =  (
SELECT tipo_imovel.cod_tipo FROM tipo_imovel
WHERE tipo_imovel.tipo = 'terreno'
)


/* 7) Dê exemplo de uma subconsulta utilizada dentro de um comando Delete. */
DELETE FROM tipo_imovel 
WHERE tipo_imovel.cod_tipo =  (
SELECT tipo_imovel.cod_tipo FROM tipo_imovel
WHERE tipo_imovel.tipo LIKE 'terr%'
)


/* 8) Escreva uma função que seja útil para a lógica de negócios de seu sistema e indique
o contexto de sua utilização. */

/*CONTEXTO: CASO CLIENTE QUE ESTIVER COMPRANDO UM IMOVEL PODE PARCELAR EM ATE 3X, E ASSIM SE UTILIZAR OS 
DESCONTOS:
1X: 20%
2X: 15%
3X: 10%
*/

CREATE DEFINER=`root`@`%` FUNCTION `func_calcula_desconto`(
	`valor` DECIMAL(20,6),
	`parcela` INT
)
RETURNS decimal(10,5)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	DECLARE valorFinal DECIMAL(10,5);
	case
		when parcela = 1 then
			SET valorFinal = valor - (valor * 20/100); 
		when parcela = 2 then
			SET valorFinal = valor - (valor * 15/100); 
		when parcela = 3 then
			SET valorFinal = valor - (valor * 10/100); 
		ELSE 	SET valorFinal = 0;
	END case;
	SET valorFinal = round(valorFinal, 2);
	RETURN valorFinal;
END


SELECT func_calcula_desconto(imovel.valor, 1) FROM imovel;


/* 9) Escreva uma Stored Procedure que implemente uma regra de negócio de sua
aplicação. Explique qual é e o porquê desta regra ser implementada no SGBD. */

/* Esta Stored Procedure faz uma busca no banco de dados para retornar apenas os imoveis no estado informado */

CREATE PROCEDURE `proc_busca_imovel_estado`(
	IN `estado` CHAR(2)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	SELECT * FROM imovel im
	INNER JOIN endereco ed ON ed.cod_endereco = im.cod_imov_endereco
	WHERE ed.estado LIKE estado;
END

CALL proc_busca_imovel_estado('sc')


/* 10) De acordo com o tema escolhido, escreva um procedimento armazenado (Stored
Procedure) para calcular totalizações do sistema. Esta totalização deve ser exibida
como resultado do procedimento ou gravada em alguma tabela.
Exemplo:
Valor total da locação (diárias + seguro + danos, se houver) => para tema da
imobiliária
Total de freqüência do aluno no curso => calculado a partir do registro diário de
frequência do aluno no curso => para tema da Escola */

ALTER TABLE imovel ADD seguro decimal(10, 5);

INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco, seguro) VALUES (130, 'ED. MONTREAL 01 DORMITORIOS  01 VAGAS', 750.00, 3, 1, 200.00);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco, seguro) VALUES (100, 'KITNET 01 DORMITORIOS  01 VAGAS', 900.00, 2, 4, 100.00);
INSERT INTO imovel (area, detalhes, valor, cod_tipo, cod_imov_endereco, seguro) VALUES (220, 'CASA 01 DORMITORIOS  01 VAGAS', 550.00, 1, 5, 150.00);

/* Foi criado um procedimento para buscar usuario por qualquer parte do nome
e ja retornar o seu nome, valor do imovel, valor seguro e o total (soma seguro com a venda ou aluguel) */

CREATE FUNCTION `func_soma_tributos`(
	`valor` DECIMAL(20,6),
	`seguro` INT
)
RETURNS deCIMAL
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	DECLARE valorTotal DECIMAL(10,7);
	
	if seguro IS NOT NULL then
		SET valorTotal = valor + seguro;
	ELSE 
		SET valorTotal = valor;
	END if;
	
	RETURN valorTotal;
END


CREATE PROCEDURE `proc_soma_valor`(
	IN `nome_cliente` VARCHAR(150)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	SELECT cl.nome, round(im.valor, 2) AS valor_imovel, im.seguro AS valor_seguro, ROUND((coalesce(im.valor ,0) + coalesce(im.seguro ,0)),2) AS Valor_Total FROM imovel im
	INNER JOIN venda_locacao vl ON vl.cod_imovel = im.cod_imovel
	INNER JOIN cliente cl ON cl.cod_cliente = vl.cod_cliente
	WHERE cl.nome LIKE CONCAT('%', nome_cliente , '%');
END

CALL proc_soma_valor('s')


/* 11) De acordo com o tema escolhido, escreva um trigger para realizar uma ação
automática que seja necessária para o sistema manter a integridade dos dados.
Exemplo:
Cada vez que for inserida a matrícula de um aluno no curso, diminuir o número de
vagas disponíveis no curso => para tema da Escola
Cada vez que uma locação for encerrada, calcular o total de pontos de fidelidade =>
para tema da Imobiliária
Atualizar o saldo bancário cada vez que ocorrer um lançamento de débito ou
crédito=> para o Tema de Contas bancárias. */

/* Foi criado uma tabela nova para controle interno, que ira conter nome do cliente, nome proprietario e o 
valor do imovel, toda vez que uma venda ou locação é realizada é inserido dados dos nessa nova tabela. */

CREATE TRIGGER `venda_locacao_after_insert` AFTER INSERT ON `venda_locacao` FOR EACH ROW BEGIN	
	INSERT INTO controle_interno_vendas(nome_cliente, nome_proprietario, valor)
	SELECT cl.nome, prt.nome, im.valor FROM venda_locacao vl 
	INNER JOIN cliente cl ON cl.cod_cliente = vl.cod_cliente
	INNER JOIN proprietario prt ON prt.cod_proprietario = vl.cod_proprietario
	INNER JOIN imovel im ON im.cod_imovel = vl.cod_imovel
	ORDER BY vl.cod_venda_locacao DESC LIMIT 1;
END

INSERT INTO venda_locacao (cod_operacao, cod_imovel, cod_proprietario, cod_cliente) VALUES (4, 5, 3, 9);

SELECT * FROM controle_interno_vendas;