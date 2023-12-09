import javax.swing.Icon;

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
     * Rectangle: "r"
     * RTriangle: "Rt"
     * Square: "s".
     */
    protected final String type;

    /**
     * The grid to display shape.
     */
    protected final String grid[][];

    /**
     * Constructor for the Shape class.
     *
     * @param width  The width of the shape.
     * @param height The height of the shape.
     * @param t      The type of the shape.
     */
    protected Shape(final int width, final int height, final String t){
        this.width = width;
        this.height = height;
        grid = new String[height][width];
        type = t;
        for(int k = 0; k < grid.length; k++)
        {
            for(int l = 0; l < grid[0].length; l++)
                grid[k][l] = " ";
        }
    }

    public final void display() {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
                System.out.print(grid[i][j]);

            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getCharAt(int row, int col) {
        return grid[row][col];
    }
}
