/*
Considerando a quantia de 1876 dias, imprima como saı́da o quanto esta quantidade equivale em anos, meses
(considerar 30 dias), semanas e, eventualmente, dias restantes. Observe o modelo de saı́da a seguir:
Total de dias: 1876
Anos:
Meses:
Semanas:
Dias restantes:
*/
#include <iostream>

using namespace std;

int main(){
int anos, mes, semana, dias_restante;
anos = 1876/365;
mes = 1876/anos;
semana = anos * mes;
dias_restante = 0;
	cout<<"Total de Dias: 1876\n";
	cout<<"Anos: "<<anos<<endl;
	cout<<"Meses: "<<mes<<endl;
	cout<<"Semanas: "<<semana<<endl;
	cout<<"Dias restantes: "<<dias_restante<<endl;

return 0;
}
