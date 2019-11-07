package GameObjects;

import Game.Game;

import java.awt.*;

public class Wall extends GameObject {

    public double x, y;

    public Wall(int x, int y, ID id, GlobalTexture globalTexture) {
        super(x, y, id, globalTexture);
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        g.drawImage(tex.brickWall, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick() { }

}
