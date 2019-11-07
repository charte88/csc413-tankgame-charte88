
package Game;

import GameObjects.GameObject;
import GameObjects.ID;

public class Camera {
    private float x, y;
    private int camX, camY;
    Game game;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object) {
       /* x += ((object.getX() - x) - 1000/2) *0.05f;
        y += ((object.getY() - y) - 563/2) *0.05f;

        if (x <= 0) x = 0;
        if (x >= 1032+28) x = 1032+28;
        if (y <= 0) y = 0;
        if (y >= 563+58) y = 563+58;*/

        if (object.getId() == ID.Player) {
            cameraTickHelper(object);
        } else if (object.getId() == ID.Player2) {
            cameraTickHelper(object);
        }
    }

    private void cameraTickHelper(GameObject tankObject) {
        /*this.x = (tankObject.getX() * 1.2f) - 1280 / 4;
        this.y = (tankObject.getY() * 1.2f) - 720 / 2;

        if (x < 0) {
            x = 0;
        } else if (x > (1280 / 2)) {
            x = (1280 / 2);
        }

        if (y < 0) {
            y = 0;
        } else if (y > (720 / 2)) {
            y = (720 / 2);
        }*/

        int offsetMaxX = 1920 - 1280;
        int offsetMaxY = 1000 - 720;
        int offsetMinX = 0;
        int offsetMinY = 0;

        camX = tankObject.getX() - 1280 / 2;
        camY = tankObject.getY() - 720 / 2;

        if (camX > offsetMaxX) {
            camX = offsetMaxX;
        } else if (camX < offsetMinX) {
            camX = offsetMinX;
        }

        if (camY > offsetMaxY) {
            camY = offsetMaxY;
        } else if (camY < offsetMinY) {
            camY = offsetMinY;
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getCamX() {
        return camX;
    }

    public int getCamY() {
        return camY;
    }
}

