#!/usr/bin/python3


from decrypt import decrypt
from encrypt import encrypt

import string


def check_password(passwd):
    if len([x for x in passwd if string.punctuation.find(x) and string.whitespace.find(x) != -1]) == 0 and passwd.isalpha():
        return True
    else:
        return False


def input_password():
    while True:
        pwd = str(input('Password: ')).upper()
        if check_password(pwd):
            return pwd
        else:
            print('\nERROR: Digitar apenas LETRAS, sem espaços, numeros ou caracteres especiais !!')

def read_save_file(input_message, output_message, message, function_cipher):
    try:
        path_file = str(input(input_message))
        with open(path_file, 'r') as file:
            contents = file.readlines()
            contents = ''.join(str(to_string) for to_string in contents)
            passwd_encript = input_password()
            data = function_cipher(contents.upper(), passwd_encript)
            name_file_output = str(input(output_message)).lower()
            if '.txt' in name_file_output:
                pass
            else: 
                name_file_output = name_file_output + '.txt'
            file_output = open(name_file_output, 'w')
            file_output.write(data)
            file_output.close()
            print(message)
    except IOError as e:
        print(f'\nErro ao ler arquivo {path_file} | {e.strerror}')

def main():

    while True:
        print("===========================")
        print('1 - Texto a ser Encriptado: ')
        print('2 - Texto a ser Decriptado: ')
        print('3 - Carregar aquivo para ser Encriptado: ')
        print('4 - Carregar aquivo para ser Decriptado: ')
        print('5 - Sair')
        opt = input('opt ->  ')

        if opt == '1':
            input_text_encript = str(input('\nTexto para encriptar: ')).upper()
            passwd_encript = input_password()
            data = encrypt.encrypt_vigenere(input_text_encript, passwd_encript)
            print('\nDados encriptados: ', data)
        elif opt == '2':
            input_text_decript = str(input('\nTexto para decriptar: ')).upper()
            passwd_decript = input_password()
            data = decrypt.decrypt_vigenere(input_text_decript, passwd_decript)
            print('\nDados decriptados: ', data)
        elif opt == '3':
            read_save_file("\nPath e nome arquivo '.txt' (EX: ./file.txt): ", 'Nome do NOVO arquivo encriptado: ', '\nArquivo encriptado com sucesso !!', encrypt.encrypt_vigenere)
        elif opt == '4':
            read_save_file("\nPath e nome arquivo '.txt' (EX: ./file.txt): ", 'Nome do NOVO arquivo decriptado: ', '\nArquivo decriptado com sucesso !!', decrypt.decrypt_vigenere)
        elif opt == '5':
            exit()
        else:
            print('\nOpção invalida !!')
            continue


if __name__ == '__main__':
    main()