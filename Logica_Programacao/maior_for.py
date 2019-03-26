maior = 0
menor = 0
for i in range(10):
	n = int(input('Digite numero: '))
	if(n > maior):
		maior = n
	elif(n < menor):
		menor = n
print('Menor ',menor)
print('Maior ',maior)
