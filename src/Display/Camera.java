package Display;


import GameObjects.Tank;

public class Camera {

    private float x,y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }
    //graphics2D method

    public void tick(Tank object) {
        x += ((object.getX() - x) - 640 / 2) * 0.05f;
        y += ((object.getY() - y) - 960 / 2) * 0.05f;
        if (x <= 0) x = 0;
        if (x >= (1280 * 2)) x = (1280 * 2);
        if (y <= 0) y = 0;
        if (y >= 960) y = 960;

    }


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}