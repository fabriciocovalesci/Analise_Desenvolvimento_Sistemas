# -*- coding: utf-8 -*-

# Insere novo Produto

import csv
import os

dados_produto = dict()

def new_Produto():
	print('\n')
	while True:

		print('\t\tNOVO PRODUTO')

		nome_produto = input('- NOME: ')
		dados_produto['NOME'] = nome_produto

		descricao = input('- DESCRIÇÃO: ')
		dados_produto['DESCRIÇÃO'] = descricao

		while True:
			try:
				quantidade = int(input('- QUANTIDADE: '))
				dados_produto['QUANTIDADE'] = quantidade
				break
			except ValueError:
				print('Digite apenas numeros real')

		while True:
			try:
				valor = float(input('- VALOR UNITÁRIO: '))
				dados_produto['VALOR'] = valor
				break

			except ValueError:
				print("Somente numeros com formato 2.99 são aceitos.\nUse ponto, não virgula\nTente novamente.")


		gravando_dados = open("Produtos.csv", "at")
		try:
			write = csv.writer(gravando_dados)
			if os.stat("Produtos.csv").st_size == 0:
				write.writerow(dados_produto.keys())
			write = csv.writer(gravando_dados)
			write.writerow(dados_produto.values())

		finally:
			gravando_dados.close()

		print('INFO: REGISTRO ARMAZENADO COM SUCESSO\n\n')

		add_produto = input('Deseja cadastra novo produto(Y/n): ')
		if add_produto == 'Y' or add_produto == 'y':
			continue
		else:
			break
