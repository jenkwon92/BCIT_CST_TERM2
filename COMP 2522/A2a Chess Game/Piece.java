import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

abstract class Piece {
    private boolean white;
    private boolean killed;
    private static final int TILE_SIZE = 60;
    
    public Piece(boolean white) {
        this.white = white;
    }

    public abstract boolean canMove(int stRow, int stCol, int desRow, int desCol);

    public void drawPiece(Graphics g, int row, int col) {
        String symbol = getSymbol();
        if (this.white) {
            g.setColor(Color.RED); // 백색 피스에 대한 글자 색상
        } else {
            g.setColor(Color.BLUE); // 흑색 피스에 대한 글자 색상
        }
        g.setFont(new Font("SansSerif", Font.BOLD, 40)); // 폰트 크기 조절
        g.drawString(symbol, col * TILE_SIZE + TILE_SIZE / 3, (row + 1) * TILE_SIZE - TILE_SIZE / 3);
    }


    public abstract String getSymbol(); // 각 피스별로 심볼을 반환하는 메서드입니다.
}
