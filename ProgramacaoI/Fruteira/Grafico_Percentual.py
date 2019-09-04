import matplotlib.pyplot as plt
import pandas as pd
import numpy as np


def grafico_percentual_estoque():
        dados = pd.read_csv('Produtos.csv', sep=',')

        nome_produtos = dados["NOME"]
        valores = dados['VALOR']

        fig1, ax1 = plt.subplots()

        ax1.pie(valores, labels=nome_produtos, autopct='%1.1f%%',
                    shadow=True, startangle=90)
        ax1.axis('equal')

        plt.show()
