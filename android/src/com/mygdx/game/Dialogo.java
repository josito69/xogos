package com.mygdx.game;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mygame.renderer.RendererXogoBeta;

public class Dialogo extends DialogFragment {
        private View rootView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.dialogo, container, false);

            Button btn = (Button) rootView.findViewById(R.id.button);   // Exemplo de referencia a un view dentro do layout do DialogFragment
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText texto=(EditText) rootView.findViewById(R.id.gamerName);
                    RendererXogoBeta.name=texto.getText().toString();
                }
            });

            return rootView;
        }
    }

