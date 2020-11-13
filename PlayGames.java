package javaapplication7;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class PlayGames extends JFrame implements ActionListener{  
	homegames homegames1 = new homegames();
	playstate1 state1 = new playstate1();
	gameover gover = new gameover();
        File sound = new File("mon.wav");
        Clip clip0;
	public PlayGames(){
		this.setSize(1000,800);
		this.add(homegames1);
		homegames1.BExit1.addActionListener(this);
                homegames1.BStart.addActionListener(this);
                try{
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
                    clip0 = AudioSystem.getClip();
                    clip0.open(audioIn);
                    clip0.loop(Clip.LOOP_CONTINUOUSLY);
                }
                catch(UnsupportedAudioFileException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }catch(LineUnavailableException e){
                    e.printStackTrace();
                }
	}
	public void actionPerformed(ActionEvent e) {
            if(e.getSource()== homegames1.BStart){
		this.setLocationRelativeTo(null);
		this.remove(homegames1);
                clip0.stop();
                this.setSize(1000,800);
                this.add(state1);
                state1.clip1.start();
                state1.requestFocusInWindow();
		state1.timestart = false;
		state1.scor=0;
		state1.HP =250;
		state1.times = 100;
		state1.startball=false;
		state1.timestart=false;
            }else if(e.getSource() == state1.BExithome){
		System.exit(0);
            }else if(e.getSource() == homegames1.BExit1){
		System.exit(0);
            }
            this.validate();
            this.repaint();
	}
        
     public static void main(String[] args) {
            PlayGames jf = new PlayGames();
            jf.setSize(1000,800);
            jf.setTitle("RPG 2D");
            jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
    }    
        
        
	
}
