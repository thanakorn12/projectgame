
package javaapplication7;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class JavaApplication7 {

    public static void main(String[] args) {
         PlayGames jf = new PlayGames();
            jf.setSize(1000,800);
            jf.setTitle("RPG 2D");
            jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
    }
    
}
