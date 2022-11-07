
-- Database: eleicao

-- DROP DATABASE eleicao;
---- Urna Eletronica -----

-- Tabela Estado
create table Estado(id_estado serial not null primary key,
				   nome varchar(100) not null,
				   quant_eleitores_validos integer not null);

-- Tabela Cidade
create table Cidade(id_cidade serial not null  primary key,
				   nome varchar(100) not null,
				   quant_eleitores_validos integer not null,
				   id_estado integer not null);

-- Tabela Endereco
create table Endereco(id_endereco serial not null  primary key,
					  rua varchar(100) not null,
					 bairro varchar(100) not null,
					 cep char(8) not null,
					 id_cidade integer not null);

-- Tabela Urna
create table Urna(id_urna serial not null  primary key,
				 data_votacao date not null,
				 hora timestamp not null,
				 id_voto integer not null,
				 id_endereco integer not null);
				 
-- Tabela Local
create table Local_Vota(id_local serial not null  primary key,
					   zona smallint not null,
					   secao smallint not null,
					   id_urna integer not null);
				 
-- Tabela Mesario
create table Mesario(id_mesario serial not null  primary key, 
					presidente varchar(100) not null,
					primeiro_mesario varchar(100) not null,
					segundo_mesario varchar(100) not null,
					secretario varchar(100) not null,
					suplente varchar(100) null,
					id_urna integer not null);
-- Tabela Voto
create table Voto(id_voto serial not null  primary key,
				 nulos smallint null,
				 branco smallint null,
				 voto_valido smallint null);
				 
-- Tabela Eleitores
create table Eleitor(id_eleitore serial not null  primary key,
					  nome varchar(100) not null,
					  rg char(10) not null unique check (rg ~'^[0-9]{10}$'),
					  carteira_trabalho char(10) not null unique check (carteira_trabalho ~'^[0-9]{10}$'),
					  cnh char(11) not null unique check (cnh ~'^[0-9]{11}$'),
					  titulo_eleitor char(12) not null unique check (titulo_eleitor ~'^[0-9]{12}$'),
					  id_voto integer not null,
					  id_local integer not null
					);
					 
--- Tabela Associativa Candidato-Urna
create table Candidato_Urna(id_candidato integer not null,
						   id_urna integer not null);


-- Tabela Candidato					
create table Candidato(id_candidato serial not null primary key,
					   nome varchar(100) not null, 
					   numero char(5) not null,
					   id_partido integer not null
					   );
-- Tabela Partido
create table Partido(id_partido serial not null primary key,
					 nome varchar(100) not null,
					 sigla char(5) not null unique check (sigla ~'^[a-z] || [A-Z]$')
					);
				 
				 
---- Chaves estrangeiras ----------

alter table Cidade add foreign key(id_estado) references Estado;

alter table Endereco add foreign key(id_cidade) references Cidade;

alter table Urna add foreign key(id_voto) references Voto;
alter table Urna add foreign key(id_endereco) references Endereco;

alter table Local_Vota add foreign key(id_urna) references Urna;

alter table Mesario add foreign key(id_urna) references Urna;

alter table Eleitor add foreign key(id_voto) references Voto;
alter table Eleitor add foreign key(id_local) references Local_Vota;

alter table Candidato_Urna add foreign key(id_candidato) references Candidato;
alter table Candidato_Urna add foreign key(id_urna) references Urna;

alter table Candidato add foreign key(id_partido) references Partido;

------- DML -------------
--- Adicionando dados às tabelas

----------------############################################################--------------------------------------
---------------------- ESTADOS ---------------------------------------------------------------------------------

-- Inserido Estado RS id=1
insert into Estado(id_estado, nome, quant_eleitores_validos)
values (default, 'Rio Grande do Sul', 10000);

-- Inserido Estado SC id=2
insert into Estado(id_estado, nome, quant_eleitores_validos)
values (default, 'Santa Catarina', 350000);

----------------############################################################--------------------------------------
---------------------- CIDADES ---------------------------------------------------------------------------------

-- Inserido Cidade Sertão id_cidade=1 id_estado=1	   
insert into Cidade(id_cidade, nome, quant_eleitores_validos, id_estado)
values (default, 'Sertão', 2000, 1); 

