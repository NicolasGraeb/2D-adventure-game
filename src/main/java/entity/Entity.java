package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    //boolean isMoving = false;

    public BufferedImage up1, up2, up3, up4, up5, up6, left1, left2, left3, left4, left5, left6, right1, right2, right3, right4, right5, right6;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
