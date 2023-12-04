import javax.swing.JPanel;

/**
 * Abstract class representing a chess board.
 */
public abstract class Board extends JPanel {
    /** The current level of the 3D chess board. */
    protected int currentLevel;

    /**
     * Constructor for the Board class. Initializes the currentLevel to 0.
     */
    public Board() {
        this.currentLevel = 0;
    }

    /**
     * Sets the current level of the 3D chess board.
     *
     * @param level The level to set.
     */
    public void setCurrentLevel(int level) {
        currentLevel = level;
    }

    /**
     * Gets the current level of the 3D chess board.
     *
     * @return The current level.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Initializes the chess board.
     */
    public abstract void initBoard();

    /**
     * Draws the current state of the chess board.
     */
    public abstract void drawBoard();

    /**
     * Gets the chess piece at the specified position in a standard chess game.
     *
     * @param row The row of the chess board.
     * @param col The column of the chess board.
     * @return The chess piece at the specified position.
     */
    public abstract Piece getPieceAt(int row, int col);

    /**
     * Sets the chess piece at the specified position in a standard chess game.
     *
     * @param row   The row of the chess board.
     * @param col   The column of the chess board.
     * @param piece The piece to set at the specified position.
     */
    public abstract void setPieceAt(int row, int col, Piece piece);

    /**
     * Removes the chess piece at the specified position in a standard chess game.
     *
     * @param row The row of the chess board.
     * @param col The column of the chess board.
     */
    public abstract void removePieceAt(int row, int col);

    /**
     * Moves a chess piece in a standard chess game.
     *
     * @param stRow The starting row of the piece.
     * @param stCol The starting column of the piece.
     */
    public abstract void movePiece(int stRow, int stCol);

    /**
     * Gets the chess piece at the specified position in a 3D chess game.
     *
     * @param level The level of the chess board.
     * @param row   The row of the chess board.
     * @param col   The column of the chess board.
     * @return The chess piece at the specified position.
     */
    public abstract Piece getPieceAt(int level, int row, int col);

    /**
     * Sets the chess piece at the specified position in a 3D chess game.
     *
     * @param level The level of the chess board.
     * @param row   The row of the chess board.
     * @param col   The column of the chess board.
     * @param piece The piece to set at the specified position.
     */
    public abstract void setPieceAt(int level, int row, int col, Piece piece);

    /**
     * Removes the chess piece at the specified position in a 3D chess game.
     *
     * @param level The level of the chess board.
     * @param row   The row of the chess board.
     * @param col   The column of the chess board.
     */
    public abstract void removePieceAt(int level, int row, int col);

    /**
     * Moves a chess piece in a 3D chess game.
     *
     * @param level  The level of the chess board.
     * @param stRow  The starting row of the piece.
     * @param stCol  The starting column of the piece.
     * @param desRow The destination row for the piece.
     * @param desCol The destination column for the piece.
     */
    public abstract void movePiece(int level, int stRow, int stCol, int desRow, int desCol);

    /**
     * Gets the JPanel representing the chess board.
     *
     * @return The JPanel representing the chess board.
     */
    public abstract JPanel getPanel();

    /**
     * Sets the selected tile on the chess board.
     *
     * @param row The row of the selected tile.
     * @param col The column of the selected tile.
     */
    protected abstract void setSelectedTile(int row, int col);
}
