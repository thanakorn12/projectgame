package javaapplication7;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fireball extends JPanel{
    public ImageIcon[] imfire = new ImageIcon[5];
    public int y=0;
    public int x;
    public int count=0;
    boolean checkgo=true;
    Fireball(int x,int y){
        for(int i=0;i<imfire.length;i++){
            imfire[i] = new ImageIcon(this.getClass().getResource("f"+(i+1)+".png"));
	}
    
            this.x=x;
            this.y=y;
    }
	
    public void move(){
	this.x-=1;
        if(checkgo){
            this.y-=2;
            if(this.y<=550){
                checkgo=false;
            }
        }else{
            this.y+=2;
            if(this.y>=650){
                checkgo=true;
            }
        }
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,25,25));
    }
}
