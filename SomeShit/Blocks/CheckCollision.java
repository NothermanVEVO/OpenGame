package Blocks;

import Player.Player;
import java.awt.Graphics2D;

public class CheckCollision {

    public static int collisionRight = 0;
    public static int collisionLeft = 0;
    public static int collisionUp = 0;
    public static int collisionDown = 0;

    public static String above = "";
    public static String below = "";
    public static String right = "";
    public static String left = "";

    public static boolean isAbove;
    public static boolean isBelow;
    public static boolean isRight;
    public static boolean isLeft;

    public float intersectionX;
    public float intersectionY;

    public float interX;
    public float interY;

    public void checkWallCollision(Player player, Block block){
        // if(player.playerCollision.intersects(block.blockCollision)){
        //     System.out.println("Px: " + (player.playerCollision.x) + " Bx: " + block.blockCollision.x);
        //     if(player.playerCollision.x <= block.blockCollision.x){
        //         player.x -= 5;
        //     }
        // }

        checkWhereBlockIs(player, block);

        //!Can't move RIGHT
        if((lineRect(block.x, block.y, block.x, (block.y + block.height), player.x, player.y, player.width, player.height) && isLeft) 
            && block.leftCollision){
            // player.x -= 5;
            player.speed *= -1;
            player.canMoveRight = false;
            collisionRight++;
        }

        //!Can't move DOWN
        if((lineRect(block.x, block.y, (block.x + block.width), block.y, player.x, player.y, player.width, player.height) && isAbove) 
            && block.upCollision){
            // player.y -= 5;
            player.speed *= -1;
            player.canMoveDown = false;
            collisionDown++;
        }

        //!Can't move UP
        if((lineRect(block.x, (block.y + block.height), (block.x + block.width), (block.y + block.height), player.x, player.y, player.width, player.height) && isBelow) 
            && block.downCollision){
            // player.y += 5;
            player.speed *= -1;
            player.canMoveUp = false;
            collisionUp++;
        }

        //!Can't move LEFT
        if((lineRect((block.x + block.width), block.y, (block.x + block.width), (block.y + block.height), player.x, player.y, player.width, player.height) && isRight) 
            && block.rightCollision){
            // player.x += 5;
            player.speed *= -1;
            player.canMoveLeft = false;
            collisionLeft++;
        }

    }

    public void draw(Graphics2D g2){
        // g2.drawString(above, 0, 10);
        // g2.drawString(below, 0, 20);
        // g2.drawString(right, 0, 30);
        // g2.drawString(left, 0, 40);
    }

    // https://www.jeffreythompson.org/collision-detection/line-rect.php
    // https://kishimotostudios.com/articles/aabb_collision/

    // LINE/RECTANGLE
    public boolean lineRect(float x1, float y1, float x2, float y2, float rx, float ry, float rw, float rh) {

        // check if the line has hit any of the rectangle's sides
        // uses the Line/Line function below
        boolean left =   lineLine(x1,y1,x2,y2, rx,ry,rx, ry+rh);
        boolean right =  lineLine(x1,y1,x2,y2, rx+rw,ry, rx+rw,ry+rh);
        boolean top =    lineLine(x1,y1,x2,y2, rx,ry, rx+rw,ry);
        boolean bottom = lineLine(x1,y1,x2,y2, rx,ry+rh, rx+rw,ry+rh);
  
        // if ANY of the above are true, the line
        // has hit the rectangle
        if (left || right || top || bottom) {
            return true;
        }
        return false;
    }
  
  
    // LINE/LINE
    public boolean lineLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
  
        // calculate the direction of the lines
        float uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
        float uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
  
        // if uA and uB are between 0-1, lines are colliding
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
            // optionally, draw a circle where the lines meet
            intersectionX = x1 + (uA * (x2-x1));
            intersectionY = y1 + (uA * (y2-y1));
            return true;
        }
        return false;
    }

    public void zerar(){
        collisionRight = 0;
        collisionLeft = 0;
        collisionUp = 0;
        collisionDown = 0;
    }

    public void finalCheck(Player player){
        if(collisionRight == 0){
            player.canMoveRight = true;
            player.speed = Math.abs(player.speed);
        }
        if(collisionLeft == 0){
            player.canMoveLeft = true;
            player.speed = Math.abs(player.speed);
        }
        if(collisionUp == 0){
            player.canMoveUp = true;
            player.speed = Math.abs(player.speed);
        }
        if(collisionDown == 0){
            player.canMoveDown = true;
            player.speed = Math.abs(player.speed);
        }
        zerar();
    }
    
    public void checkWhereBlockIs(Player player, Block block){
        if(block.y + 5 > (player.y + player.height)){
            above = "Is above";
            isAbove = true;
        } else {
            above = "Isn't above";
            isAbove = false;
        }

        if(player.y > (block.y + block.height - 5)){
            below = "Is below";
            isBelow = true;
        } else {
            below = "Isn't below";
            isBelow = false;
        }

        if(block.x + 5 > (player.x + player.width)){
            left = "Is in the left";
            isLeft = true;
        } else {
            left = "Isn't in the left";
            isLeft = false;
        }

        if(player.x > (block.x + block.width - 5)){
            right = "Is in the right";
            isRight = true;
        } else {
            right = "Isn't in the right";
            isRight = false;
        }
    }

}

