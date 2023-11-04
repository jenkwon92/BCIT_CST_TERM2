// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Board.java

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Board extends JPanel
{

    public Board(int i, int j)
    {
        squares = new Square[i][j];
    }

    public void init(MoveListener movelistener)
    {
        setLayout(new GridLayout(squares.length, squares[0].length));
        for(int i = 0; i < squares.length; i++)
        {
            for(int j = 0; j < squares[i].length; j++)
            {
                if((i + j) % 2 == 0)
                    squares[i][j] = new Square(i, j, Color.black);
                else
                    squares[i][j] = new Square(i, j, Color.white);
                add(squares[i][j]);
                squares[i][j].addMouseListener(movelistener);
            }

        }

    }

    public Square getSquareAt(int i, int j)
    {
        return squares[i][j];
    }

    public int getNumberOfRows()
    {
        return squares.length;
    }

    public int getNumberOfCols(int i)
    {
        return squares[i].length;
    }

    private final Square squares[][];
}
