package GameObjects;

import java.awt.*;

public abstract class Enemy extends GameObject{
    private Handler handler;
    private int hp;

    Enemy(int x, int y, ID id, GlobalTexture tex) {
        super(x, y, id, tex);
    }

    public void tick() { }

    public void render(Graphics g) { }

    public Rectangle getBounds() {
        return null;
    }
}
