#!/usr/bin/python3

# https://pt.wikipedia.org/wiki/Cifra_de_Vigen%C3%A8re

import string

ALPHABET = string.ascii_uppercase+'Ç'

def encrypt_vigenere(cifrar, password):
    text_encrypt = ''
    indice = 0
    for letter in cifrar:
        if ALPHABET.find(letter) == -1:
            text_encrypt = text_encrypt + str(letter.replace(letter, ' '))
            indice += 1
        else:
            sum_Mod = ALPHABET.find(letter) + ALPHABET.find(password[indice % len(password)])
            modulo = int(sum_Mod) % len(ALPHABET)
            text_encrypt = text_encrypt + str(ALPHABET[modulo])
            indice += 1

    return text_encrypt