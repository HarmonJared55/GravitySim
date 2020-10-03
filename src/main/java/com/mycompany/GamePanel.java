package com.mycompany;

/**
 * Most of this code from this class for main game loop came from a tutorial on Youtube that I followed.
 * The tutorial I followed is a series called java 2D game programmng by The Zero Doctor. Other then this file the rest of the code in this project is mine
 * 
 * Link to Github
 * https://github.com/ZeroDoctor/yt-java-game/blob/master/game-decay/src/main/java/com/zerulus/game/GamePanel.java
 * 
 */



import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;

    private Thread thread;
    private boolean running = false;
    private BufferedImage img;
    private Graphics2D g;
    private Game game;


    public GamePanel(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }


    /**
     * This method is called when the JPanel is created. This creates the gamethread and starts it.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        
        if(thread == null){
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    private void initalize(){
        this.running = true; 

        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        game = new Game(width,height);
    }

    @Override
    public void run() {
        initalize();

        final double GAME_HERTZ = 60.0;
        final double TIME_BEFORE_UPDATE = 1000000000/GAME_HERTZ;

        final int MUBR = 5; // Must Update before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;

        final double TTBR = 1000000000 / TARGET_FPS; // total time before render

        int frameCount = 0;
        int lastSecondTime = (int)(lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while(running){
            double now = System.nanoTime();
            int updateCount = 0;
            while((now-lastUpdateTime) > TIME_BEFORE_UPDATE && (updateCount < MUBR)){
                update();
                input();
                lastUpdateTime += TIME_BEFORE_UPDATE;
                updateCount++;
            }

            if(now - lastUpdateTime > TIME_BEFORE_UPDATE){
                lastSecondTime = (int)(now - TIME_BEFORE_UPDATE);
            }

            input();
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int)(lastUpdateTime / 1000000000);
            if(thisSecond>lastSecondTime){
                if(frameCount != oldFrameCount){
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while(now-lastRenderTime < TTBR && now-lastUpdateTime < TIME_BEFORE_UPDATE){
                Thread.yield();
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("Error: yeilding thread");
                }
                now = System.nanoTime();
            }
        }
    }
    
    private void update(){
        game.update(width,height);
    }

    public void input(){

    }
    
    private void render(){
        if(g != null){
            g.setColor(new Color(0,0,0));
            g.fillRect(0, 0, width, height);
            game.render(g);
        }
    }

    private void draw(){    
        Graphics g2 = (Graphics)this.getGraphics(); 
        g2.drawImage(img, 0, 0,width,height, null);
        g2.dispose();
    }

    public void addBody(){
        game.addBody(width,height);
    }

}
