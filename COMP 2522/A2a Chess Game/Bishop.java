import java.awt.Graphics;

/**
 * Represents a Bishop chess piece. The Bishop moves diagonally on the chessboard.
 * 
 * @author yongeun
 */
public class Bishop extends Piece {
    
    /**
     * Creates a new Bishop piece.
     * 
     * @param white Indicates whether this is a white piece (true for white, false for black).
     */
    public Bishop(boolean white) {
        super(white);
    }

    /**
     * Determines whether the Bishop can move from a starting position to a destination position.
     * Bishops move diagonally, so the absolute difference between rows and columns should be the same.
     * 
     * @param stRow Starting row position.
     * @param stCol Starting column position.
     * @param desRow Destination row position.
     * @param desCol Destination column position.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        // Diagonally
        return Math.abs(stRow - desRow) == Math.abs(stCol - desCol);
    }
    
    /**
     * Returns the symbol representing the Bishop. 
     * 
     * @return "B" for Bishop.
     */
    @Override
    public String getSymbol() {
        return "B";
    }
}