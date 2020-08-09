package tankrotationexample.handler;

import tankrotationexample.gameObject.*;

public class CollisionDetector {
    private Handler handler;

    public CollisionDetector(Handler handler) {
        this.handler = handler;
    }

    public void init() {
        checkEntitiesCollision(this.handler.getPlayer1());
        checkEntitiesCollision(this.handler.getPlayer2());
        playerVsPlayer(this.handler.getPlayer1(), this.handler.getPlayer2());
        BulletVsEverything(this.handler.getPlayer1());
        BulletVsEverything(this.handler.getPlayer2());
    }

    public void checkEntitiesCollision(Tank player) {
        for (int x = 0; x < this.handler.getGameObjects().size(); x++) {
            if (this.handler.getGameObjects().get(x) instanceof Tank)
                continue;
            if (player.getHitBox().intersects(this.handler.getGameObjects().get(x).getHitBox())) {
                if (this.handler.getGameObjects().get(x) instanceof SpeedBoostPowerUp) {
                    System.out.println("SpeedBoostPowerUp");
                    player.addSpeed();
                    this.handler.getGameObjects().remove(x);
                } else if (this.handler.getGameObjects().get(x) instanceof HealthPowerUp) {
                    System.out.println("HealthPowerUp");
                    player.addLives();
                    this.handler.getGameObjects().remove(x);
                } else if (this.handler.getGameObjects().get(x) instanceof ControlReversal) {
                    System.out.println("ControlReversal");
                    this.handler.getGameObjects().remove(x);
                    player.controlReversal();
                }
                tankMove(player);
            }
        }
    }
    public void playerVsPlayer(Tank player1, Tank player2) {
        if (player1.getHitBox().intersects(player2.getHitBox())) {
            player1.reBirth();
            player1.minLives();
            player2.reBirth();
            player2.minLives();
        }
    }

    public void BulletVsEverything(Tank player) {
        if (player.getBullet() != null) {
            for (int x = 0; x < this.handler.getGameObjects().size(); x++) {
                if (player.getBullet().getHitBox().intersects(player.getHitBox())) {
                    player.getBullet().update();
                }
                if (player.getBullet().getHitBox().intersects(this.handler.getGameObjects().get(x).getHitBox())) {
                    if (this.handler.getGameObjects().get(x) instanceof PowerUp) continue;
                        player.getBullet().isNotActive();
                    this.handler.getGameObjects().get(x).hurt(player.getDamage());
                    if (this.handler.getGameObjects().get(x) instanceof BreakableWall) {
                        this.handler.getGameObjects().remove(x);
                    }
                }
            }
            if (player.getBullet().animationDone()) {
                player.isIntersection();
            }
        }
    }
    protected void tankMove(Tank player) {
        if (player.getReversal()) {
            if (player.isUpPressed()) {
                player.setX(player.getX() - player.getVx());
                player.setY(player.getY() - player.getVy());
            } else if (player.isDownPressed()) {
                player.setX(player.getX() + player.getVx());
                player.setY(player.getY() + player.getVy());
            }
        } else {
            if (player.isDownPressed()) {
                player.setX(player.getX() - player.getVx());
                player.setY(player.getY() - player.getVy());
            } else if (player.isUpPressed()) {
                player.setX(player.getX() + player.getVx());
                player.setY(player.getY() + player.getVy());
            }
        }
    }
}
