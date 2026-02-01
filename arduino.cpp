#include <Servo.h>

Servo servo;

int trigPin = 10;
int echoPin = 11;

void setup() {
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);

  servo.attach(12);
  Serial.begin(9600);
}

void loop() {

  // move servo from left to right
  for (int angle = 15; angle <= 165; angle++) {
    servo.write(angle);
    delay(30);

    int distance = getDistance();

    Serial.print(angle);
    Serial.print(",");
    Serial.print(distance);
    Serial.print(".");
  }

  // move servo from right to left
  for (int angle = 165; angle >= 15; angle--) {
    servo.write(angle);
    delay(30);

    int distance = getDistance();

    Serial.print(angle);
    Serial.print(",");
    Serial.print(distance);
    Serial.print(".");
  }
}

int getDistance() {
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);

  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  long time = pulseIn(echoPin, HIGH);
  int distance = time * 0.034 / 2;

  return distance;
}
