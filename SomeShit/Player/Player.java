package Player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import Blocks.Block;
import Blocks.CheckCollision;
import Input.Key;
import Input.Mouse;
import StarterPack.Screen;

import java.util.ArrayList;

public class Player {

    // public Line2D playerVision = new Line2D.Float(0, 0, 0, 0);
    public ArrayList<Line2D> playerVision = new ArrayList<>();
    public float circX = 0;
    public float circY = 0;

    public boolean init = true;

    public boolean intersection;

    public int x = (1280/2) - 50;
    public int y = (720/2) - 50;
    public int width = 100;
    public int height = 100;

    public boolean canMoveRight = true;
    public boolean canMoveLeft = true;
    public boolean canMoveUp = true;
    public boolean canMoveDown = true;

    public int speed = 5;

    Key key;
    Mouse mouse;

    public Rectangle playerCollision = new Rectangle();

    public int[] posX = new int[46];
    public int[] posY = new int[46];

    public int cX = 640;
    public int cY = 360;

    public boolean isAbove;
    public boolean isBelow;
    public boolean isLeft;
    public boolean isRight;

    float theta;

    public Player(){
        System.out.println(x + "\n" + y);
    }

    public void restartVision(){
        
        playerVision.clear();
        float angle;
        float inc = 0;
        for(int i = 0; i < 46; i++){
            angle = theta + inc;
            for(int j = 0; j < 46; j++){
                circX += Math.cos(angle) * 10;
                circY += Math.sin(angle) * 10;
            }
            playerVision.add(new Line2D.Float(x + (width/2), y + (height/2), mouse.x + circX, mouse.y + circY));
            // playerVision.add(new Line2D.Float(x + (width/2), y + (height/2), mouse.x + circX, mouse.y + circY));
            // if(i == 23){
            //     circX = 0;
            //     circY = 0;
            // }
            // if(isAbove && isRight){
            //     if(i < 23){
            //         circX -= 4;
            //         circY -= 4;
            //     } else {
            //         circX += 4;
            //         circY += 4;
            //     }
            // } else if(isAbove && isLeft){
            //     if(i < 23){
            //         circX -= 4;
            //         circY += 4;
            //     } else {
            //         circX += 4;
            //         circY -= 4;
            //     }
            // } else if(isBelow && isRight){
            //     if(i < 23){
            //         circX += 4;
            //         circY -= 4;
            //     } else {
            //         circX -= 4;
            //         circY += 4;
            //     }
            // } else if(isBelow && isLeft){
            //     if(i < 23){
            //         circX += 4;
            //         circY += 4;
            //     } else {
            //         circX -= 4;
            //         circY -= 4;
            //     }
            // } else if(isRight){
            //     if(i < 23){
            //         circX -= 0;
            //         circY += 4;
            //     } else {
            //         circX -= 0;
            //         circY -= 4;
            //     }
            // } else if(isLeft){
            //     if(i < 23){
            //         circX += 0;
            //         circY -= 4;
            //     } else {
            //         circX += 0;
            //         circY += 4;
            //     }
            // } else if(isBelow){
            //     if(i < 23){
            //         circX += 4;
            //         circY -= 0;
            //     } else {
            //         circX -= 4;
            //         circY -= 0;
            //     }
            // } else if(isAbove){
            //     if(i < 23){
            //         circX -= 4;
            //         circY += 0;
            //     } else {
            //         circX += 4;
            //         circY += 0;
            //     }
            // }
            if(i == 23){
                inc = 0;
                circX = 0;
                circY = 0;
            }
            if(i < 23){
                inc -= 0.05;
            } else {
                inc += 0.05;
            }
            //!Retirar isso dá um treco loco
            circX = 0;
            circY = 0;
            //!
        }
        circX = 0;
        circY = 0;
        init = false;
    }

