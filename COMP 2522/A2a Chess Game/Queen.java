/**
 * Represents a Queen chess piece.
 * The Queen can move any number of squares horizontally, vertically, or diagonally.
 * 
 * @author yongeun
 */
public class Queen extends Piece {
    
    /**
     * Constructor that initializes the Queen chess piece.
     * 
     * @param white A boolean indicating if the piece is white (true) or black (false).
     */
    public Queen(boolean white) {
        super(white);
    }

    /**
     * Checks if the Queen can move from a starting square to a destination square.
     * The Queen can move horizontally, vertically, or diagonally.
     * 
     * @param stRow The starting row of the Queen.
     * @param stCol The starting column of the Queen.
     * @param desRow The destination row for the Queen.
     * @param desCol The destination column for the Queen.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        // Horizontally & Vertically
        if (stRow == desRow || stCol == desCol) {
            return true;
        }
        // Diagonally
        if (Math.abs(desRow - stRow) == Math.abs(desCol - stCol)) {
            return true;
        }

        return false;
    }
    
    /**
     * Returns the symbol representing the Queen.
     * 
     * @return A string "Q", which represents the Queen.
     */
    @Override
    public String getSymbol() {
        return "Q";
    }
}