import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
    private int num = 60;
    private ArrayList<Ball> ballList = new ArrayList<>();
    private long startFrame = 0;

    public void settings() {
        size(800, 800);
        Ball.setWindow(this);
    }

    public void setup() {
        for (int i = 0; i < num; i++) {
            ballList.add(Ball.makeRandom(this.width, this.height, 5, 3));
        }
    }

    public void draw() {
        startFrame = System.currentTimeMillis();

        background(255);

        for (Ball b : ballList) {
            b.move();
            b.draw();
        }

        long duration = System.currentTimeMillis() - startFrame;
        // System.out.println("Duration: " + duration + " ms");
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
