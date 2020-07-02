package Game_Ninja.Tile;

import Game_Ninja.Entity.Entity;
import Game_Ninja.Game;
import Game_Ninja.Handler;
import Game_Ninja.ID;

import java.awt.*;

public class Coins extends Tile { //Tiền xu tích điểm

    public Coins(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }


    public void render(Graphics g) {
        g.drawImage(Game.coin.getBufferedImage(), x, y, width, height, null);
    }


    public void tick() { //Khi va chạm với người chơi sẽ biến mất, điểm được cộng thêm vào trường "Score"
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity en = handler.entity.get(i);
            if (getBounds().intersects(en.getBounds())) {
                if (en.get_id()==ID.player){
                    Entity.score += 1;
                    this.die();
                }
            }
        }
    }
}
