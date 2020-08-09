package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;
import tankrotationexample.readTexture.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnBreakableWall extends Wall {
    private int state = 9;
    BufferedImage wallImage;

    public UnBreakableWall(Handler handler, int x, int y) {
        super(handler, x, y, Assets.unbreakableWall.getWidth(), Assets.unbreakableWall.getHeight());
        this.x = x;
        this.y = y;
        this.wallImage = Assets.unbreakableWall;
    }

    @Override
    public void drawImage(Graphics g) {
        if (state == 9) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(this.wallImage, x, y, null);
        }
    }
}
