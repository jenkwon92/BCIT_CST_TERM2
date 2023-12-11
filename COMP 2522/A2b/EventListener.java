import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * This class is responsible for handling mouse events on the chessboard.
 * It extends MouseAdapter and listens for mouseClicked events.
 * When a square on the chessboard is clicked, it triggers the movePiece method 
 * of the associated ChessBoard instance.
 * 
 * @author yongeun
 * 
 */
class EventListener extends MouseAdapter {
    /**
     * The ChessBoard instance associated with this listener.
     */
    private ChessBoard chessBoard;
    private int desRow;
    private int desCol;

    /**
     * Constructor that initializes the EventListener with a given ChessBoard instance.
     * 
     * @param chessBoard The ChessBoard instance this listener will operate on.
     */
    public EventListener(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    /**
     * Handles the mouseClicked event. 
     * When a square on the chessboard is clicked, the movePiece method of the 
     * associated ChessBoard instance is called with the x and y coordinates of the click.
     * 
     * @param e The MouseEvent object containing details about the mouse click.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        chessBoard.movePiece(e.getX(), e.getY());
    }
}