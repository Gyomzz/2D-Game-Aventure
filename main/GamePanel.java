package main;

import java.awt.*;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    
    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // if set to true, all the drawing from this component will be done in an offscreen painting buffer
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds

        // version Sleep
        // double nextDrawTime = System.nanoTime() + drawInterval;

        // delta version
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null) {

            // delta version
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >=1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

            // sleep version 
            // 1 UPDATE: 1 update information such as character positions
            // update();

            // 2 DRAW: draw the screen with the updated information
            // repaint();

            
            // try { 
            //     double remainingTime = nextDrawTime - System.nanoTime();
            //     remainingTime = remainingTime/1000000;

            //     if(remainingTime < 0) {
            //         remainingTime = 0;
            //     }

            //     Thread.sleep((long) remainingTime);

            //     nextDrawTime += drawInterval;
                
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

            // delta version

        }
    }

    public void update() {

        if(keyH.upPressed == true) {
            playerY -= playerSpeed;
        }

        if(keyH.downPressed == true) {
            playerY += playerSpeed;
        }

        if(keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }

        if(keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
