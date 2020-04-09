package com.mygame.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGame;


public class Presentacion implements Screen, InputProcessor {
    private MyGame meuxogogame;
    private OrthographicCamera camara2d;
    ShapeRenderer rotulador;
    BitmapFont writer;
    SpriteBatch batch;
    Texture fondo;
    Rectangle nuevo,salir,score;




    public Presentacion(MyGame meuxogogame) {
        this.meuxogogame = meuxogogame;
        camara2d = new OrthographicCamera();
        rotulador=new ShapeRenderer();
        batch=new SpriteBatch();
        writer=new BitmapFont();
        fondo=new Texture("fondo.png");
        nuevo=new Rectangle(45,190,210,50);
        salir=new Rectangle(45,110,210,50);
        score=new Rectangle(45,30,210,50);

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        rotulador.begin(ShapeRenderer.ShapeType.Line);
        rotulador.rect(45,190,210,50);
        rotulador.rect(45,110,210,50);
        rotulador.rect(45,30,210,50);
        rotulador.end();

        batch.begin();
           batch.draw(fondo,0,0,300,270);

           writer.setColor(Color.YELLOW);
           writer.draw(batch, "NUEVO JUEGO", 100, 220);
           writer.draw(batch, "SALIR", 125, 140);
           writer.draw(batch, "HIGH SCORES", 100, 60);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camara2d.setToOrtho(false,300, 270);
        camara2d.update();
        batch.setProjectionMatrix(camara2d.combined);
        rotulador.setProjectionMatrix(camara2d.combined);
    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(null);
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
        Vector3 dedo=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
        camara2d.unproject(dedo);
        Rectangle pulsacion=new Rectangle(dedo.x,dedo.y,10,10);

        if(Intersector.overlaps(pulsacion,nuevo)) {
            meuxogogame.setNewPantallaxogo();
            meuxogogame.setScreen((meuxogogame.getPantallaxogo()));
        }
        else if(Intersector.overlaps(pulsacion,salir))
            System.exit(0);

        else if(Intersector.overlaps(pulsacion,score))
            meuxogogame.setScreen((meuxogogame.getPantallaScores()));

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
