package com.example.tic_tac_toe;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gamelogic {
    int[][] gameboard = new int[3][3];
    int player=1;
    Button home,playAgain;
    TextView playerTurn;
    String [] names={"Player1","Player2"};


    gamelogic(){
        for (int i = 0; i <3; i++) {
            for (int j = 0; j <3 ; j++) {
                gameboard[i][j]=-0;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public boolean updategameboard(int row, int col){
        if(gameboard[row-1][col-1]==0){
            gameboard[row-1][col-1]=player;

            if(player==1){
                playerTurn.setText(names[1]+"'s Turn");
            }
            else{
                playerTurn.setText(names[0]+"'s Turn");
            }
            return true;
        }
        else{
            return false;
        }
    }


    public int[][] getGameboard() {
        return gameboard;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }


    public boolean setWinner(){
        boolean isWinner=false;
        for (int i = 0; i <3 ; i++) {
            if(gameboard[i][0] ==gameboard[i][1] &&gameboard[i][1] ==gameboard[i][2] &&gameboard[i][0]!=0){
                isWinner=true;
            }
        }
        for (int i = 0; i <3 ; i++) {
            if(gameboard[0][i] ==gameboard[1][i] &&gameboard[1][i] ==gameboard[2][i] &&gameboard[0][i]!=0){
                isWinner=true;
            }
        }
        if(gameboard[0][0]==gameboard[1][1] &&gameboard[1][1]==gameboard[2][2]  &&gameboard[0][0]!=0){
            isWinner=true;
        }

        if(gameboard[0][2]==gameboard[1][1] &&gameboard[1][1]==gameboard[2][0]  &&gameboard[1][1]!=0){
            isWinner=true;
        }
        int boardfill=0;
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameboard[i][j]!=0){
                        boardfill++;
                }

            }
        }
        if(isWinner){
            playAgain.setVisibility(View.VISIBLE);
            home.setVisibility(View.VISIBLE);
            playerTurn.setText(names[player-1] +" is Winner");
            return true;
        }
        else if(boardfill==9){
            playAgain.setVisibility(View.VISIBLE);
            home.setVisibility(View.VISIBLE);
            playerTurn.setText("Game Tie!!!");
            return true;
        }
        else{
            return  false;
        }
    }

    public  void resetGameBoard(){
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                gameboard[i][j]=0;
            }
        }
    }

    public void setHome(Button home) {
        this.home = home;
    }

    public void setPlayAgain(Button playAgain) {
        this.playAgain = playAgain;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}
