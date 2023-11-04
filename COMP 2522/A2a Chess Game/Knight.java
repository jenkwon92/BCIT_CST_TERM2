/**
 * Represents the Knight chess piece.
 * The Knight moves in an L-shape: it can move two squares vertically and one square horizontally, 
 * or two squares horizontally and one square vertically.
 * This class extends the Piece class and overrides its abstract methods to 
 * provide functionality specific to the Knight's movement and representation.
 * 
 * @author yongeun
 */
public class Knight extends Piece {
    
    /**
     * Constructor that initializes the Knight piece.
     * 
     * @param white A boolean that indicates if the piece is white (true) or black (false).
     */
    public Knight(boolean white) {
        super(white);
    }

    /**
     * Checks if the Knight can move from a starting square to a destination square.
     * The Knight moves in an L-shape.
     * 
     * @param stRow The starting row of the Knight.
     * @param stCol The starting column of the Knight.
     * @param desRow The destination row for the Knight.
     * @param desCol The destination column for the Knight.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        // Check the L-shaped movement
        if ((Math.abs(stRow - desRow) == 2 && Math.abs(stCol - desCol) == 1) || 
            (Math.abs(stRow - desRow) == 1 && Math.abs(stCol - desCol) == 2)) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns the symbol representing the Knight.
     * "N" represents a Knight.
     * 
     * @return A string representing the Knight.
     */
    @Override
    public String getSymbol() {
        return "N";
    }
}