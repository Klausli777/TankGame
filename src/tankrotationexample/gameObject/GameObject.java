package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;

import java.awt.*;


public abstract class GameObject {
    protected Handler handler;
    protected int x, y, width, height, hp;
    protected Rectangle hitBox;

    public GameObject(Handler handler, int x, int y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hp = 4;
        this.hitBox = new Rectangle(x, y, width, height);
    }

    public abstract int getX();

    public abstract int getY();

    public abstract void update();

    public abstract void drawImage(Graphics g);

    public abstract Rectangle getHitBox();

    public abstract void died();

    public abstract void hurt(int x);

}
