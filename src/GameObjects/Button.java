package GameObjects;

import Game.Game;

import java.awt.*;

public class Button  {

    private double x,y;
    private GlobalTexture tex;
    private boolean clicked = false;
    private int type;


    public Button(double x, double y, GlobalTexture tex) {
        this.x = x;
        this.y = y;
        this.tex = tex;
    }

    public void render(Graphics g) {

        if (!clicked) {
            if (type == 1) {
                g.drawImage(tex.playbutton1, (int) x, (int) y, null);
            }
        } else if (clicked) {
            if (type == 1) {
                g.drawImage(tex.playbutton2, (int) x, (int) y, null);
            }
        }
        if (type == 3) {
            g.drawImage(tex.quitbutton, (int) x, (int) y, null);
        }

    }

    public Button setTyped(int type) {

        this.type = type;
        return this;
    }

    public void clickButton(Game game) {
        clicked = true;
        if (type == 1) {
            game.setGameState(1);
        }
        if (type == 3) {
            System.exit(0);
        }
    }

    public Rectangle getButtonBounds() {
        return new Rectangle((int) x, (int) y, 200, 50);
    }
}