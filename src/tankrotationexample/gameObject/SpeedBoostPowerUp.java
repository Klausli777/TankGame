package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;
import tankrotationexample.readTexture.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpeedBoostPowerUp extends PowerUp {
    private BufferedImage speedUp;
    protected int state = 4;
    public SpeedBoostPowerUp(Handler handler, int x, int y) {
        super(handler, x, y,Assets.speedUp.getWidth() , Assets.speedUp.getHeight());
        this.speedUp = Assets.speedUp;
    }
    @Override
    public void drawImage(Graphics g) {
        if (state == 4) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(this.speedUp, x, y, null);
        }
    }
}
