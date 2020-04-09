package com.mygdx.game.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		MyGame mygame=new MyGame();
		mygame.setPlataforma(MyGame.PLATAFORMA_DESKTOP);
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "La serpiente";
		config.resizable=true;

		new LwjglApplication(mygame, config);
	}
}
