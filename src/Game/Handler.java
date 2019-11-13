package Game;

import GameObjects.GameObject;

import java.awt.*;
import java.util.ArrayList;

public class Handler {
    public ArrayList<GameObject> object;

    private TRE tre;

    private  boolean up = false, down = false, right = false, left = false, shoot = false;
    private  boolean up2 = false, down2 = false, right2 = false, left2 = false, shoot2 = false;
    private Object Tank;

    public Handler(TRE tre) {
        this.tre = tre;
        object = new ArrayList<>();
    }

    public void tick() {
        for (int i=0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i=0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public GameObjects.Tank getTanks(ID id) {
        for (GameObject temp : object) {
            if (temp.getId() == id) {
                return (GameObjects.Tank) temp;
            }
        }
        return null;
    }

    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    public void removeObject(GameObject tempObject) {
        object.remove(tempObject);
    }

    /*public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isUp2() {
        return up2;
    }

    public void setUp2(boolean up2) {
        this.up2 = up2;
    }

    public boolean isDown2() {
        return down2;
    }

    public void setDown2(boolean down2) {
        this.down2 = down2;
    }

    public boolean isRight2() {
        return right2;
    }

    public void setRight2(boolean right2) {
        this.right2 = right2;
    }

    public boolean isLeft2() {
        return left2;
    }

    public void setLeft2(boolean left2) {
        this.left2 = left2;
    }

    public boolean isShoot2() {
        return shoot2;
    }

    public void setShoot2(boolean shoot2) {
        this.shoot2 = shoot2;
    }*/
}
