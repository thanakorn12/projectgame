package javaapplication7;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class playstate1 extends JPanel implements ActionListener{
    
	private final ImageIcon imgstate1 = new ImageIcon(this.getClass().getResource("state1.jpg"));
	private final ImageIcon imgstate2 = new ImageIcon(this.getClass().getResource("state2.jpg"));
                 private final ImageIcon con = new ImageIcon(this.getClass().getResource("co.png"));
	private final ImageIcon game = new ImageIcon(this.getClass().getResource("go.png"));
	private final ImageIcon imgmeleon = new ImageIcon(this.getClass().getResource("1.png"));
                 private final ImageIcon imgmeleon2 = new ImageIcon(this.getClass().getResource("m1.png"));
	private final ImageIcon pause = new ImageIcon(this.getClass().getResource("puse.png"));
	private final ImageIcon resum = new ImageIcon(this.getClass().getResource("resum.png"));
	private final ImageIcon back = new ImageIcon(this.getClass().getResource("back.png"));
	meleon m = new meleon();
        dragon d = new dragon();
        att at = new att();
        def de = new def();
        zombie zom = new zombie();
        
        int countstate = 2;
        
	homegames hg = new homegames();
        ImageIcon feildover = new ImageIcon(this.getClass().getResource("wallstart.png"));
	ImageIcon img_paralyze = new ImageIcon(this.getClass().getResource("7.png"));
	ImageIcon exitover = new ImageIcon(this.getClass().getResource("exit.png"));
	ImageIcon restart = new ImageIcon(this.getClass().getResource("Start.png"));
        JButton BStartover = new JButton(restart);
	JButton BExitover  = new JButton(exitover);
        public ArrayList<Fireball> fireball = new ArrayList<Fireball>();
        public ArrayList<puke> pu = new ArrayList<puke>();
	
	private JLabel score = new JLabel(); 
        public JButton BPause  = new JButton(pause);
	public JButton BExithome  = new JButton(back); 
	public JButton Bresum  = new JButton(resum);
        
	public int times ;
	public int HP =250;
        public int HP1 =250;
        public int HP3 =250;
	public int rs1 = 1;
	public int rs2 = 2;
	boolean timestart = true;
	boolean startball = false;
	boolean protect = false;
	private gameover gover = new gameover();
	public int scor = 0;
	boolean paralyze1 = false;
	int time_paralyze=5;

	Thread time = new Thread(new Runnable(){
            public void run(){
		while(true){
                    try{
			Thread.sleep(10);
                    }catch(Exception e){ }
                    
                    if(timestart == false){
			repaint();
                    }
		}
            }
	});
	
	Thread actor = new Thread(new Runnable(){
            public void run(){
		while(true){
                    try{
                        Thread.sleep(1);
                    }catch(Exception e){}
                    repaint();
                    if(countstate==2)
                    {
                        puke.suspend();
                    }
                    else
                    {
                        puke.resume();
                        tballs5.suspend();
                    }
		}
            }
	});
	
	
	Thread paralyze = new Thread(new Runnable(){
            public void run(){
		while (true){
			if(time_paralyze < 1){
                            paralyze1 = false;
                            time_paralyze = 5;
			}
			try{
                            Thread.sleep(5000);
			}catch(InterruptedException e){e.printStackTrace();}
		}
            }
	});
        Thread t = new Thread(new Runnable(){
            public void run() {
		while(true){
                	if(timestart == false){
                            times = (times-1) ;
                            if(paralyze1){
				time_paralyze--;
                            }
			}
			try{
                            Thread.sleep(1000);
                            protect=false;
			}catch(InterruptedException e)
			{
                            e.printStackTrace();
			}
		}
            }
	});
        Thread tballs5 = new Thread(new Runnable(){
            public void run() {
            	while(true){
			try{
                            Thread.sleep((long)(Math.random()*3000)+1000);
                        }
                        catch(InterruptedException e){e.printStackTrace();}
                        fireball.add(new Fireball(600,600));
			d.count=3;
                        try{
                            Thread.sleep(500);
                            d.count=0;
                        }
                        catch(InterruptedException e){e.printStackTrace();}
		}
            }
	});
        Thread puke = new Thread(new Runnable(){
            public void run() {
            	while(true){
			try{
                            Thread.sleep((long)(Math.random()*3000)+1000);
                        }
                        catch(InterruptedException e){e.printStackTrace();}
                        pu.add(new puke(600,600));
			zom.count=3;
                        try{
                            Thread.sleep(500);
                            zom.count=0;
                        }
                        catch(InterruptedException e){e.printStackTrace();}
		}
            }
	});
	Thread  tprotect=new Thread(new Runnable(){
            public void run() {
            	while(true){
                    try{

                       Thread.sleep(1);

                    }catch(InterruptedException e){e.printStackTrace();}
                    if(protect){
                        try{
                            Thread.sleep(500);

                        }catch(InterruptedException e){e.printStackTrace();}
                        
                    } 
		}
            
            }
        });
        File sound1 = new File("make.wav");
        Clip clip1;
        File sound2 = new File("conansad.wav");
        Clip clip2;
        File sound3 = new File("congr.wav");
        Clip clip3;
	playstate1(){
		this.setFocusable(true);
		this.setLayout(null);
                tballs5.start();
		tprotect.start();
                puke.start();
                
                this.addKeyListener(new KeyAdapter(){
                    public void keyPressed(KeyEvent e){
                        if(e.getKeyCode()==KeyEvent.VK_A){
                            m.x-=10;
                            m.count++;
                        }
                        else if(e.getKeyCode() == KeyEvent.VK_D){
                            m.x+=10;
                            m.count++;
                        }
                        if(m.count>5){
                            m.count=0;
                        }
                        if(e.getKeyCode()==KeyEvent.VK_P){
                            at.count++;
                            int dis = (int) Math.sqrt(Math.pow(m.x-600, 2)+Math.pow(550-550, 2));
//                                System.out.println(dis);
                            if(countstate==2)
                            {
                                if(dis<20)
                                {
                                    HP=HP-10;
                                }
                            }  
                            else
                            {
                                if(dis<20)
                                {
                                    HP3=HP3-5;
                                }    
                            }
                        }
                        if(at.count>2){
                            at.count=0;
                        }
                        if(e.getKeyCode()==KeyEvent.VK_O){
                            de.count++;
                            protect = true;
                        }
                        if(de.count>2){
                            de.count=1;
                        }
                    }
                   
                    
                    public void keyReleased(KeyEvent e){
			m.count=0;
                        at.count=0;
                        de.count=0;
                        protect=false;
                    }
                });
                try{
                    AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(sound1);
                    clip1 = AudioSystem.getClip();
                    clip1.open(audioIn1);
                    clip1.loop(Clip.LOOP_CONTINUOUSLY);
                    clip1.stop();
                    AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(sound2);
                    clip2 = AudioSystem.getClip();
                    clip2.open(audioIn2);
                    clip2.loop(Clip.LOOP_CONTINUOUSLY);
                    clip2.stop();
                    AudioInputStream audioIn3 = AudioSystem.getAudioInputStream(sound3);
                    clip3 = AudioSystem.getClip();
                    clip3.open(audioIn3);
                    clip3.loop(Clip.LOOP_CONTINUOUSLY);
                    clip3.stop();
                }catch(UnsupportedAudioFileException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }catch(LineUnavailableException e){
                    e.printStackTrace();
                }
                
		m.x = 400;
		time.start();
		actor.start();
		t.start();
		paralyze.start();
                protect=false;
	}
	
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(times <= 0 || HP1<=0){
                this.setLayout(null);
                g.drawImage(game.getImage(),0,0,1000,800,this);
                g.drawImage(img_paralyze.getImage(), 600, 400, 400, 400, this);	
                clip1.stop();
                clip2.start();
            }
            else if(HP3<=0)
            {
                this.setLayout(null);
                g.drawImage(con.getImage(),0,0,1000,800,this);
                clip1.stop();
                clip3.start();
            }
            else if(HP<=0){
                countstate--;
                g.drawImage(imgstate2.getImage(),0,0,1000,800,this);
                if(paralyze1){
                    g.setColor(Color.RED);
                    g.drawImage(img_paralyze.getImage(), m.x, 550,100,150, this);
                }
                else
                {
                    g.drawImage(zom.zo[zom.count].getImage(), 600, 360, 400, 400, this);
                    if(m.count>0)
                    {
                        g.drawImage(m.im[m.count].getImage(), m.x, 550,110,160, this);
                    }
                    else if(at.count>0)
                    {
                        g.drawImage(at.ia[at.count].getImage(), m.x, 550,110,160, this);
                    }
                    else if(de.count>0)
                    {
                        g.drawImage(de.ide[de.count].getImage(), m.x, 550,110,160, this);
                    }
                    else
                    {
                        g.drawImage(m.im[m.count].getImage(), m.x, 550,110,160, this);
                    }
                }
   		if(m.x<0){
                    m.x=20;
		}
		if(m.x>610){
                    m.x=600;
		}      
                for(int i=0;i<pu.size();i++){
                    puke pk = pu.get(i);
      		    g.drawImage(pk.impuke[pk.count%5].getImage(), pk.x, pk.y,50,50, null);
      		    pk.move();
      		    pk.count++;
      		    if(pk.x<0){
      		    	pu.remove(i);
      		    }
   		}
                for(int i=0 ; i<pu.size();i++)
                {
                    if(Intersect(pu.get(i).getbound(),m.getbound()))
                    {
                        if(!protect){
                            protect=false;
                            HP1=HP1-30;
                            pu.remove(i);
                            m.x-=50;
                        }
                        else if(protect){
                            protect = true;
                            HP1=HP1;
                            pu.remove(i);
                            m.x-=10;
                        }
                            
                    }
                }
		g.setColor(Color.white);     
		g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,50));
		g.drawString("STATE "+rs2,400,80);
		g.drawString("Time "+times,400,150);
		g.setColor(Color.green);
                g.fillRoundRect(650, 70, HP3, 30, 30, 30);
                g.setColor(Color.white);
                g.drawString("HP Zombie", 650, 50);
                g.setColor(Color.black);
                g.drawRoundRect(650, 70, 250, 30, 30, 30);
                g.drawRoundRect(651, 69, 250, 30, 30, 30);    
                
                g.setColor(Color.green);
                g.fillRoundRect(50, 70, HP1, 30, 30, 30);
                g.setColor(Color.white);
                g.drawString("HP Player", 50, 50);
                g.setColor(Color.black);
                g.drawRoundRect(50, 70, 250, 30, 30, 30);
                g.drawRoundRect(49, 69, 250, 30, 30, 30);    
                
		      
            }
            else{
                g.drawImage(imgstate1.getImage(),0,0,1000,800,this);
                if(paralyze1){
                    g.setColor(Color.RED);
                    g.drawImage(img_paralyze.getImage(), m.x, 550,100,150, this);
                }else{
                    g.drawImage(d.id[d.count].getImage(), 600, 360, 400, 400, this);
                    if(m.count>0)
                    {
                        g.drawImage(m.im[m.count].getImage(), m.x, 550,110,160, this);
                    }
                    else if(at.count>0)
                    {
                        g.drawImage(at.ia[at.count].getImage(), m.x, 550,110,160, this);
                    }
                    else if(de.count>0)
                    {
                        g.drawImage(de.ide[de.count].getImage(), m.x, 550,110,160, this);
                    }
                    else
                    {
                        g.drawImage(m.im[m.count].getImage(), m.x, 550,110,160, this);
                    }
                }
                
		if(m.x<0){
                    m.x=20;
		}
		if(m.x>610){
                    m.x=600;
		}
		 
                for(int i=0;i<fireball.size();i++){
                    Fireball fire = fireball.get(i);
      		    g.drawImage(fire.imfire[fire.count%5].getImage(), fire.x, fire.y,50,50, null);
      		    fire.move();
      		    fire.count++;
      		    if(fire.x<0){
      		    	fireball.remove(i);
      		    }
   		}
                for(int i=0 ; i<fireball.size();i++)
                {
                    if(Intersect(fireball.get(i).getbound(),m.getbound()))
                    {
                        if(!protect){
                            protect=false;
                            HP1=HP1-10;
                            fireball.remove(i);
                            m.x-=50;
                        }
                        else if(protect){
                            protect = true;
                            HP1=HP1;
                            fireball.remove(i);
                            m.x-=10;
                        }
                            
                    }
                }
		g.setColor(Color.white);     
		g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,50));
		g.drawString("STATE "+rs1,400,80);
		g.drawString("Time "+times,400,150);
		g.setColor(Color.RED);
		g.setColor(Color.green);
                g.fillRoundRect(650, 70, HP, 30, 30, 30);
                g.setColor(Color.white);
                g.drawString("HP Dragon", 650, 50);
                g.setColor(Color.black);
                g.drawRoundRect(650, 70, 250, 30, 30, 30);
                g.drawRoundRect(651, 69, 250, 30, 30, 30);    
                
                g.setColor(Color.green);
                g.fillRoundRect(50, 70, HP1, 30, 30, 30);
                g.setColor(Color.white);
                g.drawString("HP Player", 50, 50);
                g.setColor(Color.black);
                g.drawRoundRect(50, 70, 250, 30, 30, 30);
                g.drawRoundRect(49, 69, 250, 30, 30, 30);    
	    }

	}

	public boolean Intersect(Rectangle2D a, Rectangle2D b){
		return (a.intersects(b));
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== BStartover){		
                    this.setSize(1000,800);
                    this.add(hg);
                    this.setLocation(null);
                    
                    timestart = true;
                    startball = true;
		}else if(e.getSource() == BExitover){
                    System.exit(0);
                    
		}		
	}
}
