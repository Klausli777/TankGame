package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;

import java.awt.*;

public abstract class PowerUp extends Stationary {
    public PowerUp(Handler handler, int x, int y, int width, int height) {
        super(handler, x, y, width, height);
        this.hitBox = new Rectangle(x, y, width, height);
    }

}
