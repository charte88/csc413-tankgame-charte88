package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Credits extends MouseAdapter {
    private BufferedImage img;
    private TRE tre;

    Credits(BufferedImage img, TRE tre) {
        this.img = img;
        this.tre = tre;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mouseOver(mx, my,40,20,100,100)) {
            tre.setId(ID.Menu);
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x+width ) {
            if (my > y && my <y +height){
                return true;
            } else return false;
        } else return false;
    }

    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img,0,0,1280,960,null);
    }
}


