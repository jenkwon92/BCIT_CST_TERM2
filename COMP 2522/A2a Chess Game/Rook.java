import java.awt.Graphics;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        // 왕의 움직임 로직 작성
        return true;
    }
    
    @Override
    public String getSymbol() {
        return "R";
    }
}