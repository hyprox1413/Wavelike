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

void setup() {
  size(1280,1280);
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

void draw() {
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
