import java.awt.Graphics2D;


public abstract class Sprite {
	float X;
	float Y;
	
	boolean vivo = true;
	
	public abstract void SimulaSe(int diftime);
	public abstract void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo);
}
