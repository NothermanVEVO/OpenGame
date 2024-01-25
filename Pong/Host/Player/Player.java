package Host.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import Host.Input.Key;
import Host.StarterPack.GamePanel;

public class Player {

    public static Rectangle player;
    public static Rectangle otherPlayer;

    public static int playerPont = 0;
    public static int otherPlayerPont = 0;

    Key key;

    int centerPointX;
    int centerPointY;
    public Point2D centerPoint;
    
    public Player(){
        //? CHANGED HERE
        player = new Rectangle(10, (480 / 2) - 100, 20, 100);
        updateCenterPoint();
        otherPlayer = new Rectangle(-100, -100, 20, 75);
    }

    public void update(Key key, GamePanel gamePanel){
        updateCenterPoint();
        if(key.is_W_Pressed || key.is_Up_Pressed){
            player.y -= 4 * gamePanel.delta;
        }
        if(key.is_S_Pressed || key.is_Down_Pressed){
            player.y += 4 * gamePanel.delta;
        }

        if(player.y <= 0){
            player.y += 4 * gamePanel.delta;
        }
        if(player.y + (player.height + 37) >= 480){
            player.y -= 4 * gamePanel.delta;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.drawLine(315, 0, 315, 480);
        g2.setFont(new Font("", Font.PLAIN, 40));
        g2.drawString("" + playerPont, 320 - 30 - 200, 240 - 200);
        g2.drawString("" + otherPlayerPont, 320 - 30 + 200, 240 - 200);
        g2.fill(otherPlayer);
        g2.fill(player);
        g2.setColor(Color.RED);
        g2.fillOval(centerPointX, centerPointY, 5, 5);
        g2.setColor(Color.WHITE);
    }

    public void updateCenterPoint(){
        centerPointX = player.x + ((player.width - player.x) / 2) + 2;
        centerPointY = player.y + (((player.y + player.height) - player.y) / 2) - 2;
        centerPoint = new Point2D.Float(centerPointX , centerPointY);
    }

}
