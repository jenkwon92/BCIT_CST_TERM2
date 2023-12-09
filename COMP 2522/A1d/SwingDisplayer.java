import javax.swing.JFrame;

/**
 * The `SwingDisplayer` class implements the Displayer interface and provides a method to display a shape
 * using a Swing-based graphical user interface (GUI). It creates a graphical frame to visualize the shape.
 * @author yongeun
 * @version 2023
 */
public class SwingDisplayer implements Displayer {
    /**
     * Displays the given shape using a Swing-based graphical user interface.
     *
     * @param shape The shape (Shape) object to be displayed in the GUI.
     */
    public void displayShape(final Shape shape) {
        final DisplayerFrame frame;

        frame = new DisplayerFrame();
        frame.init(shape);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}