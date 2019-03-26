
n1 = int(input())
n2 = int(input())
n3 = int(input())

if(n1 == n2 == n3):
	print('PROGRAMA FINALIZADO')
elif(n1<0 and n2<0 and n3<0):
	print('PROGRAMA FINALIZADO')
	
		
if(n1 > n2 and n1 > n3):
	print('Maior n1: ',n1)
elif(n2 > n1 and n2 > n3):
	print('Maior n2: ',n2)
elif(n3 > n1 and n3 > n2):
	print('Maior n3: ',n3)
	
