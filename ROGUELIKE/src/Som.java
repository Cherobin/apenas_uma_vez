/*
 * Created on 05/10/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

import java.applet.Applet;
import java.applet.AudioClip;


public class Som {
	AudioClip clip[];
	
	int index = 0;
	int canais = 0;
	
    public Som(String name,int canais){
    	this.canais = canais;
    	
    	clip = new AudioClip[canais];
    	
    	for(int i = 0; i < canais;i++ ) {
    		clip[i] = Applet.newAudioClip(getClass().getResource(name));   
    	}
    }
    
    public void TocaSom(){
        clip[index].play(); 
        index++;
        if(index>=canais) {
        	index = 0;
        }
    }
    public void TocaSomLoop(){
        clip[index].loop();   
        index++;
        if(index>=canais) {
        	index = 0;
        }
    }
    
    public void ParaSom(){
    	for(int i = 0; i < canais;i++ ) {
    		clip[i].stop();        
    	}
    	index = 0;
    }    
}
