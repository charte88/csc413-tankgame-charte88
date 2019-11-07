package Game;

import GameObjects.Button;
import GameObjects.Menu;
import GameObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private final int gameWidth = 1280;
    private final int gameHeight = 720;

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private static Camera c1, c2;

    public int ammo1 = 20;
    public int ammo2 = 20;

    public int score = 0;
    public int score2 = 0;

    public int hp1 = 100;
    public int hp2 = 100;

    //private int p1WindowBoundX, p1WindowBoundY, p2WindowBoundX, p2WindowBoundY;

    private BufferedImage gameBackground, walls, button, spritesheet, breakWall, ammo, background, map;
    private BufferedImageLoader loader;

    public BufferedImage bulletTank1;
    public BufferedImage bulletTank2;

    Button playButton;
    Button quitButton;
    GlobalTexture tex;
    Menu menu;

    //public static Tank p;
    //public static Tank p2;

    private int gamestate;

    //static Sound sound = new Sound();
    static Handler display = new Handler();

    private Graphics2D graphic;

    private BufferedImage playerCameraL = new BufferedImage(WIDTH, HEIGHT, 2);
    private BufferedImage playerCameraR = new BufferedImage(WIDTH, HEIGHT, 2);

    public void  init() {
        addMouseListener(new MouseInput(this));


        loader = new BufferedImageLoader();
        gameBackground = loader.loadImage("/gdp.png");
        button = loader.loadImage("/spritesheetbutton.png");
        spritesheet = loader.loadImage("/tankspritesheet.png");
        bulletTank1 = loader.loadImage("/bluebullet.png");
        bulletTank2 = loader.loadImage("/redbullet.png");
        walls = loader.loadImage("/wall.png");
        breakWall = loader.loadImage("/breakablewall.png");
        ammo = loader.loadImage("/ammo.png");

        tex = new GlobalTexture(this);
        handler = new Handler(this, tex);
        menu = new Menu();
        playButton = new Button(300, 300, tex).setTyped(1);
        quitButton = new Button(300, 400, tex).setTyped(3);

        this.addKeyListener(new KeyInput(handler));

        handler.addObject(new Tank(200,500, ID.Player, handler, this, tex));
        handler.addObject(new Tank(1050,500, ID.Player2, handler, this, tex));

        //p = new Tank(250,440, ID.Player, handler,this, tex);
        //p2 = new Tank(980,440, ID.Player2, handler, this, tex);

        c1 = new Camera(0,0);
        c2 = new Camera(0,0);
    }

    private synchronized void start() {
        if (isRunning) {return;}
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
       if (!isRunning) {return;}
       isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    public void tick() {
        //p.tick();
        //p2.tick();
        handler.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        if (gamestate == 0) {
            menu.createMenu(g, gameBackground, playButton, quitButton, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            g.drawString("Use mouse to hit play button", 200, 100);
            g.drawString("Tank 1 Control - W A S D  shoot - Space", 200, 160);
            g.drawString("Tank 2 Control - Arrow Keys  shoot - ENTER", 200, 220);
        } else if (gamestate == 1) {
            map = (BufferedImage) createImage(1280, 1280);
            graphic = map.createGraphics();

            display.renderMap(1280, 720, graphic);
            background = (BufferedImage) createImage(1280, 720);
            graphic = background.createGraphics();

            c1.tick(handler.getTank(ID.Player));
            c2.tick(handler.getTank(ID.Player2));

            playerCameraL = map.getSubimage(0, 0, 640, gameHeight);
            playerCameraR = map.getSubimage(640, 0, 640, gameHeight);

            BufferedImage miniMap = map.getSubimage(550,530,200,200);

            graphic.drawImage(playerCameraL,0,0, 640, gameHeight,this);
            graphic.drawImage(playerCameraR,640,0, 640, gameHeight,this);

            graphic.drawImage(map, 550, 530, 200, 200, this);

            //handler.render(g);
            graphic.dispose();

            //graphic.drawImage(map, 550, 530, 200, 200, this);


            g.drawImage(background, 0, 0, this);

            handler.render(g);

            if (hp1 > 0) {
                g.setColor(Color.red);
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
                g.drawString("Ammo: " + ammo1, 10, 50);
                g.drawString("Score: " + score, 120, 50);
                g.setColor(Color.gray);
                g.fillRect(2, 5, 200, 32);
                g.setColor(Color.red);
                g.fillRect(2, 5, hp1 * 2, 32);
                g.setColor(Color.black);
                g.drawRect(2, 5, 200, 32);
            } else {
                g.setColor(Color.red);
                score2++;
                hp1 = 100;
                hp2 = 100;
                //p = new Tank(250, 440, ID.Player, handler,this,tex);
                //handler.addObject(p);

                //p2 = new Tank(980,440, ID.Player2, handler,this,tex);
                //handler.addObject(p2);

                // delete tanks to reset player tanks
                resetGame();

                handler.addObject(new Tank(250,440, ID.Player, handler, this, tex));
                handler.addObject(new Tank(980,440, ID.Player2, handler, this, tex));

                ammo1 = 20;
                ammo2 = 20;
            }
            if (hp2 > 0) {
                g.setColor(Color.blue);
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
                g.drawString("Ammo: " + ammo2, 710, 50);
                g.drawString("Score: " + score2, 820, 50);
                g.setColor(Color.gray);
                g.fillRect(700, 5, 200, 32);
                g.setColor(Color.blue);
                g.fillRect(700, 5, hp2 * 2, 32);
                g.setColor(Color.black);
                g.drawRect(700, 5, 200, 32);

            } else {
                g.setColor(Color.blue);
                //p = new Tank(250, 440, ID.Player, handler,this,tex);
                //p2 = new Tank(980,440, ID.Player2, handler,this,tex);

                // delete tanks to reset player tanks
                resetGame();

                handler.addObject(new Tank(250,440, ID.Player, handler, this, tex));
                handler.addObject(new Tank(980,440, ID.Player2, handler, this, tex));

                score++;
                hp2 = 100;
                hp1 = 100;
                ammo1 = 20;
                ammo2 = 20;
            }
        }

        g.dispose();
        bs.show();
    }

    public BufferedImage getButtons() {
        return button;
    }

    public BufferedImage getSpriteSheet() {
        return spritesheet;
    }

    public int getWidth() {
        return gameWidth;
    }

    public int getHeight() {
        return gameHeight;
    }

    public void mouseClicked(MouseEvent e) {
        int mouse = e.getButton();

        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (mouse == MouseEvent.BUTTON1) {
            if (r.intersects(playButton.getButtonBounds())) {
                playButton.clickButton(this);
            }
            if (r.intersects(quitButton.getButtonBounds())) {
                quitButton.clickButton(this);
            }
        }
    }

    private void resetGame() {
        handler.removeObject(handler.getTank(ID.Player));
        handler.removeObject(handler.getTank(ID.Player2));
    }

    /*private int cameraX(Tank player) {
        double camX = player.getX() - getWidth() / 4;

        if (camX < 0) {
            camX = 0;
        } else if (camX > (getWidth() / 2)) {
            camX = (getWidth() / 2);
        }
        return (int) camX;
    }

    private int cameraY(Tank player) {
        double camY = player.getY() - getHeight() / 2;

        if (camY < 0) {
            camY = 0;
        } else if (camY > (getWidth() - getHeight())) {
            camY = (getWidth() - getHeight());
        }
        return (int) camY;
    }*/

    public void setGameState(int gamestate) {
        this.gamestate = gamestate;
    }

    public BufferedImage getWalls() {
        return walls;
    }

    public BufferedImage getBreakableWalls() {
        return breakWall;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public BufferedImage getAmmo() {
        return ammo;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tank Wars");
        Game game = new Game();
        frame.add(game);
        frame.setFocusable(true);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setResizable(false);
        frame.pack();
        game.start();
    }
}