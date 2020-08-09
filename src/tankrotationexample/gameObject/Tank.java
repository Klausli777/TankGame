package tankrotationexample.gameObject;


import tankrotationexample.handler.Handler;
import tankrotationexample.readTexture.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tank extends Movable {


    private int vx, vy, damage, lives, hp, speed;
    private float angle;
    private final float ROTATIONSPEED = 3.0f;
    private BufferedImage tank;
    private boolean UpPressed, DownPressed, RightPressed, LeftPressed, ShootPressed;
    private boolean dead = false;
    private Bullet bullet;
    private boolean reverse = true;
    private final int[] BIRTH;
    private boolean bulletIntersect = false;


    public Tank(Handler handler, BufferedImage tank, int x, int y, int vx, int vy, int angle) {
        super(handler, x, y, tank.getWidth(), tank.getHeight());
        this.tank = tank;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.lives = 3;
        this.hp = 4;
        this.damage = 1;
        this.speed = 1;
        this.BIRTH = new int[3];
        this.BIRTH[0] = x;
        this.BIRTH[1] = y;
        this.BIRTH[2] = angle;
    }



    @Override
    public void died() {
        this.dead = true;
    }

    @Override
    public void hurt(int x) {
        if (this.hp == 1 && lives >= 0) {
            this.minLives();
            this.hp = 4;
        } else {
            this.hp -= x;
        }
    }

    public int getLives() {
        return lives;
    }

    public int getHp() {
        return hp;
    }

    public void minLives() {
        System.out.println(lives);
        if (this.lives == 1) {
            died();
        } else {
            this.lives--;
            this.hp = 4;
        }
    }

    public boolean isDead() {
        return this.dead;
    }

    public void addLives() {
        if (this.lives < 3) {
            this.lives++;
        }
    }

    public void addSpeed() {
        this.speed++;
    }

    public int getSpeed() {
        return speed;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void controlReversal() {
        this.reverse = !this.reverse;
    }

    public boolean getReversal() {
        return this.reverse;
    }

    public int getVx() {
        return this.vx;
    }

    public int getVy() {
        return this.vy;
    }

    public boolean isUpPressed() {
        return UpPressed;
    }

    public boolean isDownPressed() {
        return DownPressed;
    }

    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void toggleShootPressed() {
        this.ShootPressed = true;
    }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void unToggleShootPressed() {
        this.ShootPressed = false;
    }

    public void update() {
        if (this.reverse) {
            if (this.UpPressed) {
                this.moveForwards();
            }
            if (this.DownPressed) {
                this.moveBackwards();
            }
            if (this.LeftPressed) {
                this.rotateLeft();
            }
            if (this.RightPressed) {
                this.rotateRight();
            }
        } else {
            if (this.DownPressed) {
                this.moveForwards();
            }
            if (this.UpPressed) {
                this.moveBackwards();
            }
            if (this.RightPressed) {
                this.rotateLeft();
            }
            if (this.LeftPressed) {
                this.rotateRight();
            }
        }
        if (this.ShootPressed && !bulletIntersect) {
            this.bullet = new Bullet(handler, x, y, Assets.bullet.getWidth(), Assets.bullet.getHeight(), (int) angle);
            this.bullet.setActive(true);
            bullet.update();
        }
        if (this.bullet != null && bulletIntersect) {
            bullet.update();
        }
        if (this.bullet != null && !this.bullet.animationDone()) {
            bulletIntersect = false;
        }
    }

    public void isIntersection() {
        this.bulletIntersect = true;
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveForwards() {
        move();
        x += vx;
        y += vy;
        this.setLocation(x, y);
    }

    private void moveBackwards() {
        move();
        x -= vx;
        y -= vy;
        this.setLocation(x, y);
    }

    private void move() {
        vx = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
    }

    public int getDamage() {
        return this.damage;
    }

    public void reBirth() {
        this.x = this.BIRTH[0];
        this.y = this.BIRTH[1];
        this.angle = this.BIRTH[2];
        this.setLocation(this.BIRTH[0], this.BIRTH[1]);
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.tank.getWidth() / 2.0, this.tank.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.tank, rotation, null);
        if (this.bullet != null)
            this.bullet.drawImage(g);
    }
}
