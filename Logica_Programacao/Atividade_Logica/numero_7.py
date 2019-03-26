total = int(input('Total de Eleitores: '))
votobranco = int(input('Informe os votos em branco: '))
nulos = int(input('Informe votos nulos: '))
validos = int(input('Informe votos validos: '))

porBranco = (100*votobranco)/total
porNulo = (100*nulos)/total
porValidos = (100*validos)/total

print('Votos Brancos: {:.2f}'.format(porBranco),'%')
print('Votos Nulos: {:.2f}'.format(porNulo),'%')
print('Votos Validos: {:.2f}'.format(porValidos),'%')

