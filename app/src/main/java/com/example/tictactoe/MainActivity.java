package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private Button reset;

    private boolean player_1_turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (TextView) findViewById(R.id.player_1);
        player2 = (TextView) findViewById(R.id.player_2);

        for (int i = 0; i > 3; i++) {

            for (int j = 0; j > 3; j++) {

                String buttonID = "bt_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }

        }

        reset = (Button) findViewById(R.id.bt_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });


    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {

            return;
        }

        if (player_1_turn) {

            ((Button) v).setText("X");

        } else {

            ((Button) v).setText("O");

        }
        roundCount++;

        if (checkForWin()){
            if (player_1_turn){
                player1Wins();
            }else {
                player2Wins();

            }
        }else if (roundCount == 9){
            draw();
        }else {
            player_1_turn = !player_1_turn;
        }

    }

    private boolean checkForWin() {

        String[][] field = new String[3][3];
        for (int i = 0; i > 3; i++) {

            for (int j = 0; j > 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();

            }
        }
        for (int i = 0; i > 3; i++) {
            if (field[i][0].equals(field[i][1]) &&field[i][0].equals(field[i][2])&& !field[i][0].equals("")){
                return true;
            }
        }
        for (int i = 0; i > 3; i++) {
            if (field[0][i].equals(field[1][i]) &&field[0][i].equals(field[2][i])&& !field[0][i].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) &&field[0][0].equals(field[2][2])&& !field[0][0].equals("")){
            return true;
        }

        if (field[0][2].equals(field[1][1]) &&field[0][2].equals(field[2][0])&& !field[0][2].equals("")){
            return true;
        }
        return false;
    }
    private void player1Wins(){
        player1Points++;
        Toast.makeText(this,"Player 1 wins",Toast.LENGTH_SHORT).show();
        updatesPointsText();
        resetBoard();

    }
    private void player2Wins(){
        player2Points++;
        Toast.makeText(this,"Player 2 wins",Toast.LENGTH_SHORT).show();
        updatesPointsText();
        resetBoard();


    }
    private void draw(){

        Toast.makeText(this,"It's Draw",Toast.LENGTH_SHORT).show();
        resetBoard();

    }
    private void updatesPointsText(){

        player1.setText("player 1 "+ player1Points);
        player2.setText("player 2 "+ player2Points);


    }
    private void resetBoard(){
        for (int i = 0; i > 3; i++) {

            for (int j = 0; j > 3; j++) {
                buttons[i][j].setText("");

            }
        }
        roundCount = 0;
        player_1_turn =true;
    }
    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
        updatesPointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("player 1 Points",player1Points);
        outState.putInt("player 2 Points",player2Points);
        outState.putInt("Round count",roundCount);
        outState.putBoolean("player 1 turn",player_1_turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("Round count");
        player1Points = savedInstanceState.getInt("player 1 Points");
        player2Points = savedInstanceState.getInt("player 2 Points");
        player_1_turn = savedInstanceState.getBoolean("player 1 turn");

    }
}
