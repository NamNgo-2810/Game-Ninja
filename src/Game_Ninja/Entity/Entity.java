package Game_Ninja.Entity;

import java.awt.*;
import Game_Ninja.*;

public abstract class Entity { /*Class trừu tượng để làm cơ sở của những nhân vật sống trong game, được implement cụ thể
    trong các class con kế thừa*/
    public int x, y;
    public int width, height;
    protected int velX, velY;
    public ID id;
    public Handler handler;
    public double gravity = 0.0;
    public int limit = 0;
    public int facing = 0;
    public boolean jumping = false;
    protected boolean falling = true;
    public boolean forward = false;
    public boolean backward = false;
    public static int health = 50;
    public static int lives = 1;
    public static int score = 0;
    public boolean slash = false;
    public int kungfu = 0;


    public Entity(int x, int y, int width, int height, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.handler = handler;
    }
    //Hai method trừu tượng bên dưới được override trong class con
    public abstract void render(Graphics g);
    public abstract void tick();
    public void die() {
        handler.removeEntity(this);
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID get_id() {
        return id;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    protected void setVelX(int velX) {
        this.velX = velX;
    }

    protected void setVelY(int velY) {
        this.velY = velY;
    }

    public static int getScore() {
        return score;
    }

    public static int getHealth() {
        return health;
    }

    public static int getLives() {return lives;}

    public ID getID() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    //Bên dưới là các hàm xác định va chạm, va chạm chung và từng hướng cụ thể

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    Rectangle getBoundsTop() {
        return new Rectangle(getX()+10, getY(), width-20, 5);
    }

    protected Rectangle getBoundsBottom() {
        return new Rectangle(getX()+10, getY()+height-5, width-20, 5 );
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle(getX(), getY()+10, 5, height-20);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(getX() + width-5, getY()+10, 5, height-20);
    }


}
