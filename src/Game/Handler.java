package Game;

import GameObjects.GameObject;

import java.awt.*;
import java.util.ArrayList;

public class Handler {
    public ArrayList<GameObject> object;
    private TRE tre;

    Handler(TRE tre) {
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
}
