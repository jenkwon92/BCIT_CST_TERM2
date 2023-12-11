/**
 * Represents a Rook chess piece. The Rook can move any number of squares
 * horizontally or vertically.
 * 
 * @author yongeun
 */
public class Rook extends Piece {
    private Piece[][] chessBoard;

    /**
     * Constructor that initializes the Rook chess piece.
     * 
     * @param white A boolean indicating if the piece is white (true) or black
     *              (false).
     */
    public Rook(boolean white, Piece[][] chessBoard) {
        super(white);
        this.chessBoard = chessBoard;
    }

    /**
     * Checks if the Rook can move from a starting square to a destination square.
     * The Rook can move horizontally or vertically. Note: The current
     * implementation always returns true. This might need to be updated for a
     * complete chess logic.
     * 
     * @param stRow  The starting row of the Rook.
     * @param stCol  The starting column of the Rook.
     * @param desRow The destination row for the Rook.
     * @param desCol The destination column for the Rook.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        // Horizontal movement: Same row, different column
        if (stRow == desRow && stCol != desCol) {
            if (isPathClearHorizontally(stRow, stCol, desCol)) {
                // Check if the destination square has an opponent's piece
                return chessBoard[desRow][desCol] == null || chessBoard[desRow][desCol].isWhite() != this.isWhite();
            }
        }
        // Vertical movement: Same column, different row
        else if (stCol == desCol && stRow != desRow) {
            if (isPathClearVertically(stCol, stRow, desRow)) {
                // Check if the destination square has an opponent's piece
                return chessBoard[desRow][desCol] == null || chessBoard[desRow][desCol].isWhite() != this.isWhite();
            }
        }
        // If the move is neither horizontal nor vertical, it's not a valid move
        return false;
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

    /**
     * Method to check if the path is clear horizontally. This method verifies if
     * there are any pieces in the horizontal path between the starting and ending
     * columns.
     * 
     * @param row      The row in which the movement is taking place.
     * @param startCol The starting column of the move.
     * @param endCol   The ending column of the move.
     * @return true if the path is clear, false if there is any piece in the way.
     */
    private boolean isPathClearHorizontally(int row, int startCol, int endCol) {
        int colStep = startCol < endCol ? 1 : -1;
        for (int col = startCol + colStep; col != endCol; col += colStep) {
            if (chessBoard[row][col] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if the path is clear vertically. This method verifies if
     * there are any pieces in the vertical path between the starting and ending
     * rows.
     * 
     * @param col      The column in which the movement is taking place.
     * @param startRow The starting row of the move.
     * @param endRow   The ending row of the move.
     * @return true if the path is clear, false if there is any piece in the way.
     */
    private boolean isPathClearVertically(int col, int startRow, int endRow) {
        int rowStep = startRow < endRow ? 1 : -1;
        for (int row = startRow + rowStep; row != endRow; row += rowStep) {
            if (chessBoard[row][col] != null) {
                return false;
            }
        }
        return true;
    }
}