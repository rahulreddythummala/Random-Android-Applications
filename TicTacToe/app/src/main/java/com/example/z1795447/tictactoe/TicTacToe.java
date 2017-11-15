package com.example.z1795447.tictactoe;


public class TicTacToe
{
    public static final int SIDE = 3;

    private int player;
    private int[][] game;

    public TicTacToe()
    {
        game = new int[SIDE][SIDE];
        resetGame();
    }//end constructor

    //Method to initialize game board to all 0s and set the
    //starting player to player 1

    public void resetGame()
    {
        for( int row = 0; row < SIDE; row++)
        {
            for(int col = 0; col < SIDE; col++)
            {
                game[row][col] = 0;
            }
        }

        player = 1;
    }

    //Process the desired play. If the spot is valid and hasn't been
    //played, mark the spot with the player number and then change
    //to the other player. Return the player that made the play.
    //If invalid, return 0.

    public int play(int row, int column)
    {
        int currentPlayer = player;

        //if the row and column values are valid and the spot is empty, put the player's
        //number in the array, switch the players, and return the player that made the move

        if (row >= 0 && row < SIDE && column >= 0 && column < SIDE && game[row][column] == 0)
        {
            game[row][column] = player;

            if (player == 1)
                player = 2;
            else
                player = 1;

            return currentPlayer;
        }

        //return 0 to indicate that something isn't correct with the specified row and
        //column
        return 0;
    }//end play

    //Determine which player won the game and return their player number. 0 is
    //returned to indicate there isn't a winnner.

    public int whoWon()
    {
        int rows = checkRows();
        if( rows > 0 )
            return rows;

        int columns = checkColumns();
        if( columns > 0 )
            return columns;

        int diagonals = checkDiagonals();
        if (diagonals > 0)
            return diagonals;

        return 0;
    }

    //Check the rows for a win. Return the player number if there is a winning row.

    protected int checkRows()
    {
        for (int row = 0; row < SIDE; row++ )
            if (game[row][0] != 0 && game[row][0] == game[row][1] && game[row][1] == game[row][2])
                return game[row][0];

        return 0;
    }

    //Check the columns for a win. Return the player number if there is a winning column.

    protected int checkColumns()
    {
        for (int col = 0; col < SIDE; col++ )
            if (game[0][col] != 0 && game[0][col] == game[1][col] && game[1][col] == game[2][col])
                return game[0][col];

        return 0;
    }

    //Check the diagonals for a win. Return the player number if there is a winning diagonal.

    protected int checkDiagonals()
    {
        if (game[0][0] != 0 && game[0][0] == game[1][1] && game[1][1] == game[2][2])
            return game[0][0];

        if (game[0][2] != 0 && game[0][2] == game[1][1] && game[1][1] == game[2][0])
            return game[0][2];

        return 0;
    }

    //Determine if the game board is full

    public boolean isBoardFull()
    {
        boolean boardFull = true;

        for( int row = 0; row < SIDE; row++)
        {
            for(int col = 0; col < SIDE; col++)
            {
                if(game[row][col] == 0)
                    boardFull = false;
            }
        }

        return boardFull;
    }

    //Determine if the game is over

    public boolean isGameOver()
    {
        return isBoardFull() || (whoWon() > 0);
    }

    public String result(){
        int winner = whoWon();
        if (winner >0)
            return "Player "+winner+" Won";
        else if (isBoardFull())
            return "Tie";
        else
            return "Let's Start !!! ";
    }
}//end of TicTacToe class

