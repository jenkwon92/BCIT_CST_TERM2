// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Piece.java


public abstract class Piece
{

    protected Piece(Player player, String s)
    {
        owner = player;
        image = s;
    }

    public String getImage()
    {
        return image;
    }

    public Player getOwner()
    {
        return owner;
    }

    public void setSquare(Square square1)
    {
        square = square1;
    }

    public Square getSquare()
    {
        return square;
    }

    public abstract boolean isValidMove(Square square1, Board board);

    private final String image;
    protected final Player owner;
    protected Square square;
}
