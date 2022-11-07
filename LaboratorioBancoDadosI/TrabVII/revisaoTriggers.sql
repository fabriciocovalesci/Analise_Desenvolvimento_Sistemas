

create database IF NOT EXISTS contas;

use contas;

/* usuario (ou customer) (cpf, nome, sobrenome, num_celular, plano); */
create TABLE IF NOT EXISTS usuario (
    cod_user int not null AUTO_INCREMENT,
    cpf char(11),
    nome VARCHAR(150),
    sobrenome VARCHAR(150),
    num_celular VARCHAR(20),
    PRIMARY KEY (cod_user),
    cod_plano INTEGER,
    CONSTRAINT cod_plano FOREIGN KEY (cod_plano) REFERENCES plano(cod_plano)
);

/* plano (ou mobileplan) (cod, taxa_conexao, preco_segundo) */
create TABLE IF NOT EXISTS plano (
    cod_plano int not null AUTO_INCREMENT,
    taxa_conexao INTEGER,
    preco_segundo DECIMAL(10,2),
    PRIMARY KEY (cod_plano)
);

/* ligacoes (ou phonecall) (cod_ligacao, data, tempo_ligacao, segundos) */
create TABLE IF NOT EXISTS ligacoes (
    cod_ligacao int not null AUTO_INCREMENT,
    data DATE,
    tempo_ligacao TIMESTAMP,
    segundos INTEGER,
    PRIMARY KEY (cod_ligacao)
);

/* conta (ou bill) (cod_conta, cod_ligacao, cpf, mes, ano, valor) */
create TABLE IF NOT EXISTS conta (
    cod_conta int not null AUTO_INCREMENT,
    cod_ligacao INTEGER,
    cpf CHAR(11),
    mes VARCHAR(20),
    ano VARCHAR(20),
    valor DECIMAL(10,2),
    PRIMARY KEY (cod_conta),
    CONSTRAINT cod_ligacao FOREIGN KEY (cod_ligacao) REFERENCES ligacoes(cod_ligacao)
);

/* plano (ou mobileplan) (cod, taxa_conexao, preco_segundo) */
INSERT INTO plano (taxa_conexao, preco_segundo) VALUES (200, 50.00);
INSERT INTO plano (taxa_conexao, preco_segundo) VALUES (300, 150.00);
INSERT INTO plano (taxa_conexao, preco_segundo) VALUES (400, 200.00);
INSERT INTO plano (taxa_conexao, preco_segundo) VALUES (500, 250.00);

/* ligacoes (ou phonecall) (cod_ligacao, data, tempo_ligacao, segundos) */
INSERT INTO ligacoes (data, tempo_ligacao, segundos) VALUES ('2022-04-23', '2022-04-23 12:15:11', 60);
INSERT INTO ligacoes (data, tempo_ligacao, segundos) VALUES ('2022-04-26', '2022-04-23 13:15:11', 40);
INSERT INTO ligacoes (data, tempo_ligacao, segundos) VALUES ('2022-04-25', '2022-04-23 14:15:11', 120);
INSERT INTO ligacoes (data, tempo_ligacao, segundos) VALUES ('2022-04-24', '2022-04-23 15:15:11', 90);
INSERT INTO ligacoes (data, tempo_ligacao, segundos) VALUES ('2022-04-24', '2022-04-20 18:45:11', 100);

/* conta (ou bill) (cod_conta, cod_ligacao, cpf, mes, ano, valor) */
INSERT INTO conta (cod_ligacao, cpf, mes, ano, valor) VALUES (1, '04777721027', '05', '2022', 180.00);
INSERT INTO conta (cod_ligacao, cpf, mes, ano, valor) VALUES (2, '52306437068', '06', '2022', 185.00);
INSERT INTO conta (cod_ligacao, cpf, mes, ano, valor) VALUES (3, '93916693018', '07', '2022', 182.00);
INSERT INTO conta (cod_ligacao, cpf, mes, ano, valor) VALUES (4, '68436898095', '08', '2022', 175.00);

