package at.fhtw.slt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    // ==========================================
    // 1. Tests for switchCurrentPlayer method
    // ==========================================

    @Test
    public void testSwitchCurrentPlayer_Positive() {
        TicTacToe game = new TicTacToe();
        // Initially it's player1 ('X')
        game.switchCurrentPlayer();
        // Positive: Switched to player2 ('O')
        assertEquals('O', game.currentPlayer.getMarker(), "Current player should switch to 'O'.");
    }

    @Test
    public void testSwitchCurrentPlayer_Negative() {
        TicTacToe game = new TicTacToe();
        game.switchCurrentPlayer();
        // Negative: Verify it successfully changed and is no longer 'X'
        assertNotEquals('X', game.currentPlayer.getMarker(), "Current player should no longer be 'X' after switching.");
    }

    // ==========================================
    // 2. Tests for hasWinner method
    // ==========================================

    @Test
    public void testHasWinner_RowWin_Positive() {
        TicTacToe game = new TicTacToe();
        // Set up a winning row scenario for current player 'X'
        game.board.place(0, 0, 'X');
        game.board.place(0, 1, 'X');
        game.board.place(0, 2, 'X');

        // Positive: Three in a row means victory
        assertTrue(game.hasWinner(), "Three matching markers in a row should result in a win.");
    }

    @Test
    public void testHasWinner_NoWinner_Negative() {
        TicTacToe game = new TicTacToe();
        // Set up a mixed row where 'O' blocks 'X'
        game.board.place(0, 0, 'X');
        game.board.place(0, 1, 'O');
        game.board.place(0, 2, 'X');

        // Negative: Mixed markers do not trigger a win
        assertFalse(game.hasWinner(), "A blocked or mixed row should not trigger a win.");
    }
    // ==========================================
    // More advanced scenarios for hasWinner & Game Rules
    // ==========================================

    @Test
    public void testHasWinner_ColumnWin_Positive() {
        TicTacToe game = new TicTacToe();
        // Set up a winning vertical column (Column 1) for player 'X'
        game.board.place(0, 1, 'X');
        game.board.place(1, 1, 'X');
        game.board.place(2, 1, 'X');

        assertTrue(game.hasWinner(), "Three matching markers in a column should result in a win.");
    }

    @Test
    public void testHasWinner_DiagonalWin_Positive() {
        TicTacToe game = new TicTacToe();
        // Set up a winning diagonal (top-left to bottom-right) for player 'X'
        game.board.place(0, 0, 'X');
        game.board.place(1, 1, 'X');
        game.board.place(2, 2, 'X');

        assertTrue(game.hasWinner(), "Three matching markers diagonally should result in a win.");
    }

    @Test
    public void testGameDraw_Scenario() {
        TicTacToe game = new TicTacToe();
        // Fill the board in a way that results in a draw (no winner, board full)
        // X O X
        // X X O
        // O X O
        game.board.place(0, 0, 'X'); game.board.place(0, 1, 'O'); game.board.place(0, 2, 'X');
        game.board.place(1, 0, 'X'); game.board.place(1, 1, 'X'); game.board.place(1, 2, 'O');
        game.board.place(2, 0, 'O'); game.board.place(2, 1, 'X'); game.board.place(2, 2, 'O');

        assertFalse(game.hasWinner(), "A draw board should not have a winner.");
        assertTrue(game.board.isFull(), "A draw board must be completely full.");
    }
}