/*
Peça para o usuário informar um número inteiro. Imprima na saı́da o resto da divisão do número informado por
i5.
*/

#include <iostream>
using namespace std;

int main(){
int inteiro;
float resto;

cout<<"Informe um numero inteiro: ";
cin>>inteiro;
resto = inteiro%5;
cout<<"resto: "<<resto<<endl;

return 0;
}
