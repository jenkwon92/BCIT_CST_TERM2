import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

/**
 * This class extends the JFrame class and provides a method to initialize and display a graphical representation of a shape.
 * It arranges JButton components in a grid layout to visualize the characters of the shape.
 * 
 * @author yongeun
 * @version 2023
 */
public class DisplayerFrame
    extends JFrame
{
     /**
     * Initializes the frame to display the given shape as a grid of buttons.
     *
     * @param shape The shape (Shape) object to be displayed.
     */
    public void init(final Shape shape)
    {
		
       setLayout(new GridBagLayout());
	   GridBagConstraints c = new GridBagConstraints();
	   c.fill = GridBagConstraints.BOTH;
        for(int row = 0; row < shape.getHeight(); row++)
        {
            
            for(int col = 0; col < shape.getWidth(); col++)
            {
				c.gridx = col;
				c.gridy = row;
                add(new JButton(shape.getCharAt(row, col)), c);
            }
        }
    }
}
