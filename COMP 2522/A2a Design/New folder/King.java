// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   King.java


public class King extends Piece
{

    public King(Player player)
    {
        super(player, "K");
    }

    public boolean isValidMove(Square square, Board board)
    {
        return true;
    }
}
