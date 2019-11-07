package GameObjects;

import Game.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Tank extends GameObject {
    private Handler handler;
    private Game game;
    private int vx, vy, angle;
    
    private final double R = 3;
    private final int ROTATIONSPEED = 3;

    //private boolean isOvershieldPlayer1 = false;
    //private boolean isOvershieldPlayer2 = false;

    //private long lastTrueTime = 0;
    private long lastFired = 0;

    private BufferedImage tank_image;

    public Tank(int x, int y, ID id, Handler handler, Game game, GlobalTexture tex) {
        super(x, y, id, tex);
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
        //tank_image = tex.playerRight[0];
    }

    public void tick() {
        this.x += velX;
        this.y += velY;


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
        // Limits the tank to one shot per second
        if (System.currentTimeMillis() - lastFired > 500) {
            // Player 1
            if (id == ID.Player) {
                if (game.ammo1 >= 1) {
                    handler.addObject(new Bullet(x, y, ID.Bullet, handler, tex, angle));
                    lastFired = System.currentTimeMillis();
                    game.ammo1--;
                }
            }
            // Player 2
            if (id == ID.Player2) {
                if (game.ammo2 >= 1) {
                    handler.addObject(new Bullet(x, y, ID.Bullet2, handler, tex, angle));
                    lastFired = System.currentTimeMillis();
                    game.ammo2--;
                }
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
        for (int i = 0; i<Handler.wallList.size(); i++) {
            Wall tempObject = Handler.wallList.get(i);
            //////////// Colliding with unbreakable walls ////////////////////
            if (tempObject.getId() == ID.Block) {
                // Player 1 collision with unbreakable wall
                if (id == ID.Player) {
                    unbreakableWallCollision(tempObject);
                }
                // Player 2 collision with unbreakable wall
                if (id == ID.Player2) {
                    unbreakableWallCollision(tempObject);
                }
            }
            /////////////////////////////////////////////////////////////

            /////////// Colliding with breakable walls ///////////////////////
            if (tempObject.getId() == ID.BreakableBlock) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                }
            }
        }
            ///////////////////////////////////////////////////////////////

        for (int j=0; j<handler.object.size(); j++) {
            GameObject tempObject2 = handler.object.get(j);


            ///////////// Colliding with ammo crate ////////////////////////
            if (tempObject2.getId() == ID.Crate) {
                // Player 1 colliding with ammo crate
                if (id == ID.Player) {
                    if (getBounds().intersects(tempObject2.getBounds())) {
                        game.ammo1 += 10;
                        handler.removeObject(tempObject2);
                    }
                }
                // Player 2 colliding with ammo crate
                if (id == ID.Player2) {
                    if (getBounds().intersects(tempObject2.getBounds())) {
                        game.ammo2 += 10;
                        handler.removeObject(tempObject2);
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////

            //////////////////// Bullet with player ///////////////////////////
            // Player 2 hit with bullet
            if (tempObject2.getId() == ID.Bullet) {
                if (id == ID.Player2) {
                    if (getBounds().intersects(tempObject2.getBounds())) {
                        /*if (overshield2Timer()) {
                            System.out.println("Player2 Health: " + game.hpPlayer2);
                            game.hpPlayer2 -= 5;
                            System.out.println("Player2 Health: " + game.hpPlayer2);
                        } else {
                            System.out.println("Player2 Health: " + game.hpPlayer2);*/
                        game.hp2 -= 10;
                        //System.out.println("Player2 Health: " + game.hpPlayer2);

                        handler.removeObject(tempObject2);
                    }
                }
            }
            // Player 1 hit with bullet
            if (tempObject2.getId() == ID.Bullet2) {
                if (id == ID.Player) {
                    if (getBounds().intersects(tempObject2.getBounds())) {
                        /*if (overshield1Timer()) {
                        System.out.println("Player1 Health: " + game.hpPlayer1);
                        game.hpPlayer1 -= 5;
                        System.out.println("Player1 Health: " + game.hpPlayer1);
                    } else {
                        System.out.println("Player1 Health: " + game.hpPlayer1);*/
                        game.hp1 -= 10;
                        //System.out.println("Player1 Health: " + game.hp1);
                        handler.removeObject(tempObject2);
                    }
                }
            }
            //////////////////////////////////////////////////////////////////

            ///////////// Colliding with Overshield ////////////////////////
            /*if (tempObject.getId() == ID.Overshield) {
                // Player 1 colliding with overshield powerup
                if (id == ID.Player) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //isOvershieldPlayer1 = true;
                        lastTrueTime = System.currentTimeMillis();
                        handler.removeObject(tempObject);
                    }
                }
                // Player 2 colliding with overshield powerup
                if (id == ID.Player2) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //isOvershieldPlayer2 = true;
                        lastTrueTime = System.currentTimeMillis();
                        handler.removeObject(tempObject);
                    }
                }
            }*/
            ///////////////////////////////////////////////////////////////////


        }
    }

    private void unbreakableWallCollision(GameObject tempObject) {
        if (getBounds().intersects(tempObject.getBounds())) {
            if (handler.isDown() || handler.isDown2()) {
                x += vx;
                y += vy;
            } else {
                x += vx * -1;
                y += vy * -1;
            }
        }
    }

    public void render(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), 40 / 2.0, 40 / 2.0);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(tank_image, rotation, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x+5, y+12,40 ,40);
    }

    public int getAngle() {
        return angle;
    }

    public int currentXPosition() {
        return this.x;
    }

    public int currentYPosition() {
        return this.y;
    }

    /*private boolean overshield1Timer() {
        if (System.currentTimeMillis() - lastTrueTime > 20000) {
            isOvershieldPlayer1 = false;
            return false;
        }
        return true;
    }

    private boolean overshield2Timer() {
        if (System.currentTimeMillis() - lastTrueTime > 20000) {
            isOvershieldPlayer2 = false;
            return false;
        }
        return true;
    }*/
}