/**
 * Represents the Pawn chess piece.
 * The Pawn moves forward one square, but captures diagonally.
 * Pawns have a unique starting move where they can advance two squares forward.
 * This class extends the Piece class and overrides its abstract methods to 
 * provide functionality specific to the Pawn's movement and representation.
 * 
 * @author yongeun
 */
class Pawn extends Piece {
    
    /**
     * Constructor that initializes the Pawn piece.
     * 
     * @param white A boolean that indicates if the piece is white (true) or black (false).
     */
    public Pawn(boolean white) {
        super(white);
    }

    /**
     * Checks if the Pawn can move from a starting square to a destination square.
     * Pawns can move two squares forward from their initial position or one square forward otherwise.
     * This method only checks for forward movement and doesn't consider diagonal captures.
     * 
     * @param stRow The starting row of the Pawn.
     * @param stCol The starting column of the Pawn.
     * @param desRow The destination row for the Pawn.
     * @param desCol The destination column for the Pawn.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        // Diagonal movement is not considered here
        if (isWhite()) {
            if (stCol == desCol) {
                if (stRow == 6 && desRow == stRow - 2) {
                    // 2 squares forward from the initial position
                    return true;
                }
                return desRow == stRow - 1; // 1 square forward
            }
        } else {
            if (stCol == desCol) {
                if (stRow == 1 && desRow == stRow + 2) {
                 // 2 squares backward from the initial position
                    return true;
                }
                return desRow == stRow + 1; // 1 square backward
            }
        }
        return false;
    }
    
    /**
     * Returns the symbol representing the Pawn.
     * "P" represents a Pawn.
     * 
     * @return A string representing the Pawn.
     */
    @Override
    public String getSymbol() {
        return "P";
    }
}