package Game_Ninja;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import Game_Ninja.Entity.Enemy.Samurai;

import Game_Ninja.Tile.*;
import Game_Ninja.Entity.*;

import javax.imageio.ImageIO;

public class Handler {
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();

    public void render(Graphics g) {
        for (int i = 0; i < entity.size(); i++) {
            Entity en = entity.get(i);
            en.render(g);
        }
        for (int i = 0; i < tile.size(); i++) {
            Tile ti = tile.get(i);
            ti.render(g);
        }
    }

    public void tick() {
        for (int i = 0; i < entity.size(); i++) {
            Entity en = entity.get(i);
            en.tick();
        }
        for (int i = 0; i < tile.size(); i++) {
            Tile ti = tile.get(i);
            ti.tick();
        }
    }

    private void addEntity(Entity en) {
        entity.add(en);
    }

    public void removeEntity(Entity en) {
        entity.remove(en);
    }

    private void addTile(Tile ti) {
        tile.add(ti);
    }

    public void removeTile(Tile ti) {
        tile.remove(ti);
    }



    void createLevel(BufferedImage level) {
        int width = level.getWidth();
        int height = level.getHeight();

        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                int pixel = level.getRGB(x,y);
                //Sử dụng toán tử dịch bit để thao tác với từng điểm ảnh, mỗi điểm ảnh có màu khác nhau sẽ biểu diễn vật thể mà màu của nó được chỉ định
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red==0 && green==127 && blue==0) addTile(new Rock(x*64, y*64, 64, 64, true, ID.rock, this));
                if (red==127 && green==127 && blue==255) addTile(new InvisibleWall(x*64, y*64, 64, 64,true, ID.invisible_wall, this));
                if (red==0 && green==0 && blue==0) addTile(new Land(x*64, y*64, 64, 64 , true, ID.land, this));
                if (red==0 && green==0 && blue==255) addEntity(new Player(x*64, y*64, 64, 64, ID.player, this));
                if (red==0 &&green==255 && blue==0) addEntity(new Samurai(x*64, y*64-43, 54, 108, ID.enemy, this));
                if (red==255 && green==0 && blue==0) addTile(new Lives(x*64, y*64 + 24, 24, 24, false, ID.live, this));
                if (red== 255 && green==255 && blue==0) addTile(new Coins(x*64, y*64 + 24, 24, 24, false, ID.coin, this));
            }
        }
    }

    void showInstruction(Graphics g) throws IOException {
        BufferedImage instruction = ImageIO.read(new File("res/instruction.png"));
        g.drawImage(instruction, 0,0, Game.getFrameWidth(), Game.getFrameHeight(), null);
    }

    void showDeathScreen(Graphics g) throws IOException {
        BufferedImage deathScreen = ImageIO.read(new File("res/Background2.png"));
        g.drawImage(deathScreen, 0,0, Game.getFrameWidth(), Game.getFrameHeight(), null);
        g.setColor(Color.RED);
        g.setFont(new Font("Courier", Font.BOLD, 40));
        g.drawString("You lose!", 500, 300);
    }
}

