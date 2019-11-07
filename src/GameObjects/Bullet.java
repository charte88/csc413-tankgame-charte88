package GameObjects;

//import Game.Game;

import Game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Bullet extends GameObject {
    //private Game game;
    private Handler handler;
    private final int R = 3;

    private BufferedImage bullet_image;


    public Bullet(int x, int y, ID id, Handler handler, GlobalTexture tex, int angle) {
        super(x, y, id, tex);
        //this.game = game;
        this.handler = handler;

        try {
            bullet_image = read(new File("resources/redbullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //speed
        this.velX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        this.velY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
    }

    public void tick() {
        this.x += velX;
        this.y += velY;

        collision();
    }

    private void collision() {
        for (int i = 0; i<Handler.wallList.size(); i++) {
            Wall tempObject = Handler.wallList.get(i);

            if (tempObject.getId() == ID.Block)  {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }

        }
    }

    public void render(Graphics g) {
        //g.setColor(Color.green);
        //g.fillOval(x, y, 20,20);

        //Graphics2D g2d = (Graphics2D) g;
        //g.setColor(Color.white);
        //g2d.draw(getBounds());

        g.drawImage(bullet_image, this.x, this.y, null);
        tick();
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y,20,20);
    }
}
