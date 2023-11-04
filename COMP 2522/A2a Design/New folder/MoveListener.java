// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MoveListener.java

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveListener extends MouseAdapter
{

    public MoveListener(Chess chess)
    {
        game = chess;
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
        Square square = (Square)mouseevent.getSource();
        if(startSquare == null)
        {
            Piece piece = square.getPiece();
            if(piece != null && piece.getOwner() == game.getCurrentPlayer())
            {
                startSquare = square;
                startSquare.setActive(true);
                startSquare.repaint();
            }
        } else
        {
            Piece piece1 = square.getPiece();
            Piece piece2 = startSquare.getPiece();
            if(piece1 != null && piece2.getOwner() == piece1.getOwner())
            {
                startSquare.setActive(false);
                startSquare.repaint();
                startSquare = square;
                startSquare.setActive(true);
                startSquare.repaint();
            } else
            if(piece2.isValidMove(square, game.getBoard()))
            {
                game.move(startSquare, square);
                startSquare.setActive(false);
                startSquare.repaint();
                startSquare = null;
                game.switchPlayers();
            }
        }
    }

    private final Chess game;
    private Square startSquare;
}
