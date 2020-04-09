package com.mygame.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygame.pantallas.Puntuaciones;
import com.mygdx.game.MyGame;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;



public class RendererXogoBeta implements InputProcessor {
    private MyGame meuxogogame;
    SpriteBatch spritebatch;
    private Texture grafico,grafico1,grafico2,grafico3;
    private ShapeRenderer forma;
    private Vector2 direccion;
    private Array<Vector2> serpiente;
    private Cereza premio;
    private Rectangle cereza,head,salir,pause;
    private int score=0;
    private  BitmapFont writer;
    private OrthographicCamera camara2d;
    private Long inicioJuego=new Date().getTime();
    Array <Puntuaciones> lista;
    File archivo;
    int velocidad=80,acelerador=0;

    public RendererXogoBeta(MyGame meuxogogame) {
        try {
            archivo=new File(Gdx.files.getExternalStoragePath()+"score.txt");
            if (!archivo.exists())
                archivo.createNewFile();
            }catch (IOException e) {
                e.printStackTrace();}
        this.meuxogogame=meuxogogame;
        spritebatch = new SpriteBatch();
        camara2d=new OrthographicCamera();
        forma=new ShapeRenderer();
        serpiente=new Array <Vector2>(10);
        direccion=new Vector2(10,0);
        premio=new Cereza();
        premio.setPosicion();
        writer=new BitmapFont();
        lista=new Array <Puntuaciones>();
        int x=0;
        for(int i=0;i<10;i++) {
            serpiente.add(new Vector2(x,0));
            x+=10;
        }
        grafico=new Texture("cherry.png");
        grafico1=new Texture("head.png");
        grafico2=new Texture("salir.png");
        grafico3=new Texture("pause.png");

        Gdx.input.setInputProcessor(this);
    }
    public void render(float delta){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        forma.begin(ShapeRenderer.ShapeType.Filled);
        forma.setColor(Color.GREEN);
        for(int i=0;i<9;i++)
            forma.rect( serpiente.get(i).x, serpiente.get(i).y, 10, 10);
        forma.setColor(Color.WHITE);
        forma.rect( 0,250, 300, 20);

        forma.end();
        forma.begin(ShapeRenderer.ShapeType.Line);
        forma.setColor(Color.OLIVE);
        for(int i=0;i<9;i++)
            forma.rect( serpiente.get(i).x, serpiente.get(i).y, 10, 10);
        forma.end();

        cereza = new Rectangle(premio.getX(),premio.getY(),10,10);
        head=new Rectangle(serpiente.get(9).x,serpiente.get(9).y,10,10);
        salir=new Rectangle(0,250,20,20);
        pause=new Rectangle(25,250,20,20);

        spritebatch.begin();
        spritebatch.draw(grafico,premio.getX(),premio.getY(),10,10);
        spritebatch.draw(grafico1,serpiente.get(9).x,serpiente.get(9).y,10,10);
        spritebatch.draw(grafico2,0,250,20,20);
        spritebatch.draw(grafico3,25,250,20,20);
        writer.setColor(Color.BLACK);
        writer.getData().setScale( 0.5f);
        writer.draw(spritebatch, "Puntuacion : "+score, 75,265);
        writer.draw(spritebatch, "TIEMPO "+getCrono(), 200,265);
        spritebatch.end();
        choques();

        readyNextFPS();
    }
    public void choques(){
        if (Intersector.overlaps(cereza, head)) {
            premio.setPosicion();
            score++;}
    }

