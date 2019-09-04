# -*- coding: utf-8 -*-
import Inserir_Produto
import Cadastra_Venda
import csv
import os

def seleciona_produto():
    print('\n\tSELECIONA PRODUTO')
    busca_produto = input('- BUSCA POR (EM BRANCO PARA TODOS OU PARTE DO NOME): ')

    nome = list()
    valores = list()
    dados_produtos = {}
    with open('Produtos.csv') as f:
        arquivo = csv.reader(f, delimiter=',')
        cabecalho = True
        for i in arquivo:
            nome.append(i[0])
            valores.append(i[3])

    del nome[0]
    del valores[0]

    dados_produtos = dict(zip(nome, valores))

    for k, v in dados_produtos.items():
        dados_produtos[k] = float(v)

    aux_valor = 0
    for k, v in dados_produtos.items():
        if busca_produto == k:
            aux_valor = v
            print(f'\nINFO: Produto {k} selecionado, custa R$ {v}')
            break

    quantidade_comprar = int(input('- QUANTIDADE: '))
    total_venda = float(aux_valor * quantidade_comprar)
    print(f'\nINFO: Total da venda é de R$ {total_venda:.2f}')

    venda_efetuada = list()
    venda_efetuada.append(busca_produto)
    venda_efetuada.append(quantidade_comprar)
    venda_efetuada.append(total_venda)

    novo_item = input('\nNOVO ITEM (Y/n): ')
    if novo_item == 'Y' or novo_item == 'y':
        seleciona_produto()
    if novo_item != 'Y' or novo_item != 'y':
        pass

    gravar_vendas = open("Vendas.csv", "at")
    try:
        write = csv.writer(gravar_vendas)
        if os.stat("Vendas.csv").st_size == 0:
            write.writerow(['PRODUTO', 'QUANTIDADE', 'VALOR_PAGO'])
        write = csv.writer(gravar_vendas)
        write.writerow(venda_efetuada)
    finally:
        gravar_vendas.close()
        print('INFO: REGISTRO ARMAZENADO COM SUCESSO\n\n')

    venda_efetuada.clear()
