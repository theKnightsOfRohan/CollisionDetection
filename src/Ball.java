import processing.core.PApplet;

public class Ball {
    private float x, y;
    private float xSpeed, ySpeed;
    private int rad;
    private static PApplet window;
    private int color;
    private boolean isColliding = false;

    public Ball(int x, int y, int rad) {
        this.x = x;
        this.y = y;
        this.rad = rad;
    }

    public void setRandomVel(float speed) {
        double angle = Math.random()*Math.PI*2;
        xSpeed = (float) (speed*Math.cos(angle));
        ySpeed = (float) (speed*Math.sin(angle));
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
        if (x + rad > window.width || x - rad < 0) {
            xSpeed = - xSpeed;
            x += xSpeed;
        }

        if (y + rad > window.height || y - rad < 0) {
            ySpeed = -ySpeed;
            y += ySpeed;
        }
    }

    public void setIsHitting(boolean val) {
        this.isColliding = val;
    }

    public int getColor() {
        return (this.isColliding ? window.color(255,0,0) : window.color(0,255,0));
    }

    public static void setWindow(PApplet win) {
        window = win;
    }

    public static Ball makeRandom(int maxX, int maxY, int radius, float speed) {
        int x = (int)(Math.random()*(maxX-2*radius)) + radius;
        int y = (int)(Math.random()*(maxY-2*radius)) + radius;
        Ball b = new Ball(x, y, radius);
        b.setRandomVel(speed);
        return b;
    }

    public boolean isHitting(Ball other) {
        int dx = other.getX() - this.getX();
        int dy = other.getY() - this.getY();
        int radSum = this.getRad() + other.getRad();
        return dx*dx + dy*dy < radSum*radSum;
    }

    public boolean isHittingSlow(Ball other) {
        double distance = distanceTo(other);
        return (distance < this.getRad() + other.getRad());
    }

    private double distanceTo(Ball other) {
        double dx = other.getX() - this.getX();
        double dy = other.getY() - this.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    public int getTop() {
        return (int)(getY()-getRad());
    }

    public int getBottom() {
        return (int)(getY()+getRad());
    }

    public int getLeft() {
        return (int)(getX()-getRad());
    }

    public int getRight() {
        return (int)(getX()+getRad());
    }

    public int getX() {
        return (int)x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (int)y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRad() {
        return rad;
    }

    public void setRad(int rad) {
        this.rad = rad;
    }

    public void draw() {
        window.fill( this.getColor() );
        window.ellipse(this.x, this.y, 2*this.rad, 2*this.rad);
    }
}
