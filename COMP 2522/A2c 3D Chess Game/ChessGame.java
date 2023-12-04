import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Represents the main Chess game application, containing the game board and managing player interactions.
 * Initializes and displays the chessboard, and allows for starting and ending games.
 * 
 * @author yongeun
 */
public class ChessGame extends JFrame {
    /** The main chessboard where pieces are placed and moved. */
    private final ChessBoard board = new ChessBoard();
   
    // 3D Chess board
    private final ChessBoard3D board3D = new ChessBoard3D();
    
    /** An array to hold the two players, indexed by their colors (0 for white, 1 for black). */
    private Player[] players = new Player[2];
    
    
    /**
     * Constructor for the ChessGame class.
     * Initializes the board and players, sets up the JFrame properties, and adds necessary event listeners.
     */
    public ChessGame() {
        setup3DFrame(); // Start 3D Chess directly
    }
    
    /**
     * Sets up the standard 2D chess game frame with the chessboard.
     */
    private void setupFrame() {
        setTitle("Chess Game");
        setSize(500, 512);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
      
        board.addMouseListener(new EventListener(board)); 
      
        add(this.board);
        setVisible(true);
    }
    
    /**
     * Sets up the 3D chess game frame with the 3D chessboard.
     */
    private void setup3DFrame() {
        setTitle("3D Chess Game");
        setSize(1500, 512);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (endGame()) {
                    System.exit(0);
                }
            }
        });
        setLocationRelativeTo(null);
        setResizable(false);
        
        board3D.addMouseListener(new EventListener(board3D)); 

        add(board3D.getPanel());  // Add the master panel from ChessBoard3D to the JFrame.
        setVisible(true);
    }

    /**
     * Starts a new game, initializing the board and placing the chess pieces in their initial positions.
     * 
     * @return true indicating the game has started.
     */
    // public boolean startGame() {
    //     //board.initBoard();
    //     board3D.initBoard(); 
    //     return true;
    // }

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
        return response == JOptionPane.YES_OPTION;
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
