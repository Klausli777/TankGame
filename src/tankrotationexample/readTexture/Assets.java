package tankrotationexample.readTexture;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 32, height = 32;

    public static BufferedImage player1, player2,
            healthUp, speedUp, controlReversal,
            unbreakableWall, breakableWall, bullet, grass;
    public static BufferedImage[] healthBar, explosions, lives, speeds;

    public static void init() {
        SpriteSheet texture = new SpriteSheet(ImageLoader.loadImage("/texture/General-Sprites.png"));
        breakableWall = texture.crop(width * 16, 0, width, height);
        unbreakableWall = texture.crop(width * 16, height, width, height);
        healthUp = texture.crop(width * 16, height * 7, width, height);
        speedUp = texture.crop(width * 17, height * 7, width, height);
        controlReversal = texture.crop(width * 19, height * 7, width, height);
        grass = texture.crop(width * 17, height * 2, width, height);
        SpriteSheet tanks = new SpriteSheet(ImageLoader.loadImage("/texture/tanksandbullet.png"));
        player1 = tanks.crop(0, height, width, height);
        player2 = tanks.crop(width, 0, width, height);
        bullet = tanks.crop(0, 0, width, height);
        healthBar = new BufferedImage[4];
        SpriteSheet hps = new SpriteSheet(ImageLoader.loadImage("/texture/hp.png"));
        healthBar[3] = hps.crop(0, 0, 60, 15);
        healthBar[2] = hps.crop(0, 15, 60, 16);
        healthBar[1] = hps.crop(0, 31, 60, 16);
        healthBar[0] = hps.crop(0, 47, 60, 16);
        explosions = new BufferedImage[6];
        SpriteSheet explosion = new SpriteSheet(ImageLoader.loadImage("/texture/explosion1_strip6.png"));
        explosions[0] = explosion.crop(0, 0, width, height);
        explosions[1] = explosion.crop(width, 0, width, height);
        explosions[2] = explosion.crop(width * 2, 0, width, height);
        explosions[3] = explosion.crop(width * 3, 0, width, height);
        explosions[4] = explosion.crop(width * 4, 0, width, height);
        explosions[5] = explosion.crop(width * 5, 0, width, height);
        lives = new BufferedImage[3];
        SpriteSheet live = new SpriteSheet(ImageLoader.loadImage("/texture/lives.png"));
        lives[2] = live.crop(0, 0, width * 3, 32);
        lives[0] = live.crop(0, 32, width * 3, 32);
        lives[1] = live.crop(0, 64, width * 3, 32);
        speeds = new BufferedImage[3];
        SpriteSheet speed = new SpriteSheet(ImageLoader.loadImage("/texture/speed.png"));
        speeds[0] = speed.crop(0, 0, width * 3, 32);
        speeds[2] = speed.crop(0, 32, width * 3, 32);
        speeds[1] = speed.crop(0, 64, width * 3, 32);
    }
}
