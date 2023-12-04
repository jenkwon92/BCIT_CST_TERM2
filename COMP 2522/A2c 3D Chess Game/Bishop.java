import java.awt.Graphics;

/**
 * Represents a Bishop chess piece. The Bishop moves diagonally on the
 * chessboard. It extends the Piece class and provides specific functionality
 * for Bishop movement and representation.
 * 
 * @author yongeun
 */
public class Bishop extends Piece {
    private Piece[][][] chessBoard3D; // 3D chess board representation
    private Piece[][] chessBoard; // 2D chess board representation

    /**
     * Creates a new Bishop piece.
     * 
     * @param white      Indicates whether this is a white piece (true for white,
     *                   false for black).
     * @param chessBoard The 2D chess board.
     */
    public Bishop(boolean white, Piece[][] chessBoard) {
        super(white);
        this.chessBoard = chessBoard;
    }

    /**
     * Creates a new Bishop piece for 3D chess.
     * 
     * @param white Indicates whether this is a white piece (true for white, false
     *              for black).
     * @param board The 3D chess board.
     */
    public Bishop(boolean white, Piece[][][] board) {
        super(white);
        this.chessBoard3D = board;
    }

    /**
     * Determines whether the Bishop can move from a starting position to a
     * destination position. Bishops move diagonally, so the absolute difference
     * between rows and columns should be the same.
     * 
     * @param stRow  Starting row position.
     * @param stCol  Starting column position.
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
     * Determines whether the Bishop can move from a starting position to a
     * destination position in 3D chess. Bishops move diagonally in rows and
     * columns, and the level can be any.
     * 
     * @param stRow    Starting row position.
     * @param stCol    Starting column position.
     * @param stLevel  Starting level position.
     * @param desRow   Destination row position.
     * @param desCol   Destination column position.
     * @param desLevel Destination level position.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(int stRow, int stCol, int stLevel, int desRow, int desCol, int desLevel) {
        // Only move diagonally in row and column, level can be any
        if (Math.abs(stRow - desRow) != Math.abs(stCol - desCol)) {
            return false;
        }

        int rowStep = (desRow > stRow) ? 1 : -1;
        int colStep = (desCol > stCol) ? 1 : -1;
        int levelStep = (desLevel > stLevel) ? 1 : -1;

        int row = stRow + rowStep;
        int col = stCol + colStep;
        int level = stLevel + levelStep;

        // Check the path
        while (row != desRow && col != desCol) {
            if (chessBoard3D[row][col][level] != null) {
                return false; // Path is not clear
            }
            row += rowStep;
            col += colStep;
            level += levelStep;
        }

        // Check if the destination square has an opponent's piece or is empty
        Piece destinationPiece = chessBoard3D[desRow][desCol][desLevel];
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
