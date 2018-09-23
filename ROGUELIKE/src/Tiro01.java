import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Tiro01 extends Projetil {
	
	Color cor1 = new Color(210, 210, 255);
	Color cor2 = new Color(160, 160, 255);
	Color cor3 = new Color(100, 100, 155);

	public Tiro01(float x, float y, float velX, float velY, Object pai) {
		super(x, y, velX, velY, pai);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		if(Constantes.telaRect.contains((int)X, (int)Y)) {
			AffineTransform trans = dbg.getTransform();
			dbg.translate((int)(X-xMundo), (int)(Y-1-yMundo));
			dbg.rotate(angulo);
			
			dbg.setColor(cor3);
			dbg.fillRect(-1,-2, 10, 5);
			dbg.setColor(cor2);
			dbg.fillRect(0,-1, 10, 3);
			dbg.setColor(cor1);
			dbg.fillRect(0,0, 10, 1);
			
			dbg.setTransform(trans);
		}
	}

}
