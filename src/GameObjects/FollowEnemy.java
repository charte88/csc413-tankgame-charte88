/*
package GameObjects;

import Game.Game;

import java.awt.*;

public class FollowEnemy extends Enemy {
    private Handler handler;
    private Game game;

    float distance, distance2, diffX, diffX2, diffY, diffY2;
    private int hp = 100;
    private int vx, vy;


    public FollowEnemy(int x, int y, ID id, Handler handler, Game game,  SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
    }

    public void tick() {
        x += velX;
        y += velY;

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                diffX = x - tempObject.getX() - 48;
                diffY = y - tempObject.getY() - 48;
                distance = (float) Math.sqrt((x - tempObject.getX()) * (x - tempObject.getX())
                        + (y - tempObject.getY()) * (y - tempObject.getY()));
            }
            */
/*if (tempObject.getId() == ID.Player2) {
                diffX2 = x - tempObject.getX() - 48;
                diffY2 = y - tempObject.getY() - 48;
                distance2 = (float) Math.sqrt((x - tempObject.getX()) * (x - tempObject.getX())
                        + (y - tempObject.getY()) * (y - tempObject.getY()));
            }*//*

        }
        if (distance < 300) {
            velX = ((-1 / distance) * diffX);
            velY = ((-1 / distance) * diffY);
        } else {
            velX = 0;
            velY = 0;
        }

        */
/*if (distance2 < 300) {
            velX = ((-1 / distance2) * diffX);
            velY = ((-1 / distance2) * diffY);
        } else {
            velX = 0;
            velY = 0;
        }*//*


        collision();
        //anim.tick();
    }


    private void collision() {
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if ((tempObject.getId() == ID.Block) || (tempObject.getId() == ID.BreakableBlock)) {
                if (getBoundsBig().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hp -= 100;
                    handler.removeObject(tempObject);

                    if (hp <= 0) {
                        handler.removeObject(this);
                        game.pointsPlayer1 += 10;
                    }
                }
            }

            if (tempObject.getId() == ID.Bullet2) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hp -= 100;
                    handler.removeObject(tempObject);

                    if (hp <= 0) {
                        handler.removeObject(this);
                        game.pointsPlayer2 += 10;
                    }
                }
            }
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y,48,48);
        //g.drawImage(enemy_image, x, y,null);
        //anim.render(g, x, y, 32,32);


        //Graphics2D g2d = (Graphics2D) g;
        //g.setColor(Color.green);
        //g2d.draw(getBoundsBig())
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,48,48);
    }

    private Rectangle getBoundsBig() {
        return new Rectangle(x-24, y-24,64,64);
    }
}
*/
