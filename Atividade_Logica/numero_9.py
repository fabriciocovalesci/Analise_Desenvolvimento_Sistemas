n1 = float(input('Nota 1: '))
n2 = float(input('Nota 2: '))
n3 = float(input('Nota 3: '))

media = (n1+n2+n3)/3

if(media >= 9.0):
	print("A")
elif(media >= 7.5 and media < 9.0):
	print("B")
elif(media >= 6.0 and media < 7.5):
	print("C")
else:
	print("D")
