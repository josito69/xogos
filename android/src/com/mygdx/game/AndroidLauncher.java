package com.mygdx.game;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	private MyGame mygame=new MyGame();
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        mygame.setPlataforma(MyGame.PLATAFORMA_ANDROID);
		initialize(new MyGame(), config);
	}
}
