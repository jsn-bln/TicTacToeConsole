public class GameManager {
    Player[] players;
    int count;
    Board board;

    public GameManager() {
        this.players = new Player[2];
        this.count = 0;
        this.board = new Board(new int[3][3]);
    }

    public void addPlayer(String name, int score, int symbol){
        if (count < 2)
        {
            Player temp = new Player(name, score, symbol);
            this.players[count] = temp;
            count++;
        }
    }

    public Player getPlayer1(){ return players[0]; }
    public Player getPlayer2(){
        return players[1];
    }



}