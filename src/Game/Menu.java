package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends MouseAdapter {
    private TRE tre;
    private BufferedImage img;

    private int WIDTH = 200;
    private int HEIGHT = 64;

    Menu(BufferedImage img, TRE tre) {
        this.img = img;
        this.tre = tre;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mouseOver(mx, my,550,140, WIDTH, HEIGHT)) {
            tre.setId(ID.Start);
        }
        if (mouseOver(mx, my,550,240, WIDTH, HEIGHT)) {
            tre.setId(ID.Help);
        }
        if (mouseOver(mx, my,550,340, WIDTH, HEIGHT)) {
            tre.setId(ID.Credits);
        }
        if (mouseOver(mx, my,550,440, WIDTH, HEIGHT)) {
            System.exit(0);
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x+width ) {
            return my > y && my < y + height;
        } else return false;
    }

    public void drawImage(Graphics g) {
        g.drawImage(img,0,0,1280,960,null);
        Font fnt = new Font("arial", Font.BOLD,70);
        Font fnt2 = new Font("arial", Font.BOLD,50);
        g.setColor(Color.BLACK);
        g.setFont(fnt);
        g.drawString("Menu",560,70);

        g.setFont(fnt2);
        g.drawRect(550,140, WIDTH, HEIGHT);
        g.drawString("Play",600,190);

        g.drawRect(550,240, WIDTH, HEIGHT);
        g.drawString("Help",600,290);

        g.drawRect(550,340, WIDTH, HEIGHT);
        g.drawString("Credits",560,390);

        g.drawRect(550,440, WIDTH, HEIGHT);
        g.drawString("Quit",600,490);
    }
}

