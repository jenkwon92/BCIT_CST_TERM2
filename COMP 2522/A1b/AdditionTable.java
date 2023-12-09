/**
 * A subclass of Table that represents an addition table.
 * It calculates and stores the addition table values based on the given start and stop range.
 */
public class AdditionTable extends Table {
    protected AdditionTable(int start, int stop) {
        super(start, stop);
        tableType = TableType.ADD;

        // Calculate and populate the addition table values
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                table[row][col] = (row + start) + (col + start);
            }
        }
    }
}
