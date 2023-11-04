// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Chess.java

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Chess extends JFrame
{

    public static void main(String args[])
    {
        Chess chess = new Chess();
        chess.init();
        chess.centre();
        chess.setVisible(true);
    }

    public Chess()
    {
        super("Chess");
        whitePlayer = new Player(Color.RED);
        blackPlayer = new Player(Color.BLUE);
    }

    private void init()
    {
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent windowevent)
            {
                shutdown(0);
            }

            final Chess this$0;

            
            {
                this$0 = Chess.this;
                super();
            }
        }
);
        board.init(new MoveListener(this));
        initPieces();
        currentPlayer = whitePlayer;
        getContentPane().setLayout(new GridLayout(1, 1));
        getContentPane().add(board);
    }

    private void initPieces()
    {
        setPiece(0, 0, new Rook(whitePlayer));
        setPiece(0, 1, new Bishop(whitePlayer));
        setPiece(0, 2, new Knight(whitePlayer));
        setPiece(0, 3, new King(whitePlayer));
        setPiece(0, 4, new Queen(whitePlayer));
        setPiece(0, 5, new Knight(whitePlayer));
        setPiece(0, 6, new Bishop(whitePlayer));
        setPiece(0, 7, new Rook(whitePlayer));
        for(int i = 0; i < board.getNumberOfCols(1); i++)
            setPiece(1, i, new Pawn(whitePlayer));

        for(int j = 0; j < board.getNumberOfCols(6); j++)
            setPiece(6, j, new Pawn(blackPlayer));

        setPiece(7, 0, new Rook(blackPlayer));
        setPiece(7, 1, new Bishop(blackPlayer));
        setPiece(7, 2, new Knight(blackPlayer));
        setPiece(7, 3, new King(blackPlayer));
        setPiece(7, 4, new Queen(blackPlayer));
        setPiece(7, 5, new Knight(blackPlayer));
        setPiece(7, 6, new Bishop(blackPlayer));
        setPiece(7, 7, new Rook(blackPlayer));
    }

    private void setPiece(int i, int j, Piece piece)
    {
        board.getSquareAt(i, j).setPiece(piece);
    }

    private void shutdown(int i)
    {
        System.exit(i);
    }

    private void centre()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int i = (int)Math.min((double)dimension.width * 0.75D, (double)dimension.height * 0.75D);
        Rectangle rectangle = new Rectangle();
        rectangle.width = i;
        rectangle.height = i;
        rectangle.x = (dimension.width - i) / 2;
        rectangle.y = (dimension.height - i) / 2;
        setBounds(rectangle);
    }

    public void move(Square square, Square square1)
    {
        Piece piece = square.getPiece();
        square.setPiece(null);
        square1.setPiece(piece);
        square.repaint();
        square1.repaint();
    }

    public Board getBoard()
    {
        return board;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void switchPlayers()
    {
        if(currentPlayer == blackPlayer)
            currentPlayer = whitePlayer;
        else
            currentPlayer = blackPlayer;
    }

    private static final String WHITE_SQUARE_KEY = "WhiteSquare";
    private static final String BLACK_SQUARE_KEY = "BlackSquare";
    private static final String WHITE_ROOK_KEY = "WhiteRook";
    private static final String BLACK_ROOK_KEY = "BlackRook";
    private static final String WHITE_BISHOP_KEY = "WhiteBishop";
    private static final String BLACK_BISHOP_KEY = "BlackBishop";
    private static final String WHITE_KNIGHT_KEY = "WhiteKnight";
    private static final String BLACK_KNIGHT_KEY = "BlackKnight";
    private static final String WHITE_KING_KEY = "WhiteKing";
    private static final String BLACK_KING_KEY = "BlackKing";
    private static final String WHITE_QUEEN_KEY = "WhiteQueen";
    private static final String BLACK_QUEEN_KEY = "BlackQueen";
    private static final String WHITE_PAWN_KEY = "WhitePawn";
    private static final String BLACK_PAWN_KEY = "BlackPawn";
    private final Board board = new Board(8, 8);
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player currentPlayer;


}
