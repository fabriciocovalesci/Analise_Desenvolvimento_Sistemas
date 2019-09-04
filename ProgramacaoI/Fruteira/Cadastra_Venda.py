# -*- coding: utf-8 -*-
import Inserir_Cliente
import Inserir_Produto
import Venda
import pandas as pd

def seleciona_cliente():
    print('\n\t\tNOVA VENDA')
    print('\n\tSELECIONA CLIENTE')
    busca_cliente = input('- BUSCA POR (EM BRANCO PARA TODOS OU PARTE DO NOME): ')
    nome_cliente = busca_cliente.capitalize()
    dados_cliente = pd.read_csv('Clientes.csv', sep=',')
    seleciona_nomes = dados_cliente["NOME"].value_counts()
    for i in seleciona_nomes:
        if nome_cliente in seleciona_nomes:
            print (f'\n- INFO: Cliente {nome_cliente} selecionado')
            print('- ITENS DA VENDA')
            Venda.seleciona_produto()
            break
        elif nome_cliente not in  seleciona_nomes:
            novo_contato = input('CLIENTE NÃO ENCONTRADO, DESEJA CADASTRAR (Y/N): ')
            if novo_contato == 'y' or novo_contato == 'Y':
                Inserir_Cliente.clientes()
            else:
                print('sair')
                break
