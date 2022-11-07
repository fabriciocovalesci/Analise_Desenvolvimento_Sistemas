/*
    1) Criar um banco de dados utilizando a linguagem SQL de uma empresa que contenha as tabelas:
    a) Produtos (Código/Série, Nome ou Modelo, e o que achar necessário);
    b) Tipos de Produto (Código/Série, descrição, etc);
    c) Funcionários (Código, Nome, Telefone, Endereço, Documento, Salário);
    d) Clientes (Código, Nome, Telefone, Estado Civíl, Número de Filhos, Endereço, Documentos);
    e) Pedidos (Código do Pedido, Código do Produto, Código do Cliente, Código do Funcionário, Valor, Observação);
*/

create database IF NOT EXISTS empresaxpto;


create TABLE IF NOT EXISTS produtos (
    id serial not null,
    codigoProduto INTEGER,
    nome VARCHAR(250),
    quantidade INTEGER,
    preco DECIMAL(5, 2),
    PRIMARY KEY (id)
);


create TABLE IF NOT EXISTS tipo_produto(
    id serial not null,
    codigo INTEGER not null,
    descricao VARCHAR(250),
    PRIMARY KEY (id)
);


create TABLE IF NOT EXISTS funcionario(
    id serial not null,
    codigoFuncionario INTEGER,
    nome VARCHAR(250),
    telefone INTEGER,
    endereco VARCHAR(250),
    documento CHAR(11),
    salario DECIMAL(5,2),
    PRIMARY KEY (id)
);


create TABLE IF NOT EXISTS cliente(
    id serial not null,
    codigoCliente int,
    nome VARCHAR(250),
    telefone INTEGER,
    estado_civil VARCHAR(150),
    numero_filhos INTEGER,
    endereco VARCHAR(250),
    documento CHAR(11),
    PRIMARY KEY (id)
);


