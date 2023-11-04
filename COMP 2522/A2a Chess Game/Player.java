import java.awt.Color;

/**
 * Represents a player in the chess game.
 * @author yongeun
 *
 */
class Player {
    private Color color;
    private ChessBoard chessBoard;

    /**
     * Constructs a Player with a specified color and an associated ChessBoard.
     * 
     * @param color The color of the player.
     * @param chessBoard The board on which the player is playing.
     */
    public Player(Color color, ChessBoard chessBoard) {
        this.color = color;
        this.chessBoard = chessBoard;
    }

    /**
     * Attempts to move a piece from a starting position to a destination position.
     * 
     * @param stRow The starting row of the piece.
     * @param stCol The starting column of the piece.
     * @param desRow The destination row.
     * @param desCol The destination column.
     * @return true if the move is valid and successful, false otherwise.
     */
    public void makeMove(int stRow, int stCol, int desRow, int desCol) {
        chessBoard.movePiece(stRow, stCol);
    }
}