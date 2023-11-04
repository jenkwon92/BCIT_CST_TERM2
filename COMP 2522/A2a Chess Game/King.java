/**
 * Represents the King chess piece.
 * The King can move one square in any direction (horizontally, vertically, or diagonally).
 * This class extends the Piece class and overrides its abstract methods to 
 * provide functionality specific to the King's movement and representation.
 * 
 * @author yongeun
 */
class King extends Piece {
    
    /**
     * Constructor that initializes the King piece.
     * 
     * @param white A boolean that indicates if the piece is white (true) or black (false).
     */
    public King(boolean white) {
        super(white);
    }

    /**
     * Checks if the King can move from a starting square to a destination square.
     * The King can move one square in any direction.
     * 
     * @param stRow The starting row of the King.
     * @param stCol The starting column of the King.
     * @param desRow The destination row for the King.
     * @param desCol The destination column for the King.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        int xDistance = Math.abs(desRow - stRow);
        int yDistance = Math.abs(desCol - stCol);

        // 1 square forward
        if (xDistance <= 1 && yDistance <= 1) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns the symbol representing the King.
     * "K" represents a King.
     * 
     * @return A string representing the King.
     */
    @Override
    public String getSymbol() {
        return "K";
    }
}