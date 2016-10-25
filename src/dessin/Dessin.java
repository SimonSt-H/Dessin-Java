package dessin;

/**
 *
 * @author Simon St-Hilaire STHS13069207 Dessin.java
 */
public class Dessin {

    public static final int ROWS = 15;
    public static final int COLUMNS = 31;

    public static char[][] caneva = new char[COLUMNS + 1][ROWS + 1];

    public static void main(String[] args) {
        //On commence avant tout par remplir le caneva d'espaces
        clearCaneva();
        command();
    }

    /*
    Cette methode met un espace dans chaque cases du caneva.
     */
    public static void clearCaneva() {
        for (int x = 0; x <= COLUMNS; x++) {
            for (int y = 0; y <= ROWS; y++) {
                caneva[x][y] = ' ';
            }
        }
    }

    /*
    Cette methode imprime le caneva a la console.
     */
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

    /*
    Cette methode dessine un point.
     */
    public static void point() {
        char paint = Pep8.chari();
        int x = Pep8.deci();
        int y = Pep8.deci();
        //On verifie que le point est dans le caneva et on le dessine.
        if (x >= 0 && x <= COLUMNS && y >= 0 && y <= ROWS) {
            caneva[x][y] = paint;
        }

    }

    /*
    Cette methode dessine un rectangle.
     */
    public static void rectangle() {
        char frame = Pep8.chari();
        int col1 = Pep8.deci();
        int lig1 = Pep8.deci();
        int col2 = Pep8.deci();
        int lig2 = Pep8.deci();

        //En allant du plus petit x et y vers le plus gros x et y
        //on n'a besoin que d'un algorithme qui va dans une direction.
        //On passe ensuite sur chaque case du rectangle et si la case est
        //dans le caneva on verifie si la case forme le cadre du rectangle en
        //verifiant si (x = col1 ou col2) ou si (y = lig1 ou lig2).
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

    /*
    Cette methode dessine un rectangle rempli.
     */
    public static void boite() {
        char frame = Pep8.chari();
        char paint = Pep8.chari();
        int col1 = Pep8.deci();
        int lig1 = Pep8.deci();
        int col2 = Pep8.deci();
        int lig2 = Pep8.deci();

        //Il s'agit exactement du même algorithme que pour le rectangle vide
        //avec une ligne de code de plus qui remplit les cases qui ne rencontre
        //pas la condition de faire partie du cadre avec le caractere paint.
        //Puisqu'on passe deja sur toutes les cases interieures du
        //rectangles avec l'algorithme precedent il n'y a presque aucun
        //changements.
        for (int x = Math.min(col1, col2); x <= Math.max(col1, col2); x++) {
            for (int y = Math.min(lig1, lig2); y <= Math.max(lig1, lig2); y++) {
                if (x >= 0 && x <= COLUMNS && y >= 0 && y <= ROWS) {
                    if ((x == col1 || x == col2) || (y == lig1 || y == lig2)) {
                        caneva[x][y] = frame;
                    } else {
                        caneva[x][y] = paint;
                    }
                }
            }
        }
    }

    /*
    Cette methode dessine un segment.
     */
    public static void segment() {
        char paint = Pep8.chari();
        int x1 = Pep8.deci();
        int y1 = Pep8.deci();
        int x2 = Pep8.deci();
        int y2 = Pep8.deci();

        //Je n'ai qu'applique le pseudo-code de l'enonce en ajoutant la
        //verification que la case est dans le caneva avant de le dessiner.
        int dx = Math.abs(x2 - x1);
        int sx = Integer.signum(x2 - x1);
        int dy = -1 * Math.abs(y2 - y1);
        int sy = Integer.signum(y2 - y1);
        int err = dx + dy;

        while (x1 != x2 | y1 != y2) {
            if (x1 >= 0 && x1 <= COLUMNS && y1 >= 0 && y1 <= ROWS) {
                caneva[x1][y1] = paint;
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
            caneva[x1][y1] = paint;
        }
    }

    /*
    Cette methode prend les input et appelle la methode floodFill.
     */
    public static void remplir() {
        char paint = Pep8.chari();
        int col = Pep8.deci();
        int lig = Pep8.deci();
        char oldPaint = caneva[col][lig];

        floodFill(col, lig, paint, oldPaint);
    }

    /*
    Cette methode remplit une zone avec un nouveau caractere.
     */
    public static void floodFill(int col, int lig, char paint, char oldPaint) {
        //Si la case cible n'est pas dans le caneva, on sort.
        if (col < 0 || lig < 0 || col > COLUMNS || lig > ROWS) {
            return;
        }
        //Si la case cible a deja la couleur voulue, on sort.
        if (oldPaint == paint) {
            return;
        }
        //Si la case cible ne partage pas la couleur du point initial, on sort.
        if (caneva[col][lig] != oldPaint) {
            return;
        }
        //Si on s'est rendu jusqu'ici, on peinture avec la nouvelle couleur.
        caneva[col][lig] = paint;

        //On fait la même chose pour les 4 cases voisines de façon recursive
        //jusqu'a ce qu'on arrive a une case dont les 4 voisins ne rencontre
        //rencontre les conditions d'un des 3 if du debut de la methode.
        floodFill(col, lig - 1, paint, oldPaint);
        floodFill(col, lig + 1, paint, oldPaint);
        floodFill(col - 1, lig, paint, oldPaint);
        floodFill(col + 1, lig, paint, oldPaint);
    }

    /*
    Cette methode determine quel methode appeler et l'appelle.
     */
    public static void command() {
        //Ces 3 lignes de code sont tres importantes, j'ai perdu 80% dans
        //le TP1 parce que j'avais mal fait cette boucle pour ignorer les sauts
        //de ligne et les espaces.
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

    /*
    Stop.
     */
    public static void stop() {
        Pep8.stop();
    }
}
