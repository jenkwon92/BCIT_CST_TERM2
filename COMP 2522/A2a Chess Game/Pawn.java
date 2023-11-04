import java.awt.Graphics;

class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(int stRow, int stCol, int desRow, int desCol) {
        // 왕의 움직임 로직 작성
        return true;
    }
    
    @Override
    public String getSymbol() {
        return "P";
    }
}