package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Gamedisplay_activity extends AppCompatActivity {
gameboard game;
gamelogic game2;
Button playAgain,home;
TextView playerTurn;
String names[]=new String [2];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamedisplay);


        game=findViewById(R.id.gameboard2);

        playAgain=findViewById(R.id.playagain);
        home=findViewById(R.id.returnhome);
        playerTurn=findViewById(R.id.playerturn);
        names=getIntent().getStringArrayExtra("Playernames");


        home.setVisibility(View.GONE);
        playAgain.setVisibility(View.GONE);



        if(names!=null){
            playerTurn.setText(names[0] + "'s Turn");
        }


          game.setGameboard(home,playAgain,playerTurn,names);





    }
    public void backHome(View view){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void playAgain(View view){
        game.resetGame();
        game.invalidate();
    }

}