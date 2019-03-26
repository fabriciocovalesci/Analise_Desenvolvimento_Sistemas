
cont = 0
maior = 0
conta = 0
salario_perc = 0
while(True):
	salario = float(input('Qual salario dos socios: '))
	if(salario < 0):
		break
	filhos = int(input('Numero de filhos: '))

	if(salario <= 400):
		salario_perc = salario_perc + salario
		conta = conta+1

	if(salario > maior):
		maior = salario

	cont = cont+1
	
f = filhos/cont	
s = salario/cont
perc =(100*salario_perc)/conta

print('\nPercentual de pessoas com salario ate R$400,00: {:.2f}%'.format(perc))
print('Media de filhos: ',f)
print('Maior salario: ',maior)
print('Media de salario dos socios: ',s)

	
	
