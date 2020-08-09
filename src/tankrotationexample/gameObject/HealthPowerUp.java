package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;
import tankrotationexample.readTexture.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthPowerUp extends PowerUp {
    private BufferedImage healthPowerUp;
    protected final int state = 3;
    public HealthPowerUp(Handler handler, int x, int y) {
        super(handler, x, y, Assets.healthUp.getWidth(), Assets.healthUp.getHeight());
        this.healthPowerUp = Assets.healthUp;
    }
    @Override
    public void drawImage(Graphics g) {
        if (state == 3) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(this.healthPowerUp, x, y, null);
        }
    }
}
