import xml.etree.ElementTree as ET
import mysql.connector
import os

current_path = os.path.dirname(os.path.abspath(__file__))
file_xml = "clienteWilliam.xml"

conn = mysql.connector.connect(
  host="172.17.0.2",
  user="root",
  password="",
  database='db_labdb'
)

if conn:
    print ("Conectado com sucesso a database " ,  conn.database)
else:
    print ("Conexao falhou !")


if os.path.exists(f"{current_path}/{file_xml}"):
    tree = ET.parse(f"{current_path}/{file_xml}")
    cliente = tree.findall('cliente')
    funcionario = tree.findall('funcionario')
    
    
    for item in funcionario:
        id = item.find('id').text.strip()
        nome = item.find('nome').text.strip()
        cpf = item.find('cpf').text.strip()
        email = item.find('email').text.strip()
        sexo = item.find('sexo').text.strip()
        cidade = item.find('cidade').text.strip()
        print(f"Nome: {nome} - cpf {cpf} - {id}")
        data = """INSERT INTO funcionario(id,nome,cpf,email,sexo,cidade) VALUES(%s,%s,%s,%s,%s,%s)"""
        c = conn.cursor()
        c.execute(data, (id,nome,cpf,email,sexo,cidade))
        conn.commit()
    
    for item in cliente:
        id = item.find('id').text.strip()
        nome = item.find('nome').text.strip()
        cpf = item.find('cpf').text.strip()
        email = item.find('email').text.strip()
        sexo = item.find('sexo').text.strip()
        cidade = item.find('cidade').text.strip()
        print(f"Nome: {nome} - cpf {cpf} - {id}")
        data = """INSERT INTO cliente(id,nome,cpf,email,sexo,cidade) VALUES(%s,%s,%s,%s,%s,%s)"""
        c = conn.cursor()
        c.execute(data, (id,nome,cpf,email,sexo,cidade))
        conn.commit()


