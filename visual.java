import processing.serial.*;

Serial myPort;
String data = "";
int angle, distance;

void setup() {
  size(800, 500);
  myPort = new Serial(this, "COM3", 9600); // ⚠️ CHANGE COM PORT
  myPort.bufferUntil('.');
}

void draw() {
  background(0);
  drawRadar();
  drawLine();
  drawObject();
}

void serialEvent(Serial myPort) {
  data = myPort.readStringUntil('.');
  data = data.substring(0, data.length()-1);

  int index = data.indexOf(",");
  angle = int(data.substring(0, index));
  distance = int(data.substring(index+1));
}

void drawRadar() {
  pushMatrix();
  translate(width/2, height);
  noFill();
  stroke(0, 255, 0);

  arc(0, 0, 700, 700, PI, TWO_PI);
  arc(0, 0, 500, 500, PI, TWO_PI);
  arc(0, 0, 300, 300, PI, TWO_PI);

  for (int i = 0; i <= 180; i += 30) {
    line(0, 0, -350*cos(radians(i)), -350*sin(radians(i)));
  }
  popMatrix();
}

void drawLine() {
  pushMatrix();
  translate(width/2, height);
  stroke(0, 255, 0);
  line(0, 0,
       -350*cos(radians(angle)),
       -350*sin(radians(angle)));
  popMatrix();
}

void drawObject() {
  if (distance < 40) {
    pushMatrix();
    translate(width/2, height);
    stroke(255, 0, 0);
    point(
      -distance*10*cos(radians(angle)),
      -distance*10*sin(radians(angle))
    );
    popMatrix();
  }
}
