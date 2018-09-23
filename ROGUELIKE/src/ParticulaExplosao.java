import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ParticulaExplosao extends Particula{

	BufferedImage img[] = null;
	
	public ParticulaExplosao(float x, float y, float velX, float velY,
			int tempoDeVida, BufferedImage img[]) {
		super(x, y, velX, velY, tempoDeVida, Color.white);
		// TODO Auto-generated constructor stub
		this.img = img;
	}

	@Override
	public void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo) {
		// TODO Auto-generated method stub
		
//		dbg.setColor(Color.black);
//		dbg.fillOval((int)(X-raio-xMundo), (int)(Y-raio-yMundo), (int)(raio*2), (int)(raio*2));

		float fazevida = (timerParticula/(float)tempoDeVida);
		float prop = 1.0f - fazevida;
		
		//float propinv = (1.0f-prop)+0.05f;

		
		AffineTransform trans = dbg.getTransform();
		Composite com = dbg.getComposite();
		
		//Composite cp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, prop);
		//dbg.setComposite(cp);
		
		dbg.translate((int)X-xMundo, (int)Y-yMundo);
		dbg.scale(prop*10+3, prop*10+3);
		dbg.drawImage(img[(int)(fazevida/0.2)], -16, -16,null);
		
		dbg.setTransform(trans);
		dbg.setComposite(com);
		//dbg.fillRect((int)X-xMundo-1, (int)Y-yMundo-1, 3, 3);
	}
}
