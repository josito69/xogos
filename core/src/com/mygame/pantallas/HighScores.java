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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HighScores implements Screen,InputProcessor {
    private MyGame meuxogogame;
    private Texture fondo;
    private OrthographicCamera camara2d;
    SpriteBatch spritebatch;
    Array<Puntuaciones> lista;
    BitmapFont writer;
    Rectangle menu;

    public HighScores (MyGame meuxogogame) {
        writer=new BitmapFont();
        this.meuxogogame = meuxogogame;
        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        fondo = new Texture(Gdx.files.internal("timeOut.jpg"));
        lista =new Array<Puntuaciones>();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        lista.clear();
        BufferedReader in=new BufferedReader(new InputStreamReader(Gdx.files.external("score.txt").read()));
        try {
            String valor;
            while ((valor = in.readLine()) != null) {
                StringTokenizer tokens = new StringTokenizer(valor, ":");
                String nombre = tokens.nextToken();
                int puntos = Integer.parseInt(tokens.nextToken());
                lista.add(new Puntuaciones(nombre, puntos));
            }
        }catch (IOException e) {
            e.printStackTrace(); }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spritebatch.begin();
        spritebatch.setColor(Color.BLUE);
        spritebatch.draw(fondo,0,0 ,300,270);
        writer.setColor(Color.RED);
        writer.getData().setScale(0.8f);
        writer.draw(spritebatch,"HIGH SCORES",110,260);
        writer.draw(spritebatch,"MENU",125,20);
        float y=220;
        int i=1;
        writer.getData().setScale(0.6f);
        writer.setColor(Color.YELLOW);
        for(Puntuaciones tmp:lista){
            String nombre=tmp.getNombre();
            String puntos=tmp.getPuntos()+"";
            writer.draw(spritebatch,i+".-   "+nombre+ " : "+puntos,100, y);
            y=y-15f;
            i++;
        }
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
        Vector3 dedo = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camara2d.unproject(dedo);
        Rectangle pulsacion = new Rectangle(dedo.x, dedo.y, 15, 10);
        menu = new Rectangle(140, 20, 20, 20);
        if (Intersector.overlaps(pulsacion,menu))
            meuxogogame.setScreen(meuxogogame.getPantallaPresentacion());
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
