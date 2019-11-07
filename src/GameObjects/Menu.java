package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Menu {

    public void createMenu(Graphics g, BufferedImage img, Button b1, Button b2, Button b3) {

        g.drawImage(img, 0 , 0 , null);

        if (b1 != null) {
            b1.render(g);
        }
        if (b2 != null) {
            b2.render(g);
        }
        if (b3 != null) {
            b3.render(g);
        }
    }
}