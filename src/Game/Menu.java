package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends MouseAdapter {
    private TRE tre;
    private Handler handler;
    private BufferedImage img;
    public Menu(TRE tre, BufferedImage img){
        this.tre = tre;
        this.img = img;

    }
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(mouseOver(mx,my,210,150,200,64)){
            tre.setId(ID.Start);
        }
        if(mouseOver(mx,my,210,250,200,64)){
            tre.setId(ID.Help);
        }
        if(mouseOver(mx,my,210,350,200,64)){
            System.exit(0);
        }

    }
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x+width ){
            if(my > y && my <y +height){
                return true;
            }else return false;

        }else return false;

    }
    public void drawImage(Graphics g){
        g.drawImage(img,0,0,1280,960,null);
        Font fnt = new Font("arial",1,50);
        Font fnt2 = new Font("arial",1,30);
        g.setColor(Color.white);
        g.setFont(fnt);
        g.drawString("Menu",240,70);

        g.setFont(fnt2);
        g.drawRect(210,150,200,64);
        g.drawString("Play",270,190);
        // g.setColor(Color.white);

        g.drawRect(210,250,200,64);
        g.drawString("Help",270,290);
        // g.setColor(Color.white);
        g.drawRect(210,350,200,64);
        g.drawString("Quit",270,390);
    }

}

