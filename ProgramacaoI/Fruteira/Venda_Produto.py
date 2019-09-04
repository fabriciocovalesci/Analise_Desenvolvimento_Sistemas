# -*- coding: utf-8 -*-

import Inserir_Produto

def busca_produto():
    print('ITENS DA VENDA')
    print('\t\tSELECIONA PRODUTO')

    while True:
        busca_item = ('- BUSCA POR (EM BRANCO PARA TODOS OU PARTE DO NOME): ')
        def check_produto():
            datafile = open('Produtos.csv', 'r')
            for line in datafile:
                if busca.capitalize() in line:
                    found = True
                    break
                else:
                    found = False
            return found

        if check_produto():
            print (f'Cliente {busca.capitalize()} selecionado')
            break
        elif not check_produto():
            novo_produto = input('PRODUTO NÃO ENCONTRADO, DESEJA CADASTRAR (Y/N): ')
            if novo_produto == 'y' or novo_produto == 'Y':
                Inserir_Produto.new_Produto()
