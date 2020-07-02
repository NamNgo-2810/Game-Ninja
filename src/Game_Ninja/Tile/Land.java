package Game_Ninja.Tile;

import Game_Ninja.Game;
import Game_Ninja.Handler;
import Game_Ninja.ID;

import java.awt.*;

public class Land extends Tile { //Nền cỏ
    public Land(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(Game.land.getBufferedImage(), x, y, width, height, null);
    }


    public void tick() {

    }
}
