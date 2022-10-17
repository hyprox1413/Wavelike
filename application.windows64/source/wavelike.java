import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class wavelike extends PApplet {

int pointsY = 22;
int pointsX = 22;
int timer = 0;
int space = 80;
int state = 0;
float lambda = 180;
float freq = 30;
int layers = 1;
int parts = 0;
float separation = 5;
Point[][][] points = new Point[layers][pointsY][pointsX];

public void setup() {
  
  colorMode(HSB);
  background(0, 0, 255);
  noStroke();
  for (int t = 0; t < layers; t ++) {
    for (int y = 0; y < pointsY; y ++) {
      for (int x = 0; x < pointsX; x ++) {
        points[t][y][x] = new Point(space * (x - 3), space * (y - 3), separation * t, (x % 2) * 2 - 1, (y % 2) * 2 - 1);
      }
    }
  }
}

public void draw() {
  for (int t = 0; t < layers; t ++) {
    for (int y = 0; y < pointsY; y ++) {
      for (int x = 0; x < pointsX; x ++) {
        points[t][y][x].update();
      }
    }
  }
  for (int t = 0; t < layers; t ++) {
    for (int y = 0; y < pointsY; y ++) {
      for (int x = 0; x < pointsX; x ++) {
        points[t][y][x].render();
      }
    }
  }
  if(timer % 2 ==0)
  //saveFrame("output/wavelike_####.png");
  timer ++;
  parts = floor(timer / PI / freq);
  state = parts % 4;
}
class Point {
  float x, y, t, xt, yt;
  int xp, yp;
  Point(float x, float y, float t, int xp, int yp) {
    this.x = x;
    this.y = y;
    this.t = t;
    this.xt = x;
    this.yt = y;
    this.xp = xp;
    this.yp = yp;
  }
  public void render() {
    fill(((t + x / 15 + y / 15 - (PApplet.parseInt(timer)) % 255) + 255) % 255, 255, 255);
    ellipse(xt, yt, 80, 80);
  }
  public void update() {
    if (timer > t) {
      switch(((state - ((timer - t) < (parts * PI * freq) ? 1 : 0)) + 1) % 4) {
      case 0: 
        xt = x + yp * lambda * sin((timer - t) / freq);
        yt = y;
        break;
      case 1:
        yt = y + xp * lambda * sin((timer - t) / freq);
        xt = x;
        break;
      case 2: 
        xt = x - yp * lambda * sin((timer - t) / freq);
        yt = y;
        break;
      case 3:
        yt = y - xp * lambda * sin((timer - t) / freq);
        xt = x;
        break;
      }
    }
  }
}

public void up() {
}
  public void settings() {  size(1280,1280); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "wavelike" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
