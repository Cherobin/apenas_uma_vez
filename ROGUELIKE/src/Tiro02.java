import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Tiro02 extends Projetil {

	Color cor = new Color(255, 100, 100);
	float xini;
	float yini;
	
	public Tiro02(float x, float y, float velX, float velY, Object pai) {
		super(x, y, velX, velY, pai);
		// TODO Auto-generated constructor stub
		tempoDeVida = 500;
		xini = x;
		yini = y;
	}
	
	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		super.SimulaSe(diftime);
		
		xini+=velX*diftime/2000.0f;
		yini+=velY*diftime/2000.0f;
	}
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		if(Constantes.telaRect.contains((int)X, (int)Y)) {
			//AffineTransform trans = dbg.getTransform();
			//dbg.translate((int)(X-xMundo), (int)(Y-1-yMundo));
			//dbg.rotate(angulo);
			dbg.setColor(cor);
			dbg.drawLine((int)(xini-xMundo),(int)(yini-yMundo),(int)(X-xMundo), (int)(Y-yMundo));
			
			//dbg.setTransform(trans);
		}
	}
}
