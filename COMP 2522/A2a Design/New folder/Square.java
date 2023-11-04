// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Square.java

import java.awt.*;

public class Square extends Canvas
{

    public Square(int i, int j, Color color)
    {
        row = i;
        col = j;
        setBackground(color);
    }

    public void setActive(boolean flag)
    {
        isActive = flag;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        if(piece != null)
        {
            Font font = g.getFont();
            font = font.deriveFont(1, 28F);
            g.setFont(font);
            g.setColor(piece.getOwner().colour);
            g.drawString(piece.getImage(), getWidth() / 2, getHeight() / 2);
        }
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public void setPiece(Piece piece1)
    {
        if(piece != null)
            piece.setSquare(null);
        piece = piece1;
        if(piece != null)
            piece.setSquare(this);
    }

    public Piece getPiece()
    {
        return piece;
    }

    private final int row;
    private final int col;
    private Piece piece;
    private boolean isActive;
}
