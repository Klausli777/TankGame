package tankrotationexample.gameObject;

import tankrotationexample.handler.Handler;
import tankrotationexample.readTexture.Animation;
import tankrotationexample.readTexture.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends Movable {
    private int vx, vy, angle;
    private final int R = 4;
    private Animation bulletExpolsion;
    private boolean active = true;
    private BufferedImage bullet;

    public Bullet(Handler handler, int x, int y, int width, int height, int angle) {
        super(handler, x, y,width, height);
        this.bullet = Assets.bullet;
        this.angle = angle;
        bulletExpolsion = new Animation(100, Assets.explosions);
    }

    public void update() {
        if (isActive()) {
            moveForwards();
        }
    }

    public void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        this.setLocation(x, y);
    }

    public BufferedImage getAnimation() {
        return bulletExpolsion.getCurrentFrame();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean check) {
        this.active = check;
    }

    public void isNotActive() {
        this.active = false;
    }

    public boolean animationDone() {
        return this.bulletExpolsion.isShown();
    }

    @Override
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), bullet.getWidth() / 2.0, bullet.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        if (this.active && animationDone()) {
            g2d.drawImage(bullet, rotation, null);
        } else {
            this.getHitBox().width = 0;
            this.getHitBox().height = 0;
            g2d.drawImage(this.getAnimation(), x, y, null);
        }
    }
}
