package StarterPack;

import javax.swing.JFrame;

public class Window extends JFrame{

    Screen screen = new Screen(this);

    public Window(){
        this.setSize(1280, 720);
        this.setTitle("I don't know yet");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(screen);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        screen.startLoop();
    }

}
