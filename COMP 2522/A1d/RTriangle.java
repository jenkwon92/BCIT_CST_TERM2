/**
 * This class represents a rtriangle shape. 
 * It extends the Shape class and allows setting the width and height of the rectangle with right aligned..
 * @author yongeun
 * @version 2023
 *
 */
public class RTriangle extends Shape{

    /**
     * Constructor for the Rtriangle class.
     *
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     */
    protected RTriangle(int width, int height){
        super(width, width, "@");
        for(int i = 0; i < width; i++)
        {
            for(int j = width-i-1; j < width; j++)
                grid[i][j] = type;

        }
    }
}
