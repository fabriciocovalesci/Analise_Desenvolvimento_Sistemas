import matplotlib.pyplot as plt
import pandas as pd

def grafico_vendas():
        dados = pd.read_csv('Vendas.csv', sep=',')

        quantidade_produtos = dados["PRODUTO"]
        valores = dados['VALOR_PAGO']

        plt.bar(quantidade_produtos, valores, color='blue')

        plt.xticks(quantidade_produtos)
        plt.ylabel('Preço R$')
        plt.xlabel('Produtos')
        plt.title('Produtos Vendidos')
        plt.grid()
        plt.show()
