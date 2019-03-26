#Escreva um programa que pergunte o valor inicial de uma dívida #e
#o juro mensal. Pergunte também o valor mensal que será pago. #Imprima o número
#de meses para que a dívida seja paga, o total pago e o total #de juros pago.


valorDiv = float(input('Valor inicial da divida: '))
juroM = float(input('Juros mensa: '))
mes = int(input('Parcelas para pagar a divida: '))
n = 1
cont = 0
while n <= mes:
	divida = (valorDiv*((juroM*100)/juroM)+)
	n += 1
	print('\nDivida para em {}'.format(n))
	print('Valor divida ao mes R$ {:.2f}'.format(divida))