    public void update(Key key, Mouse mouse, Screen screen){
        this.key = key;
        this.mouse = mouse;

        theta = (float) Math.atan2(mouse.y - (y + height/2), mouse.x - (x + width/2));
        // System.out.println(theta);
        checkMouseRelativeToPlayer();
        restartVision();
        // playerVision = new Line2D.Float(x + (width/2), y + (height/2), mouse.x, mouse.y);
        keyListen(screen);
        adjustPlayerCollision();

    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, width, height);
        g2.setColor(Color.red);
        g2.draw(playerCollision);
        // if(!intersection){
        //     restartVision();
        // }
        // theta = (float) Math.atan2(mouse.y - (y + height/2), mouse.x - (x + width/2));
        // g2.rotate(theta, (x + width/2), (y + height/2));
        for(int i = 0; i < playerVision.size(); i++){
            if(i == 0){
                g2.setColor(Color.blue);
            }
            g2.draw(playerVision.get(i));
            g2.setColor(Color.red);
        }
        // g2.rotate(-theta, (x + width/2), (y + height/2));
        g2.drawString("isAbove: " + isAbove, 0, 10);
        g2.drawString("isBelow: " + isBelow, 0, 20);
        g2.drawString("isLeft: " + isLeft, 0, 30);
        g2.drawString("isRight: " + isRight, 0, 40);
        // g2.draw(playerVision);
    }

    private void normalize(){
        //double normalize = Math.sqrt((Math.pow(speed, 2) + Math.pow(speed, 2)));
        speed = 4;
    }

    private void keyListen(Screen screen){

        if((key.up && key.left || key.up && key.right) || (key.down && key.left || key.down && key.right)){
            normalize();
        } else {
            speed = 5;
        }

        if(key.up && canMoveUp){
            y -= speed;
            // screen.worldY += speed;
        }
        if(key.down && canMoveDown){
            y += speed;
            // screen.worldY -= speed;
        }
        if(key.left && canMoveLeft){
            x -= speed;
            // screen.worldX += speed;
        }
        if(key.right && canMoveRight){
            x += speed;
            // screen.worldX -= speed;
        }

    }

    private void adjustPlayerCollision(){
        playerCollision.x = x;
        playerCollision.y = y;
        playerCollision.width = width;
        playerCollision.height = height;
    }

    public void checkForWall(Block block){
        CheckCollision checkCollision = new CheckCollision();
        for(int i = 0; i < 46; i++){
            if(checkCollision.lineRect((float) playerVision.get(i).getX1(),(float) playerVision.get(i).getY1(),(float) playerVision.get(i).getX2(),
                (float) playerVision.get(i).getY2(),(float) block.x,(float) block.y,(float) block.width,(float) block.height)){
                    // playerVision = new Line2D.Float(x + (width/2), y + (height/2), checkCollision.intersectionX, checkCollision.intersectionY);
                    playerVision.set(i, new Line2D.Float(x + (width/2), y + (height/2), checkCollision.intersectionX, checkCollision.intersectionY));
                    // intersection = true;
                    block.visible = true;
            }
        }
        // if(checkCollision.lineRect((float) playerVision.getX1(),(float) playerVision.getY1(),(float) playerVision.getX2(),
        //     (float) playerVision.getY2(),(float) block.x,(float) block.y,(float) block.width,(float) block.height)){
        //         playerVision = new Line2D.Float(x + (width/2), y + (height/2), checkCollision.intersectionX, checkCollision.intersectionY);
        //         intersection = true;
        // }
    }

    public void checkMouseRelativeToPlayer(){
        if(mouse.y < y){
            isAbove = true;
        } else{
            isAbove = false;
        }

        if(mouse.y > (y + height)){
            isBelow = true;
        } else{
            isBelow = false;
        }

        if(mouse.x < x){
            isLeft = true;
        } else{
            isLeft = false;
        }

        if(mouse.x > (x + width)){
            isRight = true;
        } else{
            isRight = false;
        }
    }

}
