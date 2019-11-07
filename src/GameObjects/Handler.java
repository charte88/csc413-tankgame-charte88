package GameObjects;

import Game.Game;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Handler extends Component {
    Game game;
    GlobalTexture tex;

    public ArrayList<GameObject> object = new ArrayList<>();
    public static ArrayList<Wall> wallList = new ArrayList<>();

    Wall tempWall;
    //Crate ammoCrate;

    private Image image, playBackground;
    private MediaTracker tracker;
    private URL url;

    private  boolean up = false, down = false, right = false, left = false, shoot = false;
    private  boolean up2 = false, down2 = false, right2 = false, left2 = false, shoot2 = false;

    public Handler() { }

    public Handler(Game game, GlobalTexture tex) {
        this.game = game;
        this.tex = tex;

        addObject(new Crate(200,150, ID.Crate, tex));
        addObject(new Crate(1100,150, ID.Crate, tex));
        addObject(new Crate(200,800, ID.Crate, tex));
        addObject(new Crate(1100,800, ID.Crate, tex));

        ///////////////////////////////////////////////////////////
        ///////////////////// Border Walls ///////////////////////
        for (int i = 0; i < 42; i++) {
            addWall(new Wall(1 + 32 * i,1, ID.Block, tex));
            addWall(new Wall(1 + 32 * i,958, ID.Block, tex));
        }

        for (int i = 0; i < 30; i++) {
            addWall(new Wall(0,30 + 32 * i, ID.Block, tex));
            addWall(new Wall(1246,30 + 32 * i, ID.Block, tex));
        }
        /////////////////////////////////////////////////////////////
        ////////////////// Horizontal Walls ////////////////////////
        for (int i = 0; i < 5; i++) {
            addWall(new Wall(150 + 32 * i,270, ID.Block, tex));
            addWall(new Wall(980 + 32 * i,270, ID.Block, tex));

            //// Breakable Walls ////
            addWall(new BreakableBlock(595 + 32 * i,500, ID.BreakableBlock,this, tex));
            ////////////////////////

            addWall(new Wall(150 + 32 * i,702, ID.Block, tex));
            addWall(new Wall(980 + 32 * i,702, ID.Block, tex));
        }
        /////////////////////////////////////////////////////////////
        //////////////////// Vertical Walls ////////////////////////
        for (int i = 0; i < 6; i++) {
            addWall(new Wall(390,400 + 32 * i, ID.Block, tex));

            //// Breakable Walls ////
            addWall(new BreakableBlock(520,150 + 32 * i, ID.BreakableBlock,this, tex));
            addWall(new BreakableBlock(520,635 + 32 * i, ID.BreakableBlock,this, tex));
            ////////////////////////

            addWall(new Wall(660,100 + 32 * i, ID.Block, tex));
            addWall(new Wall(660,685 + 32 * i, ID.Block, tex));

            addWall(new BreakableBlock(800,150 + 32 * i, ID.BreakableBlock,this, tex));
            addWall(new BreakableBlock(800,635 + 32 * i, ID.BreakableBlock,this, tex));

            addWall(new Wall(900,400 + 32 * i, ID.Block, tex));
        }
    }
    public void tick() {
        for (int i=0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }

        if (game.hp1 == 0 || game.hp2 == 0 ) {
            for (int i=0; i<object.size(); i++) {
                GameObject tempObject = object.get(i);
                if (tempObject.getId() == ID.Crate) {
                    removeObject(tempObject);
                }
            }
            addObject(new Crate(750,100, ID.Crate, tex));
            addObject(new Crate(590,650, ID.Crate, tex));
        }
    }

    public void render(Graphics g) {
        for (int i=0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    public void renderMap(int w, int h, Graphics2D g2) {

        playBackground = getGameBG("/background2.png");
        g2.drawImage(playBackground, 0, 0, 1920, 1000, this);


        for (int i = 0; i < wallList.size(); i++) {
            tempWall = wallList.get(i);
            tempWall.render(g2);
        }

        //render(g2);
        //Game.p.render(g2);
        //Game.p2.render(g2);

        //Game.p.tick();
        //Game.p2.tick();

    }

    private Image getGameBG(String name) {
        url = Game.class.getResource(name);
        image = getToolkit().getImage(url);

        try {
            tracker = new MediaTracker(this);
            tracker.addImage(image, 0);
            tracker.waitForID(0);
        } catch (InterruptedException e) { }
        return image;
    }

    private void addWall(Wall instance) {
        wallList.add(instance);
    }

    public void removeWall(Wall tempBreakWall) {
        wallList.remove(tempBreakWall);
    }

    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    public void removeObject(GameObject tempObject) {
        object.remove(tempObject);
    }

    public GameObject getTank(ID tankID) {
        for (int i=0; i<object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == tankID) {
                return tempObject;
            }
        }
        return null;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isUp2() {
        return up2;
    }

    public void setUp2(boolean up2) {
        this.up2 = up2;
    }

    public boolean isDown2() {
        return down2;
    }

    public void setDown2(boolean down2) {
        this.down2 = down2;
    }

    public boolean isRight2() {
        return right2;
    }

    public void setRight2(boolean right2) {
        this.right2 = right2;
    }

    public boolean isLeft2() {
        return left2;
    }

    public void setLeft2(boolean left2) {
        this.left2 = left2;
    }

    public boolean isShoot2() {
        return shoot2;
    }

    public void setShoot2(boolean shoot2) {
        this.shoot2 = shoot2;
    }
}
