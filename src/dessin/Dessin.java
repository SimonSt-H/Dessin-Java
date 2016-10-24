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
        // TODO code application logic here
    }
    
    public static void clearCaneva(){
        for (int i = 0; i <= ROWS; i++){
            for (int j = 0; j <= COLUMNS; j++){
                caneva[i][j] = ' ';
            }
        }
    }
    
    public static void printCaneva(){
        Pep8.stro("+--------------------------------+");
        for (int i = 0; i <= ROWS; i++){
            Pep8.charo('|');
            for (int j = 0;j <= COLUMNS; j++){
                Pep8.charo(caneva[i][j]);
            }
            Pep8.charo('|');
        }
        Pep8.stro("+--------------------------------+");
    }
}
