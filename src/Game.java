
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;




  class Fire {
      private int x;
      private int y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
      

        
    }
public class Game extends JPanel implements KeyListener,ActionListener {
    
    
    public boolean checkIt(){
        for (Fire fire:fires){
        if(new Rectangle(fire.getX(),fire.getY(),10,10).intersects(new Rectangle(ballX,0,25,25))){
            return true;
        }
        }  
        return false;
}
    Timer timer=new Timer(5,this);
    private int time=0;
    private int number_of_fires=0;
    private BufferedImage image;
    
    private ArrayList<Fire> fires=new ArrayList<Fire>();
    
    private int firesdY=1;
    private int ballX=0;
    private int balldX=2;
    private int spaceshipX=0;
    private int dspaceX=20;

    public Game() {
        try {
            image =ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.WHITE);
        timer.start();
        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
       
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
         time +=5;
        g.setColor(Color.red);
        g.fillOval(ballX, 0, 25, 25);
        g.drawImage(image, spaceshipX , 490, image.getWidth()/4,image.getHeight()/4,this);
        
        for(Fire fire:fires){
          if(fire.getY()<0){
            fires.remove(fire);
          
          }
        }
        g.setColor(Color.BLUE);
        for(Fire fire:fires){
        
        g.fillRect(fire.getX(), fire.getY(), 10, 10);
        
        }
        if(checkIt()){
            timer.stop();
           String message="You won!!!!\n"+ "Number of fires:"+number_of_fires+"\n"+
                   "The passing time:"+time/1000.0;
           JOptionPane.showMessageDialog(this, message);
           System.exit(0);
        
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
     
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
           int c=e.getKeyCode();
        if(c==KeyEvent.VK_LEFT){
           if( spaceshipX<=0){
               spaceshipX=0;
        }
           else {
              spaceshipX -=dspaceX;
           }
    }
          else if (c==KeyEvent.VK_RIGHT){
              
              if(spaceshipX>=750){
              spaceshipX=750;
              
              }
              else {
                  
                  spaceshipX +=dspaceX;
              }
        }
          else if(c==KeyEvent.VK_CONTROL){
           fires.add(new Fire(spaceshipX+15,470));
           number_of_fires++;
          
          
          }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        for(Fire fire:fires){
            fire.setY(fire.getY()-firesdY);
        
        }
        ballX +=balldX;
        if(ballX>=750){ 
            balldX =-balldX;
        }
        if(ballX<=0){
           balldX= -balldX;
        
        }
    repaint();
       
    }

    
    
}
