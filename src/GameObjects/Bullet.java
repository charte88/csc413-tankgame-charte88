package GameObjects;

import Game.Handler;
import Game.ID;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {
    private Handler handler;
    private final int R = 3;
    private int angle;


    Bullet(int x, int y, ID id, Handler handler, BufferedImage img, int angle) {
        super(x, y, id, img);
        this.handler = handler;
        this.angle = angle;

        //speed
        this.velX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        this.velY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
    }

    public void tick() {
        x += velX;
        y += velY;

        collision();
    }

    private void collision() {
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Block)  {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), 0, 0);
        g2.drawImage(this.img, rotation, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, img.getWidth(), img.getHeight());
    }
}
