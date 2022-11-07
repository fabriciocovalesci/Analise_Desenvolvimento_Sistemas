

/*******************************************************************************

1) Para iniciarmos, crie um banco de dados com o nome
Clínica;

Obs.: A partir segunda questão considere que os atributos em
negrito são chaves primárias (PK) e os em itálico são chaves
estrangeiras (FK). Não esqueça de utilizar CONSTRAINT.

*********************************************************************************/

create database IF NOT EXISTS clinica;

use clinica;


/**********************************************************************************

2) Crie as seguintes tabelas:
a) ambulatorios
    i) cod_amb (int)
    ii) andar (int) (não nulo)
    iii) capacidade (int)
b) medicos
    i) cod_m (int)
    ii) nome (varchar(40)) (não nulo)
    iii) idade (int) (não nulo)
    iv) especialidade (varchar(20))
    v) CPF (varchar(11)) (único)
    vi) cidade (varchar(30))
    vii) cod_amb (int)
c) pacientes
    i) cod_p (int)
    ii) nome (varchar(40)) (não nulo)
    iii) idade (smallint) (não nulo)
    iv) cidade (char(30))
    v) CPF (numeric(11)) (único)
    vi) doenca (varchar(40)) (não nulo)
d) funcionarios
    i) cod_f (int)
    ii) nome (varchar(40)) (não nulo)
    iii) idade (smallint)
    iv) CPF (varchar(11)) (único)
    v) cidade (varchar(30))
    vi) salario (numeric(10))
    vii) cargo (varchar(20))
e) consultas
    i) cod_m (int)
    ii) cod_p (int)
    iii) data (date) (não nulo)
    iv) hora (time) (não nulo)

**********************************************************************************/

create TABLE IF NOT EXISTS ambulatorios (
    cod_amb int not null AUTO_INCREMENT,
    andar INTEGER,
    capacidade INTEGER,
    PRIMARY KEY (cod_amb)
);


create TABLE IF NOT EXISTS medicos (
    cod_m int not null AUTO_INCREMENT,
    nome VARCHAR(40) not null,
    idade INTEGER not null,
    especialidade VARCHAR(20),
    cpf CHAR(11) unique,
    cidade VARCHAR(30),    
    PRIMARY KEY (cod_m),
    cod_amb INTEGER,
    CONSTRAINT cod_amb FOREIGN KEY (cod_amb) REFERENCES ambulatorios(cod_amb)
);


CREATE TABLE IF NOT EXISTS `pacientes` (
    `cod_p` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(40) NOT NULL,
    idade smallint NOT NULL,
    cidade varchar(30),
    cpf char(11) UNIQUE,
    doenca varchar(40) NOT NULL,
    PRIMARY KEY (`cod_p`)
);


CREATE TABLE IF NOT EXISTS `funcionarios` (
    `cod_f` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(40) NOT NULL,
    idade smallint NOT NULL,
    cidade varchar(30),
    cpf char(11) UNIQUE,
    salario DECIMAL(10, 2),
    cargo varchar(40),
    PRIMARY KEY (`cod_f`)
);


CREATE TABLE IF NOT EXISTS `consultas` (
    `cod_m` INT NOT NULL,
    `cod_p` INT NOT NULL,
    data date NOT NULL,
    hora TIME NOT NULL,
    CONSTRAINT cod_m FOREIGN KEY (cod_m) REFERENCES medicos(cod_m),
    CONSTRAINT cod_p FOREIGN KEY (cod_p) REFERENCES pacientes(cod_p)
);


/**********************************************************************************

3) Agora insira 5 linhas de dados na tabelas: ambulatorio,
medico, paciente, funcionários e insira 10 linhas de código na
tabela consultas.

**********************************************************************************/

INSERT INTO ambulatorios (andar, capacidade) VALUES (2, 200);
INSERT INTO ambulatorios (andar, capacidade) VALUES (2, 230);
INSERT INTO ambulatorios (andar, capacidade) VALUES (4, 50);
INSERT INTO ambulatorios (andar, capacidade) VALUES (5, 120);
INSERT INTO ambulatorios (andar, capacidade) VALUES (6, 90);


