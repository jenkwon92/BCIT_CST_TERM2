import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Represents a 3D Chess Board, extending the functionality of a standard chess
 * board to include three levels. Players can move pieces across levels, and the
 * board is visualized as three separate boards in a row. Handles player moves,
 * piece selection, and captures in a 3D chess game.
 * 
 * @author yongeun
 */
class ChessBoard3D extends ChessBoard {
    private Piece[][][] board; // 3D representation of the chess board
    private int selectedRow;
    private int selectedCol;
    private int selectedLevel;

    private boolean currentPlayerWhite = true;

    // Create 3 JPanel for 3D Chess
    private JPanel panel = new JPanel(); // Master panel to hold all the chess boards.
    private JPanel[] boards = new JPanel[3]; // Array of panels for each chess board.

    /**
     * Constructs a 3D Chess Board, initializing the board and setting up graphical
     * components.
     */
    public ChessBoard3D() {
        this.board = new Piece[8][8][3]; // 3D array to represent the chess board

        this.initBoard(); // Initialize the board with chess pieces
        panel.setLayout(new GridLayout(1, 3)); // Arrange the chess boards in a row.

        for (int i = 0; i < 3; i++) {
            final int level = i; // The level of this chess board.
            boards[i] = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    drawBoard(g, level); // Draw the chess board at the specified level
                }
            };

            // Add MouseListener to each JPanel
            boards[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectPiece(e.getX(), e.getY(), level); // Handle piece selection on click
                }
            });

            panel.add(boards[i]); // Add the chess board to the master panel.
        }
    }

    /**
     * Draws the chess board and pieces at the specified level.
     * 
     * @param g     The Graphics object used for drawing.
     * @param level The level of the chess board to draw.
     */
    private void drawBoard(Graphics g, int level) {
        // Draw the chess board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // Draw the chess pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j][level];
                if (piece != null) {
                    piece.drawPiece(g, i, j); // Draw the piece at the specified level
                }
            }
        }
    }

    /**
     * Initializes the 3D chess board with the standard chess piece setup.
     */
    @Override
    public void initBoard() {
        // Initialize black pieces
        board[0][0][0] = new Rook(false, board);
        board[0][1][0] = new Knight(false, board);
        board[0][2][0] = new Bishop(false, board);
        board[0][3][0] = new Queen(false, board);
        board[0][4][0] = new King(false, board);
        board[0][5][0] = new Bishop(false, board);
        board[0][6][0] = new Knight(false, board);
        board[0][7][0] = new Rook(false, board);

        for (int i = 0; i < 8; i++) {
            board[1][i][0] = new Pawn(false, board);
        }

        // Initialize white pieces
        for (int i = 0; i < 8; i++) {
            board[6][i][0] = new Pawn(true, board);
        }
        board[7][0][0] = new Rook(true, board);
        board[7][1][0] = new Knight(true, board);
        board[7][2][0] = new Bishop(true, board);
        board[7][3][0] = new Queen(true, board);
        board[7][4][0] = new King(true, board);
        board[7][5][0] = new Bishop(true, board);
        board[7][6][0] = new Knight(true, board);
        board[7][7][0] = new Rook(true, board);

        // Initialize the other levels with no pieces
        for (int level = 1; level < 3; level++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[i][j][level] = null;
                }
            }
        }
    }

    /**
     * Gets the master JPanel that holds all the chess boards.
     * 
     * @return The master JPanel.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Gets an array of JPanels representing each chess board.
     * 
     * @return An array of JPanels.
     */
    public JPanel[] getPanels() {
        return boards;
    }

    /**
     * Moves a chess piece to the specified destination coordinates and level.
     * 
     * @param selectedRow   The row of the selected piece.
     * @param selectedCol   The column of the selected piece.
     * @param selectedLevel The level of the selected piece.
     * @param desRow        The destination row.
     * @param desCol        The destination column.
     * @param desLevel      The destination level.
     */
    public void movePiece(int selectedRow, int selectedCol, int selectedLevel, int desRow, int desCol, int desLevel) {
        Piece selectedPiece = board[selectedRow][selectedCol][selectedLevel];

        if (selectedPiece != null
                && selectedPiece.canMove(selectedRow, selectedCol, selectedLevel, desRow, desCol, desLevel)) {
            // If there is a piece at the destination, capture it
            Piece destinationPiece = board[desRow][desCol][desLevel];
            if (destinationPiece != null) {
                capturePiece(destinationPiece);
            }

            // Move the piece
            board[desRow][desCol][desLevel] = selectedPiece;
            board[selectedRow][selectedCol][selectedLevel] = null;

            // Redraw the boards
            for (JPanel boardPanel : boards) {
                boardPanel.repaint();
            }
        }
    }

    /**
     * Captures a chess piece, removing it from the game board.
     * 
     * @param piece The piece to be captured.
     */
    public void capturePiece(Piece piece) {
        // Remove the piece from the game board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for (int k = 0; k < board[i][j].length; k++) {
                    if (board[i][j][k] == piece) {
                        board[i][j][k] = null;
                        break;
                    }
                }
            }
        }
        // Additional logic can be added here, such as updating the score.
    }

    /**
     * Handles the selection of a chess piece on a specific board level.
     * 
     * @param x     The x-coordinate of the selected tile.
     * @param y     The y-coordinate of the selected tile.
     * @param level The level of the selected tile.
     */
    public void selectPiece(int x, int y, int level) {
        int col = x / TILE_SIZE;
        int row = y / TILE_SIZE;

        System.out.println("Selected tile at " + row + ", " + col + ", " + level);

        Piece piece = board[row][col][level];

        if (selectedRow == -1 && selectedCol == -1) {
            // No piece selected yet
            if (piece != null && piece.isWhite() == currentPlayerWhite) {
                // Select the piece if it's the current player's turn
                setSelectedTile(row, col, level);
                System.out.println("Selected piece at " + row + ", " + col + ", " + level);
            } else if (piece != null) {
                JOptionPane.showMessageDialog(null, "Not your turn");
            }
        } else {
            // A piece is already selected
            if (piece != null && piece.isWhite() == currentPlayerWhite) {
                // Select the other piece of the same player
                setSelectedTile(row, col, level);
                System.out.println("Selected piece at " + row + ", " + col + ", " + level);
            } else if (board[selectedRow][selectedCol][selectedLevel].canMove(selectedRow, selectedCol, selectedLevel,
                    row, col, level)) {
                // Move the piece if the move is valid
                movePiece(selectedRow, selectedCol, selectedLevel, row, col, level);
                // Reset the selected piece
                setSelectedTile(-1, -1, -1);
                // Change the turn
                currentPlayerWhite = !currentPlayerWhite;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Move");
                // Reset the selected piece
                setSelectedTile(-1, -1, -1);
            }
        }
    }

    /**
     * Sets the row, column, and level of the selected tile.
     * 
     * @param row   The row of the selected tile.
     * @param col   The column of the selected tile.
     * @param level The level of the selected tile.
     */
    public void setSelectedTile(int row, int col, int level) {
        this.selectedRow = row;
        this.selectedCol = col;
        this.selectedLevel = level;
    }
}
