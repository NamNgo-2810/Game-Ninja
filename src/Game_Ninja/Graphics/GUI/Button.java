package Game_Ninja.Graphics.GUI;


import Game_Ninja.Game;


import java.awt.*;

public class Button { //Các lựa chọn trên menu
    public int x, y;
    public int width, height;

    private String Label;

    Button(int x, int y, int width, int height, String Label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.Label = Label;
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Century Gothic", Font.BOLD, 40));
        FontMetrics fm = g.getFontMetrics();
        int stringX = (getWidth() - fm.stringWidth(getLabel())) / 2;
        int stringY = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent()))/2);
        g.drawString(getLabel(),getX() + stringX, getY() + stringY);
    }

    public void triggerEvent() {
        if(!Game.showInstruction && getLabel().toLowerCase().contains("start")) {
            Game.playing = true;
        }
        else if (!Game.showInstruction && getLabel().toLowerCase().contains("instruction")) {
            Game.showInstruction = true;
        }
        else if (Game.showInstruction && getLabel().equals("")) {
            Game.playing = false;
            Game.showInstruction = false;
        }
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

    private String getLabel() {
        return Label;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
