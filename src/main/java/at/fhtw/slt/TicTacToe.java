package at.fhtw.slt;

import java.util.Scanner;

// Main class controlling the game loop and rules execution
public class TicTacToe {
    Player player1;
    Player player2;
    Player currentPlayer;
    Board board; // Package-private access allows direct population in unit tests

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            board.clear();
            currentPlayer = player1;
            boolean gameActive = true;

            while (gameActive) {
                System.out.println("Current Player: " + currentPlayer.getMarker());
                board.print();

                int row = -1;
                int col = -1;
                boolean validMove = false;

                // Validate terminal user input
                while (!validMove) {
                    System.out.print("row (0-2): ");
                    if (scanner.hasNextInt()) {
                        row = scanner.nextInt();
                    } else {
                        scanner.next(); // Clear buffer input
                    }

                    System.out.print("column (0-2): ");
                    if (scanner.hasNextInt()) {
                        col = scanner.nextInt();
                    } else {
                        scanner.next(); // Clear buffer input
                    }

                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board.isCellEmpty(row, col)) {
                        validMove = true;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                }

                // Execute move
                board.place(row, col, currentPlayer.getMarker());

                // Evaluate board state rules
                if (hasWinner()) {
                    board.print();
                    System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                    gameActive = false;
                } else if (board.isFull()) {
                    board.print();
                    System.out.println("It's a draw!");
                    gameActive = false;
                } else {
                    switchCurrentPlayer();
                }
            }

            // Prompt user for a new game session matching User Stories
            System.out.print("Do you want to play again? (y/n): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("y");
        }

        System.out.println("Game Over. Thanks for playing!");
        scanner.close();
    }

    // Package-private allows direct verification in TicTacToeTest
    void switchCurrentPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    // Package-private allows game condition evaluation in testing
    boolean hasWinner() {
        char marker = currentPlayer.getMarker();

        // Check matching patterns across rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board.getCell(i, 0) == marker && board.getCell(i, 1) == marker && board.getCell(i, 2) == marker) ||
                    (board.getCell(0, i) == marker && board.getCell(1, i) == marker && board.getCell(2, i) == marker)) {
                return true;
            }
        }

        // Check matching patterns across diagonals
        if ((board.getCell(0, 0) == marker && board.getCell(1, 1) == marker && board.getCell(2, 2) == marker) ||
                (board.getCell(0, 2) == marker && board.getCell(1, 1) == marker && board.getCell(2, 0) == marker)) {
            return true;
        }

        return false;
    }
}