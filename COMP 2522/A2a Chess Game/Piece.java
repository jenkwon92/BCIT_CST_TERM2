import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Represents a generic chess piece.
 * This class provides common functionality and properties for all chess pieces.
 * It's designed to be an abstract base class, with specific chess pieces (like Pawn, Bishop, King, etc.) 
 * extending it to provide piece-specific behaviors.
 * 
 * @author yongeun
 */
abstract class Piece {
    protected boolean white; // Indicates if the piece is white or black
    protected boolean killed;  // Indicates if the piece has been captured
    
    private static final int TILE_SIZE = 60; // The size of a single chessboard square
    
    /**
     * Constructor that initializes the chess piece.
     * 
     * @param white A boolean indicating if the piece is white (true) or black (false).
     */
    public Piece(boolean white) {
        this.white = white;
    }

    /**
     * Checks if the piece can move from a starting square to a destination square.
     * The specific movement rules are defined in subclasses (e.g., Pawn, King).
     * 
     * @param stRow The starting row of the piece.
     * @param stCol The starting column of the piece.
     * @param desRow The destination row for the piece.
     * @param desCol The destination column for the piece.
     * @return true if the move is valid, false otherwise.
     */
    public abstract boolean canMove(int stRow, int stCol, int desRow, int desCol);

    /**
     * Draws the chess piece on the chessboard.
     * The color and symbol of the piece are determined by its type and color (white or black).
     * 
     * @param g The Graphics object used to draw the piece.
     * @param row The row where the piece should be drawn.
     * @param col The column where the piece should be drawn.
     */
    public void drawPiece(Graphics g, int row, int col) {
        String symbol = getSymbol();
        if (this.white) {
            g.setColor(Color.RED); //White pieces
        } else {
            g.setColor(Color.BLUE); //Black pieces
        }
        g.setFont(new Font("SansSerif", Font.BOLD, 40)); //Font size
        g.drawString(symbol, col * TILE_SIZE + TILE_SIZE / 3, (row + 1) * TILE_SIZE - TILE_SIZE / 3);
    }

    /**
     * Checks if the piece is white.
     * 
     * @return true if the piece is white, false otherwise.
     */
    public boolean isWhite() {
        return white;
    }


    /**
     * Returns the symbol representing the piece.
     * The specific symbol is determined in subclasses (e.g., "P" for Pawn, "K" for King).
     * 
     * @return A string representing the piece's symbol.
     */
    public abstract String getSymbol();
}
