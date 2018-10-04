// connect motor controller pins to Arduino digital pins
// motor one
int enA = 11;
int in1 = 4;
int in2 = 7;
// motor two
int enB = 10;
int in3 = 8;
int in4 = 9;
//ir sensor
#define Lir A1
#define Rir A2
//led
int redPin = 3;
int greenPin = 5;
int bluePin = 6;
#define COMMON_ANODE

char cmd = 's';


void setup()
{
  Serial.begin(9600);
  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT);
  pinMode(bluePin, OUTPUT);
  // set all the motor control pins to outputs
  pinMode(enA, OUTPUT);
  pinMode(enB, OUTPUT);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
  // initialize digital pin 13 as an output.
  pinMode(Lir, INPUT);
  pinMode(Rir, INPUT);
}

double get_ir(uint16_t value){
  if(value<16) value = 16; 
  return 2076.0/(value-11.0); 
  }

int is_obs(){
  uint16_t value_l = analogRead(Lir);
  double ds_l = get_ir(value_l);
  uint16_t value_r = analogRead(Rir);
  double ds_r = get_ir(value_r);  
  if (ds_l>=20 && ds_r>=20){
    return 0;
  }//no obstacle  
  else if (ds_l>=20 && ds_r<20){
    return 1;
  }//obstacle at right  
  else if (ds_l<20 && ds_r>=20){
    return 2;
  }//obstacle at left
  else if (ds_l<20 && ds_r<20){
    return 3;
  }//obstacle at both side
}



void forward() //move forward
{
  //motor left
  digitalWrite(in1, HIGH);
  digitalWrite(in2, LOW);
  analogWrite(enA, 250);
  //motor right
  digitalWrite(in3, HIGH);
  digitalWrite(in4, LOW);
  analogWrite(enB, 250);
}

void left() //turn left
{
  //motor left
  digitalWrite(in1, LOW);
  digitalWrite(in2, HIGH);
  analogWrite(enA, 250);
  //motor right
  digitalWrite(in3, HIGH);
  digitalWrite(in4, LOW);
  analogWrite(enB, 250);
}

void right() //turn right
{
  //motor left
  digitalWrite(in1, HIGH);
  digitalWrite(in2, LOW);
  analogWrite(enA, 250);
  //motor right
  digitalWrite(in3, LOW);
  digitalWrite(in4, HIGH);
  analogWrite(enB, 250);
}

void back() //reverse
{
  //motor left
  digitalWrite(in1, LOW);
  digitalWrite(in2, HIGH);
  analogWrite(enA, 250);
  //motor right
  digitalWrite(in3, LOW);
  digitalWrite(in4, HIGH);
  analogWrite(enB, 250);
}

void stopmove() //stop move
{
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);  
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);
}

void demo()
{
  delay(4000);
  back();
  delay(3000);
  forward();
  delay(3000);
  left();
  delay(1000);
  right();
  delay(2000);
  left();
  delay(1000);
  stopmove();
  delay(18000);
  
}

void dance()
{
left();
delay(3000);
right();
delay(3000);
forward();
delay(1000);
back();
delay(1000);
stopmove();
}

void auto_drive()
{
  int obs = is_obs();
  if(obs==0){
    forward();
    //Serial.println("no obstacle");
  }
  else if (obs==1){
    stopmove();
    left();
    delay(300);
    //Serial.println("right obstacle");
  }
  else if (obs==2){
    stopmove();
    right();
    delay(300);
    //Serial.println("left obstacle");
  }
  else if (obs==3){
    stopmove();
    right();
    delay(400);
    //Serial.println("is that a wall?");
  }
}
//LED control
void setColor(int red, int green, int blue)
{
  #ifdef COMMON_ANODE
    red = 255 - red;
    green = 255 - green;
    blue = 255 - blue;
  #endif
  analogWrite(redPin, red);
  analogWrite(greenPin, green);
  analogWrite(bluePin, blue);  
}

void loop()
{
  while (Serial.available() > 0)
  {
  cmd = Serial.read();
  //Serial.print(cmd);
  delay(2);
  }
  if (cmd == 'f')
  {
    forward();
  }
  else if(cmd == 'l')
  {
    left();
  }
  else if(cmd == 'r')
  {
    right();
    
  }
  else if(cmd == 'b')
  {
    back();
    
  }
  else if(cmd == 'd')
  {       
      dance();  
  }
  else if (cmd=='v')
  {
    auto_drive();
  }
  else if (cmd=='x')
  {
    demo();
  }
  else if(cmd == 's')
  {
    stopmove();   
  } 
  
  setColor(255, 0, 0);  // red
  delay(100);
  setColor(0, 255, 0);  // green
  delay(100);
  setColor(0, 0, 255);  // blue
  delay(100);
  setColor(255, 255, 0);  // yellow
  delay(100);  
  setColor(80, 0, 80);  // purple
  delay(100);
  setColor(0, 255, 255);  // aqua
  delay(100);
 
}

