package Game_Ninja.Graphics.GUI;

import Game_Ninja.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Launcher { //Menu của game

    public Button[] buttons;
    public Launcher() {
        buttons = new Button[3];
        buttons[0] = new Button(100, 100, 100, 100, "Start");
        buttons[1] = new Button(200, 200, 100, 100, "Instruction");
        buttons[2] = new Button(900, 120, 100, 100, "");
    }

    public void render(Graphics g) throws IOException {
        BufferedImage background = ImageIO.read(new File("res/Background3.png"));
        g.drawImage(background, 0,0, Game.getFrameWidth(), Game.getFrameHeight(), null);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].render(g);
        }
    }
}
