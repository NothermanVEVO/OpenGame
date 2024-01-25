package StarterPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Blocks.Block;
import Blocks.CheckCollision;
import Input.Key;
import Input.Mouse;
import Player.Player;

public class Screen extends JPanel{

    Thread thread1 = new Thread();

    int fps = 60;
    long sleepTime = 1000/fps;

    Key key = new Key();
    Mouse mouse = new Mouse();

    Player player = new Player();

    ArrayList<Block> blocks = new ArrayList<>();

    CheckCollision checkCollision = new CheckCollision();

    public int worldX = 0;
    public int worldY = 0;

    public Screen(JFrame window){

        this.setBounds(0, 0, window.getWidth(), window.getHeight());
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.addKeyListener(key);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        createBlocks();

    }

    public void startLoop(){
        thread1.start();
        while (true) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
            update();
            repaint();
        }
    }

    public void update(){
        player.update(key, mouse, this);
        for(int i = 0; i < blocks.size(); i++){
            blocks.get(i).update();
            player.checkForWall(blocks.get(i));
            checkCollision.checkWallCollision(player, blocks.get(i));
        }
        checkCollision.finalCheck(player);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.translate(worldX, worldY);
        for(int i = 0; i < blocks.size(); i++){
            if(blocks.get(i).visible){
                blocks.get(i).draw(g2);
            }
            checkCollision.draw(g2);
        }
        g2.translate(-worldX, -worldY);
        g2.dispose();
    }

    public void createBlocks(){
        blocks.add(new Block(1080, 0, 100, 100));
        blocks.get(0).setFullCollision();
        blocks.add(new Block(700, 250, 100, 100));
        blocks.get(1).setCollision(true, false, true, true);
        blocks.add(new Block(700, 350, 100, 100));
        blocks.get(2).setCollision(false, true, true, true);
        blocks.add(new Block(600, 450, 100, 100));
        blocks.get(3).setCollision(true, true, true, false);
        blocks.add(new Block(500, 450, 100, 100));
        blocks.get(4).setCollision(true, true, false, true);
        blocks.add(new Block(800, 600, 100, 100));
        blocks.get(5).setFullCollision();
    }

}
