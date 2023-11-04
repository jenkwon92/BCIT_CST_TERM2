// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pawn.java


public class Pawn extends Piece
{

    public Pawn(Player player)
    {
        super(player, "P");
    }

    public boolean isValidMove(Square square, Board board)
    {
        return true;
    }
}
