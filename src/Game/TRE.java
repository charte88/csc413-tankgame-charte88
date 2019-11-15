package Game;

import Display.Camera;
import GameObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class TRE extends JPanel {
    private static final int SCREEN_WIDTH = 1280;
    private static final int SCREEN_HEIGHT = 960;

    private BufferedImage world;
    private BufferedImage menuBack;
    private BufferedImage gameOverP1;
    private BufferedImage gameOverP2;
    private BufferedImage floor;
    private BufferedImage level;
    private BufferedImage wall;
    private BufferedImage breakable;
    private BufferedImage helpMenu;
    private BufferedImage creditsMenu;
    private BufferedImage overshield;
    private BufferedImage life;
    private BufferedImage ammo;

    private static Camera c1, c2;
    private ID id = ID.Menu;
    private Handler handler;
    private Menu menu;
    private Help help;
    private Credits credits;

    private static Graphics2D buffer;
    private JFrame jf;

    public static void main(String[] args) {
        TRE trex = new TRE();

        trex.init();
        trex.run();
    }

    private void init() {
        this.jf = new JFrame("DGD Wars");
        this.world = new BufferedImage((TRE.SCREEN_WIDTH * 2)+300, (TRE.SCREEN_HEIGHT * 2) + 100, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null, t2img = null, bubble = null, bullet = null;
        try {
            System.out.println(System.getProperty("user.dir"));

            t1img = read(new File("resources/DGDTank1.png"));
            t2img = read(new File("resources/DGDTank2.1.png"));
            bubble = read(new File("resources/Bubble.png"));
            floor = read(new File("resources/DGDBackground.png"));
            wall = read(new File("resources/DGDWall.png"));
            breakable = read(new File("resources/DGDWood.png"));
            level = read(new File("resources/Final_Map.png"));
            bullet = read(new File("resources/DGDBullet.png"));
            menuBack = read(new File("resources/DGDMenu.JPG"));
            gameOverP1 = read(new File("resources/DGDloserP1.png"));
            gameOverP2 = read(new File("resources/DGDloserP2.png"));
            helpMenu = read(new File("resources/DGDHelpMenu.png"));
            creditsMenu = read(new File("resources/DGDCredits.png"));
            overshield = read(new File("resources/Overshield.png"));
            life = read(new File("resources/ExtraLife.png"));
            ammo = read(new File("resources/DGDammo.png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        handler = new Handler(this);

        handler.addObject(new Tank(300,810, ID.Player, handler, t1img, bubble,0, bullet));
        handler.addObject(new Tank(2260,810, ID.Player2, handler, t2img, bubble,180, bullet));

        TankControl tc1 = new TankControl(handler.getTanks(ID.Player), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        TankControl tc2 = new TankControl(handler.getTanks(ID.Player2), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        c1 = new Camera(0, 0);
        c2 = new Camera(0, 0);

        loadMap();


        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        menu = new Menu(menuBack,this);
        help = new Help(helpMenu,this);
        credits = new Credits(creditsMenu,this);

        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);
        this.addMouseListener(menu);
        this.addMouseListener(help);
        this.jf.setSize(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);
    }

    private void run() {
        try {

            while (true) {
                tick();
                repaint(new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT));    //Repaints the whole screen every time

                ////// Checks to see if either Player has lost /////////
                if (handler.getTanks(ID.Player).isGameOver()) {
                    id = ID.GameOverP1;
                }
                if (handler.getTanks(ID.Player2).isGameOver()) {
                    id = ID.GameOverP2;
                }
                ////////////////////////////////////////////////////////

                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {}
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (ID.Menu == id) {
            g2.setColor(Color.black);
            g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            menu.drawImage(g);
        }
        if (ID.Help == id) {
            help.drawImage(g);
        }
        if (ID.Credits == id) {
            credits.drawImage(g);
        }
        if (ID.Start == id) {
            buffer = world.createGraphics();
            c1.tick(handler.getTanks(ID.Player));
            c2.tick(handler.getTanks(ID.Player2));
            try {
                super.paintComponent(g2);

                buffer.drawImage(floor,0,0,SCREEN_WIDTH * 2+300,SCREEN_HEIGHT * 2,null);
                handler.render(buffer);

                BufferedImage newWorld = world.getSubimage((int) c1.getX(), (int) c1.getY(), 640, SCREEN_HEIGHT);
                BufferedImage newWorld2 = world.getSubimage((int) c2.getX(), (int) c2.getY(), 640, SCREEN_HEIGHT);
                BufferedImage miniMap = world.getSubimage(0,0, world.getWidth()-240, world.getHeight()-360);

                g2.drawImage(newWorld,0,0,null);
                g2.drawImage(newWorld2,640,0,null);

                g2.scale(.1,.1);
                g2.drawImage(miniMap,5000,5250,null);
                g2.scale(1,1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (ID.GameOverP1 == id) {
            g2.drawImage(gameOverP1,0,0,1280,960,null);
        }
        if (ID.GameOverP2 == id) {
            g2.drawImage(gameOverP2,0,0,1280,960,null);
        }
    }

    private void loadMap() {
        int w = level.getWidth();
        int h = level.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = level.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                if (red == 255) {
                    handler.addObject(new Block(xx * 32,yy * 32, ID.Block, wall));
                }
                if (red == 200) {
                    handler.addObject(new BreakableBlock(xx * 32,yy * 32, ID.BreakableBlock, handler, breakable));
                }
                if (red == 175) {
                    handler.addObject(new Crate(xx * 32,yy * 32, ID.Crate, ammo));
                }
                if (red == 150) {
                    handler.addObject(new Overshield(xx * 32,yy * 32, ID.Overshield, overshield));
                }
                if(red == 225) {
                    handler.addObject(new ExtraLife(xx*32,yy*32, ID.ExtraLife, life));
                }
            }
        }
    }

    public void tick() {
        handler.tick();
    }

    public void setId(ID id) {
        this.id = id;
    }
}