maior = menor = count = soma = soma2 = soma3 = soma4 =0
cid = ''
cid2 = ''
for c in range(1,3):
    cidade = input("\nDigite o nome da cidade: ")
    codigo = int(input("DIgite o codigo da cidade: "))
    veiculos = int(input("Numero de veiculos de passeio: "))
    acidentes = int(input("Numero de acidentes de transito com vitimas: "))
    count+=1
    soma+= veiculos
    soma2 = soma / count
    soma3+=acidentes

    if acidentes > maior:
        maior = acidentes
        cid = cidade
    if count == 1 or acidentes < menor:
        menor = acidentes
        cid2 = cidade
    if veiculos < 2000:
        soma4 = soma3 / count

print("-="*30)
print(f"O menor indice de acidentes de transito {menor} cidade que pertence {cid2}")
print(f"O maior indice de acidenstes de transito {maior} cidade que pertence {cid}")
print(f"Media de veiculos nas cincos cidades {soma2}")
print(f"Media de acidentes de transitos nas cidades com menos de 2000 {soma4}")