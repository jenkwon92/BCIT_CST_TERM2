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
        super(width, height, "t");
    }

    /**
     * Displays the triangle shape on the screen.
     * It prints a triangle of the appropriate size based on the width and height.
     */
    @Override
    public void display(){
        for (int i = 1; i <= (width/2)+1; i++) {
            for (int j = (width/2)+1 - i; j > 0 ; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("@");
            }
            System.out.println();
        } 
    }
}
