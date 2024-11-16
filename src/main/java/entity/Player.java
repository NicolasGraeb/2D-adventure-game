package entity;

import game.GamePanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction= "down";


    }
    public void getPlayerImage(){
        try{
            left1 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_left/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_left/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_left/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_left/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_left/left5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_left/left6.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_right/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_right/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_right/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_right/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_right/right5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_right/right6.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_up/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_up/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_up/up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_up/up4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_up/up5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/bohater/player_up/up6.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
        boolean isMoving = false;

        collisionOn = false;

        if (keyH.downPressed) {
            direction = "down";
            gp.cChecker.checkTile(this);
            if (!collisionOn) {
                worldY += speed;
                isMoving = true;
            }
        }
        if (keyH.upPressed) {
            direction = "up";
            gp.cChecker.checkTile(this);
            if (!collisionOn) {
                worldY -= speed;
                isMoving = true;
            }
        }
        if (keyH.leftPressed) {
            direction = "left";
            gp.cChecker.checkTile(this);
            if (!collisionOn) {
                worldX -= speed;
                isMoving = true;
            }
        }
        if (keyH.rightPressed) {
            direction = "right";
            gp.cChecker.checkTile(this);
            if (!collisionOn) {
                worldX += speed;
                isMoving = true;
            }
        }
        // Aktualizacja animacji tylko wtedy, gdy bohater się porusza
        if (isMoving) {
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum + 1) % 6;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage[][] sprites_arr = new BufferedImage[4][6];
        sprites_arr[0] = new BufferedImage[] {up1, up2, up3, up4, up5, up6};   // up
        sprites_arr[1] = new BufferedImage[] {left1, left2, left3, left4, left5, left6}; // left
        sprites_arr[2] = new BufferedImage[] {right1, right2, right3, right4, right5, right6}; // right

        BufferedImage image = null;


            if (direction.contains(" ")) { // Jesli jest kombinacja kierunkow, np. "up right"
                String[] splitDirections = direction.split(" ");
                direction = splitDirections[1]; // Wybierz drugi kierunek
            }


            if (direction.equals("up")) {
                if (spriteNum < sprites_arr[0].length) {
                    image = sprites_arr[0][spriteNum];
                } else {
                    System.err.println("Invalid spriteNum for 'up': " + spriteNum);
                }
            } else if (direction.equals("down")) {
                if (spriteNum < sprites_arr[2].length) {
                    image = sprites_arr[2][spriteNum];
                } else {
                    System.err.println("Invalid spriteNum for 'down': " + spriteNum);
                }
            } else if (direction.equals("left")) {
                if (spriteNum < sprites_arr[1].length) {
                    image = sprites_arr[1][spriteNum];
                } else {
                    System.err.println("Invalid spriteNum for 'left': " + spriteNum);
                }
            } else if (direction.equals("right")) {
                if (spriteNum < sprites_arr[2].length) {
                    image = sprites_arr[2][spriteNum];
                } else {
                    System.err.println("Invalid spriteNum for 'right': " + spriteNum);
                }
            }

            if (image == null) {
                System.err.println("Sprite for direction '" + direction + "' is null.");
                return;
            }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }



}
