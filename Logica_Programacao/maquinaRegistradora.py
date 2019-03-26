
pagar = soma = valor = 0

while True:

	codigo = int(input('\nDigite codigo do produto: '))
	quant = int(input('Quantidade: '))

	if codigo == 1:
		valor = 0.50
		pagar = quant * valor
		soma =+ pagar

	elif codigo == 2:
		valor = 1.00
		pagar = quant * valor
		soma += pagar

	elif codigo == 3:
		valor = 4.00
		pagar = quant * valor
		soma += pagar

	elif codigo == 5:
		valor = 7.00
		pagar = quant * valor
		soma += pagar

	elif codigo == 9:
		valor = 8.00
		pagar = quant * valor
		soma += pagar

	elif codigo == 0:
		break

	else:
		print('Codigo invalido')

print("\n\033[34mTotal de compras R$ {:.2f} ".format(soma))