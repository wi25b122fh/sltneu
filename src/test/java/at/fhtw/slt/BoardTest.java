package at.fhtw.slt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    // ==========================================
    // 1. Tests for isCellEmpty method
    // ==========================================

    @Test
    public void testIsCellEmpty_Positive() {
        Board board = new Board();
        // Positive: A fresh board must have empty cells
        assertTrue(board.isCellEmpty(0, 0), "A new board should have empty cells.");
    }

    @Test
    public void testIsCellEmpty_Negative() {
        Board board = new Board();
        board.place(0, 0, 'X');
        // Negative: Cell must not be empty after placing a marker
        assertFalse(board.isCellEmpty(0, 0), "Cell should not be empty after a marker is placed.");
    }

    // ==========================================
    // 2. Tests for place method
    // ==========================================

    @Test
    public void testPlace_Positive() {
        Board board = new Board();
        board.place(1, 1, 'O');
        // Positive: Verify the value is stored correctly
        assertEquals('O', board.getCell(1, 1), "The cell should contain the marker 'O'.");
    }

    @Test
    public void testPlace_Negative() {
        Board board = new Board();
        board.place(2, 2, 'X');
        // Negative: Placing a marker should not alter unrelated cells
        assertEquals(' ', board.getCell(0, 0), "Placing a marker in (2,2) should not affect (0,0).");
    }

    // ==========================================
    // 3. Tests for isFull method
    // ==========================================

    @Test
    public void testIsFull_Positive() {
        Board board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.place(i, j, 'X');
            }
        }
        // Positive: Full board returns true
        assertTrue(board.isFull(), "Board should be full when all cells are occupied.");
    }

    @Test
    public void testIsFull_Negative() {
        Board board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 2) continue; // Leave one cell empty
                board.place(i, j, 'O');
            }
        }
        // Negative: Board with an empty cell returns false
        assertFalse(board.isFull(), "Board should not be full if at least one cell is empty.");
    }

    // ==========================================
    // 4. Tests for clear method
    // ==========================================

    @Test
    public void testClear_Positive() {
        Board board = new Board();
        board.place(0, 0, 'X');
        board.clear();
        // Positive: Cells reset back to empty space character
        assertEquals(' ', board.getCell(0, 0), "Cell should be reset to empty.");
    }

    @Test
    public void testClear_Negative() {
        Board board = new Board();
        board.place(1, 1, 'O');
        board.clear();
        // Negative: Verifying via state checks instead of direct character check
        assertTrue(board.isCellEmpty(1, 1), "isCellEmpty should return true after clear.");
    }
}