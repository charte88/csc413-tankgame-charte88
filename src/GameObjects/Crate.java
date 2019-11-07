package GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crate extends GameObject {
    //private BufferedImage crate_image;

    public Crate(int x, int y, ID id, GlobalTexture tex) {
        super(x, y, id, tex);

        //crate_image = ss.grabImage(6,2,32,32);

    }

    public void tick() {

    }

    public void render(Graphics g) {
        //g.setColor(Color.cyan);
        //g.fillRect(x, y, 32,32);
        g.drawImage(tex.ammoCrate, x, y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}
