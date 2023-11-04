// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Queen.java


public class Queen extends Piece
{

    public Queen(Player player)
    {
        super(player, "Q");
    }

    public boolean isValidMove(Square square, Board board)
    {
        return true;
    }
}
