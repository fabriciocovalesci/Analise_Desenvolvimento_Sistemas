# -*- coding: utf-8 -*-

import csv
import os
import datetime


# Insere novo clientes
def clientes():

	dados_cliente_novo = {}
	print('\t\tNOVO CLIENTE')

	nome_cliente = str(input('- NOME: '))
	dados_cliente_novo['NOME'] = nome_cliente.capitalize()

	while  True:
		data=input('- DATA[dia/mes/ano]: ')
		try:
			data2 = datetime.datetime.strptime(data, "%d/%m/%Y")
			data_formatada = "{}/{}/{}".format(data2.day,data2.month,data2.year)
			dados_cliente_novo['DATA_NASCIMENTO'] = data_formatada
			break
		except ValueError:
			print("DATA Inavalida !\nDigite novamente")

	while True:
		cpf = input('- CPF: ')
		if len(cpf) == 11 and cpf.isdigit():
			dados_cliente_novo['CPF'] = cpf
			break
		else:
			print('ERRO - VALOR INFORMADO NÃO FOI ACEITO PELOS CRITÉRIOS DE VALIDAÇÃO')


	endereco = str(input('- ENDEREÇO: '))
	dados_cliente_novo['ENDEREÇO'] = endereco

	while True:
		telefone = input('-TELEFONE(11 Digitos): ')
		if len(telefone) == 11 and telefone.isdigit():
			dados_cliente_novo['TELEFONE'] = telefone
			break
		else:
			print('ERRO - VALOR INFORMADO NÃO FOI ACEITO PELOS CRITÉRIOS DE VALIDAÇÃO')

	gravar = open("Clientes.csv", "at")
	try:

	   write = csv.writer(gravar)
	   if os.stat("Clientes.csv").st_size == 0:
	        write.writerow(dados_cliente_novo.keys())
	   write = csv.writer(gravar)
	   write.writerow(dados_cliente_novo.values())
	finally:
	   gravar.close()
	print('INFO: REGISTRO ARMAZENADO COM SUCESSO\n\n')
