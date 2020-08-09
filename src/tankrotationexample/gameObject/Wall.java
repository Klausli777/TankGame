package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;

import java.awt.*;

public abstract class Wall extends Stationary {
    public Wall(Handler handler, int x, int y, int width, int height) {
        super(handler, x, y, width, height);
    }
}
