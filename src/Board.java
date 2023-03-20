import java.lang.reflect.Array;
import java.util.Arrays;

public class Board {
    private int[][] board;
    public Board(int[][] board){
        this.board = board;
    }

    public void init(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = 0;
            }
        }
    }

    public int positionValue(int i,int j){
        return board[i][j];
    }

    public void populateSpot(Player player ,int i, int j){
        board[i][j] = player.getSymbol();
    }

    public boolean makeMove(Player player,int i, int j){
        if(positionValue(i,j) == 0 ){
            populateSpot(player, i,j);
            return true;
        }
        return false;
    }

    public String showBoard(){
        String boardview = "";

        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                boardview += "[" + board[i][j] + "]";
            }
            boardview += "\n";
        }
        return boardview;
    }
    public boolean check(int[][] arr, int row, int col, int val, int count) {
        if (row < 0 || col < 0 || row >= arr.length || col >= arr[row].length) {
            return count == 3;
        }
        if (arr[row][col] == val) {
            count++;
        } else {
            count = 1;
        }

        return check(arr, row, col+1, val, count)   // right
                || check(arr, row+1, col, val, count)   // down
                || check(arr, row+1, col+1, val, count) // diagonal down-right
                || check(arr, row-1, col+1, val, count); // diagonal up-right
    }

}