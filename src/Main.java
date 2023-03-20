public class Main {
    public static void main(String[] args) {

        GameManager gameManager = new GameManager();
        gameManager.board.init();
        System.out.println(gameManager.board.showBoard());
    }
}