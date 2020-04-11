package com.mygdx.game;
import android.app.FragmentManager;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication {
	Plataforma platform;
	String name="";
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		platform=new Plataforma() {
			@Override
			public void onPressGuardar () {
				runOnUiThread(new Runnable() {
					public void run() {
						FragmentManager fm = getFragmentManager();
						Dialogo dialogoFragmento=new Dialogo();
						dialogoFragmento.show(fm, "MEJORES PUNTUACIONES");
					}
				});
			}
		};
		initialize(new MyGame(platform,0), config);
	}
}
