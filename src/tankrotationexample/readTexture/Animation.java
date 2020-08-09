package tankrotationexample.readTexture;


import java.awt.image.BufferedImage;

public class Animation {

    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;
    private boolean show = true;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > speed) {
            index++;
            timer = 0;
            if (index == frames.length - 1)
                this.show = false;
        }
    }

    public boolean isShown() {
        return this.show;
    }

    public BufferedImage getCurrentFrame() {
        if (this.show) {
            tick();
            return frames[index];
        }
        return null;
    }
}
