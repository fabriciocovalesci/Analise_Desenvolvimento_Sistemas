

/*
1- docker run -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -v /home/fabricio/Documentos/volume_mysql:/var/lib/mysql mysql
2- docker exec -it numero_container bash */

/*
1 - Criar uma tabela de estoque que referencia a tabela produto e a tipo produto
mostrando a quantidade que há no estoque de cada produto. Insira uma linha para
cada produto sendo ao menos uma delas com valor nulo para teste.
*/

create TABLE IF NOT EXISTS estoque (
    id int NOT NULL,
    nome VARCHAR(250),
    quantidade INT,
    produtoId INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (produtoId) REFERENCES produtos(id)
);


/*
2 - Criar uma coluna nova nas tabelas cliente e funcionário indicando o sobrenome.
Para os que fizeram a coluna nome e inseriram o nome composto, podem inserir
uma nova coluna com um terceiro nome
*/

ALTER TABLE cliente ADD sobrenome VARCHAR(200);
ALTER TABLE funcionario ADD sobrenome VARCHAR(200);


/*
3 - Criar um SELECT com CONCAT para os nomes.
CONCAT(string | coluna);
*/

SELECT CONCAT('Nome do cliente: ', nome) as descricao FROM cliente WHERE id = 2;
SELECT CONCAT('Nome do funcionario: ', nome) as descricao FROM funcionario WHERE id = 5;

/*
4 - Criar um SELECT com CONCAT para mostrar os produtos mais vendidos (apenas
ficcional).
*/

SELECT CONCAT('Produto: ', nome) as Produto_mais_vendido FROM produtos WHERE preco > 4;

/*
5 - Criar um SELECT com IFNULL que mostre um valor nulo sendo substituído por 0;
IFNULL (valor, substituição)
*/

select CONCAT('Sobrenome: ', IFNULL(sobrenome, 0)) as Sobrenome FROM cliente WHERE id = 2;


/*
Criar um SELECT com COALESCE que mostre um valor nulo sendo substituído por 0;
IFNULL (valor, substituição)
*/

select COALESCE(produtos.preco > 10, produtos.preco > 5) as preco_produto FROM produtos WHERE id = 3;