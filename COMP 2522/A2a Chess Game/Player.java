import java.awt.Color;

class Player {
    private Color color;
    private ChessBoard chessBoard;
    private int desRow, desCol;

    public Player(Color color, ChessBoard chessBoard) {
        this.color = color;
        this.chessBoard = chessBoard;
    }

    public void makeMove(int stRow, int stCol, int desRow, int desCol) {
        chessBoard.movePiece(stRow, stCol);
    }
}