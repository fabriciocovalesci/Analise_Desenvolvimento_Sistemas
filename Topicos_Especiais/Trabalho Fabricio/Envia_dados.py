import serial

conexao = serial.Serial('/dev/ttyACM0', 9600) # Configuração da conexão
def rele_2():
    conexao.write(str.encode('2')) # Escreve 2 no arduino (Aciona Rele)
