package dessin;

/**
 *
 * @author Simon
 */
public class Dessin {

    public static final int ROWS = 15;
    public static final int COLUMNS = 31;

    public static char[][] caneva = new char[32][16];

    public static void main(String[] args) {
        clearCaneva();
        command();
    }

    public static void clearCaneva() {
        for (int x = 0; x <= COLUMNS; x++) {
            for (int y = 0; y <= ROWS; y++) {
                caneva[x][y] = ' ';
            }
        }
    }

    public static void printCaneva() {
        Pep8.stro("+--------------------------------+\n");
        for (int y = ROWS; y >= 0; y--) {
            Pep8.charo('|');
            for (int x = 0; x <= COLUMNS; x++) {
                Pep8.charo(caneva[x][y]);
            }
            Pep8.charo('|');
            Pep8.charo('\n');
        }
        Pep8.stro("+--------------------------------+\n");
    }

    public static void point() {
        char p = Pep8.chari();
        int x = Pep8.deci();
        int y = Pep8.deci();
        if (y < 0 || y > ROWS || x < 0 || x > COLUMNS) {
            return;
        }
        caneva[x][y] = p;
    }

    public static void rectangle() {
        char frame = Pep8.chari();
        int col1 = Pep8.deci();
        int lig1 = Pep8.deci();
        int col2 = Pep8.deci();
        int lig2 = Pep8.deci();

        for (int x = Math.min(col1, col2); x <= Math.max(col1, col2); x++) {
            for (int y = Math.min(lig1, lig2); y <= Math.max(lig1, lig2); y++) {
                if (x >= 0 && x <= COLUMNS && y >= 0 && y <= ROWS) {
                    if ((x == col1 || x == col2) || (y == lig1 || y == lig2)) {
                        caneva[x][y] = frame;
                    }
                }
            }
        }
    }

    public static void boite() {
        char frame = Pep8.chari();
        char filling = Pep8.chari();
        int col1 = Pep8.deci();
        int lig1 = Pep8.deci();
        int col2 = Pep8.deci();
        int lig2 = Pep8.deci();

        for (int x = Math.min(col1, col2); x <= Math.max(col1, col2); x++) {
            for (int y = Math.min(lig1, lig2); y <= Math.max(lig1, lig2); y++) {
                if (x >= 0 && x <= COLUMNS && y >= 0 && y <= ROWS) {
                    if ((x == col1 || x == col2) || (y == lig1 || y == lig2)) {
                        caneva[x][y] = frame;
                    } else {
                        caneva[x][y] = filling;
                    }
                }
            }
        }
    }

    public static void ligne() {
        return;
    }

    public static void command() {
        char c = Pep8.chari();
        while (c == '\n' || c == ' ') {
            c = Pep8.chari();
        }
        switch (c) {
            case 'z':
                clearCaneva();
                command();
                break;
            case 'a':
                printCaneva();
                command();
                break;
            case 'p':
                point();
                command();
                break;
            case 'b':
                boite();
                command();
            case 'r':
                rectangle();
                command();
            case 'q':
                stop();

            default:
                break;
        }
    }

    public static void stop() {
        Pep8.stop();
    }
}
