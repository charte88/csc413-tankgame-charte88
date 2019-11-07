package GameObjects;

import java.awt.*;

public class BreakableBlock extends Wall {
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
        for (int i = 0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Bullet || tempObject.getId() == ID.Bullet2) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hp -= 100;
                    handler.removeObject(tempObject);
                }
            }
        }
        if (hp <= 0) handler.removeWall(this);
    }

    public void render(Graphics g) {
        //g.setColor(Color.black);
        //g.fillRect((int) x, (int) y,32,32);
        g.drawImage(tex.breakWall, (int) x, (int) y,null);
        tick();
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,32,32);
    }
}