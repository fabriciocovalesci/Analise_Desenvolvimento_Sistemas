# -*- coding: utf-8 -*-

import Menu_Cliente
import Inserir_Produto
import Cadastra_Venda
import Inserir_Cliente
import Listar_Clientes
import Visualizar_Graficos

# Menu Principal
def menu():

    while True:

        print('\n\t\t\033[33mFRUTEIRA DA TUTI V1.0\n\n')
        print('MENU')
        print('1 - CLIENTES')
        print('2 - CADASTRA PRODUTO')
        print('3 - CADASTRA VENDA')
        print('4 - VISUALIZAR GRÁFICOS')
        print('9 - SAIR')

        op = input('> ')

        if op == '1':
            Menu_Cliente.menu_Cliente()

        elif op == '2':
            Inserir_Produto.new_Produto()
        elif op == '3':
            Cadastra_Venda.seleciona_cliente()
        elif op == '4':
            Visualizar_Graficos.visualizar_dados_graficos()
        elif op == '9':
            print('ATÉ LOGO !')
            exit()

menu()