-- Inserido Cidade Erechim id_cidade=1 id_estado=1	 
insert into Cidade(id_cidade, nome, quant_eleitores_validos, id_estado)
values (default, 'Erechim', 45000, 1); 

-- Inserido Cidade Passo Fundo id_cidade=1 id_estado=1	 	
insert into Cidade(id_cidade, nome, quant_eleitores_validos, id_estado)
values (default, 'Passo Fundo', 55000, 1);

-- Inserido Cidade Florianopolis id_cidade=1 id_estado=2	 	
insert into Cidade(id_cidade, nome, quant_eleitores_validos, id_estado)
values (default, 'Florianopolis', 215000, 2); 

-- Inserido Cidade Chapecó id_cidade=1 id_estado=2 	
insert into Cidade(id_cidade, nome, quant_eleitores_validos, id_estado)
values (default, 'Chapeco', 95000, 2); 

----------------############################################################--------------------------------------
---------------------- ENDERECO ----------------------------------------------------------------------------------

-- Inserido Endereco cidade = Sertao id_cidade=1		   
insert into Endereco(id_endereco, rua, bairro, cep, id_cidade)
values (default, 'Zpto','Aldo Ariole', '12345678', 1); 

-- Inserido Endereco cidade = Erechim id_cidade=2		   
insert into Endereco(id_endereco, rua, bairro, cep, id_cidade)
values (default, 'Xuuxu','Beleza', '12345678', 2); 

-- Inserido Endereco cidade = Passo Fundo id_cidade=3		   
insert into Endereco(id_endereco, rua, bairro, cep, id_cidade)
values (default, 'Tito','Av. Brasil', '00003345', 3); 

----------------############################################################--------------------------------------
---------------------- PARTIDOS ----------------------------------------------------------------------------------

-- Inserido Partido TI
insert into Partido(id_partido, nome, sigla)
values (default, 'Partido TI', 'PTI'); 

-- Inserido Partido Python id=2
insert into Partido(id_partido, nome, sigla)
values (default, 'Partido Python', 'PTHON'); 

-- Inserido Partido Java id=3
insert into Partido(id_partido, nome, sigla)
values (default, 'Partido Java', 'PJA'); 

-- Inserido Partido C++ id=4
insert into Partido(id_partido, nome, sigla)
values (default, 'Partido C++', 'PC++'); 

-- Inserido Partido Swift id=5
insert into Partido(id_partido, nome, sigla)
values (default, 'Partido Swift', 'Psw'); 

----------------############################################################--------------------------------------
---------------------- CANDIDATOS ---------------------------------------------------------------------------------

-- Inserido Candidato id=1
insert into Candidato(id_candidato, nome, numero, id_partido)
values (default, 'Pedro Paulo', '123', 1);

-- Inserido Candidato id=2
insert into Candidato(id_candidato, nome, numero, id_partido)
values (default, 'Guido van Rossum', '1989', 2); 

-- Inserido Candidato id=3
insert into Candidato(id_candidato, nome, numero, id_partido)
values (default, 'James Gosling,', '1990', 3); 

-- Inserido Candidato id=4
insert into Candidato(id_candidato, nome, numero, id_partido)
values (default, 'Bjarne Stroustrup', '1979', 4); 

----------------############################################################--------------------------------------
---------------------- VOTOS ---------------------------------------------------------------------------------

-- Inserido Voto id=1
insert into Voto(id_voto, nulos, branco, voto_valido)
values (default, 0, 1, 0); -- inserido 1 vez

-- Inserido Voto id=2
insert into Voto(id_voto, nulos, branco, voto_valido)
values (default, 1, 0, 0); -- inserido 2 vez

-- Inserido Voto id=3
insert into Voto(id_voto, nulos, branco, voto_valido)
values (default, 0, 1, 0); -- inserido 3 vez

----------------############################################################--------------------------------------
---------------------- URNA ELETRONICA ---------------------------------------------------------------------------------

-- Inserido Urna id=1
insert into Urna(id_urna, data_votacao, hora, id_voto, id_endereco)
values (default, '12/12/2018', '2004-10-19 10:23:54', 1, 1);

-- Inserido Urna id=2
insert into Urna(id_urna, data_votacao, hora, id_voto, id_endereco)
values (default, '08/10/2018', '2018-10-08 13:03:04', 2, 1);

