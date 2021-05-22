package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1 : red , 2: empty

    int[][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int player = 0;

    // 0:Game not won, 1: game won

    int gameStatus = 0;

    public void fallIn(View view){



        ImageView counter = (ImageView)view;

        String tagString = counter.getTag().toString();

        int tag = Integer.parseInt(tagString);

        if(gameState[tag]==2 && gameStatus==0) {

            gameState[tag] = player;

            String message;

            counter.setTranslationY(-1500);


            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                message = "Yellow has won";
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                message = "Red has won";
                player = 0;
            }

            counter.animate().rotation(3600).translationYBy(1500).setDuration(600);

            int draw=0;
            for(int i=0; i<gameState.length; i++) {
                if (gameState[i] == 0 || gameState[i] == 1)
                    draw = 1;
                if (gameState[i] == 2) {
                    draw = 0;
                    break;
                }
            }
            for (int winning[] : winningPosition) {
                if (gameState[winning[0]] == gameState[winning[1]] && gameState[winning[0]] == gameState[winning[2]] && gameState[winning[0]] != 2){
                    gameStatus=1;
                    TextView WinnerText = (TextView)findViewById(R.id.WinnerText);
                    Button playAgain = (Button)findViewById(R.id.playAgain);
                    WinnerText.setText(message);
                    WinnerText.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                    draw=0;
                }
            }

                    if(draw==1){
                        TextView WinnerText = (TextView)findViewById(R.id.WinnerText);
                        Button playAgain = (Button)findViewById(R.id.playAgain);
                        message="That's a draw";
                        WinnerText.setText(message);
                        WinnerText.setVisibility(View.VISIBLE);
                        playAgain.setVisibility(View.VISIBLE);
                    }

        }

    }

    public void play (View view){
        TextView WinnerText = (TextView)findViewById(R.id.WinnerText);
        Button playAgain = (Button)findViewById(R.id.playAgain);
        WinnerText.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        player = 0;
        gameStatus = 0;

        Arrays.fill(gameState, 2);

        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);


       for(int i=0; i<grid.getChildCount(); i++)
        {
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}