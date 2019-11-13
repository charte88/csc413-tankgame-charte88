package GameObjects;

import Game.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crate extends GameObject {

    public Crate(int x, int y, ID id, BufferedImage img) {
        super(x, y, id, img);
    }

    public void tick() {}

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, 32,32);
        //g.drawImage(img, x, y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}
