#Escreva um programa que pergunte o depósito inicial e a taxa de
#juros de uma poupança. Exiba os valores mês a mês para os 24 primeiros meses.
#Escreva o total ganho com juros no período.

mes = 24
valorMes = 0
x = 1
deposito = float(input('Valor do deposito: '))
taxa = float(input('Valor da taxa: '))

while x <= 24:
	rendimento = deposito*taxa*x
	print('\nRendimento mes {} foi de R$ {:.2f}'.format(x,rendimento))
	x += 1

