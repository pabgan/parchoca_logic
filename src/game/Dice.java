/**
 * 
 */
package game;

import java.util.Random;

/**
 * It represents a dice with 6 faces.
 * 
 * @author pganuza
 * 
 */
public class Dice {
    public static int throwDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}
