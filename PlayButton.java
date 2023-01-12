import greenfoot.*;
import java.util.*;
public class PlayButton extends Actor
{
    public PlayButton(){
        this.setImage("Play.png");
    }
    public void act(){
        MyWorld myWorld=(MyWorld)getWorld();
        if(myWorld.isGameActive){
            this.getImage().setTransparency(0);
        }else{
            this.getImage().setTransparency(255);
        }
        if((Greenfoot.isKeyDown("Space")|| Greenfoot.mouseClicked(this))&&!myWorld.isGameActive){
            myWorld.resetWorld();
            myWorld.isGameActive=true;
        }
        /* 
        Wenn das Spiel aktiv ist, wird die transparenz des Objectes auf 0 gesetzt, damit es nichg sichtbar ist. Ist das Spiel nicht Aktiv, so ist die Transparez maximal, damit es sichbar ist.
        Wenn die Leertaste oder das Objekt selbst gedrückt wird, wird in der Welt die Welt resettet und das spiel auf aktiv gesetzt. 
        */
    }
}
