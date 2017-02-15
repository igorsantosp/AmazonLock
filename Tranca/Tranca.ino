#include <EEPROM.h>
#define MAX  4 //Definiçao do tamanho max da senha
char recebido[MAX];
int ind=0;
boolean superUser=false;
byte entrada1=5;
byte entrada2=7;
char senha[MAX];

void paraMotor()  
{  
  Serial.println("Parando motor");
  digitalWrite(entrada1, LOW);  
  digitalWrite(entrada2, LOW);  
  delay(1000);  
}

void abreTranca()
{
  Serial.println("Abrindo tranca");
  digitalWrite(entrada1, LOW);  
  digitalWrite(entrada2, HIGH);  
  delay(1300); 
}
void fechaTranca()
{
  Serial.println("Fechando tranca");
  digitalWrite(entrada1, HIGH);  
  digitalWrite(entrada2, LOW);  
  delay(1300);  
}

void gravaSenha(char sen[MAX])
{
  Serial.println("Gravando senha");
for(int i=0;i<MAX;i++){
EEPROM.write(i+MAX,sen[i]);
}
}

boolean comparaSenha(char senha[MAX]){
char b[MAX];
Serial.println("Senha comum");
for(int i=0;i<MAX;i++){
EEPROM.get(i+MAX,b[i]);
Serial.println(b[i]);
Serial.println(senha[i]);
}
return strcmp(senha,b)==0;
}

boolean comparaSuperSenha(char superSenha[MAX]){
  Serial.println("Senha super usuario");
char c[MAX];
for(int i=0; i<MAX;i++){
EEPROM.get(i,c[i]);
Serial.println(c[i]);
Serial.println(superSenha[i]);
}
Serial.println(strcmp(c,superSenha));
return strcmp(c,superSenha)==0;
}

void setup(){
  Serial.begin(9600);
Serial.println("Senha super usuário");
Serial.println(EEPROM.read(0));
Serial.println(EEPROM.read(1));
Serial.println(EEPROM.read(2));
Serial.println(EEPROM.read(3));
}

void loop(){

  while(!Serial.available()){
    //loop vazio que espera o buffer da serial ficar pronto
  }

  char a=Serial.read();
  Serial.println(a);
  
  if(a!='p' && ind<MAX){
    recebido[ind]=a;
    ind++;
  }
  else{
      ind=0;
      Serial.println("a atual");
      Serial.println(a);
    if(a=='p'){
      if(superUser){
        Serial.println("Modo super usuario");
        gravaSenha(recebido);
        superUser=false;
      }
      else{
        if(comparaSuperSenha(recebido)){
          superUser=true;
          Serial.println("s");
        }
        else{
          if(comparaSenha(recebido))
          {
            abreTranca();
            paraMotor();
          }
          else{
            Serial.print("e");
          }
        }
      }

    }
else{
if(a=='c'){
  Serial.print("fechando porta");
fechaTranca();
paraMotor();
}
}
  }


}

