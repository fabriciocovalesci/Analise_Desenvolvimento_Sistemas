/*
Desenvolva um programa que imprima o resultado de f (x, y) para x = 4 e y = −1 utilizando a função abaixo:
f (x, y) = (x ∗ 2 + x/5 − x ∗ y 2 )/4.5
*/

#include<iostream>

using namespace std;

int main(){
float x, y, resultado;
x = 4;
y = -1;
resultado = (((x*2) +(x/5)) -(x*(y*y))/4.5);
cout<<"Resultado: "<<resultado<<endl;

return 0;
}
