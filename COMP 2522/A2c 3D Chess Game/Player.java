import java.awt.Color;

/**
 * Represents a player in the chess game.
 * 
 * @author yongeun
 *
 */
class Player {
    private Color color;
    private ChessBoard chessBoard;
    private ChessBoard3D chessBoard3D;
    private boolean isPlaying3D;

    /**
     * Constructs a Player with a specified color and an associated ChessBoard.
     * 
     * @param color      The color of the player.
     * @param chessBoard The board on which the player is playing.
     */
    public Player(Color color, ChessBoard chessBoard) {
        this.color = color;
        this.chessBoard = chessBoard;
        this.isPlaying3D = false;
    }

    public Player(Color color, ChessBoard3D chessBoard3D) {
        this.color = color;
        this.chessBoard3D = chessBoard3D;
        this.isPlaying3D = true;
    }

    /**
     * Attempts to move a piece from a starting position to a destination position.
     * 
     * @param stRow  The starting row of the piece.
     * @param stCol  The starting column of the piece.
     * @param desRow The destination row.
     * @param desCol The destination column.
     * @return true if the move is valid and successful, false otherwise.
     */
    public void makeMove(int stRow, int stCol, int desRow, int desCol) {
        System.out.println();
        if (isPlaying3D) {
            chessBoard3D.movePiece(0, stRow, stCol, desRow, desCol); // 0 is an example level. Change according to the
                                                                     // actual logic.
        } else {
            chessBoard.movePiece(stRow, stCol);
        }
    }
}
