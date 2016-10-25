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
        if (x >= 0 && x <= COLUMNS && y >= 0 && y <= ROWS) {
            caneva[x][y] = p;
        }

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

    public static void segment() {
        char peinture = Pep8.chari();
        int x1 = Pep8.deci();
        int y1 = Pep8.deci();
        int x2 = Pep8.deci();
        int y2 = Pep8.deci();

        int dx = Math.abs(x2 - x1);
        int sx = Integer.signum(x2 - x1);
        int dy = -1 * Math.abs(y2 - y1);
        int sy = Integer.signum(y2 - y1);
        int err = dx + dy;

        while (x1 != x2 | y1 != y2) {
            if (x1 >= 0 && x1 <= COLUMNS && y1 >= 0 && y1 <= ROWS) {
                caneva[x1][y1] = peinture;
            }
            int e2 = 2 * err;
            if (e2 >= dy) {
                err += dy;
                x1 += sx;
            }
            if (e2 <= dx) {
                err += dx;
                y1 += sy;
            }
        }
        if (x1 >= 0 && x1 <= COLUMNS && y1 >= 0 && y1 <= ROWS) {
            caneva[x1][y1] = peinture;
        }
    }

    public static void remplir() {
        char paint = Pep8.chari();
        int col = Pep8.deci();
        int lig = Pep8.deci();
        char oldPaint = caneva[col][lig];

        floodFill(col, lig, paint, oldPaint);
    }

    public static void floodFill(int col, int lig, char paint, char oldPaint) {
        if (col < 0 || lig < 0 || col > COLUMNS || lig > ROWS) {
            return;
        }
        if (oldPaint == paint) {
            return;
        }
        if (caneva[col][lig] != oldPaint) {
            return;
        }
        caneva[col][lig] = paint;

        floodFill(col, lig - 1, paint, oldPaint);
        floodFill(col, lig + 1, paint, oldPaint);
        floodFill(col - 1, lig, paint, oldPaint);
        floodFill(col + 1, lig, paint, oldPaint);
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
            case 'l':
                segment();
                command();
            case 'f':
                remplir();
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
