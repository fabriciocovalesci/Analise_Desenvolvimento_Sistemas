m = int(input('\nNumeros de macas compradas: '))

if(m < 12):
	custo = m * 2.00
	print('Valor por unidade R$ 2.00')
	print("Valor a pagar por {} macas e de R$ {:.2f}\n".format(m, custo))
elif(m >= 12):
	custo = m * 1.50
	print('Valor pode unidade R$ 1.50')
	print('Valor a pagar por {} macas e de R$ {:.2f}\n'.format(m, custo))
 
