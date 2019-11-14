package GameObjects;

import Game.Handler;
import Game.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableBlock extends GameObject {
    private Handler handler;
    private int hp = 100;

    public BreakableBlock(int x, int y, ID id, Handler handler, BufferedImage img) {
        super(x, y, id, img);
        this.handler = handler;
    }

    public void tick() {
        collision();
    }

    private void collision() {
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hp -= 100;
                    handler.removeObject(tempObject);
                }
            }
        }
        if (hp <= 0) handler.removeObject(this);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, x, y, 32, 32, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}