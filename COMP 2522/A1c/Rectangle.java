/**
 * This class represents a rectangle shape. 
 * It extends the Shape class and allows setting the width and height of the rectangle.
 * @author yongeun
 * @version 2023
 *
 */
public class Rectangle extends Shape{

    /**
     * Constructor for the Rectangle class.
     *
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @throws BadWidthException Exception thrown if the width is invalid.
     */
    protected Rectangle(int width, int height) throws BadWidthException {
        super(width, height, "r");
    }

    /**
     * Displays the rectangle shape on the screen.
     * It prints a rectangle of the appropriate size based on the width and height.
     */
    @Override
    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

}
