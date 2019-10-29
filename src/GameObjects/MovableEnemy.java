package GameObjects;

import java.awt.*;
import java.util.Random;

public class MovableEnemy extends Enemy {
    private Handler handler;
    private Random r = new Random();
    private int choose = 0;
    private int hp = 100;

    //private BufferedImage enemy_image;
    //private BufferedImage[] enemy_image = new BufferedImage[3];
    //TankGame.Animation anim;

    public MovableEnemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;

        //enemy_image = ss.grabImage(4,1,32,32);
        //enemy_image[0] = ss.grabImage(4,1,32,32);
        //enemy_image[1] = ss.grabImage(5,1,32,32);
        //enemy_image[2] = ss.grabImage(6,1,32,32);
        //anim = new TankGame.Animation(enemy_image,3);



    }

    public void tick() {
        x += velX;
        y += velY;

        choose = r.nextInt(10);

        collision();

        //anim.tick();
    }

    private void collision() {
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if ((tempObject.getId() == ID.Block) || (tempObject.getId() == ID.BreakableBlock)) {
                if (getBoundsBig().intersects(tempObject.getBounds())) {
                    x += (velX * 4) * -1;
                    y += (velY * 4) * -1;
                    velX *= -1;
                    velY *= -1;
                } else if (choose == 0) {
                    velX = (r.nextInt(4 - -4) + -4);
                    velY = (r.nextInt(4 - -4) + -4);
                }
            }
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }
        }
        if (hp <= 0) handler.removeObject(this);
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y,32,32);
        //g.drawImage(enemy_image, x, y,null);
        //anim.render(g, x, y, 32,32);


        //Graphics2D g2d = (Graphics2D) g;
        //g.setColor(Color.green);
        //g2d.draw(getBoundsBig())
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }

    private Rectangle getBoundsBig() {
        return new Rectangle(x-16, y-16,64,64);
    }
}
