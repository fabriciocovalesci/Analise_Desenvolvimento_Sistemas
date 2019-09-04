import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

def grafico_relacao_valor_produtos():
        dados = pd.read_csv('Produtos.csv', sep=',')

        nome_produtos = dados["NOME"]
        valores = dados['VALOR']

        plt.bar(nome_produtos, valores, color='red')

        plt.xticks(nome_produtos)
        plt.ylabel('Preço R$')
        plt.xlabel('Produtos')
        plt.title('Relação Produtos X Preços')
        plt.grid()

        plt.show()
