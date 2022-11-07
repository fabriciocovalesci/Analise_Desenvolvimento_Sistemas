import serial

conexao = serial.Serial('/dev/ttyACM0', 9600) # Configuração da conexão
def rele_1():
        conexao.write(str.encode('1')) # Escreve 1 no arduino (Aciona Rele)
