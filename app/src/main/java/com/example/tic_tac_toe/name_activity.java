package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class name_activity extends AppCompatActivity {
EditText player1name,player2name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        player1name=findViewById(R.id.firstname);
        player2name=findViewById(R.id.secondname);









    }
    public void SubmitButton(View view){
        Intent intent =new Intent(this,Gamedisplay_activity.class);
        String firstplayername=player1name.getText().toString();
        String secondplayername=player2name.getText().toString();
        intent.putExtra("Playernames",new String[]{firstplayername,secondplayername});
        startActivity(intent);
    }
}