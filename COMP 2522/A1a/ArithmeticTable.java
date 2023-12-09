public class ArithmeticTable {
     private int start;
     private int end;
     private TableType tableType;
     private float[][] table;

    // Representing TableType to enum
    private enum TableType {
        MULT, ADD
    }

    /**
     * Creates a table with specified begin and finish values, and of a specified type.
     * 
     * @param begin     The starting value for the table.
     * @param finish    The ending value for the table.
     * @param tableType The type of table (TableType.ADD or TableType.MULT).
     */
    public void createTable(int begin, int finish, TableType tableType) {
        // Initialize the table with appropriate dimensions
        table = new float[finish - begin + 1][finish - begin + 1];
        
        // Fill the table based on the specified table type
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (tableType == TableType.MULT) {
                    table[row][col] = (row + begin) * (col + begin);
                } else {
                    table[row][col] = (row + begin) + (col + begin);
                }
            }
        }
    }

    /**
     * Prints table contents.
     * Handles empty table cases by providing a relevant message (Prevent NullPointerException)
     */
    public void printTable() {

        // Print a prefix character ('+' or '*'),based on the 'tableType' variable.
        // Dynamically creates a table header based on user-defined input ranges.
        for (int i = 0; i < table.length; i++) {
            if (i == 0) {
                if (tableType == TableType.ADD) {
                    System.out.printf("%5c", '+');
                } else {
                    System.out.printf("%5c", '*');
                }
            }
            System.out.printf("%5d", (int) (i + start));
        }

        // Method that prints a separator line to distinguish between the header and results section.
        System.out.printf("%n%5s%s%n", "", "-----".repeat(table.length));

        if (table != null) {
            for (int row = 0; row < table.length; row++) {
                System.out.printf("%3d |", (int) (row + start));
                for (int col = 0; col < table[row].length; col++) {
                    System.out.printf("%5d", (int) table[row][col]);
                }
                System.out.println();
            }
        }else {
            System.out.println("Table is empty!");
        }
    }

    public boolean argumentCheck(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +, \"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }

        if (args[0].charAt(0) == '+')
            tableType = TableType.ADD;
        else
            tableType = TableType.MULT;
        int sta;
        int sto;

        try {
            sta = Integer.parseInt(args[1]);
            sto = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +, \"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }

        if ((sta < 1 || sta > 100) || ((sto < 1 || sto > 100))) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +, \"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }

        if (sta >= sto) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +,\"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }

        start = sta;
        end = sto;
        return true;
    }

    public static void main(String[] args) {
        ArithmeticTable table = new ArithmeticTable();
        if (table.argumentCheck(args)) {
            table.createTable(table.start, table.end, table.tableType);
            table.printTable();
        }
    }
}
