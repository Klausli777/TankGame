package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Movable extends GameObject {

    public Movable(Handler handler, int x, int y, int width, int height) {
        super(handler, x, y, width, height);
    }

    public void hurt(int x) {
    }

    public void died() {
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setLocation(int x, int y) {
        this.hitBox.setLocation(x, y);
    }


}
