letra = input().split(' ')
a = int(letra[0])
b = int(letra[1])
c = int(letra[2])

if(a < (b+c)) and (b < (a+c)) and (c < (a+b)):
	if(a==b) and (b==c):
		print('Triangulo Equilatero')
	elif(a==b) or (b==c) or (a==c):
		print('Triangulo Isosceles')
	else:
		print('Triangulo Escaleno')
else:
	print('Nao foi possivel formar um triangulo')

