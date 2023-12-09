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
    protected Rectangle(int width, int height){
        super(width, height, "*");
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
                grid[i][j] = this.type;

        }
    }
}
