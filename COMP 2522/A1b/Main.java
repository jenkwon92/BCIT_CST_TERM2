/**
 * The driver program. Displays the specified arithmetic table with the
 * specified start/stop values.
 *
 */
public final class Main {
    /**
     * Disallow the creation of any Main objects.
     */
    private Main() {
    }

    /**
     * The entry point to the program.
     *
     * @param argv the command line args. argv[0] - the type (+ or *) argv[1] - the
     *             start value (> 1 && < 100) argv[2] - the end value (> 1 && < 100)
     */
    public static void main(final String[] argv) {
        final TableType type;
        final int start;
        final int stop;
        final Table table;

        if (argv.length != 3) {
            usage();
        }

        type = getType(argv[0]);
        start = getNumber(argv[1]);
        stop = getNumber(argv[2]);
        table = getTable(type, start, stop);
        
        // Display table
        table.display();
    }

    /**
     * Convert the supplied string into the appropriate TableType. If the string is
     * not a valid type then exit the program.
     *
     * @param str the stringto convert
     * @return the appropriate TableType
     */
    public static TableType getType(final String str) {
        final TableType type;

        if (str.equals("+")) {
            type = TableType.ADD;
        } else if (str.equals("*")) {
            type = TableType.MULT;
        } else {
            usage();
            type = null;
        }

        return (type);
    }

    /**
     * Convert the supplied string into an int. If the string is not a valid int
     * then exit the program. To be valid the string must be an integer and be > 0
     * and < 100.
     *
     * @param str the string to convert
     * @return the converted number
     */
    public static int getNumber(final String str) {
        int val;

        try {
            val = Integer.parseInt(str);

            if (val < 1 || val > 100) {
                usage();
            }
        } catch (final NumberFormatException ex) {
            usage();
            val = 0;
        }

        return (val);
    }

    /**
     * Creates and returns an instance of the specified table type with the given dimensions.
     *
     * @param type   The type of table to create (ADD or MULT).
     * @param width  The width of the table.
     * @param height The height of the table.
     * @return An instance of the specified table type with the given dimensions,
     *         or null if an invalid table type is provided.
     */
    public static Table getTable(final TableType type, final int width, final int height) {

        if (type == TableType.ADD) {
            return new AdditionTable(width, height);
        } else if (type == TableType.MULT) {
            return new MultiplicationTable(width, height);
        } else {
            usage();
            return null;
        }
    }

    /**
     * Display the usage message and exit the program.
     */
    public static void usage() {
        System.err.println("Usage: Main <type> <start> <stop>");
        System.err.println("\tWhere <type> is one of +, \"*\"");
        System.err.println("\tand <start> is between 1 and 100");
        System.err.println("\tand <stop> is between 1 and 100");
        System.err.println("\tand start < stop");
        System.exit(1);
    }
}
