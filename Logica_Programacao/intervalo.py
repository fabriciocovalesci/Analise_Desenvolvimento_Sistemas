cont = 0
cont1 = 0

for i in range(10):
	a=int(input('Digite um valor: '))
	if(a >= 10  and a <= 20):
		cont=cont + 1
	else:
		cont1=cont1+1
print('Dentro de [10,20]:',cont)
print('Fora de [10,20]:',cont1)
