package GameObjects;

import java.awt.*;

public class Bullet extends GameObject {
    private Handler handler;
    private final int R = 8;

    Bullet(int x, int y, ID id, Handler handler, SpriteSheet ss, int angle) {
        super(x, y, id, ss);
        this.handler = handler;

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
        g.setColor(Color.green);
        g.fillOval(x, y, 20,20);

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.white);
        g2d.draw(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,20,20);
    }
}
