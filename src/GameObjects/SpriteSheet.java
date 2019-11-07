package GameObjects;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getSprite(int col, int row, int width, int height) {
        BufferedImage sprite;
        sprite = image.getSubimage((col*64)-64, (row*64)-64, width, height);
        return sprite;
    }
}
