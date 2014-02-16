/* *********************
 * Dane Hjeresen       *
 * cs251 Section 001   *
 * 05-02-2014          *
 ***********************/

import cs251.lab2.*;

public class Gomoku implements GomokuModel {

    boolean playerOneTurn = true;
    String boardString;
    Square[][] Board = new Square [NUM_HSQUARES][NUM_VSQUARES];    

    boolean checkWinner(int row, int col){
        /*check whose turn it is. If it is their turn and their 
         * symbol has 5 in a row based off the most recent click
         * return true. The rest can be handled back in doClick
         */
        return false;
    }
    public java.lang.String boardString() {
        return boardString;
    }

    public GomokuModel.Outcome doClick(int row, int col) {
        if(Board[row][col] == Square.EMPTY)
        {
            Board[row][col] = (playerOneTurn ? Square.CROSS : Square.RING);
            updateString();
            /* Delete if you wanttt!!!!!! hehehe <33
             *if(playerOneTurn)
             *{
             *    Board[row][col] = Square.CROSS;
             *    updateString();
             *}
             *else {
             *    Board[row][col] = Square.RING;
             *    updateString();
             *}
             */
        }
        if(checkWinner(row, col))
            return (playerOneTurn ? Outcome.CROSS_WINS :  Outcome.RING_WINS );
        playerOneTurn = !playerOneTurn;
        return Outcome.GAME_NOT_OVER;
    }
    public void newGame() {
        for(int i = 0; i < Board.length; i++)
        {
            for(int j = 0; j < Board[0].length; j++)
            {
                Board[i][j] = Square.EMPTY;
            }
        }    
        updateString();   
    } 
    public void updateString() {
        String convertedBoard = "";
        for(int i = 0; i < Board.length; i++)
        {
            for(int j = 0; j < Board[0].length; j++)
            {
                convertedBoard += Board[i][j].toChar();
            }
            convertedBoard += "\n";
        }    
        boardString = convertedBoard;
    }

    public void setComputerPlayer(java.lang.String opponent) {

    }

    public static void main(String[] args) {
        Gomoku game = new Gomoku();
        game.newGame();
        if(args.length > 0) {
            game.setComputerPlayer(args [0]);
        }
        GomokuGUI.showGUI(game);

    }
}
