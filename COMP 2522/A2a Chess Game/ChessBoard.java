import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Represents the chessboard where pieces are placed and moved.
 * @author yongeun
 *
 */
class ChessBoard extends JPanel {
    private Piece[][] board = new Piece[8][8];
    
    private static final int TILE_SIZE = 60;
    private Piece selectedPiece = null;
    private int selectedRow = -1;
    private int selectedCol = -1;
    
    /**
     * Initializes the chessboard, placing pieces in their starting positions.
     */
    public ChessBoard() {
        this.initBoard();
    }

    /**
     * Places the pieces on the board in their initial positions.
     */
    public void initBoard() {
        // Black Pieces
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
        }
        
        // White Pieces
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(true);
        }
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);
    }

    /**
     * Moves a piece from a starting position to a destination position on the board.
     * 
     * @param stRow The starting row of the piece.
     * @param stCol The starting column of the piece.
     * @param desRow The destination row.
     * @param desCol The destination column.
     */
    public void movePiece(int x, int y) {
        int col = x / TILE_SIZE;
        int row = y / TILE_SIZE;

        if (selectedPiece == null) {
            //Select piece
            if (board[row][col] != null) {
                selectedPiece = board[row][col];
                selectedRow = row;
                selectedCol = col;
            }
        } else {
            board[row][col] = selectedPiece;
            board[selectedRow][selectedCol] = null;
            selectedPiece = null;
            selectedRow = -1;
            selectedCol = -1;
            
            //repaint the board
            repaint();
        }
    }

    /**
     * Paints the board and pieces on the JPanel. Called automatically by the Swing framework.
     * 
     * @param g The Graphics object for drawing.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if (board[i][j] != null) {
                    board[i][j].drawPiece(g, i, j);
                }
            }
        }
    }
}