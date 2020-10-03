package com.mycompany;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;


public class GBody {
    
    private int radius;
    private int mass;
    private Color color;

    private Vector2D position;
    private Vector2D velocity;

    Random rand = new Random();
    

    public GBody(Vector2D position, int radius, int mass, Vector2D velocity, Color color)  {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.mass = mass;
        this.color = color;
    }


    public void update(){
        position = position.add(velocity);
        
    }

    public void render(Graphics g, int count){
        
        g.setColor(this.color);

        g.fillOval((int)position.getX()-(radius/2), (int)position.getY()-(radius/2), radius, radius);
    }

    private void accelerate(Vector2D accelration){
        this.velocity = this.velocity.add(accelration);
    }

    private double angleTo(GBody body){
        return Math.atan2(body.position.getY()-this.position.getY(),body.position.getX()-this.position.getX());
    }
    
    public double distanceTo(GBody body){
        double dx = body.position.getX() - this.position.getX();
        double dy = body.position.getY() - this.position.getY();
        return Math.sqrt(dx*dx+dy*dy);
    }

    public void gravitateTo(GBody body){
        Vector2D gravity = new Vector2D(0, 0);
        double distance = this.distanceTo(body);
        gravity.setLength(body.mass/(distance*distance));
        gravity.setAngle(this.angleTo(body));
        this.velocity = this.velocity.add(gravity);
    }


	public double calcVel(double dist) {
        return Math.sqrt(mass/radius);
    }
    
    public int getMass(){
        return mass;
    }

    public Vector2D getPosition(){
        return this.position;
    }

}
