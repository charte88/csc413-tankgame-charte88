package GameObjects;

import java.awt.image.BufferedImage;

public class SpriteSheetButton {

    private BufferedImage images;

    public SpriteSheetButton(BufferedImage image) {
        this.images = image;
    }

    public BufferedImage getSprite(int col, int row, int width, int height) {
        BufferedImage sprite = images.getSubimage((col*200)- 200, (row*50) - 50 , width, height);
        return sprite;
    }
}
