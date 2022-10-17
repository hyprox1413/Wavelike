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
  void render() {
    fill(((t + x / 15 + y / 15 - (int(timer)) % 255) + 255) % 255, 255, 255);
    ellipse(xt, yt, 80, 80);
  }
  void update() {
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

void up() {
}
