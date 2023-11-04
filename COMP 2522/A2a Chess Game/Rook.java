/**
 * Represents a Rook chess piece.
 * The Rook can move any number of squares horizontally or vertically.
 * 
 * @author yongeun
 */
public class Rook extends Piece {
    
    /**
     * Constructor that initializes the Rook chess piece.
     * 
     * @param white A boolean indicating if the piece is white (true) or black (false).
     */
    public Rook(boolean white) {
        super(white);
    }

    /**
     * Checks if the Rook can move from a starting square to a destination square.
     * The Rook can move horizontally or vertically.
     * Note: The current implementation always returns true. This might need to be updated for a complete chess logic.
     * 
     * @param stRow The starting row of the Rook.
     * @param stCol The starting column of the Rook.
     * @param desRow The destination row for the Rook.
     * @param desCol The destination column for the Rook.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        return true;
    }
    
    /**
     * Returns the symbol representing the Rook.
     * 
     * @return A string "R", which represents the Rook.
     */
    @Override
    public String getSymbol() {
        return "R";
    }
}