package Game_Ninja.Entity;

import java.awt.*;

import Game_Ninja.Game;
import Game_Ninja.Handler;
import Game_Ninja.ID;
import Game_Ninja.Tile.Tile;

public class Player extends Entity {

    private int frame = 0;
    private int attack = 0;
    private int frameDelay = 0; //Biến frameDelay giúp cho hình ảnh hoạt họa được render liên tiếp nhau với độ trễ hợp lý, người chơi nhìn rõ được chuyển động(motion) của nhân vật

    public Player(int x, int y, int width, int height, ID id, Handler handler) {
        super(x, y, width, height, id, handler);
    }

    public void render(Graphics g) { //Hàm xử lý đồ họa, được override từ class trừu tượng Entity
        if (facing == 0) {
            if (!jumping) {
                if (!slash) {
                    if (forward)
                        g.drawImage(Game.player_move_right[frame].getBufferedImage(), x, y, width, height, null);
                    else g.drawImage(Game.player_right.getBufferedImage(), x, y - 2, width, height, null);
                }
                else g.drawImage(Game.player_attack_right[attack].getBufferedImage(), x, y - 20, width + 20, height + 20, null);
            }
            else g.drawImage(Game.player_jump_right.getBufferedImage(), x, y, width, height, null);
        } else if (facing == 1) {
            if(!jumping) {
                if (!slash) {
                    if (backward) g.drawImage(Game.player_move_left[frame].getBufferedImage(), x, y, width, height, null);
                    else g.drawImage(Game.player_left.getBufferedImage(), x, y-2, width, height, null);
                } else g.drawImage(Game.player_attack_left[attack].getBufferedImage(), x, y-20, width+20, height+20, null);

            }
            else g.drawImage(Game.player_jump_left.getBufferedImage(), x, y, width, height, null);
        }
    }

    public void tick() { //Hàm xử lý hoạt động, được override từ class trừu tượng Entity
        x += velX;
        y += velY;
        boolean animate;
        animate = velX != 0;
        for (int i = 0; i < handler.tile.size(); i++) {
            Tile t = handler.tile.get(i);
            if (t.isSolid() && t.get_id() != ID.invisible_wall) {
                if (getBoundsTop().intersects(t.getBounds())) {
                    setVelY(0);
                    if (jumping) {
                        jumping = false;
                        gravity = 0.0;
                        falling = true;
                    }
                }
                if (getBoundsBottom().intersects(t.getBounds())) {
                    setVelY(0);
                    if (falling)
                        falling = false;
                 else {
                    if (!jumping) {
                        gravity = 0.8;
                        falling = true;
                    }
                }
            }

                if (getBoundsLeft().intersects(t.getBounds())) {
                    setVelX(0);
                    x = t.getX() + 59;
                }
                if (getBoundsRight().intersects(t.getBounds())) {
                    setVelX(0);
                    x = t.getX() -  59;
                }
            }
        }

        if (forward) { //Đi sang phải
            limit -= 2;
            setVelX(limit);
            if (limit <= 0) {
                forward = false;
            }
        }
        if (backward) { //Đi sang trái
            limit -= 2;
            setVelX(-limit);
            if (limit <= 0) {
                backward = false;
            }
        }
        if (jumping) { //Nhảy
            gravity -= 0.15;
            setVelY((int) -gravity);
            if (gravity <= 0.0) {
                jumping = false;
                falling = true;
            }
        }
        if (falling) { //Rơi (theo trọng lực)
            gravity += 0.1;
            setVelY((int) gravity);
        }
        if (slash) { //Tấn công
            kungfu--;
            attack += 5;
            if (attack >= 1) {
                attack = 0;
            }
            if (kungfu <= 0) {
                slash = false;
            }
            for (int i = 0; i < handler.entity.size(); i++) {
                Entity e = handler.entity.get(i);
                if (e.get_id() == ID.enemy) {
                    if (getBounds().intersects(e.getBoundsRight())|| getBounds().intersects(e.getBoundsLeft())) {
                            e.die();
                            score += 5;
                    }
                }
            }
        }



        if (animate) {
            frameDelay++;
            if (frameDelay >= 6) {
                frame++;
                    if (frame >= 6) {
                        frame = 0;
                    }
                    frameDelay = 0;
                }
            }
        }
    }


