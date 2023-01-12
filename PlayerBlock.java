import greenfoot.*;
import java.util.*;
public class PlayerBlock extends Actor
{
    long lastAdded=System.currentTimeMillis();
    int i;
    ArrayList<Color> colors;
    Color color;
    int z;
    boolean isFirst=true;
    public PlayerBlock(){
        this.colors = new ArrayList<Color>();
        this.setColor();
        this.getImg(this.color);
        i=0;
        z=0;
    }
    public void act()
    {
        MyWorld myworld = (MyWorld) getWorld();
        if(myworld.isGameActive){
            this.checkColor();
            if(i==8){
                this.checkMovement();
                i=0;
            }
            this.move(myworld.speed);
            i++;
            this.swapColor(this.getRandomInterval(10000,20000));
        }
        if(this.z==500){
            Greenfoot.setSpeed(50);
            MyWorld myWorld=(MyWorld) getWorld();
            myWorld.speed=2;
        }
        z++;
        /*
        es wird geschaut ob unter dem Block die gleiche farbe ist wie der block selbst.
        in einem bestimmten interval wird geschaut ob der spieler sich bewegen möchte.
        der block wird bewegt und es wird das wechseln der farbe eingeleitet.
        nach einem weiterem interval wird die geschwindigkeit der spiels wieder auf normal gestezt.
        */
    }
    public int getRandomInterval(int start,int end)
    {
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
    }
    public void swapColor(int frequenz){
        long curTime=System.currentTimeMillis();
        if(curTime>=this.lastAdded +frequenz){
            int size= Greenfoot.getRandomNumber(6);
            int transperancy= this.getRandomInterval(100,200);
            int x= Greenfoot.getRandomNumber(500);
            this.setColor();
            this.getImg(this.color);
            this.lastAdded  = curTime;
        }
        /*
        Diese methode ändert in der vorgegebenen frequenz die farbe des Spieler Blockes
        */
    }
    public void setColor(){
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.MAGENTA);
        colors.add(Color.GREEN);
        Collections.shuffle(colors);
        this.color=colors.get(0);
        //Diese Methode setzt die Frabe des Spielerblockes auf eine Zufällige farbe.
    }
    public void dash(){
        World w=getWorld();
        for(int i=0;i<5;i++){
            List<Block> firstLine=w.getObjectsAt(this.getX(), this.getY()+25, Block.class);
            List<Block> secondLine=w.getObjectsAt(this.getX(), this.getY()+75, Block.class);
            if(!(this.getY()>=725)){
                if(!secondLine.isEmpty()){
                   firstLine.get(0).removeLine();
                   this.setLocation(this.getX(), this.getY()+50);
                   w.addObject(new ShadowBlock(), this.getX(), this.getY());
                }else{
                    break;
                }  
            }else{
                break;
            }
        }
        //Diese Methode lässt den Spielerblock nach unten "Dashen"
    }
    public void checkColor(){
        World w=getWorld();
        List<Block> k=w.getObjectsAt(this.getX(), this.getY()+25, Block.class);
        if(!k.isEmpty()){
            Block b= k.get(0);
            if(b.isSpecial==true){
                if(b.q==0){
                    this.dash();
                }else if(b.q==1){
                    MyWorld myWorld=(MyWorld) getWorld();
                    Greenfoot.setSpeed(50);
                    myWorld.speed=1;
                    z=0;
                    b.removeLine();
                }else{
                    MyWorld myWorld=(MyWorld) getWorld();
                    myWorld.speed=2;
                    Greenfoot.setSpeed(51);
                    z=0;
                    b.removeLine();
                }
            }else{
                if(b.getColor()==this.color){
                    b.removeLine();
                    MyWorld myworld=(MyWorld)this.getWorld();
                    myworld.score=myworld.score+1;
                }
            }
        }
        //Diese Methode schaut ob die farbe unter dem Spieler Block gleich der Farbe des Spieler Blockes ist oder ob unter dem Spieler ein spezial Block ist, 
        //falls ja wird die entsprechende methode ausgeführt um die spezial fähgkeit auszuführen
    }
    public void checkMovement(){
        if(Greenfoot.isKeyDown("left")){
            if(this.getX()!=25){
                for(int i=0;i<50;i++){
                    this.setLocation(this.getX()-1, this.getY());
                }
            }
        }
        if(Greenfoot.isKeyDown("right")){
            if(this.getX()!=225){
                for(int i=0;i<50;i++){
                    this.setLocation(this.getX()+1, this.getY());
                }
            }
        }
        // Diese Methode liest die eingabe des Spielers und lässt den Block entweder nach links oder rechts bewegen.
    }
    public void move(int speed){
        World w=getWorld();
        List<Block> k=w.getObjectsAt(this.getX(), this.getY()+25, Block.class);
        if(!k.isEmpty()){
            if(true){
                this.setLocation(this.getX(),this.getY()-speed);
                this.isFirst=false;
                w.removeObjects(w.getObjects(ShadowBlock.class));  
            }
        }else{
            if(!this.isFirst){
                w.addObject(new ShadowBlock(), this.getX(), this.getY());
            }
            if(this.getY()<=725){
                this.setLocation(this.getX(),this.getY()+speed);
            }
        }
        //Diese Methode lässt den Spielerblock nach oben bewegen, wenn sich unter ihm eine linie befindet, wenn nicht geht er nach unten. 
    }
    public void getImg(Color color){
        GreenfootImage image = new  GreenfootImage(50, 50);
        image.setColor(color);
        image.fill();
        setImage(image);
        //diese methode ändert die farbe des Blockes und seine größe.
    }
}
