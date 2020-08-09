package tankrotationexample.handler;

import tankrotationexample.GameConstants;
import tankrotationexample.gameObject.Tank;

public class GameCamera {
    private Tank tank;
    private int xOffset, yOffset;

    public GameCamera(Tank tank, int xOffset, int yOffset) {
        this.tank = tank;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * let tank to follow the center on object
     */

    public void centerOnObject() {
        xOffset = this.tank.getX() - GameConstants.GAME_SCREEN_WIDTH / 4 + this.tank.getWidth();
        yOffset = this.tank.getY() - GameConstants.GAME_SCREEN_HEIGHT / 4 + this.tank.getHeight();
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > GameConstants.GAME_SCREEN_WIDTH / 2 + this.tank.getWidth() / 2) { // 2000/2 1280 /2
            xOffset = GameConstants.GAME_SCREEN_WIDTH / 2 + this.tank.getWidth() / 2;
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > GameConstants.GAME_SCREEN_HEIGHT / 2) {//2000/2
            yOffset = GameConstants.GAME_SCREEN_HEIGHT / 2;
        }
    }

    public int getYOffset() {
        return yOffset;
    }

    public int getXOffset() {
        return xOffset;
    }

}
