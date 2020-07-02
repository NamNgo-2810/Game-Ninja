package Game_Ninja;

import Game_Ninja.Entity.Entity;

public class Camera { //Đóng vai trò tập trung khung hình vào nhân vật người chơi
    public int x, y;

    public void tick(Entity player) { //Luôn đặt tọa độ người chơi vào giữa khung hình
        setX(-player.getX() + Game.getFrameWidth()/2);
        setY(-player.getY() + 3*Game.getFrameHeight()/4);
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
