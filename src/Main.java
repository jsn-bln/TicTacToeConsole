import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        GameManager gameManager = new GameManager();
        gameManager.board.init(); // sets the boards initial value to 0


        System.out.println("TIC TAC TOE");
        System.out.println("Please select a mode");
        System.out.println("1. Two player mode");
        System.out.println("2. Easy AI");
        System.out.println("3. Impossible AI");
        int mode = keyboard.nextInt();
        keyboard.nextLine();

        if (mode == 1){
            System.out.println("Enter player 1 name: ");
            String name1 = keyboard.nextLine();
            System.out.println("Enter player 2 name: ");
            String name2 = keyboard.nextLine();


            gameManager.addPlayer(name1, 0, 1);
            gameManager.addPlayer(name2, 0, -1);

            System.out.println("Who do you want to play first?");
            System.out.println("1. " + name1);
            System.out.println("2. " + name2);

            int seq = 0;
            while (seq != 1 && seq != 2){
                seq = keyboard.nextInt();
                keyboard.nextLine();
            }

            Player ply1 = gameManager.players[0];
            Player ply2 = gameManager.players[1];

            int winner = 0;
            boolean player2moveDone = seq != 2;

            while (gameManager.board.isPlayable()) {

                // PLAYER MOVE
                // if player select ai to move first then skip player move in the first loop
                if (player2moveDone) {
                    int x1, y1;
                    do {
                        System.out.println(gameManager.board.showBoard());
                        System.out.println(ply1.getName() + "'s turn to move");
                        System.out.println("Choose a valid coordinate: e.g. [(0,0), (1,0), (0,1)]");
                        System.out.println("Please enter row coordinate");
                        x1 = keyboard.nextInt();
                        System.out.println("Please enter col coordinate");
                        y1 = keyboard.nextInt();
                        keyboard.nextLine();
                    } while (!gameManager.board.makeMove(ply1, x1, y1));

                    if (gameManager.board.checkNotRecursion(gameManager.board.getBoard()) != 0) {
                        winner = ply1.getSymbol();
                        break;
                    }
                }

                // PLAYER 2 MOVE
                int x2, y2;
                do {
                    System.out.println(gameManager.board.showBoard());
                    System.out.println(ply2.getName() + "'s turn to move");
                    System.out.println("Choose a valid coordinate: e.g. [(0,0), (1,0), (0,1)]");
                    System.out.println("Please enter row coordinate");
                    x2 = keyboard.nextInt();
                    System.out.println("Please enter col coordinate");
                    y2 = keyboard.nextInt();
                    keyboard.nextLine();
                } while (!gameManager.board.makeMove(ply2, x2, y2));
                player2moveDone = true;

                if (gameManager.board.checkNotRecursion(gameManager.board.getBoard()) != 0) {
                    winner = ply2.getSymbol();
                    break;
                }


            }
            // displays game status
            if (winner != 0){
                System.out.println(gameManager.board.showBoard());
                Player won = ply1.getSymbol() == winner? ply1: ply2;
                System.out.println(won.getName() + " wins!");
            }else {
                System.out.println(gameManager.board.showBoard());
                System.out.println("Match Draw!");
            }


        } else if (mode == 2) {
            System.out.println("Enter player name: ");
            String name = keyboard.nextLine();
            String symbol = "";
            while (!symbol.toUpperCase().equals("X") && !symbol.toUpperCase().equals("O")){
                System.out.println("Enter player symbol [X or O]: ");
                symbol = keyboard.nextLine();
            }

            int symb1 = symbol.toUpperCase().equals("X") ? 1:-1;
            int symb2 = symbol.toUpperCase().equals("X") ? -1:1;
            gameManager.addPlayer(name, 0, symb1);
            gameManager.addPlayer("AI", 0, symb2);

            System.out.println("Who do you want to play first?");
            System.out.println("1. You");
            System.out.println("2. AI");

            int seq = 0;
            while (seq != 1 && seq != 2){
                seq = keyboard.nextInt();
                keyboard.nextLine();
            }

            Player ply1 = gameManager.players[0];
            Player ply2 = gameManager.players[1];

            int winner = 0;
            boolean aiFirstMoveDone = seq != 2;
            while (gameManager.board.isPlayable()){

                // PLAYER MOVE
                // if player select ai to move first then skip player move in the first loop
                if (aiFirstMoveDone){
                    int x1,y1;
                    do {
                        System.out.println(gameManager.board.showBoard());
                        System.out.println(ply1.getName() + "'s turn to move");
                        System.out.println("Choose a valid coordinate: e.g. [(0,0), (1,0), (0,1)]");
                        System.out.println("Please enter row coordinate");
                        x1 = keyboard.nextInt();
                        System.out.println("Please enter col coordinate");
                        y1 = keyboard.nextInt();
                        keyboard.nextLine();
                    }while (!gameManager.board.makeMove(ply1, x1, y1));

                    if (gameManager.board.checkNotRecursion(gameManager.board.getBoard()) != 0){
                        winner = ply1.getSymbol();
                        break;
                    }
                }


                // AI MOVE
                gameManager.board.move(gameManager.board.getBoard(), ply2);
                aiFirstMoveDone = true;

                if (gameManager.board.checkNotRecursion(gameManager.board.getBoard()) != 0){
                    winner = ply2.getSymbol();
                    break;
                }
            }

            // displays game status
            if (winner != 0){
                System.out.println(gameManager.board.showBoard());
                Player won = ply1.getSymbol() == winner? ply1: ply2;
                System.out.println(won.getName() + " wins!");
            }else {
                System.out.println(gameManager.board.showBoard());
                System.out.println("Match Draw!");
            }


        }else if (mode == 3){
            System.out.println("Enter player name: ");
            String name = keyboard.nextLine();
            String symbol = "";
            while (!symbol.toUpperCase().equals("X") && !symbol.toUpperCase().equals("O")){
                System.out.println("Enter player symbol [X or O]: ");
                symbol = keyboard.nextLine();
            }

            int symb1 = symbol.toUpperCase().equals("X") ? 1:-1;
            int symb2 = symbol.toUpperCase().equals("X") ? -1:1;
            gameManager.addPlayer(name, 0, symb1);
            gameManager.addPlayer("AI", 0, symb2);

            System.out.println("Who do you want to play first?");
            System.out.println("1. You");
            System.out.println("2. AI");

            int seq = 0;
            while (seq != 1 && seq != 2){
                seq = keyboard.nextInt();
                keyboard.nextLine();
            }

            Player ply1 = gameManager.players[0];
            Player ply2 = gameManager.players[1];

            int winner = 0;
            // sets value to true if player will play first and false if ai will play first
            boolean aiFirstMoveDone = seq != 2;
            while (gameManager.board.isPlayable()){

                // PLAYER MOVE
                // if player select ai to move first then skip player move in the first loop
                if (aiFirstMoveDone){
                    int x1,y1;
                    do {
                        System.out.println(gameManager.board.showBoard());
                        System.out.println(ply1.getName() + "'s turn to move");
                        System.out.println("Choose a valid coordinate: e.g. [(0,0), (1,0), (0,1)]");
                        System.out.println("Please enter row coordinate");
                        x1 = keyboard.nextInt();
                        System.out.println("Please enter col coordinate");
                        y1 = keyboard.nextInt();
                        keyboard.nextLine();
                    }while (!gameManager.board.makeMove(ply1, x1, y1));

                    if (gameManager.board.checkNotRecursion(gameManager.board.getBoard()) != 0){
                        winner = ply1.getSymbol();
                        break;
                    }
                }


                // AI MOVE
                int[] bestMove = gameManager.board.bestAiMove(ply2);
                try{
                    gameManager.board.moveAi(bestMove[1], bestMove[2], ply2.getSymbol());
                }catch (ArrayIndexOutOfBoundsException ai){
                    // match draw when ai run out of valid spots
                    System.out.println("Match Draw");
                }
                aiFirstMoveDone = true;

                if (gameManager.board.checkNotRecursion(gameManager.board.getBoard()) != 0){
                    winner = ply2.getSymbol();
                    break;
                }

            }

            // displays game status
            if (winner != 0){
                System.out.println(gameManager.board.showBoard());
                Player won = ply1.getSymbol() == winner? ply1: ply2;
                System.out.println(won.getName() + " wins!");
            }else {
                System.out.println(gameManager.board.showBoard());
                System.out.println("Match Draw!");
            }

        }else {
            return;
        }


    }
}