INSERT INTO medicos (nome, idade, especialidade, cpf, cidade, cod_amb) VALUES ("Paulo silva", 45, 'Clinico geral', '29548186039', 'Sertao', 1);
INSERT INTO medicos (nome, idade, especialidade, cpf, cidade, cod_amb) VALUES ("maria antnia", 42, 'pediatra', '94457703079', 'erechim', 3);
INSERT INTO medicos (nome, idade, especialidade, cpf, cidade, cod_amb) VALUES ("Isadora Sebastiana Cristiane Gomesa", 50, 'Geriatria', '32038023093', 'erechim', 3);
INSERT INTO medicos (nome, idade, especialidade, cpf, cidade, cod_amb) VALUES ("Rayssa Brenda", 33, 'Dermatologia', '36234279007', 'erechim', 4);
INSERT INTO medicos (nome, idade, especialidade, cpf, cidade, cod_amb) VALUES ("Ruan Pedro Assunção", 36, 'Infectologia', '00166965065', 'passo fundo', 5);


INSERT INTO pacientes (nome, idade, cidade, cpf, doenca) VALUES ('Elias Ian Benedito Peixoto', 34, 'Porto Alegre', '64953423143', 'Alzheimer');
INSERT INTO pacientes (nome, idade, cidade, cpf, doenca) VALUES ('Esther Lorena Renata', 54, 'Boa Vista', '50490878059', 'Depressão');
INSERT INTO pacientes (nome, idade, cidade, cpf, doenca) VALUES ('Carlos Eduardo Renato Peixoto', 55, 'Brasília', '42403368059', 'Câncer');
INSERT INTO pacientes (nome, idade, cidade, cpf, doenca) VALUES ('Márcia Fabiana Teixeira', 23, 'Novo Gama', '13854510098', 'Asma');
INSERT INTO pacientes (nome, idade, cidade, cpf, doenca) VALUES ('Sarah Andreia Isabel', 60, 'Paranavaí', '12632232066', 'Hipertensão');


INSERT INTO funcionarios (nome, idade, cidade, cpf, salario, cargo) VALUES ("joao pedro", 33, 'passo fundo', '07338514011', 2000.45, 'enfermeiro');
INSERT INTO funcionarios (nome, idade, cidade, cpf, salario, cargo) VALUES ("Augusto Nicolas Bernardes", 31, 'ijui', '57093762050', 1200.45, 'faxineiro');
INSERT INTO funcionarios (nome, idade, cidade, cpf, salario, cargo) VALUES ("Oliver Theo Manuel Santos", 40, 'sertao', '61922771090', 2000.45, 'cozinheiro');
INSERT INTO funcionarios (nome, idade, cidade, cpf, salario, cargo) VALUES ("Benjamin Anthony Marcos Santos", 37, 'soledade', '17091293010', 2500.25, 'enfermeiro');
INSERT INTO funcionarios (nome, idade, cidade, cpf, salario, cargo) VALUES ("Antonella Marlene da Silva", 51, 'Dourados', '82925397000', 3200.00, 'enfermeiro');


INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (2, 2, '2022-04-23', '12:15:11');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (3, 5, '2022-05-01', '16:00:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (5, 4, '2022-05-08', '09:00:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (1, 2, '2022-05-06', '10:00:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (5, 1, '2022-05-05', '11:30:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (2, 5, '2022-05-04', '15:45:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (4, 3, '2022-05-03', '13:10:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (3, 1, '2022-05-02', '16:15:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (5, 5, '2022-05-09', '17:45:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (1, 4, '2022-04-25', '23:20:00');


/* 4) Resolva estes problemas: */

/* a) O segundo paciente da lista se mudou para Ilhota; */
update pacientes set cidade = "Ilhota" where cod_p = 2;

/* b) A consulta do médico 1 com o paciente 4 foi marcada para as 12 horas do dia 4 de novembro de 2021; */
update consultas SET data = '2021-11-04', hora = "12:00:00" WHERE cod_m = 1 and cod_p = 4;

/* c) O funcionário 4 deixou a clínica; */
delete from funcionarios where cod_f = 4;

/* d) Foram contratados 2 novos funcionários. O funcionário Matheus e a funcionária Martina; */
INSERT INTO funcionarios (nome, idade, cidade, cpf, salario, cargo) VALUES ("Matheus", 25, 'passo fundo', '59116849000', 2600.00, 'enfermeiro');
INSERT INTO funcionarios (nome, idade, cidade, cpf, salario, cargo) VALUES ("Martina", 26, 'erechim', '81931604010', 2600.00, 'enfermeiro');

/* e) Delete o paciente 3 pela doença e adicione um novo com a mesma doença; */
delete from pacientes where doenca = 'Cancer';
INSERT INTO pacientes (nome, idade, cidade, cpf, doenca) VALUES ('Andreia', 45, 'Paranavaí', '12632232060', 'cancer');

/* f) Os pacientes com idade inferior a 24 anos deixaram a clínica. */
SET FOREIGN_KEY_CHECKS=0;
delete from pacientes where idade < 24; 
SET FOREIGN_KEY_CHECKS=1;

/**********************************************************************************
5) Crie uma nova tabela chamada Situacao_Sistema. Ela tem de
conter apenas uma coluna para receber a data da última
atualização (`data_atualizacao` TIMESTAMP NULL DEFAULT
CURRENT_TIMESTAMP) e então criar um trigger que insira a data
e a hora atual SEMPRE que der um UPDATE na tabela
(NEW.data_atualizacao = NOW();).  

*********************************************************************************/

CREATE TABLE IF NOT EXISTS `situacao_sistema` (
    `cod_s` INT NOT NULL AUTO_INCREMENT,
    data_atualizacao TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY (`cod_s`)
);

CREATE TRIGGER `situacao_sistema_before_update` BEFORE UPDATE ON `situacao_sistema` FOR EACH ROW BEGIN
	SET NEW.data_atualizacao = NOW();
END

/* 6) Busque as seguintes informações filtrando os dados: */

/* a) Buscar os dados dos ambulatórios que possuem capacidade superior à 30; */
select * from ambulatorios where capacidade > 30;

/* b) Buscar os dados dos médicos com menos de 40 anos ou com especialidade traumatologia; */
select * from medicos where idade < 40 or especialidade = 'traumatologia';

/**********************************************************************************

c) Buscar os códigos dos médicos e os códigos dos
pacientes, para todas as consultas marcadas no período
da tarde (a partir das 13h) após o dia 15/05/2022 (caso
não tenha, insira na tabela ou modifique e coloque o
UPDATE também na resposta dessa questão); 

*********************************************************************************/
update consultas set data = '2022-05-16' where cod_m = 5 and cod_p = 5;
SELECT cod_m, cod_p from consultas WHERE hour(hora) > 13 and day(data) >= 15;


/* d) Buscar o nome e a idade dos pacientes que não residem em Passo Fundo; */
select nome, idade from pacientes where cidade != 'passo fundo';


/* e) Buscar o nome e a idade (em meses) dos pacientes; */
select nome, idade*12 AS idade_meses from pacientes;


/* f) Qual o menor e o maior salário dos funcionários de Passo Fundo? */
select max(salario) as Maior_Salario, min(salario) as Menor_Salario from funcionarios;


/* g) Qual a média de idade dos médicos e o total de ambulatórios atendidos por eles? */
SELECT round(avg(idade),2) as media_idade, COUNT(cod_amb) AS Total_Ambulatorio from medicos;


/********************************************************************************** 
7) Agora vamos fazer buscas juntando as tabelas: 

**********************************************************************************/

/********************************************************************************** 
a) Buscar o número e o andar dos ambulatórios utilizados
por médicos ortopedista (caso não tenha médico com
especialidade de ortopedia, insira e coloque o INSERT
ou o UPDATE também na resposta desta questão); 

*********************************************************************************/

INSERT INTO medicos (nome, idade, especialidade, cpf, cidade, cod_amb) VALUES ("Ruan Assunção", 46, 'ortopedia', '10166965065', 'passo fundo', 5);

select capacidade, andar from ambulatorios 
INNER JOIN medicos ON ambulatorios.cod_amb = medicos.cod_amb
where medicos.especialidade = "ortopedia";


/* b) Retornar pares (código, nome) de funcionários e de
médicos que residem na mesma cidade; */
INSERT INTO pacientes (nome, idade, cidade, cpf, doenca) VALUES ('Márcia silva', 23, 'Porto Alegre', '12854510098', 'Asma');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (2, 9, '2022-05-25', '14:20:00');
INSERT INTO consultas (cod_m, cod_p, data, hora) VALUES (2, 9, '2022-05-25', '15:20:00');

select pacientes.cod_p as codigo_paciente, pacientes.nome as nome_paciente, pacientes.cidade AS cidade_paciente, 
medicos.cod_m as codigo_medico, medicos.nome as nome_medico, medicos.cidade AS medico_cidade
from consultas
inner join medicos on medicos.cod_m = consultas.cod_m
inner join pacientes on pacientes.cod_p = consultas.cod_p
where pacientes.cidade = medicos.cidade


/* c) Buscar o código, nome e data dos pacientes com
consulta marcada para horários após às 14 horas; */
select pacientes.cod_p, pacientes.nome, consultas.data, consultas.hora from pacientes
inner join consultas on consultas.cod_p = pacientes.cod_p 
where hour(consultas.hora) > 14;


/********************************************************************************** 
8) Crie um procedimento para buscar o código, o nome e o
salário líquido dos funcionários. O salário líquido é obtido pela
diferença entre o salário cadastrado menos 20% desse
mesmo salário; 

*********************************************************************************/

CREATE FUNCTION `calcula_salario_liquido`(
	`salario` DECIMAL(10,2),
	`desconto` DECIMAL(10,2)
)
RETURNS FLOAT
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	DECLARE salarioliquido DECIMAL(10,2);
	SET salarioliquido = salario - (salario*(desconto/100));
	RETURN salarioliquido;
END

CREATE PROCEDURE `proc_salario_liquido`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN	
	select func.cod_f, func.nome, calcula_salario_liquido(func.salario,0.2) from funcionarios as func;
END

CALL proc_salario_liquido();


/********************************************************************************** 
9) Agora transforme todas as consultas da questão 5 e 6 em
procedimentos armazenados, podendo ser Function ou
Procedure; 

*********************************************************************************/

/* a) Buscar os dados dos ambulatórios que possuem capacidade superior à 30; */
CREATE PROCEDURE `proc_ambulatorio_capacidade`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	select * from ambulatorios where capacidade > 30;
END

call proc_ambulatorio_capacidade()


/* b) Buscar os dados dos médicos com menos de 40 anos ou com especialidade traumatologia; */
CREATE PROCEDURE `proc_filtro_medico`(
	IN `_idade` INT,
	IN `_especializadade` VARCHAR(150)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
		select * from medicos where idade < _idade or especialidade = _especializadade;
END

call proc_ambulatorio_capacidade(40, 'traumatologia')

/* c) Buscar os códigos dos médicos e os códigos dos
pacientes, para todas as consultas marcadas no período
da tarde (a partir das 13h) após o dia 15/05/2022 (caso
não tenha, insira na tabela ou modifique e coloque o
UPDATE também na resposta dessa questão); */
update consultas set data = '2022-05-16' where cod_m = 5 and cod_p = 5;
SELECT cod_m, cod_p from consultas WHERE hour(hora) > 13 and day(data) >= 15;

CREATE PROCEDURE `proc_busca_consulta_data`(
	IN `_dia` INT,
	IN `_hora` INT
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	SELECT cod_m, cod_p from consultas WHERE hour(hora) > _hora and day(data) >= _dia;
END

call proc_busca_consulta_data(05, 15);


/* d) Buscar o nome e a idade dos pacientes que não residem em Passo Fundo; */
select nome, idade from pacientes where cidade != 'passo fundo';

CREATE PROCEDURE `proc_busca_paciente_cidade_diferente`(
	IN `_cidade` VARCHAR(50)
)
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	select nome, idade from pacientes where cidade != _cidade;
END

call proc_busca_paciente_cidade_diferente('passo fundo')

/* e) Buscar o nome e a idade (em meses) dos pacientes; */
select nome, idade*12 AS idade_meses from pacientes;

CREATE PROCEDURE `proc_idade_to_meses`()
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	select nome, idade*12 AS idade_meses from pacientes;
END

call proc_idade_to_meses();

/* f) Qual o menor e o maior salário dos funcionários de Passo Fundo? */
select max(salario) as Maior_Salario, min(salario) as Menor_Salario from funcionarios;

CREATE PROCEDURE `proc_maior_menor_salario_funcionario`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	select max(salario) as Maior_Salario, min(salario) as Menor_Salario from funcionarios;
END

call proc_maior_menor_salario_funcionario()

/* g) Qual a média de idade dos médicos e o total de ambulatórios atendidos por eles? */
SELECT round(avg(idade),2) as media_idade, COUNT(cod_amb) AS Total_Ambulatorio from medicos;

CREATE PROCEDURE `proc_media_idade_amb_medicos`()
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	SELECT round(avg(idade),2) as media_idade, COUNT(cod_amb) AS Total_Ambulatorio from medicos;
END

call proc_media_idade_amb_medicos()