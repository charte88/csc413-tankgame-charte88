package GameObjects;


import Game.Handler;
import Game.ID;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tank extends GameObject {
    private Handler handler;
    private BufferedImage overshield;
    private BufferedImage bullet;

    private int vx, vy, angle;
    private int life = 3;
    private int health = 100;
    private int ammo = 20;
    
    private final int R = 2;
    private final int ROTATIONSPEED = 2;

    private boolean isDisplayAmmoPickup = false;
    private boolean isDisplayOvershieldPickup = false;
    private boolean isDisplayExtraLifePickup = false;
    private boolean gameOver = false;

    private long lastTrueTimeOvershield = 0;
    private long lastTrueTimeAmmo = 0;
    private long lastTrueTimeExtraLife = 0;
    private long lastFired = 0;

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;

    public Tank(int x, int y, ID id, Handler handler, BufferedImage img, BufferedImage overshield, int angle, BufferedImage bullet) {
        super(x, y, id, img);
        this.handler = handler;
        this.overshield = overshield;
        this.angle = angle;
        this.bullet = bullet;

        vx = 0;
        vy = 0;
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPresses() {
        this.ShootPressed = true;
    }

    void unToggleShootPressed() {
        this.ShootPressed = false;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void tick() {
        this.x += velX;
        this.y += velY;


        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.ShootPressed) {
            shootBullet();
        }
        if (this.health <= 0) {
            if (this.life != 0) {
                this.life--;
                this.health = 100;

            }
            if (this.life == 0 && this.health == 0) {
                this.gameOver = true;
            }
        }

        collision();
    }

    private void shootBullet() {
        // Limits the tank to one shot per second
        if (System.currentTimeMillis() - lastFired > 1000) {
            // Player 1
            if (id == ID.Player) {
                if (this.ammo >= 1) {
                    handler.addObject(new Bullet(x + 19, y + 19, ID.Bullet, handler, bullet, angle));
                    lastFired = System.currentTimeMillis();
                    this.ammo--;
                }
            }
            // Player 2
            if (id == ID.Player2) {
                if (this.ammo >= 1) {
                    handler.addObject(new Bullet(x + 19, y + 19, ID.Bullet2, handler, bullet, angle));
                    lastFired = System.currentTimeMillis();
                    this.ammo--;
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
        this.vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        this.vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        this.x -= vx;
        this.y -= vy;
    }

    private void moveForwards() {
        this.vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        this.vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        this.x += vx;
        this.y += vy;
    }

    private void collision() {
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
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
            ///////////////////////////////////////////////////////////////

            ///////////// Colliding with ammo crate ////////////////////////
            if (tempObject.getId() == ID.Crate) {
                // Player 1 colliding with ammo crate
                if (id == ID.Player) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        this.isDisplayAmmoPickup = true;
                        this.lastTrueTimeAmmo = System.currentTimeMillis();
                        this.ammo += 10;
                        handler.removeObject(tempObject);
                    }
                }
                // Player 2 colliding with ammo crate
                if (id == ID.Player2) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        this.isDisplayAmmoPickup = true;
                        this.lastTrueTimeAmmo = System.currentTimeMillis();
                        this.ammo += 10;
                        handler.removeObject(tempObject);
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////

            ///////////// Colliding with Overshield ////////////////////////
            if (tempObject.getId() == ID.Overshield) {
                // Player 1 colliding with overshield powerup
                if (id == ID.Player) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        isDisplayOvershieldPickup = true;
                        this.lastTrueTimeOvershield = System.currentTimeMillis();
                        handler.removeObject(tempObject);
                    }
                }
                // Player 2 colliding with overshield powerup
                if (id == ID.Player2) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        isDisplayOvershieldPickup = true;
                        this.lastTrueTimeOvershield = System.currentTimeMillis();
                        handler.removeObject(tempObject);
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////

            /////////////// Colliding with ExtraLife //////////////////////////
            if (tempObject.getId() == ID.ExtraLife) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    this.isDisplayExtraLifePickup = true;
                    this.lastTrueTimeExtraLife = System.currentTimeMillis();
                    addLife();
                    handler.removeObject(tempObject);
                }
            }
            ///////////////////////////////////////////////////////////////////

            ////////////////// Colliding with enemy ///////////////////////////
            if (tempObject.getId() == ID.Enemy) {
                // Player 1 colliding with enemy
                if (id == ID.Player) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (isOvershieldPickup()) {
                            this.health--;
                        } else {
                            this.health -= 2;
                        }
                    }
                }
                // Player 2 colliding with enemy
                if (id == ID.Player2) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (isOvershieldPickup()) {
                            this.health--;
                        } else {
                            this.health -= 2;
                        }
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////

            //////////////////// Bullet with player ///////////////////////////
            // Player 2 hit with bullet
            if (tempObject.getId() == ID.Bullet) {
                if (id == ID.Player2) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (isOvershieldPickup()) {
                            this.health -= 10;
                        } else {
                            this.health -= 20;
                        }
                        handler.removeObject(tempObject);
                    }
                }
            }
            // Player 1 hit with bullet
            if (tempObject.getId() == ID.Bullet2) {
                if (id == ID.Player) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (isOvershieldPickup()) {
                            this.health -= 10;
                        } else {
                            this.health -= 20;
                        }
                        handler.removeObject(tempObject);
                    }
                }
            }
            //////////////////////////////////////////////////////////////////
        }
    }

    private void unbreakableWallCollision(GameObject tempObject) {
        if (getBounds().intersects(tempObject.getBounds())) {
            if (this.DownPressed) {
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
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.img, rotation, null);
        g.setColor(Color.red);
        g.fillRect(x, y + 60, health / 2, 10);
        g.setColor(new Color(75, 255, 0));
        g.fillRect(x, y + 60, health / 2, 10);
        g.setColor(Color.blue);
        g.drawRect(x, y + 60, health / 2, 10);
        g.setColor(Color.GREEN);
        g.setFont(new Font("arial", Font.BOLD, 12));
        g.drawString("Lives : " + life + " [ " + ammo + " ]", x, y + 90);

        if (isDisplayAmmoPickup()) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("arial", Font.BOLD, 24));
            g.drawString("Ammo +10", x, y + -25);
        }
        if (isOvershieldPickup()) {
            if (id == ID.Player) {
                g2d.drawImage(overshield, x, y - 11, null);
            }
            if (id == ID.Player2) {
                g2d.drawImage(overshield, x - 12, y - 10, null);
            }
        }
        if (isDisplayExtraLifePickup()) {
            g.setColor(Color.cyan);
            g.setFont(new Font("arial", Font.BOLD, 24));
            g.drawString("Extra Life!", x, y + 130);
        }
    }
    private void addLife() {
        this.life+=1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean isDisplayAmmoPickup() {
        if (System.currentTimeMillis() - this.lastTrueTimeAmmo > 500) {
            this.isDisplayAmmoPickup = false;
            return false;
        }
        return true;
    }

    private boolean isOvershieldPickup() {
        if (System.currentTimeMillis() - this.lastTrueTimeOvershield > 20000) {
            isDisplayOvershieldPickup = false;
            return false;
        }
        return true;
    }

    private boolean isDisplayExtraLifePickup() {
        if (System.currentTimeMillis() - this.lastTrueTimeExtraLife > 500) {
            this.isDisplayExtraLifePickup = false;
            return false;
        }
        return true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, this.img.getWidth(),this.img.getHeight());
    }
}