package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygame.pantallas.HighScores;
import com.mygame.pantallas.Juego;
import com.mygame.pantallas.Pause;
import com.mygame.pantallas.Presentacion;

public class MyGame extends Game {
    public static int PLATAFORMA_ANDROID=0;
    public static int PLATAFORMA_DESKTOP=1;
    public static int PLATAFORMA_HTML=2;
    public Plataforma platform;
    public int tipo;
    public MyGame(Plataforma platform,int tipo)
    {
        this.platform=platform;
        this.tipo=tipo; }
    public MyGame(int tipo){
        this.tipo=tipo;
    }

    private Juego pantallaxogo;
    private Presentacion pantallaPresentacion;
    private HighScores pantallaScores;
    private Pause pausa;

    public HighScores getPantallaScores() { return pantallaScores; }
    public Pause getPausa() {
        return pausa;
    }
    public Presentacion getPantallaPresentacion() { return pantallaPresentacion; }
    public Juego getPantallaxogo() { return pantallaxogo; }

    public void setNewPantallaxogo() {
        this.pantallaxogo = new Juego(this);
    }

    @Override
    public void create() {
        pantallaxogo = new Juego(this);
        pantallaPresentacion=new Presentacion(this);
        pantallaScores=new HighScores(this);
        pausa=new Pause(this);

        setScreen(pantallaPresentacion);
    }
    public void dispose(){
        super.dispose();
    }
}

