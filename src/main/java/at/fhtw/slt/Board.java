package at.fhtw.slt;

// Class responsible for managing the game board state
public class Board {
    private char[][] cells;

    public Board() {
        cells = new char[3][3];
        clear();
    }

    public boolean isCellEmpty(int x, int y) {
        return cells[x][y] == ' ';
    }

    public void place(int x, int y, char marker) {
        cells[x][y] = marker;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ') {
                    return false; // Empty space found, board is not full
                }
            }
        }
        return true; // All cells are occupied
    }

    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    public void print() {
        System.out.println("▁▁▁▁▁▁");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(cells[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("▔▔▔▔");
    }

    // Helper method to retrieve cell status for verification in TicTacToe and Tests
    public char getCell(int x, int y) {
        return cells[x][y];
    }
}