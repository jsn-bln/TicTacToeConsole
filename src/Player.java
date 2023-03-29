public class Player {
    private String name;
    private int score;
    private int symbol;

    public Player(String name, int score, int symbol) {
        this.name = name;
        this.score = score;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }
    public int getOppositeSymbol(){
        return -symbol;
    }
}