package GameObjects;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) handler.setUp(true);
                if (key == KeyEvent.VK_S) handler.setDown(true);
                if (key == KeyEvent.VK_A) handler.setLeft(true);
                if (key == KeyEvent.VK_D) handler.setRight(true);
                if (key == KeyEvent.VK_SPACE) handler.setShoot(true);
            } else if (tempObject.getId() == ID.Player2) {
                if (key == KeyEvent.VK_UP) handler.setUp2(true);
                if (key == KeyEvent.VK_DOWN) handler.setDown2(true);
                if (key == KeyEvent.VK_LEFT) handler.setLeft2(true);
                if (key == KeyEvent.VK_RIGHT) handler.setRight2(true);
                if (key == KeyEvent.VK_ENTER) handler.setShoot2(true);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) handler.setUp(false);
                if (key == KeyEvent.VK_S) handler.setDown(false);
                if (key == KeyEvent.VK_A) handler.setLeft(false);
                if (key == KeyEvent.VK_D) handler.setRight(false);
                if (key == KeyEvent.VK_SPACE) handler.setShoot(false);
            } else if (tempObject.getId() == ID.Player2) {
                if (key == KeyEvent.VK_UP) handler.setUp2(false);
                if (key == KeyEvent.VK_DOWN) handler.setDown2(false);
                if (key == KeyEvent.VK_LEFT) handler.setLeft2(false);
                if (key == KeyEvent.VK_RIGHT) handler.setRight2(false);
                if (key == KeyEvent.VK_ENTER) handler.setShoot2(false);
            }
        }
    }
}
