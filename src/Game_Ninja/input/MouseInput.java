package Game_Ninja.input;

import Game_Ninja.Game;
import Game_Ninja.Graphics.GUI.Button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener { //Thao tác bằng chuột (trên menu)
    public int x, y;
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    public void mousePressed(MouseEvent mouseEvent) {
        for (int i = 0; i < Game.launcher.buttons.length; i++) {
            Button button = Game.launcher.buttons[i];
            if (x >= button.getX()&&y>=button.getY()&&x<=button.getX()+button.getWidth()&&y<=button.getY()+button.getHeight()) button.triggerEvent();
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {

    }

    public void mouseEntered(MouseEvent mouseEvent) {

    }

    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void mouseDragged(MouseEvent mouseEvent) {

    }

    public void mouseMoved(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
    }
}
