package javaapplication7;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class zombie{
    public ImageIcon[] zo = new ImageIcon[4];
    public int x;
    public int y=360;
    public int count = 0;
    zombie(){
        for(int i=0;i<zo.length;i++){
            zo[i] = new ImageIcon(this.getClass().getResource("z"+(i+1)+".png"));
	}
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,70,132));
    }
}
