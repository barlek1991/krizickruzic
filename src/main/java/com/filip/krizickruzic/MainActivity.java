package com.filip.krizickruzic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0=zeleno, 1= crveno

    int activePlayer= 0;

    boolean gameIsActive = true;

    // 2 znaci neodigrano

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2,};

    int[][] winningPositions= {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter= (ImageView) view;



        int tappedCounter= Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameIsActive) {

            gameState[tappedCounter]= activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.zelenikruzic);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.crvenikruzic);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(720).setDuration(300);

            for (int[]winningPositions: winningPositions){
                if (gameState[winningPositions[0]]== gameState[winningPositions[1]]&&
                        gameState[winningPositions[1]]== gameState[winningPositions[2]]&&
                        gameState[winningPositions[0]] !=2){

                    gameIsActive= false;

                    //imamo pobjednika!!

                    String winner= "Red";

                    if(gameState[winningPositions[0]]==0);{

                          winner= "Green";

                    }



                    TextView winnerMessage= (TextView)findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                } else {
                    boolean gameIsOver= true;
                    for (int counterState:gameState){

                        if (counterState==2) gameIsOver= false;
                    }

                    if (gameIsOver){

                        TextView winnerMessage= (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("Draw");

                        LinearLayout layout= (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);


                    }
                }
            }
        }
    }

    public void  playAgain(View view){

        gameIsActive= true;

        LinearLayout layout= (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

         activePlayer= 0;

         for (int i = 0; i< gameState.length; i++) {
             gameState[i]= 2;

         }
        GridLayout gridLayout= (GridLayout)findViewById(R.id.gridLayout);

         for (int i = 0; i< gridLayout.getChildCount(); i++ ){

             ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
         }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
