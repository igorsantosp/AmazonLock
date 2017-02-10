#include <string.h>
byte enable=3;
byte entrada1=5;
byte entrada2=7;
char senha[4]={1,2,3,4};
char recebido[4];
void setup() {
  // put your setup code here, to run once:
pinMode(entrada1,OUTPUT);
pinMode(entrada2,OUTPUT);
pinMode(enable,OUTPUT);
digitalWrite(enable,HIGH);
Serial.begin(9600);
}

void loop() {
  if(Serial.available()){
    int i=0;
    while(Serial.available()&&i<4){
    recebido[i]= Serial.read();
    delay(15);
    Serial.print(recebido[i]);
    i++;
    }
  Serial.flush();
  Serial.print(strcmp(recebido,senha));
  if(strcmp(recebido,senha)==48){
  //Aciona o motor  
  digitalWrite(entrada1, LOW);  
  digitalWrite(entrada2, HIGH);  
  delay(1300);  

  //Chama a rotina de parada do motor  
  para_motor();  

  //Aciona o motor no sentido inverso  
  digitalWrite(entrada1, HIGH);  
  digitalWrite(entrada2, LOW);  
  delay(1300);  
  para_motor();
  }
  }
  }

    
void para_motor()  
{  
  digitalWrite(entrada1, LOW);  
  digitalWrite(entrada2, LOW);  
  delay(2000);  
}
