package GameObjects;

import java.awt.*;

public abstract class Enemy extends GameObject{
    private Handler handler;
    private int hp;

    Enemy(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
    }

    public void tick() { }

    public void render(Graphics g) { }

    public Rectangle getBounds() {
        return null;
    }
}
