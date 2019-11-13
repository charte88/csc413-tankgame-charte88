package GameObjects;

import Game.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    int x, y;
    float velX = 0, velY = 0;
    protected ID id;
    BufferedImage img;

    GameObject(int x, int y, ID id, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.img = img;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