    public void readyNextFPS(){
        for (int i = 0; i < 9; i++)
            serpiente.get(i).add((serpiente.get(i + 1).cpy()).sub(serpiente.get(i).cpy()));
        serpiente.get(9).add(direccion);
        if(serpiente.get(9).x<0)
            direccion=new Vector2(10,0);
        else if(serpiente.get(9).x>290)
            direccion=new Vector2(-10,0);
        else if(serpiente.get(9).y<0)
            direccion=new Vector2(0,10);
        else if( serpiente.get(9).y>=240)
            direccion=new Vector2(0,-10);
        try {
            Thread.sleep(velocidad);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private String getCrono(){
        acelerador++;
        int minutos,segundos,millis;
        long instante=new Date().getTime()-inicioJuego;
        if(acelerador>=35) {
            velocidad--;
            acelerador=0;
        }
        if (instante>=60000)
            finDeJuego();
        long resto;
        minutos=(int)instante/60000;
        resto=instante%60000;
        segundos=(int)resto/1000; resto=resto%1000;
        millis=(int)resto;
        return minutos+" : "+segundos+" : "+millis;
    }
    public void finDeJuego(){
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
        if( (lista.size<10)) {
            String nombre="";
            switch(meuxogogame.getPlataforma()){
                case(MyGame.PLATAFORMA_DESKTOP):
                        nombre = JOptionPane.showInputDialog(null,"Mejores 10 puntuaciones\nIntroduzca su Nombre:",
                        "TIME OUT", JOptionPane.WARNING_MESSAGE);
                        break;
                case(MyGame.PLATAFORMA_ANDROID):
                    nombre = JOptionPane.showInputDialog(null,"Mejores 10 puntuaciones\nIntroduzca su Nombre:",
                            "TIME OUT", JOptionPane.WARNING_MESSAGE);
                    break;
            }

            if(nombre=="")
                nombre="unknown";
            lista.add(new Puntuaciones(nombre,score));
        }else
            comprobarPuntuaciones(score);

        guardarPuntuaciones();

        meuxogogame.setScreen(meuxogogame.getPantallaScores());
    }

    public void guardarPuntuaciones() {
        FileHandle arquivo = Gdx.files.external("score.txt");
        arquivo.writeString("", false);
        while (lista.size > 0) {
            int puntos = 0;
            int index=0;
            for (int i=0;i<lista.size;i++ )
                if (lista.get(i).getPuntos() > puntos) {
                    puntos = lista.get(i).getPuntos();
                    index = i;
                }
            arquivo.writeString(lista.get(index).getNombre() + ":" + lista.get(index).getPuntos() + "\n", true);
            lista.removeIndex(index);
        }
    }

    private void comprobarPuntuaciones(int score) {
        int puntos=lista.get(0).getPuntos();
        int index=0;
        for (int i=1;i<10;i++ )
            if (lista.get(i).getPuntos()< puntos) {
                puntos=lista.get(i).getPuntos();
                index = i;
            }
        if(score>puntos)
            lista.set(index,new Puntuaciones(JOptionPane.showInputDialog(null, "Mejores 10 puntuaciones\nIntroduzca su Nombre:",
                                "TIME OUT", JOptionPane.WARNING_MESSAGE), score));
    }

    public void resize(int width, int height) {
        camara2d.setToOrtho(false,300,270);
        camara2d.update();
        spritebatch.setProjectionMatrix(camara2d.combined);
        forma.setProjectionMatrix(camara2d.combined);
    }

    public void dispose(){
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case(Input.Keys.UP):
                direccion=new Vector2(0,10);
                 break;
            case(Input.Keys.DOWN):
                direccion=new Vector2(0,-10);
                break;
            case(Input.Keys.LEFT):
                direccion=new Vector2(-10,0);
                break;
            case(Input.Keys.RIGHT):
                direccion=new Vector2(10,0);
                break;
            default:
                break;
        }
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 dedo=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
        camara2d.unproject(dedo);
        Rectangle pulsacion=new Rectangle(dedo.x,dedo.y,10,10);
        if(Intersector.overlaps(pulsacion,salir))
            meuxogogame.setScreen(meuxogogame.getPantallaPresentacion());
        if(Intersector.overlaps(pulsacion,pause))
           meuxogogame.setScreen(meuxogogame.getPausa()) ;
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}
