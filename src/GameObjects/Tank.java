package GameObjects;

import Game.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Tank extends GameObject {
    Handler handler;
    Game game;
    int vx, vy, angle;
    
    private final int R = 3;
    private final int ROTATIONSPEED = 4;

    private long LastFired = 0;

    private BufferedImage tank_image;
    //private BufferedImage wizard_image;
    //private BufferedImage[] wizard_image = new BufferedImage[3];
    //TankGame.Animation anim;

    public Tank(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        
        angle = 0;
        vx = 0;
        vy = 0;

        try {
            tank_image = read(new File("resources/tank1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //wizard_image = ss.grabImage(1,1,32,48);
        //wizard_image[0] = ss.grabImage(1,1,32,48);
        //wizard_image[1] = ss.grabImage(2,1,32,48);
        //wizard_image[2] = ss.grabImage(3,1,32,48);

        //anim = new TankGame.Animation(wizard_image, 3);
    }

    public void tick() {
        x += velX;
        y += velY;


        //movement Player 1
        if (id == ID.Player) {
            if (handler.isUp()) {
                moveForwards();
            }

            if (handler.isDown()) {
                moveBackwards();
            }

            if (handler.isLeft()) {
                rotateLeft();
            }

            if (handler.isRight()) {
                rotateRight();
            }

            if (handler.isShoot()) {
                shootBullet();
            }
        }

        //movement Player 2
        if (id == ID.Player2) {
            if (handler.isUp2()) {
                moveForwards();
            }

            if (handler.isDown2()) {
                moveBackwards();
            }

            if (handler.isLeft2()) {
                rotateLeft();
            }

            if (handler.isRight2()) {
                rotateRight();
            }

            if (handler.isShoot2()) {
                shootBullet();
            }
        }

        collision();

        //anim.tick();
    }

    private void shootBullet() {
        if (System.currentTimeMillis() - LastFired > 1000) {
            if (game.ammoPlayer1 >= 1) {
                handler.addObject(new Bullet(x + 18, y + 18, ID.Bullet, handler, ss, angle));
                LastFired = System.currentTimeMillis();
                game.ammoPlayer1--;
            }

            if (game.ammoPlayer2 >= 1) {
                handler.addObject(new Bullet(x + 18, y + 18, ID.Bullet, handler, ss, angle));
                LastFired = System.currentTimeMillis();
                game.ammoPlayer2--;
            }
        }
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
    }

    private void collision() {
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            // Colliding with unbreakable walls
            if (tempObject.getId() == ID.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (handler.isDown()) {
                        x += vx;
                        y += vy;
                    } else {
                        x += vx * -1;
                        y += vy * -1;
                    }
                }
            }
            // Colliding with ammo crate
            if (tempObject.getId() == ID.Crate) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    game.ammoPlayer1 += 10;
                    handler.removeObject(tempObject);
                }
            }
            // Colliding with enemy
            if (tempObject.getId() == ID.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //game.hp--;
                }
            }
        }
        if (game.hpPlayer1 <= 0) handler.removeObject(this);
    }

    public void render(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), 48 / 2.0, 48 / 2.0);
        //Shape rotatedBounds = rotation.createTransformedShape(getBounds());
        Graphics2D g2d = (Graphics2D) g;

        //g.setColor(Color.blue);
        //g.fillRect(x, y,32,48);

        g2d.drawImage(tank_image, rotation, null);

        //g2d.setColor(Color.green);
        //g2d.draw(rotatedBounds);

        //g2d.setColor(Color.green);
        //g2d.draw(getBounds());

        //g.drawImage(wizard_image, x, y,null);
        //if (velX == 0 && velY == 0)
            //g.drawImage(wizard_image[0], x, y, null);
        //else
            //anim.render(g, x, y,32,48);

       // if (id == TankGame.GameObjects.ID.Player2) g.setColor(Color.white);
      //  g.fillRect(x, y, 32,48);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,48,48);
    }
}