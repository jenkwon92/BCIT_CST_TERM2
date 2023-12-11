import java.awt.Graphics;

/**
 * Represents a Bishop chess piece. The Bishop moves diagonally on the chessboard.
 * 
 * @author yongeun
 */
public class Bishop extends Piece {
    private Piece[][] chessBoard;
    
    /**
     * Creates a new Bishop piece.
     * 
     * @param white Indicates whether this is a white piece (true for white, false for black).
     */
    public Bishop(boolean white, Piece[][] chessBoard) {
        super(white);
        this.chessBoard = chessBoard;
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
        // Only move diagonally
        if (Math.abs(stRow - desRow) != Math.abs(stCol - desCol)) {
            return false;
        }

        int rowStep = (desRow > stRow) ? 1 : -1;
        int colStep = (desCol > stCol) ? 1 : -1;

        int row = stRow + rowStep;
        int col = stCol + colStep;

        // Check the path
        while (row != desRow && col != desCol) {
            if (chessBoard[row][col] != null) {
                return false; // Path is not clear
            }
            row += rowStep;
            col += colStep;
        }

        // Check if the destination square has an opponent's piece or is empty
        Piece destinationPiece = chessBoard[desRow][desCol];
        return destinationPiece == null || destinationPiece.isWhite() != this.isWhite();
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