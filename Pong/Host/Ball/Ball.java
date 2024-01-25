package Host.Ball;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Host.Player.Player;
import Host.StarterPack.GamePanel;

public class Ball {

    public static int x = 320 - 30;
    public static int y = 240 - 30;
    public static int r = 15;

    public static boolean intersectOtherPlayer;

    public static int speedX = 5;
    public static int speedY = 5;

    public static double realSpeedX = 5;
    public static double realSpeedY = 5;

    public int distantX;
    public int distantY;
    public double realDistance;

    public double angle;
    
    public Ball(){
        angle = Math.toRadians(45);
    }

    public void update(Player player, GamePanel gamePanel){
        realSpeedX = speedX * Math.cos(angle);
        realSpeedY = speedY * Math.sin(angle);

        x += realSpeedX  * gamePanel.delta;
        y += realSpeedY  * gamePanel.delta;

        // System.out.println("speedX: " + realSpeedX + " distY: " + realSpeedY);

        //? CHANGED HERE
        if(ballIntersectPlayer(Player.player) && speedX < 0){
            speedX *= -1;
            checkDistance(player);
        }

        if((intersectOtherPlayer || ballIntersectPlayer(Player.otherPlayer)) && speedX > 0){
            speedX *= -1;
        }

        if(y <= 0 && realSpeedY < 0){
            speedY *= -1;
        }
        if(y >= 480 - 55 && realSpeedY > 0){
            speedY *= -1;
        }
        if(x >= 640 - 30 && realSpeedX > 0){
            speedX *= -1;
            x = 320 - 30 + 200;
            y = 240 - 30;
            Player.playerPont++;
        }
        if(x <= 0 && realSpeedX < 0){
            speedX *= -1;
            x = 320 - 30 - 200;
            y = 240 - 30;
            Player.otherPlayerPont++;
        }
    }

    public void draw(Graphics2D g2){
        g2.fillOval(x, y, r, r);
    }

    public boolean ballIntersectPlayer(Rectangle player){

        // https://www.jeffreythompson.org/collision-detection/circle-rect.php

        float testX = x;
        float testY = y;

        // which edge is closest?
        if (x < player.x)         testX = player.x;      // test left edge
        else if (x > player.x+player.width) testX = player.x+player.width - 15;   // right edge
        if (y < player.y)         testY = player.y;      // top edge
        else if (y > player.y+player.height) testY = player.y+player.height;   // bottom edge

        // get distance from closest edges
        float distX = x-testX;
        float distY = y-testY;
        float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );

        // if the distance is less than the radius, collision!
        if (distance <= r) {
            return true;
        }
        return false;

    }

    public void checkDistance(Player player){
        distantX = (int) ((x + (r / 2)) - player.centerPoint.getX());
        distantY = (int) ((y + (r / 2)) - player.centerPoint.getY());
        realDistance = Math.sqrt((Math.pow(distantX, 2)) + (Math.pow(distantY, 2)));
        adjustAngle();
        // System.out.println("disX: " + distantX + " distY: " + distantY);
        System.out.println(realDistance);
        // System.out.println("BallX: " + (x + (r / 2)) + " PlayerX: " + player.centerPoint.getX());
    }

    public void adjustAngle(){
        if(realDistance >= 18 && realDistance <= 22){
            angle = Math.toRadians(0);
        } else if(realDistance >= 15 && realDistance <= 25){
            angle = Math.toRadians(10);
        } else if(realDistance >= 12 && realDistance <= 28){
            angle = Math.toRadians(20);
        } else if(realDistance >= 9 && realDistance <= 31){
            angle = Math.toRadians(30);
        } else if(realDistance >= 6 && realDistance <= 34){
            angle = Math.toRadians(40);
        } else {
            angle = Math.toRadians(45);
        }
    }

}