/* usuario (ou customer) (cpf, nome, sobrenome, num_celular, plano); */
INSERT INTO usuario (cpf, nome, sobrenome, num_celular, cod_plano) VALUES ('04777721027', 'paulo', 'jose', '1188993467', 1);
INSERT INTO usuario (cpf, nome, sobrenome, num_celular, cod_plano) VALUES ('52306437068', 'maria', 'antonia', '2288664490', 2);
INSERT INTO usuario (cpf, nome, sobrenome, num_celular, cod_plano) VALUES ('93916693018', 'augusto', 'leo', '5488664400', 3);
INSERT INTO usuario (cpf, nome, sobrenome, num_celular, cod_plano) VALUES ('68436898095', 'lucia', 'jose', '1488446655', 4);


ALTER TABLE ligacoes ADD numero_chamado VARCHAR(20);

/* 1) Escreva um trigger que depois de cada ligação nova, crie uma nova conta referente aquela ligação. */

CREATE TRIGGER `ligacoes_before_insert` AFTER INSERT ON `ligacoes` FOR EACH ROW BEGIN
	INSERT INTO conta (cod_ligacao, cpf, mes, ano, valor) VALUES (NEW.cod_ligacao, '04777721027', '05', '2022', 190.00);
END

/* 2) Assumimos que as contas atualizadas estão sempre já presente no banco de
dados. Para fazer isso, podemos criar outro gatilho que crie uma fatura com
um valor de 0 para cada cliente registrado no início de cada mês (suponha
que temos o evento END_MONTH). */
INSERT INTO usuario (cpf, nome, sobrenome, num_celular, cod_plano)  VALUES ('68436898095', 'fabio', 'jose', '1488446655', 3);

CREATE TRIGGER `usuario_after_insert_fatura` AFTER INSERT ON `usuario` FOR EACH ROW BEGIN
	INSERT INTO conta (cod_ligacao, cpf, mes, ano, valor) VALUES (5, NEW.cpf, '06', '2022', 0.00);
END


/* 3) Escreva um gatilho que, no final de cada mês, desconte das faturas em 5
centavos por chamada para usuários diretos da empresa (ou seja, para
números de usuários cadastrados na tabela CLIENTE) se o valor total mensal
a fatura for superior a R$100,00. */

INSERT INTO usuario (cpf, nome, sobrenome, num_celular, cod_plano)
 VALUES ('68436898085', 'bruno', 'jose', '1488446655', 4);

CREATE TRIGGER `usuario_after_insert` AFTER INSERT ON `usuario` FOR EACH ROW BEGIN

	DECLARE value_fatura DECIMAL(10, 2);
	DECLARE id_conta int;
	
	declare fimloop INT DEFAULT 0;
	
	DECLARE cursor_status CURSOR FOR SELECT conta.cod_conta , (conta.valor - (COUNT(usuario.cpf)*0.05)) FROM usuario 
			INNER JOIN conta ON conta.cpf = usuario.cpf
			INNER JOIN ligacoes ON ligacoes.cod_ligacao = conta.cod_ligacao
			WHERE conta.valor >= 100.00
			GROUP BY usuario.cod_user, usuario.cpf, conta.valor, conta.cod_conta;
			
	DECLARE CONTINUE handler FOR NOT FOUND SET fimloop = 1;
	
	OPEN cursor_status;
	
		while(fimloop != 1)do
		
		fetch NEXT from cursor_status INTO id_conta, value_fatura;
		
		UPDATE conta SET conta.valor = value_fatura WHERE conta.cod_conta = id_conta;
			
	END while;
	
	close cursor_status;
END


/**********************************************************************************

    Tabela VOLEIBOL

**********************************************************************************/

create database IF NOT EXISTS volei;

use volei;


/* jogador (id_jogador, nome, time, altura, aniversario, partidas_jogadas) */
create TABLE IF NOT EXISTS jogador (
    id_jogador int not null AUTO_INCREMENT,
    nome varchar(100),
    id_time INTEGER,
    altura integer,
    aniversario date,
    partidas_jogadas integer,
    PRIMARY KEY (id_jogador),
    CONSTRAINT id_time FOREIGN KEY (id_time) REFERENCES time(id_time),
    CONSTRAINT partidas_jogadas FOREIGN KEY (partidas_jogadas) REFERENCES partida(id_partida)
);

