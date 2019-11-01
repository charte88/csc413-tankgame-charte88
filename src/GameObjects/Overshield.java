package GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Overshield extends GameObject {
    private BufferedImage crate_image;

    public Overshield(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);

    }

    public void tick() { }

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, 32,32);
        //g.drawImage(crate_image, x, y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}