-- Inserido Urna id=3
insert into Urna(id_urna, data_votacao, hora, id_voto, id_endereco)
values (default, '08/10/2018', '2018-10-08 08:55:12', 3, 1);


----------------############################################################--------------------------------------
---------------------- ZONA - SECAO ---------------------------------------------------------------------------------

-- Inserido Local - Zona -Secao Urna id=1
insert into Local_Vota(id_local, zona, secao, id_urna)
values (default, 250, 23, 1);

-- Inserido Local - Zona -Secao Urna id=2
insert into Local_Vota(id_local, zona, secao, id_urna)
values (default, 200, 15, 1);

-- Inserido Local - Zona -Secao Urna id=3
insert into Local_Vota(id_local, zona, secao, id_urna)
values (default, 45, 56, 1); 

----------------############################################################--------------------------------------
---------------------- ELEITORES ---------------------------------------------------------------------------------

-- Inserido Eleitor id=1  id_voto=1 id_local=2
insert into Eleitor(id_eleitore, nome, rg, carteira_trabalho, cnh, titulo_eleitor, id_voto, id_local)
values (default, 'Fabricio', '1234567890', '1234567890', '12345678912','123456789123', 1, 1); 

-- Inserido Eleitor id=2 id_voto=2 id_local=2
insert into Eleitor(id_eleitore, nome, rg, carteira_trabalho, cnh, titulo_eleitor, id_voto, id_local)
values (default, 'Julio', '0000011111', '5555555555', '12121212121','898989898989', 2, 2); 

-- Inserido Eleitor id=2  id_voto=3 id_local=3
insert into Eleitor(id_eleitore, nome, rg, carteira_trabalho, cnh, titulo_eleitor, id_voto, id_local)
values (default, 'Carla', '2222233333', '4444444444', '45454545450','676767676767', 3, 3); 

----------------############################################################--------------------------------------
---------------------- MESARIOS ---------------------------------------------------------------------------------

-- Inserido Mesario id=1
insert into Mesario(id_mesario, presidente, primeiro_mesario, segundo_mesario, secretario, suplente, id_urna)
values (default, 'Antonio', 'Luis', 'Maria', 'Joao','', 1); 

-- Inserido Mesario id=2
insert into Mesario(id_mesario, presidente, primeiro_mesario, segundo_mesario, secretario, suplente, id_urna)
values (default, 'Vania', 'Abrahão', 'Fernando', '','Sonia', 2); 

-- Inserido Mesario id=3
insert into Mesario(id_mesario, presidente, primeiro_mesario, segundo_mesario, secretario, suplente, id_urna)
values (default, 'Robin', 'Batman', 'Natma', 'Alan','', 3); 


----------------############################################################--------------------------------------
---------------------- EXIBINDO TODOS DADOS DA TABELA ------------------------------------------------------------

select * from Estado;

select * from Cidade;

select * from Endereco;

select * from Urna;

select * from Mesario;

select * from Eleitor;

select * from Candidato;

select * from Partido;

select * from Local_Vota;

select * from Voto;

----------------############################################################--------------------------------------
---------------------- ECLUINDO TABELAS ------------------------------------------------------------

-- COMANDOS FORAM COMENTADOS PARA QUE OS DEMAIS COMANDOS ABAIXOS PUDESSEM SER EXECUTADOS -------------------

-- Excluido tabela Partido
--- drop table if exists Partido cascade;

-- Excluido tabela Candidato
-- drop table if exists Candidato cascade;

-- Excluido tabela Urna
-- drop table if exists Urna cascade;

-- Excluido tabela PostgresSQL que não existe no banco
-- drop table if exists PostgresSQL cascade;

----------------############################################################--------------------------------------
---------------------- FILTRANDO DADOS COM SELECT - WHERE -------------------------------------------------------

-- Exibir quantidade de eleitores validos que votaram na cidade Erechim 
select quant_eleitores_validos
from Cidade
where (cidade.nome = 'Erechim')

-- Exibir quantidade de eleitores validos que votaram no estado do RS 
select quant_eleitores_validos
from Estado
where (estado.nome) = 'Rio Grande do Sul'

-- Exibir nome do eleitor e seu titulo eleitoral
select nome, titulo_eleitor
from Eleitor
where (eleitor.nome) = 'Fabricio'



