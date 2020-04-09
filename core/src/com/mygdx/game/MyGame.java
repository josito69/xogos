package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygame.pantallas.HighScores;
import com.mygame.pantallas.Juego;
import com.mygame.pantallas.Pause;
import com.mygame.pantallas.Presentacion;

public class MyGame extends Game {
    public final static int PLATAFORMA_ANDROID=0;
    public final static int PLATAFORMA_DESKTOP=1;
    public final static int PLATAFORMA_HTML=2;
    private int plataforma;
    public int getPlataforma() {return plataforma; }
    public void setPlataforma(int plataforma) { this.plataforma = plataforma; }

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
