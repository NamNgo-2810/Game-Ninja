package Game_Ninja.Tile;

import Game_Ninja.Entity.Entity;
import Game_Ninja.Game;
import Game_Ninja.Handler;
import Game_Ninja.ID;

import java.awt.*;

public class Lives extends Tile { //Mạng

    public Lives(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    public void render(Graphics g) {
        g.drawImage(Game.lives.getBufferedImage(), x, y, width, height, null);
    }

    public void tick() { /*Khi người chơi va chạm với biểu tượng trái tim, đối tượng biến mất, trường "Lives" được cộng
         thêm một điểm, nếu lượng máu về 0 thì Lives trừ 1, nếu Lives về 0 thì nhân vật người chơi "chết"*/
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity en = handler.entity.get(i);
            if (getBounds().intersects(en.getBounds())) {
                if (en.get_id()==ID.player){
                    Entity.health += 20;
                    if (Entity.health > 100) {
                        Entity.lives += 1;
                        Entity.health -= 50;
                    }
                    this.die();
                }
            }
        }
    }
}
