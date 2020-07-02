package Game_Ninja.Tile;

import java.awt.*;


import Game_Ninja.*;



public abstract class Tile { /*Class trừu tượng để làm cơ sở của những vật thể tĩnh trong game, được implement cụ thể
    trong các class con kế thừa*/
    public int x, y;
    public int width, height;
    private boolean solid;
    public ID id;
    public Handler handler;

    Tile(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.handler = handler;
    }
    //Hai method trừu tượng bên dưới được override trong class con
    public abstract void render(Graphics g);
    public abstract void tick();

    void die() {
        handler.removeTile(this);
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID get_id() {
        return id;
    }
    public boolean isSolid() {
        return solid;
    }



    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    } //Hàm xác định va chạm giữa hai vật thể


}


