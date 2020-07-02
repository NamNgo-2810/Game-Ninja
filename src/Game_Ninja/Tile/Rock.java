package Game_Ninja.Tile;

import Game_Ninja.Game;
import Game_Ninja.Handler;
import Game_Ninja.ID;

import java.awt.*;

public class Rock extends Tile { //Phần đất lót dưới nền

    public Rock(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(Game.rock.getBufferedImage(), x, y, width, height, null);
    }

    public void tick() {

    }
}
