package GameObjects;

import Game.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Overshield extends GameObject {

    public Overshield(int x, int y, ID id, BufferedImage img) {
        super(x, y, id, img);
    }

    public void tick() { }

    public void render(Graphics g) {
        g.drawImage(img, x, y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}
