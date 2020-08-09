package tankrotationexample.handler;

import tankrotationexample.GameConstants;
import tankrotationexample.Launcher;
import tankrotationexample.gameObject.*;
import tankrotationexample.readBGM.SoundPlayer;
import tankrotationexample.readMap.ReadMap;
import tankrotationexample.readTexture.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Handler {
    protected Launcher lf;
    private BufferedImage world;
    private Tank player1, player2;
    private TankControl tc1, tc2;
    private ReadMap readMap;
    private GameCamera gameCamera1, gameCamera2;
    private CollisionDetector CD;
    private ArrayList<GameObject> gameObjects;
    private SoundPlayer soundPlayer;

    public Handler(Launcher lf) {
        this.lf = lf;
    }

    public void init() {
        this.world = new BufferedImage(GameConstants.WORLD_SCREEN_WIDTH,
                GameConstants.WORLD_SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        this.readMap = new ReadMap(this);
        readMap.init();
        this.gameObjects = this.readMap.getGameObjects();
        this.readMap.addEntities();
        this.setPlayer1();
        this.setPlayer2();
        CD = new CollisionDetector(this);
        soundInit();
    }

    protected void setPlayer1() {
        this.player1 = new Tank(this, Assets.player1, 232, 860, 0, 0, 0);
        this.gameCamera1 = new GameCamera(this.player1, 232, 860);
        this.gameCamera1.move(0, 0);
        this.tc1 = new TankControl(player1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        this.gameObjects.add(this.player1);
        this.lf.getJf().addKeyListener(this.tc1);
    }

    protected void setPlayer2() {
        this.player2 = new Tank(this, Assets.player2, 872, 860, 0, 0, 0);
        this.gameCamera2 = new GameCamera(this.player2, 872, 860);
        this.gameCamera2.move(0, 0);
        this.tc2 = new TankControl(player2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_NUMPAD0);
        this.gameObjects.add(this.player2);
        this.lf.getJf().addKeyListener(this.tc2);
    }

    public Tank getPlayer1() {
        return this.player1;
    }

    public Tank getPlayer2() {
        return this.player2;
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.readMap.getGameObjects();
    }

    public CollisionDetector getCD() {
        return this.CD;
    }

    protected void soundInit(){
        this.soundPlayer = new SoundPlayer("tankGameMusic.wav");
    }

    public SoundPlayer getSoundPlayer(){
        return this.soundPlayer;
    }

    public boolean gameOver() {
        return this.player1.isDead()||this.player2.isDead();
    }

    public void reset() {
        for (int x = 0; x < this.gameObjects.size(); x++) {
            this.gameObjects.remove(x);
        }
        this.init();
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        buffer.setColor(Color.WHITE);
        buffer.fillRect(0, 0, GameConstants.WORLD_SCREEN_WIDTH, GameConstants.WORLD_SCREEN_HEIGHT);
        this.gameObjects.forEach(gameObjects -> gameObjects.drawImage(buffer));
        this.gameCamera1.centerOnObject();
        this.gameCamera2.centerOnObject();
        BufferedImage leftHalf = world.getSubimage(this.gameCamera1.getXOffset(), this.gameCamera1.getYOffset(),
                GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT / 2);
        BufferedImage rightHalf = world.getSubimage(this.gameCamera2.getXOffset(), this.gameCamera2.getYOffset(),
                GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT / 2);
        BufferedImage miniMap = world.getSubimage(0, 0, GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);
        g2.drawImage(leftHalf, 0, 0, GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT / 2, null);
        g2.drawImage(rightHalf, GameConstants.GAME_SCREEN_WIDTH / 2, 0, GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT / 2, null);
        g2.scale(.20, .20);
        g2.drawImage(miniMap, GameConstants.WORLD_SCREEN_WIDTH * 5 / 4, GameConstants.WORLD_SCREEN_HEIGHT, null);
        g2.setColor(Color.white);
        g2.fillRect(GameConstants.GAME_SCREEN_WIDTH / 2 , GameConstants.GAME_SCREEN_HEIGHT * 3-100, 1500, 800);
        g2.fillRect(GameConstants.GAME_SCREEN_WIDTH * 9 / 3 , GameConstants.GAME_SCREEN_HEIGHT * 3-100, 1500, 800);
        g2.setColor(Color.black);
        g2.setFont(new Font("Courier New", Font.BOLD, 100));
        //player one info
        g2.drawString("Player One", GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT * 3);
        g2.drawString("HP     : ", GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT * 3 + 200);
        g2.drawImage(Assets.healthBar[this.getPlayer1().getHp() - 1], GameConstants.GAME_SCREEN_WIDTH / 2 + 700, GameConstants.GAME_SCREEN_HEIGHT * 3 +100, 700, 200, null);
        g2.drawString("Live   : ", GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT * 3 + 400);
        g2.drawImage(Assets.lives[this.getPlayer1().getLives() - 1], GameConstants.GAME_SCREEN_WIDTH / 2 + 700, GameConstants.GAME_SCREEN_HEIGHT * 3 + 300, 600, 200, null);
        g2.drawString("Speed  : ", GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT * 3 + 600);
        g2.drawImage(Assets.speeds[this.getPlayer1().getSpeed()-1], GameConstants.GAME_SCREEN_WIDTH / 2 + 700, GameConstants.GAME_SCREEN_HEIGHT * 3 + 450, 600, 200, null);

        //player two info
        g2.drawString("Player Two", GameConstants.GAME_SCREEN_WIDTH * 9 / 3, GameConstants.GAME_SCREEN_HEIGHT * 3);
        g2.drawString("HP     : ", GameConstants.GAME_SCREEN_WIDTH * 9 / 3, GameConstants.GAME_SCREEN_HEIGHT * 3 + 200);
        g2.drawImage(Assets.healthBar[this.getPlayer2().getHp() - 1], GameConstants.GAME_SCREEN_WIDTH * 9 / 3 + 700, GameConstants.GAME_SCREEN_HEIGHT * 3+100, 700, 200, null);
        g2.drawString("Live   : ", GameConstants.GAME_SCREEN_WIDTH * 9 / 3, GameConstants.GAME_SCREEN_HEIGHT * 3 + 400);
        g2.drawImage(Assets.lives[this.getPlayer2().getLives() - 1], GameConstants.GAME_SCREEN_WIDTH * 9 / 3 + 700, GameConstants.GAME_SCREEN_HEIGHT * 3 + 300, 600, 200, null);
        g2.drawString("Speed  : ", GameConstants.GAME_SCREEN_WIDTH * 9 / 3, GameConstants.GAME_SCREEN_HEIGHT * 3 + 600);
        g2.drawImage(Assets.speeds[this.getPlayer2().getSpeed() - 1], GameConstants.GAME_SCREEN_WIDTH * 9 / 3 + 700, GameConstants.GAME_SCREEN_HEIGHT * 3 + 450, 600, 200, null);

    }

}

