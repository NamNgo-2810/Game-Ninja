package Game_Ninja.Entity.Enemy;

import Game_Ninja.Entity.Entity;
import Game_Ninja.Game;
import Game_Ninja.Handler;
import Game_Ninja.ID;
import Game_Ninja.Tile.Tile;

import java.awt.*;
import java.util.Random;

public class Samurai extends Entity { //Tương tự như class Player, lược bỏ một số chức năng do là nhân vật máy điều khiển trong game
    private int frame = 0;
    private int frameDelay = 0;
    private int attack = 0;

    private boolean animate = false;
    public Samurai(int x, int y, int width, int height, ID id, Handler handler) {
        super(x, y, width, height, id, handler);
        Random random = new Random();
        int dir = random.nextInt(2);
        switch (dir) {
            case 0:
                setVelX(-1);
                facing = 0;
                break;
            case 1:
                setVelX(1);
                facing = 1;
                break;
        }
    }
    public void render(Graphics g) {
        if (facing == 0) {
            if (!slash) {
                g.drawImage(Game.enemy_move_left[frame].getBufferedImage(),x, y, width, height,null);
                setVelX(-2);
            }
            else {
                if (attack == 1) g.drawImage(Game.enemy_attack_left[attack].getBufferedImage(), x, y-10, 64, 128, null);
                else g.drawImage(Game.enemy_attack_left[attack].getBufferedImage(), x, y, width, height, null);
                setVelX(0);
            }
        }
        else {
            if (!slash) {
                g.drawImage(Game.enemy_move_right[frame].getBufferedImage(), x, y, width, height,null);
                setVelX(2);
            }
            else {
                if (attack == 1) g.drawImage(Game.enemy_attack_right[attack].getBufferedImage(), x, y-10, 64, 128, null);
                else g.drawImage(Game.enemy_attack_right[attack].getBufferedImage(), x, y, width, height, null);
                setVelX(0);
            }
        }
    }

    public void tick() {
        x += velX;
        y += velY;
        if (velX != 0) animate = true;
        for (int i = 0; i < handler.tile.size(); i++) {
            Tile t = handler.tile.get(i);
            if (t.isSolid()) {

                if (getBoundsBottom().intersects(t.getBounds())) {
                    setVelY(0);
                    if (falling) falling = false;
                }
                if (getBoundsLeft().intersects(t.getBounds())) {
                        setVelX(2);
                        facing = 1;
                }
                if (getBoundsRight().intersects(t.getBounds())) {
                    setVelX(-2);
                    facing = 0;
                }

            }
        }

        if (slash) {
            frameDelay++;
            if (frameDelay > 12) {
                attack++;
                if (attack > 2) {
                    attack = 0;
                }
                frameDelay = 0;
            }
            for (int i = 0; i < handler.entity.size(); i++) {
                Entity e = handler.entity.get(i);
                if (e.get_id()==ID.player) {
                    frameDelay++;
                    if (frameDelay > 10) health -= 1;
                    if (health == 0) {
                        if (lives == 0) {
                            e.die();
                            Game.showDeathScreen = true;
                        }
                        else {
                            health = 100;
                            lives -= 1;
                        }
                    }
                } else slash = false;
            }
        }
        else if (animate){
            frameDelay++;
            if (frameDelay >= 12) {
                frame++;
                if (frame > 2) {
                    frame = 0;
                }
                frameDelay = 0;
            }
        }
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity e = handler.entity.get(i);
            if (e.get_id()==ID.player) {
                slash = (getBounds().intersects(e.getBoundsRight()) && facing == 0) || (getBounds().intersects(e.getBoundsLeft())) && facing == 1;
            }
        }
    }
}


