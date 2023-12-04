/**
 * Represents the Pawn chess piece. The Pawn has unique movement rules,
 * including its initial two-square forward move and capturing diagonally. This
 * class extends the Piece class and overrides its abstract methods to provide
 * functionality specific to the Pawn's movement and representation.
 * 
 * @author yongeun
 */
class Pawn extends Piece {

    private Piece[][][] chessBoard3D;
    private Piece[][] chessBoard;

    /**
     * Constructor that initializes the Pawn piece.
     * 
     * @param white A boolean that indicates if the piece is white (true) or black
     *              (false).
     */
    public Pawn(boolean white, Piece[][][] board) {
        super(white);
        this.chessBoard3D = board;
    }

    public Pawn(boolean white, Piece[][] board) {
        super(white);
        this.chessBoard = board;
    }

    /**
     * Checks if the Pawn can move from a starting position to a destination
     * position. The Pawn has unique movement rules, including its initial
     * two-square forward move and capturing diagonally.
     * 
     * @param srcRow    The starting row position.
     * @param srcCol    The starting column position.
     * @param srcLevel  The starting level position.
     * @param destRow   The destination row position.
     * @param destCol   The destination column position.
     * @param destLevel The destination level position.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int srcRow, int srcCol, int srcLevel, int destRow, int destCol, int destLevel) {
        boolean isFirstMove = (isWhite() && srcRow == 6) || (!isWhite() && srcRow == 1);

        // Check if the piece is moving to a different level
        if (srcLevel != destLevel) {
            // Allow the pawn to move to a different level if it is moving forward
            if (srcCol == destCol && ((isWhite() && destRow == srcRow - 1) || (!isWhite() && destRow == srcRow + 1))) {
                return true;
            }
            // Allow the pawn to move two levels if it is moving two steps forward
            else if (Math.abs(srcLevel - destLevel) == 2 && srcCol == destCol
                    && ((isWhite() && destRow == srcRow - 2) || (!isWhite() && destRow == srcRow + 2))) {
                // Check if the path is clear for both white and black players
                return (isWhite() && chessBoard3D[srcRow - 1][srcCol][srcLevel] == null
                        && chessBoard3D[destRow][destCol][destLevel] == null)
                        || (!isWhite() && chessBoard3D[srcRow + 1][srcCol][srcLevel] == null
                                && chessBoard3D[destRow][destCol][destLevel] == null);
            }
            return false;
        }

        if (srcCol == destCol) {
            if (isWhite()) {
                if (isFirstMove && destRow == srcRow - 2) {
                    return chessBoard3D[srcRow - 1][srcCol][srcLevel] == null
                            && chessBoard3D[destRow][destCol][srcLevel] == null;
                }
                return destRow == srcRow - 1 && chessBoard3D[destRow][destCol][srcLevel] == null;
            } else {
                if (isFirstMove && destRow == srcRow + 2) {
                    return chessBoard3D[srcRow + 1][srcCol][srcLevel] == null
                            && chessBoard3D[destRow][destCol][srcLevel] == null;
                }
                return destRow == srcRow + 1 && chessBoard3D[destRow][destCol][srcLevel] == null;
            }
        }

        if (Math.abs(srcCol - destCol) == 1) {
            Piece destinationPiece = chessBoard3D[destRow][destCol][srcLevel];
            if (destinationPiece != null && destinationPiece.isWhite() != this.isWhite()) {
                if (isWhite() && destRow == srcRow - 1) {
                    return true;
                } else if (!isWhite() && destRow == srcRow + 1) {
                    return true;
                }
            }
        }

        return false;
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

    /**
     * Checks if the Pawn can move in a 2D space from a starting position to a
     * destination position. The Pawn has unique movement rules, including its
     * initial two-square forward move and capturing diagonally.
     * 
     * @param srcRow  The starting row position.
     * @param srcCol  The starting column position.
     * @param destRow The destination row position.
     * @param destCol The destination column position.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int srcRow, int srcCol, int destRow, int destCol) {
        boolean isFirstMove = (isWhite() && srcRow == 6) || (!isWhite() && srcRow == 1);

        if (srcCol == destCol) {
            if (isWhite()) {
                if (isFirstMove && destRow == srcRow - 2) {
                    return chessBoard[srcRow - 1][srcCol] == null && chessBoard[destRow][destCol] == null;
                }
                return destRow == srcRow - 1 && chessBoard[destRow][destCol] == null;
            } else {
                if (isFirstMove && destRow == srcRow + 2) {
                    return chessBoard[srcRow + 1][srcCol] == null && chessBoard[destRow][destCol] == null;
                }
                return destRow == srcRow + 1 && chessBoard[destRow][destCol] == null;
            }
        }

        if (Math.abs(srcCol - destCol) == 1) {
            Piece destinationPiece = chessBoard[destRow][destCol];
            if (destinationPiece != null && destinationPiece.isWhite() != this.isWhite()) {
                if (isWhite() && destRow == srcRow - 1) {
                    return true;
                } else if (!isWhite() && destRow == srcRow + 1) {
                    return true;
                }
            }
        }

        return false;
    }
}
