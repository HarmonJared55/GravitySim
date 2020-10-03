package com.mycompany;

public class Vector2D {
    double x;
    double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }


    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setAngle(double angle){
        double length = this.getLength();
        this.x = (Math.cos(angle) * length);
        this.y = (Math.sin(angle) * length);
    }

    public double getAngle() {
        return Math.atan2(this.y, this.x);
    }

    public void setLength(double length){
        double angle = this.getAngle();
        this.x = (Math.cos(angle) * length);
        this.y = (Math.sin(angle) * length);
    }

    public double getLength(){
        return Math.sqrt(this.x*this.x+this.y*this.y);
    }

    public Vector2D add(Vector2D v){
        return new Vector2D(getX()+v.getX(),getY()+v.getY());
    }

    public Vector2D sub(Vector2D v){
        return new Vector2D(getX()-v.getX(),getY()-v.getY());
    }

    public Vector2D multiply(double value){
        return new Vector2D(this.x*value,this.y*value);
    }

    public Vector2D divide(double value){
        return new Vector2D(this.x/value,this.y/value);
    }
}
