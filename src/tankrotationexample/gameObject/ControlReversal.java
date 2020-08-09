package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;
import tankrotationexample.readTexture.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ControlReversal extends PowerUp {
    private BufferedImage controlReversal;
    protected final int state = 5;
    public ControlReversal(Handler handler, int x, int y) {
        super(handler, x, y, Assets.controlReversal.getWidth(), Assets.controlReversal.getHeight());
        this.controlReversal = Assets.controlReversal;
    }
    @Override
    public void drawImage(Graphics g) {
        if (state == 5) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(this.controlReversal, x, y, null);
        }
    }
}
