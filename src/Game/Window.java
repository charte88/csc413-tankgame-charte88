package Game;

import Game.Game;

import javax.swing.JFrame;
import java.awt.Dimension;

public class Window {
    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        // size of frame
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.add(game); //add in game class to frame
        frame.setResizable(false); //cannot resize window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //when game started box will start center of window
        frame.setVisible(true); //lets us see window
    }
}