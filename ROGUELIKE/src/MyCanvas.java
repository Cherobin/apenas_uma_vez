import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


public abstract class MyCanvas {
	public abstract void SimulaSe(int diftime);
	public abstract void DesenhaSe(Graphics2D dbg);
	
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e );
	
	public abstract void mouseMoved(MouseEvent e); 
	public abstract void mouseDragged(MouseEvent e);
	
	public abstract void mouseReleased(MouseEvent arg0);
	public abstract void mousePressed(MouseEvent arg0);
	public abstract void mouseExited(MouseEvent arg0);
	public abstract void mouseEntered(MouseEvent arg0);
	public abstract void mouseClicked(MouseEvent arg0);
	
	public abstract void mouseWheelMoved(MouseWheelEvent arg0);

}
