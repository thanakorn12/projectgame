package javaapplication7;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class meleon{
    public ImageIcon[] im = new ImageIcon[7];
    public int x;
    public int y=550;
    public int count = 0;
    meleon(){
        for(int i=0;i<im.length;i++){
            im[i] = new ImageIcon(this.getClass().getResource((i+1)+".png"));
	}
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,70,132));
    }
}
