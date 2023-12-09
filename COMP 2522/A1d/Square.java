/**
 * This class represents a rtriangle shape. 
 * It extends the Shape class and allows setting the width and height of the rectangle with right aligned..
 * @author yongeun
 * @version 2023
 *
 */
public class Square extends Rectangle{

     /**
     * Constructor for the Rtriangle class.
     *
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     */
    protected Square(int width, int height){
        super(width, width);
    }
}
