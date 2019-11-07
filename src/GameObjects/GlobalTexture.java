package GameObjects;

import Game.Game;

import java.awt.image.BufferedImage;

public class GlobalTexture {

    public BufferedImage[] player = new BufferedImage[4];
    public BufferedImage[] playerLeft = new BufferedImage[4];
    public BufferedImage[] playerRight = new BufferedImage[4];
    public BufferedImage[] playerDown = new BufferedImage[4];
    public BufferedImage[] playerUpRight = new BufferedImage[1];
    public BufferedImage[] playerUpLeft = new BufferedImage[1];
    public BufferedImage[] playerDownLeft = new BufferedImage[1];
    public BufferedImage[] playerDownRight = new BufferedImage[1];

    public BufferedImage playbutton1, playbutton2, quitbutton, brickWall, breakWall , ammoCrate;

    private SpriteSheetButton button;
    private SpriteSheet spriteSheet1;
    private SpriteSheet wallSpriteSheet;
    private SpriteSheet breakableWall;
    private SpriteSheet ammoUp;

    public GlobalTexture(Game game) {
        button = new SpriteSheetButton(game.getButtons());
        spriteSheet1 = new SpriteSheet(game.getSpriteSheet());
        wallSpriteSheet = new SpriteSheet(game.getWalls());
        breakableWall = new SpriteSheet(game.getBreakableWalls());
        ammoUp = new SpriteSheet(game.getAmmo());
        getSprites();
    }

    public void getSprites() {

        playbutton1 = button.getSprite(1, 1, 200, 50);
        playbutton2 = button.getSprite(1, 2, 200, 50);
        quitbutton = button.getSprite(2, 1, 200, 50);

        player[0] = spriteSheet1.getSprite(1, 1, 64, 64);
        player[1] = spriteSheet1.getSprite(2, 1, 64, 64);
        player[2] = spriteSheet1.getSprite(3, 1, 64, 64);
        player[3] = spriteSheet1.getSprite(4, 1, 64, 64);

        playerLeft[0] = spriteSheet1.getSprite(1, 4, 64, 64);
        playerLeft[1] = spriteSheet1.getSprite(2, 4, 64, 64);
        playerLeft[2] = spriteSheet1.getSprite(3, 4, 64, 64);
        playerLeft[3] = spriteSheet1.getSprite(4, 4, 64, 64);

        playerRight[0] = spriteSheet1.getSprite(1, 2, 64, 64);
        playerRight[1] = spriteSheet1.getSprite(2, 2, 64, 64);
        playerRight[2] = spriteSheet1.getSprite(3, 2, 64, 64);
        playerRight[3] = spriteSheet1.getSprite(4, 2, 64, 64);

        playerDown[0] = spriteSheet1.getSprite(1, 3, 64, 64);
        playerDown[1] = spriteSheet1.getSprite(2, 3, 64, 64);
        playerDown[2] = spriteSheet1.getSprite(3, 3, 64, 64);
        playerDown[3] = spriteSheet1.getSprite(4, 3, 64, 64);

        playerUpLeft[0] = spriteSheet1.getSprite(4, 5, 64, 64);
        playerUpRight[0] = spriteSheet1.getSprite(4, 6, 64, 64);
        playerDownRight[0] = spriteSheet1.getSprite(4, 7, 64, 64);
        playerDownLeft[0] = spriteSheet1.getSprite(4, 8, 64, 64);

        brickWall = wallSpriteSheet.getSprite(1, 1, 32, 32);
        breakWall = breakableWall.getSprite(1, 1, 32, 32);

        ammoCrate = ammoUp.getSprite(1, 1, 32, 32);

    }
}