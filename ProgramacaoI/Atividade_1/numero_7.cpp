#include <iostream>

using namespace std;

int main(){
// juro = capital*tempo*taxa/100.00;
int tempo;
float capital, taxa, juro, total;
cout<<"Qual sera o valor do emprestimo: ";
cin>>capital;
cout<<"Tempo em que deseja pagar: ";
cin>>tempo;
cout<<"Taxa de juros: ";
cin>>taxa;
juro =((capital*tempo*taxa)/100.00);
cout<<"O juro final do emprestimo:  "<<juro<<endl;
total = capital + juro;
cout<<"Emprestimo com juros final: "<<total<<endl;
return 0;
}
