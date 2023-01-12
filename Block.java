import greenfoot.*;
public class Block extends Actor
{
    Line line;
    Color color;
    int num;
    boolean isSpecial;
    int q;
    public Block(int num, Color color,Line line,boolean isSpecial){
        this.color=color;
        this.num=num;
        this.line=line;
        this.isSpecial=isSpecial;
        this.drawBlock();
        if(this.isSpecial==true){
            this.q=Greenfoot.getRandomNumber(3);
        }
    }
    public void act(){
        MyWorld myWorld= (MyWorld) getWorld();
        if(myWorld.isGameActive){
            drawBlock();
        }
    }
    public void removeLine(){
        this.line.deleteLine();
    }
    public void drawBlock(){
        if(this.isSpecial){
            if(this.q==0){
                setImage("Dash.png");
            }else if(this.q==1){
                setImage("Time_green.png");
            }else if(this.q==2){
                setImage("Time_red.png");
            }
        }else{
            GreenfootImage image = new  GreenfootImage(50, 50);
            image.setColor(this.color);
            image.fill();
            setImage(image);  
        }
    }
    public void move(int speed){
        this.setLocation(this.getX(), this.getY()-speed);
    }
    public void setColor(Color color){
        this.color=color;
    } 
    public Color getColor(){
        return this.color;
    }
    public Line getLine(){
        return this.line;
    }
}
