/*
Sabemos que a área de retângulo é calculada multiplicando-se o tamanho da base pela altura, ou seja,
area = base *  altura
(a) Declarar uma variável chamada base e outra chamada altura;
(b) Atribuir a  variável base o valor 0.45;
(c) Atribuir a  variável altura o valor 0.67;
(d) Declarar uma variável chamada area;
(e) Atribuir a  variável area o resultado do cálculo da área do retângulo;
(f) Imprimir na saı́da o valor necessário para comprar 50 unidades de piso cerâmico de área igual a calculada
anteriormente ao custo de R$ 35,00 ao m 2 .
*/
#include<iostream>
using namespace std;

int main(){

float area, base, altura, custo;
base = 0.45;
altura = 0.65;
	area = base * altura;

	cout<<"Tamanho da area: "<<area<<endl;
	custo = 50 * (area *3.6);
	cout<<"Para comprar 50unidades:R$ "<<custo<<endl;


return 0;
}

