import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

class EventListener extends MouseAdapter {
    private ChessBoard chessBoard;

    public EventListener(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        chessBoard.movePiece(e.getX(), e.getY());
    }
}