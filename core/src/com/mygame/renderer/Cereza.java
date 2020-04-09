package com.mygame.renderer;

import java.util.Random;

public class Cereza {
   private float x,y;

    public Cereza() {
    }

    public Cereza(float x, float y, float sizeX, float sizeY) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosicion(){
        int x=new Random().nextInt(30);
        int y=new Random().nextInt(25);
        setX((float)(x*10));
        setY((float) (y*10));
    }

}
