### Escrever um algoritmo que lê o número de um funcionário, seu número de horas trabalhadas, o valor que
##recebe por hora, o número de filhos com idade menor do que 14 anos e o valor do salário família ( pago por
##filho com menos de 14 anos ). Calcular o salário total deste funcionário e escrever o seu número e o seu salário
#total.

numero = int(input('\nNumero do funcionario: '))
horaTrab = int(input('Horas trabalhadas: '))
Valorhora = float(input('Valor da hora trabalhada: '))
filhos = int(input('Numero de filhos: '))
salfamilia = float(input('Valor salario familia: '))
salario = ((horaTrab * Valorhora) + (filhos*salfamilia))
print("Salario total do funcionario:R$ {:.2f}".format(salario))
print('Numero do funcionario:',numero)
