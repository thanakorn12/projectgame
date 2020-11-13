package javaapplication7;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class dragon{
    public ImageIcon[] id = new ImageIcon[7]
            ;
    public int x;
    public int y = 360;
    public int count = 0;
    dragon(){
        for(int i=0;i<id.length;i++){
            id[i] = new ImageIcon(this.getClass().getResource("m"+(i+1)+".png"));
	}
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,25,25));
    }
}
