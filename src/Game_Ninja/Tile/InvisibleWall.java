package Game_Ninja.Tile;

import Game_Ninja.Game;
import Game_Ninja.Handler;
import Game_Ninja.ID;

import java.awt.*;

public class InvisibleWall extends Tile { //Khi các nhân vật kẻ thù đi hết đoạn đường (được đặt trên không), sẽ va chạm với InvisibleWall để không bị rơi, có thể quay về hướng ngược lại đi tiếp

    public InvisibleWall(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(Game.invisible_wall.getBufferedImage(), x, y, width, height, null);
    }

    public void tick() {}
}
