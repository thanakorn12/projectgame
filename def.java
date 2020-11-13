package javaapplication7;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class def{
    public ImageIcon[] ide = new ImageIcon[3];
    public int x;
    public int y=550;
    public int count = 0;
    def(){
        for(int i=0;i<ide.length;i++){
            ide[i] = new ImageIcon(this.getClass().getResource("d"+(i+1)+".png"));
	}
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,70,132));
    }
}
