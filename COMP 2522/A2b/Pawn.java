/**
 * Represents the Pawn chess piece. The Pawn moves forward one square, but
 * captures diagonally. Pawns have a unique starting move where they can advance
 * two squares forward. This class extends the Piece class and overrides its
 * abstract methods to provide functionality specific to the Pawn's movement and
 * representation.
 * 
 * @author yongeun
 */
class Pawn extends Piece {
    private Piece[][] chessBoard;

    /**
     * Constructor that initializes the Pawn piece.
     * 
     * @param white A boolean that indicates if the piece is white (true) or black
     *              (false).
     */
    public Pawn(boolean white, Piece[][] board) {
        super(white);
        this.chessBoard = board;
    }

    /**
     * Checks if the Pawn can move from a starting square to a destination square.
     * Pawns can move two squares forward from their initial position or one square
     * forward otherwise. This method only checks for forward movement and doesn't
     * consider diagonal captures.
     * 
     * @param stRow  The starting row of the Pawn.
     * @param stCol  The starting column of the Pawn.
     * @param desRow The destination row for the Pawn.
     * @param desCol The destination column for the Pawn.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        boolean isFirstMove = (isWhite() && stRow == 6) || (!isWhite() && stRow == 1);

        // Forward movement
        if (stCol == desCol) {
            if (isWhite()) {
                if (isFirstMove && desRow == stRow - 2) {
                    // The first step
                    return chessBoard[stRow - 1][stCol] == null && chessBoard[desRow][desCol] == null;
                }
                return desRow == stRow - 1 && chessBoard[desRow][desCol] == null;
            } else {
                if (isFirstMove && desRow == stRow + 2) {
                    // The first step
                    return chessBoard[stRow + 1][stCol] == null && chessBoard[desRow][desCol] == null;
                }
                return desRow == stRow + 1 && chessBoard[desRow][desCol] == null;
            }
        }

        // Capture diagonally
        if (Math.abs(stCol - desCol) == 1) {
            Piece destinationPiece = chessBoard[desRow][desCol];
            if (destinationPiece != null && destinationPiece.isWhite() != this.isWhite()) {
                if (isWhite() && desRow == stRow - 1) {
                    return true; // White pawn capturing diagonally
                } else if (!isWhite() && desRow == stRow + 1) {
                    return true; // Black pawn capturing diagonally
                }
            }
        }

        return false; // Invalid move
    }

    /**
     * Returns the symbol representing the Pawn. "P" represents a Pawn.
     * 
     * @return A string representing the Pawn.
     */
    @Override
    public String getSymbol() {
        return "P";
    }
}