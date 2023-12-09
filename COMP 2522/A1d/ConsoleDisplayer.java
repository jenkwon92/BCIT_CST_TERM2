/**
 * This class implements the Displayer interface to provide functionality for displaying a shape on the console.
 * It prints characters based on the width and height of the shape to visualize the shape on the screen.
 * @author yongeun
 * @version 2023
 */
public class ConsoleDisplayer implements Displayer {
     /**
     * Displays the given shape on the console.
     *
     * @param shape The shape (Shape) object to be displayed.
     */
    public void displayShape(Shape shape) {
        for(int i = 0; i < shape.getHeight(); i++)
        {
            for(int j = 0; j < shape.getWidth(); j++)
                System.out.print(shape.getCharAt(i, j));

            System.out.println();
        }
    }
}