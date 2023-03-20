public class GameManager {
    Player[] players;
    int count;
    Board board;

    public GameManager() {
        this.players = new Player[2];
        this.count = 0;
        this.board = new Board(new int[3][3]);
    }

    public boolean addPlayer(String name, int score, int symbol){
        if (players.length > 2)
        {
            Player temp = new Player(name, score, symbol);
            this.players[count] = temp;
            count++;
            return true;
        }else{
            return false;
        }
    }

    public void startGame(Player[] players, Board board){
        System.out.println(board.showBoard());
    }

}