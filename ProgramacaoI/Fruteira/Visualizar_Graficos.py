# -*- coding: utf-8 -*-
import Grafico_Produtos_Valores
import Grafico_Percentual
import Grafico_Vendas


# Menu de Gráficos
def visualizar_dados_graficos():
    while True:
        print('\nMENU DE GRÁFICOS')
        print('1 - Relação Produtos X Valores')
        print('2 - Percentual do Estoque')
        print('3 - Gráfico de Vendas')
        print('4 - RETORNAR')

        operacao = input('> ')

        if operacao == '1':
            Grafico_Produtos_Valores.grafico_relacao_valor_produtos()

        if operacao == '2':
            Grafico_Percentual.grafico_percentual_estoque()

        if operacao == '3':
            Grafico_Vendas.grafico_vendas()

        else:
            break

    '''
    for i in dados_produto['NOME']:
        if nome_produto in i:
            print (f'\n- INFO: Produto {nome_produto} selecionado\n')
            break

        if nome_produto not in  seleciona_nomes_produtos:
            novo_produto_add = input('PRODUTO NÃO ENCONTRADO, DESEJA CADASTRAR (Y/N): ')
            if novo_produto_add == 'y' or novo_produto_add == 'Y':
                Inserir_Produto.new_Produto()
            if novo_produto_add != 'y':
                seleciona_produto()

        novo_item = input('NOVO ITEM [Y / n]: ')
        if novo_item == "Y" or novo_item == 'y':
            seleciona_produto()
        elif novo_item != "Y" or novo_item != 'y':
            valor_pagar = seleciona_quantidade * valor_produto
            print(f'INFO: TOTAL DA VENDA {valor_pagar}')
    '''
