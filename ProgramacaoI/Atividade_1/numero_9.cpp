#include <iostream>
#include <iomanip>
using namespace std;

int main(){

float velocidade, distancia, gasto, tempo;
// distancia = velocidade/tempo
	cout<<"Tempo gasto: ";
	cin>>tempo;
	
	cout<<"Velocidade media percorida: ";
	cin>>velocidade;

	distancia = velocidade*tempo;
	
	cout<<setprecision(4)<<"Distancia percorida: "<<distancia<<" km"<<endl;
	cout<<fixed;
	gasto = distancia/12;
	
	cout<<setprecision(2)<<"Valor gasto para pecorrer "<< distancia<<" km "<<"sera de R$ "<<gasto<<endl;
	cout<<fixed;
return 0;
}
