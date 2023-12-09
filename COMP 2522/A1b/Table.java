public abstract class Table {
    private int start;
    private int size;
    protected float[][] table;
    protected TableType tableType;

    // stores the start and size of table, creates the correct size of array
    protected Table(int start, int stop) {
        this.start = start;
        size = stop - start + 1;
        table = new float[stop - start + 1][stop - start + 1];
    }

    // displays with a good format (see A1a) a table
    /**
     * Displays the table with a formatted output.
     * If the table is an addition table, it prefixes each row with '+';
     * if it's a multiplication table, it prefixes each row with '*'.
     */
    public void display() {
        String tableTypeSymbol = tableType == TableType.ADD ? "+" : "*";
        System.out.printf("%5s", tableTypeSymbol);

        for (int i = 0; i < size; i++) {
            System.out.printf("%5d", (int) (i + start));
        }

        System.out.printf("%n%5s%s%n", "", "-----".repeat(size));
        if (table != null) {
            for (int row = 0; row < size; row++) {
                System.out.printf("%3d |", (int) (row + start));
                for (int col = 0; col < size; col++) {
                    System.out.printf("%5d", (int) table[row][col]);
                }
                System.out.println();
            }
        }
    }

}
