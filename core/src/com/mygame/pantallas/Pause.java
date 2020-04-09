package com.mygame.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGame;


public class Pause implements Screen, InputProcessor {
    private MyGame meuxogogame;
    private Texture fondo,fondo1;
    private OrthographicCamera camara2d;
    SpriteBatch spritebatch;
    Rectangle pause;

    public Pause(MyGame meuxogogame) {
        this.meuxogogame = meuxogogame;
        pause=new Rectangle(0,0,300,270);
        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        fondo = new Texture(Gdx.files.internal("fondo.png"));
        fondo1 = new Texture(Gdx.files.internal("pause.png"));
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spritebatch.begin();
        spritebatch.draw(fondo,0,0 ,300,270);
        spritebatch.draw(fondo1,0,0 ,300,270);
        spritebatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camara2d.setToOrtho(false,300,270);
        camara2d.update();
        spritebatch.setProjectionMatrix(camara2d.combined);
    }

    @Override
    public void pause() {
         Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resume() {
         Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
         Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        meuxogogame.setScreen(meuxogogame.getPantallaxogo()) ;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
