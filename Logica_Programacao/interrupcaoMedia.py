#Escreva um programa que leia números inteiros do teclado. O pro-
#grama deve ler os números até que o usuário digite 0 (zero). No final da execução,
#exiba a quantidade de números digitados, assim como a soma e a média aritmética.


cont = soma = 0

while True:
	numero = int(input('Digite numeros inteiros(SAIR = 0): '))
	cont += 1
	soma += numero
	
	if numero== 0:
		break

	media = soma/cont

print('\nQuantidade de numeros digitados: ',cont)
print('Media aritmética dos numeros informados: {:.2f}'.format(media))
