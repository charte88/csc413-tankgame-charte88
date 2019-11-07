package GameObjects;

import Game.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    Game game;

    public MouseInput(Game game) {
        this.game = game;
    }

    public void mouseClicked(MouseEvent e) {
        game.mouseClicked(e);
    }
}