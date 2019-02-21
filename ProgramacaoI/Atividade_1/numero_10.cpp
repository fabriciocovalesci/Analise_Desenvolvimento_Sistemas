#include <iostream>

using namespace std;

int main(){
float avaliacao1, avaliacao2, avaliacao3, media;
cout<<"\nPrimeira avalizacao: ";
cin>>avaliacao1;
cout<<"Segunda avalicao: ";
cin>>avaliacao2;
cout<<"Terceira avaliacao: ";
cin>>avaliacao3;

media = (((avaliacao1*2) +(avaliacao2*3)+(avaliacao3*5))/10.0); 
cout<<"Media final do estudante: "<<media<<endl;
return 0;
}
