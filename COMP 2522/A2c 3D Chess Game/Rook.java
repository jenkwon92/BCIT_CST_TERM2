/**
 * Represents a Rook chess piece. The Rook can move any number of squares
 * horizontally, vertically, or in depth in a 3D chess board.
 * 
 * @author yongeun
 */
public class Rook extends Piece {
    private Piece[][][] chessBoard3D;
    private Piece[][] chessBoard;

    /**
     * Constructor for the Rook chess piece in a 2D chess board.
     * 
     * @param white      A boolean indicating if the piece is white (true) or black
     *                   (false).
     * @param chessBoard The 2D chess board on which the Rook is placed.
     */
    public Rook(boolean white, Piece[][] chessBoard) {
        super(white);
        this.chessBoard = chessBoard;
    }

    /**
     * Constructor for the Rook chess piece in a 3D chess board.
     * 
     * @param white A boolean indicating if the piece is white (true) or black
     *              (false).
     * @param board The 3D chess board on which the Rook is placed.
     */
    public Rook(boolean white, Piece[][][] board) {
        super(white);
        this.chessBoard3D = board;
    }

    /**
     * Determines if the Rook can move from a starting square to a destination
     * square in a 2D chess board.
     * 
     * @param stRow  The starting row of the Rook.
     * @param stCol  The starting column of the Rook.
     * @param desRow The destination row for the Rook.
     * @param desCol The destination column for the Rook.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        // Horizontal movement: same row, different column
        if (stRow == desRow && stCol != desCol) {
            if (isPathClearHorizontally(stRow, stCol, desCol)) {
                // Check if the destination square has an opponent's piece
                return chessBoard[desRow][desCol] == null || chessBoard[desRow][desCol].isWhite() != this.isWhite();
            }
        }
        // Vertical movement: same column, different row
        else if (stCol == desCol && stRow != desRow) {
            if (isPathClearVertically(stCol, stRow, desRow)) {
                // Check if the destination square has an opponent's piece
                return chessBoard[desRow][desCol] == null || chessBoard[desRow][desCol].isWhite() != this.isWhite();
            }
        }
        return false;
    }

    /**
     * Determines if the Rook can move from a starting square to a destination
     * square in a 3D chess board.
     * 
     * @param stRow    The starting row of the Rook.
     * @param stCol    The starting column of the Rook.
     * @param stLevel  The starting level of the Rook.
     * @param desRow   The destination row for the Rook.
     * @param desCol   The destination column for the Rook.
     * @param desLevel The destination level for the Rook.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int stLevel, int desRow, int desCol, int desLevel) {
        // Horizontal movement: same row, different column, any level
        if (stRow == desRow && stCol != desCol) {
            return isPathClearHorizontally(stRow, stCol, desCol, stLevel) && isPathClearHorizontally(stRow, stCol, desCol, desLevel);
        }
        // Vertical movement: same column, different row, any level
        else if (stCol == desCol && stRow != desRow) {
            return isPathClearVertically(stCol, stRow, desRow, stLevel) && isPathClearVertically(stCol, stRow, desRow, desLevel);
        }
        // Depth movement: same row, same column, different level
        else if (stRow == desRow && stCol == desCol && stLevel != desLevel) {
            return isPathClearDepth(stRow, stCol, stLevel, desLevel);
        }
        return false;
    }

    /**
     * Gets the symbol representing the Rook.
     * 
     * @return A string "R" representing the Rook.
     */
    @Override
    public String getSymbol() {
        return "R";
    }

    /**
     * Check if the horizontal path is clear at a specific level in the 3D chess
     * board.
     * 
     * @param row      The row of the Rook.
     * @param startCol The starting column of the Rook's movement.
     * @param endCol   The ending column of the Rook's movement.
     * @param level    The level at which the Rook is moving.
     * @return true if the path is clear, false otherwise.
     */
    private boolean isPathClearHorizontally(int row, int startCol, int endCol, int level) {
        int colStep = startCol < endCol ? 1 : -1;
        for (int col = startCol + colStep; col != endCol; col += colStep) {
            if (chessBoard3D[row][col][level] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the vertical path is clear at a specific level in the 3D chess
     * board.
     * 
     * @param col      The column of the Rook.
     * @param startRow The starting row of the Rook's movement.
     * @param endRow   The ending row of the Rook's movement.
     * @param level    The level at which the Rook is moving.
     * @return true if the path is clear, false otherwise.
     */
    private boolean isPathClearVertically(int col, int startRow, int endRow, int level) {
        int rowStep = startRow < endRow ? 1 : -1;
        for (int row = startRow + rowStep; row != endRow; row += rowStep) {
            if (chessBoard3D[row][col][level] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the depth path is clear at a specific row and column in the 3D chess
     * board.
     * 
     * @param row        The row of the Rook.
     * @param col        The column of the Rook.
     * @param startLevel The starting level of the Rook's movement.
     * @param endLevel   The ending level of the Rook's movement.
     * @return true if the path is clear, false otherwise.
     */
    private boolean isPathClearDepth(int row, int col, int startLevel, int endLevel) {
        int levelStep = startLevel < endLevel ? 1 : -1;
        for (int level = startLevel + levelStep; level != endLevel; level += levelStep) {
            if (chessBoard3D[row][col][level] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the horizontal path is clear on the 2D chess board.
     * 
     * @param row      The row of the Rook.
     * @param startCol The starting column of the Rook's movement.
     * @param endCol   The ending column of the Rook's movement.
     * @return true if the path is clear, false otherwise.
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
     * Check if the vertical path is clear on the 2D chess board.
     * 
     * @param col      The column of the Rook.
     * @param startRow The starting row of the Rook's movement.
     * @param endRow   The ending row of the Rook's movement.
     * @return true if the path is clear, false otherwise.
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
