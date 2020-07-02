package Game_Ninja.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Game_Ninja.Game;
import Game_Ninja.Entity.Entity;
import Game_Ninja.ID;

public class KeyInput implements KeyListener {
    public void keyTyped(KeyEvent keyEvent) {}

    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        for (Entity en : Game.handler.entity) {
            if (en.get_id()== ID.player){
                switch (key) {
                    case KeyEvent.VK_UP:
                        if (!en.jumping) {
                            en.jumping = true;
                            en.gravity = 10;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (!en.backward) {
                            en.backward = true;
                            en.limit = 20;
                        }
                        en.facing = 1;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!en.forward) {
                            en.forward = true;
                            en.limit = 20;
                        }
                        en.facing = 0;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!en.slash) {
                            en.slash = true;
                            en.kungfu = 10;
                        }
                        break;

                }
            }
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        for (Entity en : Game.handler.entity) {
            if (en.get_id()== ID.player){
                switch (key) {
                    case KeyEvent.VK_UP:
                        en.slash = false;
                        if (!en.jumping) {
                            en.jumping = true;
                            en.gravity = 10;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        en.slash = false;
                        if (!en.backward) {
                            en.backward = true;
                            en.limit = 20;
                        }
                        en.facing = 1;
                        break;
                    case KeyEvent.VK_RIGHT:
                        en.slash = false;
                        if (!en.forward) {
                            en.forward = true;
                            en.limit = 20;
                        }
                        en.facing = 0;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!en.slash) {
                            en.slash = true;
                            en.kungfu = 10;
                        }
                        break;
                }
            }
        }
    }
}
