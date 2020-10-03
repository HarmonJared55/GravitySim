package com.mycompany;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class Game {
    int count = 0;
    Random rand = new Random();
    ArrayList<GBody> bodies;

    public Game(int width, int height){
        
        bodies = new ArrayList<>();
        Vector2D p = new Vector2D(width/2, height/2);
        Vector2D v = new Vector2D(0, 0);
        bodies.add(new GBody(p , 50, 10000, v,new Color(255,255,255)));
        Vector2D p2 = new Vector2D(width/2 + 50, height/2);
        Vector2D v2 = new Vector2D(0, -17);
        bodies.add(new GBody(p2 , 10, 15, v2,new Color(255,255,255)));
        Vector2D p3 = new Vector2D(width/2 + 300, height/2);
        Vector2D v3 = new Vector2D(0, -4);
        bodies.add(new GBody(p3 , 10, 10, v3,new Color(255,255,255)));
        
    }
    
    public void render(Graphics g){
        for (GBody gBody : bodies) {
            gBody.render(g,count);
        }
    }

    public void update(int width, int height){
        for(int i = 1; i < bodies.size(); i++){
            for (int j = 0; j < bodies.size(); j++) {
                if(bodies.get(i) != bodies.get(j)){
                    bodies.get(i).gravitateTo(bodies.get(j));
                }
            }
        }
        for (GBody gBody : bodies) {
            gBody.update();
        }    

        
        double dist = bodies.get(2).distanceTo(bodies.get(0));
        double velocity_num = Math.sqrt(bodies.get(0).getMass()/dist);
        
    }

    public void addBody(int width, int height){
        int randomX = rand.nextInt(width);
        int randomy = rand.nextInt(height);
        int randomVel1 = rand.nextInt(5);
        int randomVel2 = rand.nextInt(5);
        int randMass = rand.nextInt(50);
        int randRadius = rand.nextInt(50);
        Vector2D p = new Vector2D(randomX, randomy);
        Vector2D v = new Vector2D(randomVel1, randomVel2);
        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);
        
        bodies.add(new GBody(p , randRadius, randMass, v,new Color(red,green,blue)));
    }
}