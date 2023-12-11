/**
 * Represents a Queen chess piece.
 * The Queen can move any number of squares horizontally, vertically, or diagonally.
 * 
 * @author yongeun
 */
public class Queen extends Piece {
    private Piece[][] chessBoard;
    
    /**
     * Constructor that initializes the Queen chess piece.
     * 
     * @param white A boolean indicating if the piece is white (true) or black (false).
     */
    public Queen(boolean white, Piece[][] chessBoard) {
        super(white);
        this.chessBoard = chessBoard;
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
            // Check if the path is clear and if the destination square has an opponent's piece or is empty
            return (isPathClearHorizontally(stRow, stCol, desCol) || isPathClearVertically(stCol, stRow, desRow))
                    && (chessBoard[desRow][desCol] == null || chessBoard[desRow][desCol].isWhite() != this.isWhite());
        }
        // Diagonally
        if (Math.abs(desRow - stRow) == Math.abs(desCol - stCol)) {
            // Check if the path is clear and if the destination square has an opponent's piece or is empty
            return isPathClearDiagonally(stRow, stCol, desRow, desCol)
                    && (chessBoard[desRow][desCol] == null || chessBoard[desRow][desCol].isWhite() != this.isWhite());
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
    
    /**
     * Checks if the diagonal path from the start position to the end position is clear of other pieces.
     * This method is used to determine if a piece like a Bishop or Queen can move diagonally without any obstruction.
     *
     * @param startRow The starting row of the piece.
     * @param startCol The starting column of the piece.
     * @param endRow The destination row of the piece.
     * @param endCol The destination column of the piece.
     * @return true if the path is clear, false if there is any piece in the way.
     */
    private boolean isPathClearDiagonally(int startRow, int startCol, int endRow, int endCol) {
        int rowStep = (endRow > startRow) ? 1 : -1;
        int colStep = (endCol > startCol) ? 1 : -1;

        int row = startRow + rowStep;
        int col = startCol + colStep;

        while (row != endRow && col != endCol) {
            if (chessBoard[row][col] != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }

        return true;
    }

}