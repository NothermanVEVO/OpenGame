package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener{

    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 87 || code == 38){
            up = true;
        }
        if(code == 65 || code == 37){
            left = true;
        }
        if(code == 83 || code == 40){
            down = true;
        }
        if(code == 68 || code == 39){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 87 || code == 38){
            up = false;
        }
        if(code == 65 || code == 37){
            left = false;
        }
        if(code == 83 || code == 40){
            down = false;
        }
        if(code == 68 || code == 39){
            right = false;
        }
    }
    
}
