import csv
import pandas as pd
import Inserir_Cliente

def listar():
#	df = pd.DataFrame()

	print('\t\tSELICIONA CLIENTE')
	f = open('Clientes.csv', 'r')

	busca = input('- BUSCAR POR (EM BRANCO PARA TODOS OU PARTE DO NOME): ')
	busca_listar = busca.capitalize()
	listar_dados_cliente = pd.read_csv('Clientes.csv', delimiter=',')

	print('\n')
	if  len(listar_dados_cliente[listar_dados_cliente['NOME'] == busca_listar]) > 0:
		print(listar_dados_cliente[listar_dados_cliente['NOME'] == busca_listar])
