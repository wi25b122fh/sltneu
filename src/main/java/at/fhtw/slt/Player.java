package at.fhtw.slt;

// Class representing a game player and their designated marker ('X' or 'O')
public class Player {
    private char marker;

    public Player(char marker) {
        this.marker = marker;
    }

    public char getMarker() {
        return marker;
    }
}