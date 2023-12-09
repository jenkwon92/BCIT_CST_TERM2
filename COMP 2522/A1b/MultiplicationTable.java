/**
 * A subclass of Table that represents an multiplication table.
 * It calculates and stores the multiplication table values based on the given start and stop range.
 */
public class MultiplicationTable extends Table {
    protected MultiplicationTable(int start, int stop) {
        super(start, stop);
        tableType = TableType.MULT;
        
        // Calculate and populate the multiplication table values
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                table[row][col] = (row + start) * (col + start);
            }
        }
    }
}
