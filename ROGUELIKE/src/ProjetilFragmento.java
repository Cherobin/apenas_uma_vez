import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class ProjetilFragmento extends Projetil {

	public ProjetilFragmento(float x, float y, float velX, float velY, Object pai) {
		super(x, y, velX, velY, pai);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		if(Constantes.telaRect.contains((int)X, (int)Y)) {
			AffineTransform trans = dbg.getTransform();
			dbg.translate((int)(X-xMundo), (int)(Y-yMundo));
			dbg.rotate(angulo + tempoDeVida*0.01);
			
			int frame = (tempoDeVida/50)%2;
			dbg.drawImage(Constantes.fragmento[frame], -8,-8,null);
			
			dbg.setTransform(trans);
		}
	}

}
