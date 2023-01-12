import greenfoot.*;
public class ShadowBlock extends Actor
{
    int i=0;
    public ShadowBlock(){
        GreenfootImage image = new  GreenfootImage(50, 50);
        image.setColor(new Color(142,142,142));
        image.fill();
        setImage(image);
        this.getImage().setTransparency(25);
    }
    public void act()
    {
        if(i==20){
           World w=getWorld();
           w.removeObject(this);
        }else
        i++;
        //nach 20 Methoden aufrufen löscht sich das objekt selbst.
    }
}
