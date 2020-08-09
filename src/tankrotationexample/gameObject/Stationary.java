package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;

import java.awt.*;


public abstract class Stationary extends GameObject {
    public Stationary(Handler handler, int x, int y, int width, int height){
        super(handler,x,y,width,height);
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public void update() {}
    public void died() {}
    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }
    public void hurt(int x) {}

}
