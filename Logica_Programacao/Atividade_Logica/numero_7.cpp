/* (FACCAT) Escreva um algoritmo para ler o número total de eleitores de um município, o
número de votos brancos, nulos e válidos. Calcular e escrever o percentual que cada um
representa em relação ao total de eleitores
*/

#include<iostream>
using namespace std;

int main(){
int total, validos, nulos, branco;
float percBranco, percNulos, percValidos;

// Solicita ao usuario os dados
cout<<"Total de eleitores: ";
cin>> total;
cout<<"Votos em branco: ";
cin>> branco;
cout<<"Votos nulos: ";
cin>> nulos;
cout<<"Votos validos: ";
cin>>validos;

// Caculos de percentual
percBranco = (100*branco)/total;
percNulos = (100*nulos)/total;
percValidos = (100*validos)/total;

// Exibe os resultados
cout<<"Percentual de votos branco..: "<<percBranco<<"%"<<endl;
cout<<"Percentual de votos nulos...: "<<percNulos<<"%"<<endl;
cout<<"Percntutal de votos validos.: "<<percValidos<<"%"<<endl;

return 0;
}
