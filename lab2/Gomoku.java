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
        Square current = (playerOneTurn ? Square.CROSS : Square.RING);
        for(int i = -1; i < 1; i++)
        {
            for(int j = -1; j < 1; j++)
            { 
                if(row == 0 && i == -1) break;
                if(col == 0 && j == -1) break;
                if(col == NUM_VSQUARES && j == 1) break;
                if(row == NUM_HSQUARES && i == 1) break;
                if(current == Board[row + i][col + j])
                {
                    int curi = i + 1;
                    int curj = j + 1;
                    int currentCount = 0;
                    while(currentCount < 5){
                        if(row == 0 && curi == -1) currentCount = 6;
                        if(col == 0 && curj == -1) currentCount = 6;
                        if(col == NUM_VSQUARES && curj == 1) currentCount = 6;
                        if(row == NUM_HSQUARES && curi == 1) currentCount = 6;
                        if(current == Board[row + curi][col + curj])
                        {
                            currentCount++;
                            if(currentCount == 4) return true;
                        }
                    }
                    int curNegi = i - 1;
                    int curNegj = j - 1;
                    while(currentCount < 5){
                        if(row == 0 && curNegi == -1) currentCount = 6;
                        if(col == 0 && curNegj == -1) currentCount = 6;
                        if(col == NUM_VSQUARES && curNegj == 1) currentCount = 6;
                        if(row == NUM_HSQUARES && curNegi == 1) currentCount = 6;
                        if(current == Board[row + curNegi][col + curNegj])
                        {
                            currentCount++;
                            if(currentCount == 4) return true;
                        }
                    }
                }
            }
        }
        updateString();   
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
