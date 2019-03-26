cores = {'limpa':'\033[m',
'azul':'\033[34m',
'amarelo':'\033[33m',
'lilas':'\033[m',
'pretobranco':'\033[7;30m'}

nome = input('Nome: ')

print('Meu nome e {}{}{}'.format(cores['pretobranco'],nome,cores['limpa']))