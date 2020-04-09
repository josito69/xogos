package com.mygame.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygame.renderer.RendererXogoBeta;
import com.mygdx.game.MyGame;

public class Juego implements Screen {
    private boolean pause;
    private boolean finxogo;
    private boolean sair;

    private MyGame meuxogogame;
    private RendererXogoBeta rendererxogo;

    public Juego(MyGame meuxogogame) {
        this.meuxogogame = meuxogogame;
        rendererxogo=new RendererXogoBeta(meuxogogame);
    }
    @Override
    public void render(float delta) {

        rendererxogo.render(delta);
    }

    @Override
    public void resize(int width, int height) {

        rendererxogo.resize(width, height);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(rendererxogo);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(rendererxogo);

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        rendererxogo.dispose();
    }

}
