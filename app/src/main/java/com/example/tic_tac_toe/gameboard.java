package com.example.tic_tac_toe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class gameboard extends View {
    TypedArray at;
    int bg,xColor,oColor,winningLineColor;
    int cellsize=getWidth()/3;
    gamelogic game=new gamelogic();
    Paint paint =new Paint();
    boolean winningLine=false;
    public gameboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        at=context.getTheme().obtainStyledAttributes(attrs,R.styleable.gameboard,0,0);
        try{
            bg=at.getInteger(R.styleable.gameboard_gameBackground,0);
            xColor=at.getInteger(R.styleable.gameboard_xColor,0);
            oColor=at.getInteger(R.styleable.gameboard_oColor,0);
            winningLineColor=at.getInteger(R.styleable.gameboard_winninglinecolor,0);

        }
        finally {
        at.recycle();
        }
    }

    @Override
    public void onMeasure(int width,int height){
        super.onMeasure(width,height);
        int dimension=Math.min(getMeasuredHeight(),getMeasuredWidth());
        setMeasuredDimension(dimension,dimension);
        cellsize=dimension/3;

    }
    @Override
    public void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawGameBoard(canvas);
        drawMarkers(canvas);



    }

@SuppressLint("ClickableViewAccessibility")
@Override
  public  boolean onTouchEvent(MotionEvent event){
        float x=event.getX();
        float y=event.getY();

        int action=event.getAction();
        if(action==MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y / cellsize);
            int col = (int) Math.ceil(x / cellsize);

            if (!winningLine) {
                if (game.updategameboard(row, col)) {
                    invalidate();

                    if(game.setWinner()){
                        winningLine=true;
                        invalidate();
                    }

                    if (game.getPlayer() % 2 == 0) {
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }

                }
                invalidate();
                return true;
            }

        }
        return  false;
}

    public  void drawGameBoard(Canvas canvas) {
        paint.setColor(bg);
        paint.setStrokeWidth(16);

        for (int i = 1; i < 3; i++) {
            canvas.drawLine(0,cellsize*i,canvas.getWidth(),cellsize*i,paint);

        }
        for (int i = 1; i <3 ; i++) {
            canvas.drawLine(cellsize*i,0,cellsize*i,canvas.getWidth(),paint);
        }
    }


    public void drawMarkers(Canvas canvas){
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                if(game.getGameboard()[i][j]!=0){
                    if(game.getGameboard()[i][j]==1){
                        drawX(canvas,i,j);
                    }
                    else if(game.getGameboard()[i][j]==2){
                        drawO(canvas,i,j);
                    }
                }
            }
        }
    }



    public void drawX(Canvas canvas,int row,int col){
      paint.setColor(xColor);




        canvas.drawLine((col+1)*cellsize-(cellsize*0.2f),(row*cellsize)+cellsize*0.2f,(col*cellsize)+cellsize*0.2f,((row+1)*cellsize)-cellsize*0.2f,paint);
       canvas.drawLine((col)*cellsize+cellsize*0.2f,row*cellsize+cellsize*0.2f,(cellsize*(col+1))-cellsize*0.2f,((row+1)*cellsize)-cellsize*0.2f,paint);
    }

    public void drawO(Canvas canvas,int row,int col){
       paint.setColor(oColor);
        canvas.drawOval(col*cellsize+(cellsize*0.2f),(row)*cellsize+(cellsize*0.2f),(col+1)*cellsize-(cellsize*0.2f),(row+1)*cellsize-(cellsize*0.2f),paint);

    }

    public void resetGame(){
        game.resetGameBoard();
    }

    public void setGameboard(Button home, Button playAgain, TextView playTurn, String [] names){
        game.setHome(home);
        game.setPlayAgain(playAgain);
        game.setPlayerTurn(playTurn);
        game.setNames(names);

    }

}
