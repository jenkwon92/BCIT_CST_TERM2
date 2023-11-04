import java.awt.Color;

import javax.swing.JFrame;

public class ChessGame extends JFrame{
    private ChessBoard board;
    private Player[] players = new Player[2];
    
    public ChessGame() {
        this.board = new ChessBoard();
        this.players[0] = new Player(Color.WHITE, board);
        this.players[1] = new Player(Color.BLACK, board);
        
        setTitle("Chess Game");
        setSize(505, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        board = new ChessBoard();
        board.addMouseListener(new EventListener(board)); 
        
        add(board);
        setVisible(true);
    }

    public boolean startGame() {
        board.initBoard();
        return true;
    }

    public boolean endGame() {
        return true;
    }
    
    public static void main(String[] args) {
        new ChessGame();
    }
}