# -*- coding: utf-8 -*-
import Inserir_Cliente
import Listar_Clientes

# Menu de Clientes
def menu_Cliente():
    while True:
        print('\nMENU CLIENTES')
        print('1 - CADASTRAR CLIENTES')
        print('2 - LISTAR CLIENTES')
        print('3 - RETORNAR')

        operacao = input('> ')

        if operacao == '1':
            Inserir_Cliente.clientes()

        elif operacao == '2':
            Listar_Clientes.listar()
        else:
            break
