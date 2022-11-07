
int rele1 = 13; // Nao Reconhece Face
int rele2 = 12; // Reconhecer Face 
int leitura;
void setup() {
  
  Serial.begin(9600); // Velocidade padrão do Uno
  pinMode(rele1, OUTPUT); // Porta onde o rele será inserido, configurado como saida
  pinMode(rele2, OUTPUT);// Porta onde o rele será inserido, configurado como saida
  digitalWrite(rele1,  HIGH);
  digitalWrite(rele2, HIGH);
}
void loop() {

  while (Serial.available() > 0) { // Avalia se ha conexao serial

    leitura = Serial.read(); // Variavel que receberá os valores enviados pelo programa em python


    if (leitura == '1') {
      digitalWrite(rele1, HIGH); // Liga o rele 1 se o valor recebido for 1
      digitalWrite(rele2, LOW); // Desliga o rele 2 se o valor recebido for 1
      Serial.println("\nDesconhecido\nLed 1 Ligado\n");
    }
    else if (leitura == '2') {
      digitalWrite(rele1, LOW); // Liga rele se o valor recebido for 2
      digitalWrite(rele2, HIGH); // Desliga o rele se o valor recebido for 2
      Serial.println("\nFabricio\nLed 2 Ligado\n");
    }
    else if(leitura == '3'){
      digitalWrite(rele1, HIGH);
      digitalWrite(rele2, HIGH);
      Serial.println("Desligado");
    }
  }
}
