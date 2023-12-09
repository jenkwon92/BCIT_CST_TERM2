/**
 * This class represents a triangle shape. 
 * It extends the Shape class and allows setting the width and height of the triangle.
 * @author yongeun
 * @version 2023
 *
 */
public class Triangle extends Shape{

    /**
     * Constructor for the Triangle class.
     *
     * @param width  The width of the triangle.
     * @param height The height of the triangle.
     * @throws BadWidthException Exception thrown if the width is invalid.
     */
    protected Triangle(int width, int height) throws BadWidthException{
        super(width, width / 2 + 1, "@");
        if(width % 2 == 0)
            throw new BadWidthException();
        for(int k = 0; k <= width / 2; k++)
        {
            for(int l = width / 2 - k; l <= width / 2 + k; l++)
                grid[k][l] = type;

        }
    }
}
