#A confederacao nacional de nataçao precisa de um programa que #leia de um atleta o ano de nascimento e mostre sua categoria #conforme idade:
#ate 9anos - mirim
#ate 14anos - infantil
#ate 19anos - junior
#ate 20anos - senior 
#acima de 20anos - master

cores = {'azul':'\033[34m',
		'amarelo':'\033[33m',
		'vermelho':'\033[31m',
		'branco':'\033[30m',
		'lilas':'\033[35m',
		'limpa':'\033[m'}

idade = int(input('\nDigite a idade: '))

if idade <= 9:
	print('\nIdade {}{}{} anos, categoria MIRIM'.format(cores['azul'], 
		idade,cores['limpa']))

elif idade >= 10 and idade <= 14:
	print('\nIdade {}{}{} anos, categoria INFANTIL'.format(cores['amarelo'], 
		idade,cores['limpa']))

elif idade >= 15 and idade <= 19:
	print('\nIdade {}{}{} anos, categoria JUNIOR'.format(cores['vermelho'], 
		idade,cores['limpa']))

elif idade == 20:
	print('\nIdade {}{}{} anos, categoria SENIOR'.format(cores['branco'], 
		idade,cores['limpa']))
else:
	print('\nIdade {}{}{} anos, categoria MASTER'.format(cores['lilas'], 
		idade,cores['limpa']))
