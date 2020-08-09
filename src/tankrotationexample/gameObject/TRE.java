/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankrotationexample.gameObject;


import tankrotationexample.Launcher;
import tankrotationexample.handler.Handler;
import tankrotationexample.readTexture.Assets;
import javax.swing.*;
import java.awt.*;


public class TRE extends JPanel implements Runnable {

    private Launcher lf;
    private long tick = 0;
    static long tickCount = 0;
    private Handler handler;

    public TRE(Launcher lf) {
        this.lf = lf;
    }

    @Override
    public void run() {
        try {
            this.resetGame();
            while (true) {
                this.tickCount++;
                this.tick++;
                this.handler.getGameObjects().forEach(GameObject::update);
                this.repaint();   // redraw game
                this.handler.getCD().init();
                this.handler.getSoundPlayer().play();
                Thread.sleep(1000 / 144); //sleep for a few milliseconds
                /*
                 * simulate an end game event
                 * we will do this with by ending the game when drawn 2000 frames have been drawn
                 */
                if (this.handler.gameOver()) {
                    this.lf.setFrame("end");
                    return;
                }//超过一定数字以后 就会 自己关掉 这个 tick 就是 数字
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    /**
     * Reset game to its initial state.
     */
    public void resetGame() {
        this.tick = 0;
        this.handler.reset();
    }


    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {
        Assets.init();
        handler = new Handler(lf);
        handler.init();
    }

    @Override
    public void paintComponent(Graphics g) {
        this.handler.draw(g);
    }
}