create TABLE IF NOT EXISTS pedidos(
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


/* 2) Insira 5 linhas de dados em cada tabela, para pedidos 7 e para o tipo de produtos insira pelo menos 12.*/
/* Produtos */
INSERT INTO produtos (id, codigoProduto, nome, quantidade, preco) VALUES (1, 001, 'batata', 10, 2.99);
INSERT INTO produtos (id, codigoProduto, nome, quantidade, preco) VALUES (2, 002, 'cebola', 10, 2.99);
INSERT INTO produtos (id, codigoProduto, nome, quantidade, preco) VALUES (3, 003, 'banana', 10, 4.99);
INSERT INTO produtos (id, codigoProduto, nome, quantidade, preco) VALUES (4, 004, 'maca', 10, 3.99);
INSERT INTO produtos (id, codigoProduto, nome, quantidade, preco) VALUES (5, 005, 'melao', 10, 1.99);

/* Tipo Produto */
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (1, 001, 'batata branca');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (2, 002, 'cebola roxa');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (3, 003, 'banana verde');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (4, 004, 'maca 2');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (5, 005, 'melao amarelo');

INSERT INTO tipo_produto (id, codigo, descricao) VALUES (6, 006, 'melancia');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (7, 007, 'arroz');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (8, 008, 'pao');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (9, 009, 'presunto');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (10, 010, 'queijo');

INSERT INTO tipo_produto (id, codigo, descricao) VALUES (11, 011, 'laranja');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (12, 012, 'morango');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (13, 013, 'suco');
INSERT INTO tipo_produto (id, codigo, descricao) VALUES (14, 014, 'suco de uva');
    INSERT INTO tipo_produto (id, codigo, descricao) VALUES (15, 015, 'cafe');

/* Funcionario */
INSERT INTO funcionario (id, codigoFuncionario, nome, telefone, endereco, documento, salario) 
VALUES (1, 001, 'Mariah Liz Ester Pereira', 1125013803, 'Vila Abdala' , '21359216537', 100.50);
INSERT INTO funcionario (id, codigoFuncionario, nome, telefone, endereco, documento, salario) 
VALUES (2, 002, 'Lúcia Antonella Jéssica Barros', 82983859190, 'Avenida Amazonas' , '14008166025', 230.50);
INSERT INTO funcionario (id, codigoFuncionario, nome, telefone, endereco, documento, salario) 
VALUES (3, 003, 'Eloá Alana Andreia Peixoto', 1125213803, 'Rua Santo Antônio' , '23547812097', 120.10);
INSERT INTO funcionario (id, codigoFuncionario, nome, telefone, endereco, documento, salario) 
VALUES (4, 004, 'Sebastiana Brenda da Mota', 1125013803, 'Rua Caraúbas' , '00826481092', 140.50);
INSERT INTO funcionario (id, codigoFuncionario, nome, telefone, endereco, documento, salario) 
VALUES (5, 005, 'Nicole Allana Novaes', 1125013803, 'Vitória da Conquista' , '11067398509', 130.50);

/* Cliente */
INSERT INTO cliente (id, codigoCliente, nome, telefone, estado_civil, numero_filhos, endereco, documento) VALUES (1, 001, 'Lorenzo Levi da Rosa', 1125013803, 'solteiro', 0, 'Vila Abdala' , '21359216537');
INSERT INTO cliente (id, codigoCliente, nome, telefone, estado_civil, numero_filhos, endereco, documento) VALUES (2, 002, 'Sueli Clara Barros', 1125013803, 'cadada', 2, 'Avenida Amazonas' , '12376598610');
INSERT INTO cliente (id, codigoCliente, nome, telefone, estado_civil, numero_filhos, endereco, documento) VALUES (3, 003, 'Augusto Alexandre Arthur Campos', 1125013803, 'separado', 2, 'Rua Santo Antônio' , '23547812097');
INSERT INTO cliente (id, codigoCliente, nome, telefone, estado_civil, numero_filhos, endereco, documento) VALUES (4, 004, 'Bryan Yuri Ruan Cardoso', 1125013803, 'solteiro', 1, 'Rua Caraúbas' , '00826481092');
INSERT INTO cliente (id, codigoCliente, nome, telefone, estado_civil, numero_filhos, endereco, documento) VALUES (5, 005, 'Maitê Mariana Rosa Corte Real', 1125013803, 'casada', 3, 'Vitória da Conquista' , '11067398509');

/* Pedidos */

INSERT INTO pedidos (id, codigo_pedido, codigo_produto, codigo_cliente, codigo_funcionario, valor, observacao) 
VALUES (1, 001, 2, 1, 3, 3.50, 'cafe sem açucar');

INSERT INTO pedidos (id, codigo_pedido, codigo_produto, codigo_cliente, codigo_funcionario, valor, observacao) 
VALUES (2, 002, 2, 2, 3, 10.50, 'pizza');

INSERT INTO pedidos (id, codigo_pedido, codigo_produto, codigo_cliente, codigo_funcionario, valor, observacao) 
VALUES (3, 3, 3, 1, 1, 12.50, 'x-tudo com refri');

INSERT INTO pedidos (id, codigo_pedido, codigo_produto, codigo_cliente, codigo_funcionario, valor, observacao) 
VALUES (4, 4, 1, 2, 5, 22.50, 'churasco');

INSERT INTO pedidos (id, codigo_pedido, codigo_produto, codigo_cliente, codigo_funcionario, valor, observacao) 
VALUES (4, 4, 2, 4, 3, 34.50, 'janta');

INSERT INTO pedidos (id, codigo_pedido, codigo_produto, codigo_cliente, codigo_funcionario, valor, observacao) 
VALUES (5, 5, 3, 1, 4, 16.50, 'almoco e cafe');


/* 3) Altere o código do cliente para um código com autoincremento. */
ALTER TABLE cliente AUTO_INCREMENT = 6;

/* 
4) Faça os SELECTs que:
    a) Um select para cada tabela.
*/
select * from produtos;
select * from tipo_produto;
select * from funcionario;
select * from cliente;
select * from pedidos;


/* Apresente as informações de todos os pedidos incluindo o nome do Cliente e do Funcionário; */
SELECT pedidos.valor, pedidos.observacao
FROM ((pedidos
INNER JOIN cliente ON cliente.id = pedidos.codigo_cliente)
INNER JOIN funcionario ON funcionario.id = pedidos.codigo_funcionario);


/* Mostre para cada pedido os tipos de produtos que participaram da transação. */


/* Busque o nome dos funcionários e o último pedido que ele participou 
(mesmo se não participou de nenhuma, deve aparecer no SELECT); */
SELECT funcionario.nome as nome_funcionarios, funcionario.codigoFuncionario, pedidos.observacao  from  
(funcionario inner join pedidos on pedidos.codigo_funcionario = funcionario.id);


/* Para cada funcionário que participou de uma venda mostre o nome, o código, e o salário acrescido em 5% 
para cada pedido que ele participa. */
select funcionario.nome as nome_funcionarios, funcionario.codigoFuncionario, count(pedidos.codigo_funcionario) as numero_pedidos,
funcionario.salario as salario, salario+count(pedidos.codigo_funcionario)*0.05 as acrescimo from 
funcionario inner join pedidos on pedidos.codigo_funcionario = funcionario.id 
GROUP by funcionario.salario , pedidos.codigo_funcionario;


/* Fala uma visão que exiba, para cada CPF e nome de cliente os pedidos que ele fez juntamente com o valor. */
select cliente.documento as CPF, cliente.nome as Nome, produtos.nome as produto, pedidos.valor from ((pedidos
INNER JOIN cliente ON cliente.id = pedidos.codigo_cliente)
INNER JOIN produtos ON produtos.id = pedidos.codigo_produto);






