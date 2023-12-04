import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * This class is responsible for handling mouse events on the chessboard. It
 * extends MouseAdapter and listens for mouseClicked events. When a square on
 * the chessboard is clicked, it triggers the movePiece method of the associated
 * Board instance.
 * 
 * @author yongeun
 * 
 */
class EventListener extends MouseAdapter {
    /**
     * The Board instance associated with this listener.
     */
    private Board board;
    private int desRow;
    private int desCol;
    private int selectedRow;
    private int selectedCol;
    private int selectedLevel;

    /**
     * Constructor that initializes the EventListener with a given Board instance.
     * 
     * @param board The Board instance this listener will operate on.
     */
    public EventListener(Board board) {
        this.board = board;
    }

    /**
     * Handles the mouseClicked event. When a square on the chessboard is clicked,
     * the movePiece method of the associated Board instance is called with the x
     * and y coordinates of the click.
     * 
     * @param e The MouseEvent object containing details about the mouse click.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (board instanceof ChessBoard) {
            ChessBoard chessBoard = (ChessBoard) board;
            chessBoard.movePiece(e.getX(), e.getY());

            int col = e.getX() / ChessBoard.TILE_SIZE;
            int row = e.getY() / ChessBoard.TILE_SIZE;
            chessBoard.setSelectedTile(row, col);
        }
    }
}
