nota1 = float(input('\nQual a nota da primeira prova..-> '))
nota2 = float(input('Qual a nota da segunda prova...-> '))
nota3 = float(input('Qual a nota da terceira prova..-> '))

mediafinal = ((nota1*2) + (nota2*3) + (nota3*5))/10

if(mediafinal >=6.0):
	print('\nAPROVADO\nMedia Final -> ',mediafinal)
else:
	print('\nREPROVADO\nMedia Final -> ',mediafinal)