INSERT INTO jogador (nome, id_time, altura, aniversario, partidas_jogadas) VALUES ("paulo", 1, 175, "2000-04-25", 1);
INSERT INTO jogador (nome, id_time, altura, aniversario, partidas_jogadas) VALUES ("maria", 2, 185, "2000-04-25", 2);

/* time (time, técnico, jogos_ganhos) */
create TABLE IF NOT EXISTS time (
    id_time int not null AUTO_INCREMENT,
    tecnico VARCHAR(100),
    jogos_ganhos int,
    PRIMARY KEY (id_time)
);

INSERT INTO time (tecnico, jogos_ganhos) VALUES ("pedro", 0);
INSERT INTO time (tecnico, jogos_ganhos) VALUES ("julio", 0);

/* partida (id_partida, data, time1, time2, sets_ganhos_time1, sets_ganhos_time2, juiz) */
create TABLE IF NOT EXISTS partida (
    id_partida int not null AUTO_INCREMENT,
    data DATE,
    time1 INTEGER,
    time2 INTEGER,
    sets_ganhos_time1 INTEGER,
    sets_ganhos_time2 INTEGER,
    juiz VARCHAR(100),
    PRIMARY KEY (id_partida),
    CONSTRAINT time1 FOREIGN KEY (time1) REFERENCES time(id_time),
    CONSTRAINT time2 FOREIGN KEY (time2) REFERENCES time(id_time)
);

INSERT INTO partida (data, time1, time2, sets_ganhos_time1, sets_ganhos_time2, juiz) VALUES ("2022-04-25", 1, 2, 0,3, 'kiko');
INSERT INTO partida (data, time1, time2, sets_ganhos_time1, sets_ganhos_time2, juiz) VALUES ("2022-04-25", 1, 2, 3,0, 'chaves');

/* partida_jogador (id_jogadas, id_partida, id_jogador, funcao, pontuação */
create TABLE IF NOT EXISTS partida_jogador (
    id_jogadas int not null AUTO_INCREMENT,
    id_partida INTEGER,
    id_jogador INTEGER,
    funcao VARCHAR(100),
    pontuacao INTEGER,
    PRIMARY KEY (id_jogadas),
    CONSTRAINT id_partida FOREIGN KEY (id_partida) REFERENCES partida(id_partida),
    CONSTRAINT id_jogador FOREIGN KEY (id_jogador) REFERENCES jogador(id_jogador)
);

INSERT INTO partida_jogador (id_partida, id_jogador, funcao, pontuacao) VALUES (1, 2, 'jogador', 20);
INSERT INTO partida_jogador (id_partida, id_jogador, funcao, pontuacao) VALUES (2, 1, 'jogador2', 25);

/* 1) Construa um gatilho que mantenha o valor de Jogos_Ganhos após inserções
em GAME tendo em conta que Jogos_Ganhos é relativo a todo o história da
equipe, não só para o torneio atual, e que um equipe ganha um jogo quando
ele ganha 3 sets. */
CREATE TRIGGER `partida_after_update` AFTER UPDATE ON `partida` FOR EACH ROW BEGIN
	if new.sets_ganhos_time1 = 3 then
		UPDATE time SET time.jogos_ganhos = 3 WHERE time.id_time = 1;
	ELSEIF new.sets_ganhos_time2 = 3 then
		UPDATE time SET time.jogos_ganhos = 3 WHERE time.id_time = 2;
	else
		UPDATE time SET time.jogos_ganhos = 0 WHERE time.id_time = 1;
		UPDATE time SET time.jogos_ganhos = 0 WHERE time.id_time = 2;
	END if;
END

UPDATE partida SET partida.sets_ganhos_time1 = 3 WHERE partida.id_partida = 1;


/*2) Construa um trigger que mantenha partidas_jogadas atualizada após inserir
na tabela partida_jogador.*/

CREATE TRIGGER `partida_jogador_after_insert` AFTER INSERT ON `partida_jogador` FOR EACH ROW BEGIN

	if NEW.pontuacao = 25 then
		UPDATE partida SET sets_ganhos_time1 = 1 WHERE partida.id_partida = NEW.id_partida;
	END if;

END

INSERT INTO partida_jogador (id_partida, id_jogador, funcao, pontuacao) VALUES (2, 1, 'jogador23', 25);
