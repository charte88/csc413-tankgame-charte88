package GameObjects;

import java.awt.*;

public class BreakableBlock extends GameObject {
    private Handler handler;
    private int hp = 100;

    public BreakableBlock(int x, int y, ID id, Handler handler, GlobalTexture tex) {
        super(x, y, id, tex);
        this.handler = handler;

        //block_image = ss.grabImage(5,2,32,32);

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
        g.setColor(Color.black);
        g.fillRect(x, y,32,32);
        //g.drawImage(block_image, x, y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}