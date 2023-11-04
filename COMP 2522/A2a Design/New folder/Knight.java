// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Knight.java


public class Knight extends Piece
{

    public Knight(Player player)
    {
        super(player, "X");
    }

    public boolean isValidMove(Square square, Board board)
    {
        return true;
    }
}
