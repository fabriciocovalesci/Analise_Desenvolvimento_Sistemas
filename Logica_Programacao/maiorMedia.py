# Escrever um algoritmo que lê 3 valores a, b, c e calcula e escreve a média ponderada com peso 5 para o maior
# dos 3 valores e peso 2.5 para os outros dois.

a = int(input('Digite valor de A: '))
b = int(input('Digite valor de B: '))
c = int(input('Digite valor de C: '))

if(a>b and a>c):
    print('A e o maior: ',a)
    media = ((a+5)+(b+2.5)+(c+2.5)/3)
    print('Media: {:.2f}'.format(media))

elif(b>a and b>c):
    print('B e o maior: ',b+5)
    media = ((a+2.5)+(b+5)+(c+2.5)/3)
    print('Media: {:.2f}'.format(media))

elif(c>a and c>b):
    print('C e o maior ',c+5)
    media = ((a+2.5)+(b+2.5)+(c+5)/3)
    print('Media: {:.2f}'.format(media))
