    package javaapplication7;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class att{
    public ImageIcon[] ia = new ImageIcon[3];
    public int x;
    public int y=550;
    public int count = 0;
    att(){
        for(int i=0;i<ia.length;i++){
            ia[i] = new ImageIcon(this.getClass().getResource("a"+(i+1)+".png"));
	}
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,70,1322));
    }
}
