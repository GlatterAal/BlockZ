import greenfoot.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Line extends Actor
{
    ArrayList<Color> Colors;
    Block[] Blocks;
    int speed;
    public Line(int speed){
        //this.setImg();
        Colors = new ArrayList<Color>();
        this.Blocks= new Block[5];
        this.createLine();
        this.speed=speed;
        //Jede Linie besteht aus 5 Blöcken, welche alle eine verschiedene Frabe haben.
    }
    public void act()
    {
        MyWorld myWorld =(MyWorld) getWorld();
        this.speed=myWorld.speed;
        if(myWorld.isGameActive){
            int i=1;
            if(i==1){
                this.spawnLine();
                i++;
            }
            this.moveLine(this.speed);
        }
        this.checkEnd();
        //währedn das Spiel aktiv ist werden alle linien nach oben bewegt und es wird geschaut ob das spiel zu ende ist.
    }
    public void checkEnd(){
        MyWorld myWorld =(MyWorld) getWorld();
        if(Blocks[0].getY()==25){
            myWorld.isGameActive=false;
            if(myWorld.score>myWorld.record){
                myWorld.record=myWorld.score;
            }
        }
        //Wenn die linie am oberen ende der Welt angekommen ist wird das spiel beendet.
    }
    public void setImg(){
        GreenfootImage image = new  GreenfootImage(50, 50);
        image.setColor(Color.RED);
        image.fill();
        setImage(image);
    }
    public void createLine(){
        Colors = new ArrayList<Color>();
        Colors.add(Color.RED);
        Colors.add(Color.BLUE);
        Colors.add(Color.ORANGE);
        Colors.add(Color.MAGENTA);
        Colors.add(Color.GREEN);
        Collections.shuffle(Colors);
        int k= Greenfoot.getRandomNumber(15);
        boolean u=false;
        int y= 6;
        if(k==0){
            u=true;
            y= Greenfoot.getRandomNumber(5);
        } 
        for(int i=0; i<5;i++){
            if(i==y){
                this.Blocks[i]=new Block(i, Colors.get(i),this,true);
            }else{
                this.Blocks[i]=new Block(i, Colors.get(i),this,false);
            }
        }
        //Diese Methode erzeugt eine linie die mit einer Wahrscheinlichkeit von 1 zu 50 einen Spezial Block enthält
        //Die anordnung der Farben der Blöcke sind ebenfalls zufällig
    }
    private void spawnLine(){
        World world= this.getWorld();
        int i=0;
        for (Block b:this.Blocks){
            world.addObject(b,25+(i*50), 925);
            i++;
        }
    }
    public void moveLine(int speed){
        for (Block b:this.Blocks){
            b.move(speed);
        }
    }
    public void deleteLine(){
        World w =getWorld();
        for (Block b:this.Blocks){
            w.removeObject(b);
        }
        w.removeObject(this);
    }
}
