
n = 0
repetidor = 2
cont = 1
totalVeic = 0
tot_acid = 0
total_cidade = 0

while n <= repetidor:

	print('\n\tCidade',cont)
	cidade = str(input('Nome da cidade: '))
	num_veic_pas = int(input('Numero de veiculos de passeio: '))
	nu_acid = int(input('Numero de acidente com vitimas: '))

	if num_veic_pas > 0:
		totalVeic = totalVeic+num_veic_pas

	if tot_acid > 0:
		tot_acid = tot_acid+nu_acid

	indice = (nu_acid*100)/num_veic_pas

	if(total_cidade==0):
		maiorCidade = cidade
		maior_indice = indice
		menor_cidade = cidade
		menor_indice = indice

	if (indice<menor_indice):
		menor_cidade = cidade
		menor_indice = indice

	cont = cont + 1
	n = n +1	


print('\nCidade maior indice de acidente: ',maiorCidade)
print('Maior Indice de Acidente: ',maior_indice)
print('\nCidade menor indice de acidente: ',menor_cidade)
print('Menor Indice de Acidente: ',menor_indice)

