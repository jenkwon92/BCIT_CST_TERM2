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
        super(width, height, "d");
    }

     /**
     * Displays the diamond shape on the screen.
     * It prints a diamond of the appropriate size based on the width and height.
     */
    @Override
    public void display() {
        for (int i = 0; i < width / 2; i++) {
            for (int j = 0; j < width / 2 - i; j++) {
                System.out.print(' ');
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print('#');
            }
            System.out.println();
        }
        for (int i = width / 2; i >= 0; i--) {
            for (int j = 0; j < width / 2 - i; j++) {
                System.out.print(' ');
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print('#');
            }
            System.out.println();
        }
    }

}
