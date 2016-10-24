package dessin;

/**
 *
 * @author Simon
 */
public class Dessin {

   public static final int ROWS = 15;
   public static final int COLUMNS = 31;
   
   public static char[][] caneva = new char[16][32];
           
    public static void main(String[] args) {
        clearCaneva();
        command();
    }
    
    public static void clearCaneva() {
        for (int i = 0; i <= ROWS; i++) {
            for (int j = 0; j <= COLUMNS; j++) {
                caneva[i][j] = ' ';
            }
        }
    }
    
    public static void printCaneva() {
        Pep8.stro("+--------------------------------+\n");
        for (int i = ROWS; i >= 0; i--) {
            Pep8.charo('|');
            for (int j = 0;j <= COLUMNS; j++) {
                Pep8.charo(caneva[i][j]);
            }
            Pep8.charo('|');
            Pep8.charo('\n');
        }
        Pep8.stro("+--------------------------------+\n");
    }
    
    public static void point() {
        char p = Pep8.chari();
        int y = Pep8.deci();
        int x = Pep8.deci();
        if (y < 0 || y > ROWS || x < 0 || x > COLUMNS ){
            return;
        }
        caneva[x][y] = p;
    }
    
    public static int rectangle(){
        
    }
    
    public static int coordonneX(){
        int x = Pep8.deci();
        if (x < 0) {
            return 0;
        } else if (x > COLUMNS) {
            return COLUMNS;
        }else {
            return x;
        }
    }
    
    public static int coordonneY(){
        int y = Pep8.deci();
        if (y < 0) {
            return 0;
        } else if (y > ROWS) {
            return ROWS;
        }else {
            return y;
        }
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
