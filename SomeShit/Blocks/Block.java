package Blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean leftCollision;
    public boolean rightCollision;
    public boolean upCollision;
    public boolean downCollision;

    public boolean visible;

    public Rectangle blockCollision = new Rectangle();

    public Block(){

    }

    public Block(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update(){
        adjustCollision();
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.fillRect(x, y, width, height);
        g2.setColor(Color.blue);
        g2.draw(blockCollision);
    }

    private void adjustCollision(){
        blockCollision.x = x;
        blockCollision.y = y;
        blockCollision.width = width;
        blockCollision.height = height;
    }

    public void setCollision(boolean up, boolean down, boolean right, boolean left){
        this.upCollision = up;
        this.downCollision = down;
        this.rightCollision = right;
        this.leftCollision = left;
    }

    public void setFullCollision(){
        setCollision(true, true, true, true);
    }

}
