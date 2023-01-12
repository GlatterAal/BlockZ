import greenfoot.*;
import java.util.*;
//import java.awt.*;
public class MyWorld extends World
{
    int i;
    Line lines;
    boolean isGameActive;
    int speed;
    PlayerBlock pB;
    int score;
    int record;
    public MyWorld()
    {    
        super(250, 750, 1,false);
        //super((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(),1,false);
        drawBg();
        setPaintOrder(PlayButton.class,Line.class);
        i=0;
        isGameActive=true;
        speed=2;
        this.score=0;
        Greenfoot.setSpeed(50);
        this.addObject(new PlayButton(), 125, 252);
        
    }
    public void act(){
        if(isGameActive){
            this.placeLines(); 
        }
        displayScore();
    }
    public void resetWorld(){
        deleteAllLines();
        this.removeObject(this.getObjects(PlayerBlock.class).get(0));   
        i=0;
        speed=2;
        score=0;
        Greenfoot.setSpeed(50);
        /*
        Diese Methode entfernt alle Linien in der Welt. 
        Ebenfalls wird das Spieler Objekt aus der Welt entfernt.
        Die initilasierungs Variablen, wie geschwindigkeit des Spielers und der score werden auf null gesetzt. 
        */
    }
    public void displayScore(){
        String s="Score: "+this.score;
        showText(s,195,25);
        String r="Record: "+this.record;
        showText(r,55,25);
        //Diese Methode zeigt den Score und den Maximalen Score des Spielers an.
    }
    public void placeLines(){
        if(i==0){
            pB=new PlayerBlock();
            this.addObject(pB,125,225);
            this.addObject(new Line(this.speed), 30, 960);
            i++;
        }else{
            List<Block> k=getObjectsAt(25, 875, Block.class);
            if(!k.isEmpty()){
                int y=k.get(0).getY();
                if(y==875){
                    this.addObject(new Line(this.speed), 30, 960);
                }
            }
        }
        // Diese Methode fügt der welt immer dann eine neue linie hinzu, wenn die vorherige linie 50 Blöcke über dem Spawnpunkt der zu spawnenden linie ist. 
        // sollte noch ,keine Linie in der Welt sein, wird eine Linie gespawnt.
    }
    public void drawBg(){
        GreenfootImage img = new GreenfootImage(300,100);
        img.setColor(Color.BLACK);
        img.fill();
        setBackground(img);
        // Diese Methode färbt den Hintergrund Schwarz.
    }
    public void deleteAllLines(){
        List<Line> lines=this.getObjects(Line.class);
        for(Line l:lines){
            l.deleteLine();
        }
        //Diese Methode löscht alle Linien aus der Welt.
    }
    public void resetPlayer(){
        pB.setLocation(125, 225);
        //Diese Methode setzt die Position des Spielerblockes auf die Ausgangsposition.
        
    }
}
