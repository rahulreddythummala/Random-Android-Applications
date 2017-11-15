package com.example.z1795447.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button[][]buttons;
    private TicTacToe game;
    private TextView gameStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        game = new TicTacToe();
        buildGUI();

    }

    public void buildGUI(){
        //Get the screen size
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x / TicTacToe.SIDE;

        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(TicTacToe.SIDE);
        //to hold text view
        gridLayout.setRowCount(TicTacToe.SIDE+1);

        buttons  = new Button[TicTacToe.SIDE][TicTacToe.SIDE];

        ButtonHandler buttonHandler = new ButtonHandler();

        for ( int row = 0;row<TicTacToe.SIDE;row++){
            for (int col = 0; col<TicTacToe.SIDE;col++){
                buttons[row][col] = new Button(this);
                buttons[row][col].setTextSize((int)(screenWidth*0.2));
                buttons[row][col].setOnClickListener(buttonHandler);


                gridLayout.addView(buttons[row][col],screenWidth,screenWidth);
            }
        }
        gameStatus = new TextView(this);
        GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE,1),
                        colSpec = GridLayout.spec(0,TicTacToe.SIDE);
        GridLayout.LayoutParams layoutParams =  new GridLayout.LayoutParams(rowSpec,colSpec);
        gameStatus.setLayoutParams(layoutParams);
        gameStatus.setWidth(TicTacToe.SIDE * screenWidth);
        gameStatus.setHeight(screenWidth);
        gameStatus.setGravity(Gravity.CENTER);
        gameStatus.setBackgroundColor(Color.CYAN);
        gameStatus.setTextSize((int)(screenWidth*0.15));
        gameStatus.setText(game.result());
        gridLayout.addView(gameStatus);
        setContentView(gridLayout);


    }//end build GUI

    public void update (int row,int col){
        //Toast.makeText(this,"update : "+row+", "+col,Toast.LENGTH_LONG).show();
        //buttons[row][col].setText("X");
        //whos is paying 1 or 2??
        int currentPlayer = game.play(row,col);
        if (currentPlayer == 1)
            buttons[row][col].setText("X");
        else if (currentPlayer==2)
            buttons[row][col].setText("0");
        if (game.isGameOver()) {
            gameStatus.setBackgroundColor(Color.BLUE);
            enableButtons(false);
            gameStatus.setText(game.result());
            showNewDialogue();
        }


    }
    public void enableButtons(boolean enabled){
        for ( int row = 0;row<TicTacToe.SIDE;row++){
            for (int col = 0; col<TicTacToe.SIDE;col++) {
                buttons[row][col].setEnabled(enabled)   ;
            }
        }
    }
    private class ButtonHandler implements View.OnClickListener{

        @Override
        public void onClick(View view) {
           // Toast.makeText(MainActivity.this, "Button Handler : "+ view, Toast.LENGTH_SHORT).show();
            for ( int row = 0;row<TicTacToe.SIDE;row++){
                for (int col = 0; col<TicTacToe.SIDE;col++){
                    if (view == buttons[row][col]){
                        update(row,col);
                    }
                }
            }
        }
    }
    public void resetButtons(){
        for ( int row = 0;row<TicTacToe.SIDE;row++){
            for (int col = 0; col<TicTacToe.SIDE;col++){
                    buttons[row][col].setText("");
                }
            }
        }

    public void showNewDialogue() {
        AlertDialog .Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Tic Tac Toe");
        alert.setMessage("Wanna buckle up again ? ");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                game.resetGame();
                enableButtons(true);
                resetButtons();
                gameStatus.setBackgroundColor(Color.CYAN);
                gameStatus.setText(game.result());
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        }); 
        alert.show();

    }
}
