package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;
import tankrotationexample.readTexture.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {
    protected int state = 2;
    BufferedImage wallImage;
    public BreakableWall(Handler handler, int x, int y) {
        super(handler, x, y, Assets.breakableWall.getWidth(), Assets.breakableWall.getHeight());
        this.x = x;
        this.y = y;
        this.wallImage = Assets.breakableWall;
     }

    @Override
    public void drawImage(Graphics g) {
        if (state == 2) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(this.wallImage, x, y, null);
        }
    }
}
