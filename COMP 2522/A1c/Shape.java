/**
 * This is an abstract class representing a shape. Subclasses of this class can represent
 * specific types of shapes such as Triangle, Diamond, or Rectangle.
 * @author yongeun
 * @version 2023
 */
public abstract class Shape {
    /**
     * The width of the shape.
     */
    protected final int width;

    /**
     * The height of the shape.
     */
    protected final int height;

     /**
     * The type of the shape
     * Triangle: "t"
     * Diamond: "d"
     * Rectangle: "r".
     */
    protected final String type;

    /**
     * Constructor for the Shape class.
     *
     * @param width  The width of the shape.
     * @param height The height of the shape.
     * @param t      The type of the shape.
     * @throws BadWidthException Exception thrown if the width is invalid for certain types.
     */
    protected Shape(final int width, final int height, final String t) throws BadWidthException{
        this.width = width;
        this.height = height;
        type = t;
        
        if ((type.equals("t") || type.equals("d")) && width % 2 == 0) {
            throw new BadWidthException();
        }
    }

   /**
     * Abstract method to be implemented by subclasses.
     * It should display the shape on the screen.
     */
    public abstract void display();
}
