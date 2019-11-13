package GameObjects;

import Game.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
    public Block(int x, int y, ID id, BufferedImage img) {
        super(x, y, id, img);
    }

    public void tick() {}

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img,x,y,32,32,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}
