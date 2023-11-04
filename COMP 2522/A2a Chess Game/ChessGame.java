import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Represents the main Chess game application, containing the game board and managing player interactions.
 * Initializes and displays the chessboard, and allows for starting and ending games.
 * 
 * @author yongeun
 */
public class ChessGame extends JFrame{
    /** The main chessboard where pieces are placed and moved. */
    private ChessBoard board;
    /** An array to hold the two players, indexed by their colors (0 for white, 1 for black). */
    private Player[] players = new Player[2];
    
    /**
     * Constructor for the ChessGame class.
     * Initializes the board and players, sets up the JFrame properties, and adds necessary event listeners.
     */
    public ChessGame() {
        this.board = new ChessBoard();
        this.players[0] = new Player(Color.WHITE, board);
        this.players[1] = new Player(Color.BLACK, board);
        
        setTitle("Chess Game");
        setSize(500, 512);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (endGame()) {
                    System.exit(0);
                }
            }
        });
        setLocationRelativeTo(null);
        setResizable(false);
        
        board = new ChessBoard();
        board.addMouseListener(new EventListener(board)); 
        
        add(board);
        setVisible(true);
    }

    /**
     * Starts a new game, initializing the board and placing the chess pieces in their initial positions.
     * 
     * @return true indicating the game has started.
     */
    public boolean startGame() {
        board.initBoard();
        return true;
    }

    /**
     * Ends the current game.
     * 
     * @return true indicating the game has ended.
     */
    public boolean endGame() {
        int response = JOptionPane.showConfirmDialog(this, 
                                                    "Do you really want to exit?", 
                                                    "Confirm Exit", 
                                                    JOptionPane.YES_NO_OPTION, 
                                                    JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }
    
    /**
     * Main method to start and run the chess game application.
     * 
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new ChessGame();
    }
}