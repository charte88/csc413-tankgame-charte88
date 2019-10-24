package Game;

import GameObjects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private SpriteSheet ss;

    private BufferedImage level = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;

    public int ammoPlayer1 = 100;
    public int hpPlayer1 = 100;

    public int ammoPlayer2 = 100;
    public int hpPlayer2 = 100;

    public Game() {
        handler = new Handler();
        new Window(1000,563, "GameObjects.Tank Wars", this);
        start();

        camera = new Camera(0,0);
        this.addKeyListener(new KeyInput(handler));


        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/Tank_Level.png");
        sprite_sheet = loader.loadImage("/sprite_sheet.png");

        ss = new SpriteSheet(sprite_sheet);

        floor = ss.grabImage(4,2,32,32);

        //this.addMouseListener(new MouseInput(handler, camera, this, ss));

        loadLevel(level);
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
       isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
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
        for (int i=0; i<handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                camera.tick(handler.object.get(i));
            }
        }
        handler.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        /////////////////////////////////////

        //g.setColor(Color.red);
        //g.fillRect(0,0, 1000, 563);

        g2d.translate(-camera.getX(), -camera.getY());

        for (int xx=0; xx<30*72; xx+=32) {
            for (int yy=0; yy<30*72; yy+=32) {
                g.drawImage(floor, xx, yy,null);
            }
        }

        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());

        //hp Player 1 display
        g.setColor(Color.gray);
        g.fillRect(5,5,200,32);
        g.setColor(Color.green);
        g.fillRect(5,5,hpPlayer1*2,32);
        g.setColor(Color.black);
        g.drawRect(5,5,200,32);

        //hp Player 2 display
        g.setColor(Color.gray);
        g.fillRect(780,5,200,32);
        g.setColor(Color.green);
        g.fillRect(780,5,hpPlayer2*2,32);
        g.setColor(Color.black);
        g.drawRect(780,5,200,32);

        //ammo display player 1
        g.setColor(Color.white);
        g.drawString("Ammo: " + ammoPlayer1,5,50);

        /////////////////////////////////////
        g.dispose();
        bs.show();
    }
    //Loading level
    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx=0; xx<w; xx++) {
            for (int yy=0; yy<h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                // wall
                if (red == 255 && green == 0 && blue == 0)
                    handler.addObject(new Block(xx*32,yy*32, ID.Block, ss));
                // player 1
                if (red == 0 && green == 0 && blue == 255)
                    handler.addObject(new Tank(xx*32,yy*32, ID.Player, handler,this, ss));
                // player 2
                if (red == 255 && green == 255 && blue == 0)
                    handler.addObject(new Tank(xx*32,yy*32, ID.Player2, handler,this, ss));
                // enemy
                if (red == 0 && green == 255 && blue == 0)
                    handler.addObject(new Enemy(xx*32,yy*32, ID.Enemy, handler, ss));
                // crate
                if (red == 0 && green == 255 && blue == 255)
                    handler.addObject(new Crate(xx*32,yy*32, ID.Crate, ss));
            }
        }

    }

    public static void main(String args[]) {
        new Game();
    }


}