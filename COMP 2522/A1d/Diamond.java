/**
 * This class represents a diamond shape. 
 * It extends the Shape class and allows setting the width and height of the diamond.
 * @author yongeun
 * @version 2023
 *
 */
public class Diamond extends Shape {

    /**
     * Constructor for the Diamond class.
     *
     * @param width  The width of the diamond.
     * @param height The height of the diamond.
     * @throws BadWidthException Exception thrown if the width is invalid.
     */
    protected Diamond(int width, int height) throws BadWidthException {
        super(width, width, "#");
        if(width % 2 == 0)
            throw new BadWidthException();
        for(int k = 0; k <= width / 2; k++)
        {
            for(int i1 = width / 2 - k; i1 <= width / 2 + k; i1++)
                grid[k][i1] = type;

        }

        int l = width / 2 + 1;
        for(int j1 = width / 2 - 1; j1 >= 0; j1--)
        {
            for(int k1 = width / 2 - j1; k1 <= width / 2 + j1; k1++)
                grid[l][k1] = type;

            l++;
        }

    }
}
