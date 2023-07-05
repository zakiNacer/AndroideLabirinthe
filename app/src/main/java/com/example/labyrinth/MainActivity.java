package com.example.labyrinth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private boolean gameIsRunning = false;

    Modele monModele;

    private Handler myHandler;
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            if(gameIsRunning) {
                vueJeu.invalidate();
            }

            myHandler.postDelayed(this, 100);
        }
    };

    ZoneDessin vueJeu;
    LinearLayout zoneJeu;

    Button LeftBouton;
    Button UpBouton;
    Button TurnRight;
    Button GetDown;
    Button rejouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monModele = new Modele(this);


//        bouton qui permet de tourner a gauche
        LeftBouton = (Button) findViewById(R.id.gaucheBouton);
        LeftBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameIsRunning)
                    monModele.GetLeft();
            }
        });
       // bouton qui permet de remonter
        UpBouton = (Button) findViewById(R.id.hautBouton);
        UpBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameIsRunning)
                    monModele.GetUp();
            }
        });
        //bouton qui permet de tourner a droite
        TurnRight = (Button) findViewById(R.id.droiteBouton);
        TurnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameIsRunning)
                    monModele.GetRight();
            }
        });

        // bouton qui permet de décendre
        GetDown = (Button) findViewById(R.id.enbasBouton);
        GetDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameIsRunning)
                    monModele. GetDown();
            }
        });

        //bouton permet de rejouer la partie
        rejouer = (Button) findViewById(R.id.rejourBouton);
        rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monModele.rebuildLabyrinthe();
                Commencer();
            }
        });

        vueJeu = new ZoneDessin(this, monModele);
        zoneJeu = (LinearLayout) findViewById(R.id.zoneJeu);
        zoneJeu.addView(vueJeu);

        Commencer();
    }

    public boolean isGameIsRunning(){
        return gameIsRunning;
    }

    public void Commencer(){
        rejouer.setVisibility(View.GONE);
        gameIsRunning = true;
        myHandler = new Handler();
        myHandler.postDelayed(myRunnable, 100);
    }
    public void stop(){
        rejouer.setVisibility(View.VISIBLE);
        gameIsRunning = false;
        if(myHandler != null)
            myHandler.removeCallbacks(myRunnable);
    }
}