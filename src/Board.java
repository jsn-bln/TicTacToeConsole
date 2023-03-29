import java.util.Random;

public class Board {
    private int[][] board;
    public Random rand;
    public Board(int[][] board){
        this.board = board;
        rand = new Random();
    }

    public void init(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = 0;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public int positionValue(int i, int j){
        return board[i][j];
    }

    public void populateSpot(Player player ,int i, int j){
        board[i][j] = player.getSymbol();
    }

    public boolean makeMove(Player player,int i, int j){
        try {
            if(positionValue(i,j) == 0 ){
                populateSpot(player, i,j);
                return true;
            }
        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            return  false;
        }
        return false;
    }

    public String showBoard(){
        String boardview = "";
        String[] chars = new String[]{"0","1","2"};
        boardview += "  0  1  2\n";
        String symbol = "";


        for(int i = 0; i < board.length; i++){
            boardview += chars[i];
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 0){
                    symbol = " ";
                } else if (board[i][j] == 1) {
                    symbol = "X";
                }
                else if (board[i][j] == -1) {
                    symbol = "O";
                }
                boardview +=  "[" + symbol + "]";
            }
            boardview += "\n";
        }
        return boardview;
    }
    public boolean check(int[][] board, int row, int col, int val, int count) {
        // row and col is the coordinates of the last move to check
        // BASE CASE ( 3 count means somebody has won )
        if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) {
            return count == 3;
        }
        if (board[row][col] == val) {
            count++;
        } else {
            count = 1;
        }

        return check(board, row, col+1, val, count)   // right
                || check(board,row+1, col, val, count)   // down
                || check(board,row+1, col+1, val, count) // diagonal down-right
                || check(board,row-1, col+1, val, count); // diagonal up-right
    }

    public int checkNotRecursion(int[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 1) {
                    return 1; // Player 1 wins
                } else if (board[i][0] == -1) {
                    return -1; // Player 2 wins
                }
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                if (board[0][j] == 1) {
                    return 1; // Player 1 wins
                } else if (board[0][j] == -1) {
                    return -1; // Player 2 wins
                }
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 1) {
                return 1; // Player 1 wins
            } else if (board[0][0] == -1) {
                return -1; // Player 2 wins
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 1) {
                return 1; // Player 1 wins
            } else if (board[0][2] == -1) {
                return -1; // Player 2 wins
            }
        }

        // If no above conditions where satisfied means the game is a draw
        return 0; // Match draw
    }

    public int[] move(int[][] board ,Player player){
        int x2 = 0, y2 = 0;
        if (isPlayable()){
            do {
                x2 = rand.nextInt(3);
                y2 = rand.nextInt(3);
            } while (!makeMove(player, x2, y2));
        }
        return new int[]{x2,y2};
    }

    public boolean isPlayable(){
        // Iterates through the board and returns true
        // if the board still has a cell that has a value of 0
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 0){
                    return true;
                }
            }
        }
        return false;
    }


    // MINIMAX CODE



    public int[] minimax(int[][] board, Player player, int depth, boolean isMaximizing) {
        // Initializes the value of bestScore depending if the player is trying to maximize or minimize the score
        // Sets the value to the max int value if player is minimizing
        // Sets the value to the min int value if player is maximizing
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = new int[2];

        //BASE CASE
        // Stops game if game has a winner or depth is 0 which means the game has been drawn
        if (checkNotRecursion(board) != 0 || depth == 0) {
            int score = evaluate(board, player);
            return new int[]{score, -1, -1}; // returns an array of score and coordinate of the best move
        }

        // Loop through the board to find valid spot
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    // sets the value of the spot in the board to symbol of the maximizing or minimizing player
                    board[i][j] = isMaximizing ? player.getSymbol() : player.getOppositeSymbol();
                    // calls minimax recursively, subtracts 1 to depth ( going up the BST )
                    // and inverting isMaximizing ( false when it is simulation the human move )
                    int[] currentMove = minimax(board, player, depth - 1, !isMaximizing);
                    int currentScore = currentMove[0];
                    board[i][j] = 0; // Undo the move

                    // Update the best move and score
                    if ((isMaximizing && currentScore > bestScore) || (!isMaximizing && currentScore < bestScore)) {
                        bestScore = currentScore;
                        bestMove = new int[]{bestScore, i, j};
                    }
                }
            }
        }

        return bestMove; // returns an array of score and coordinate of the best move
    }

    public int evaluate(int[][] board, Player player) {
        int winner = checkNotRecursion(board);
        if (winner == player.getSymbol()) {
            return 1;
        } else if (winner == player.getOppositeSymbol()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int[] bestAiMove(Player aiPlayer) {
        return minimax(board, aiPlayer, 9, true);
    }

    public void moveAi(int x,int y, int symbol){
        board[x][y] = symbol;
    }



}