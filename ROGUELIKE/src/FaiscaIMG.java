import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;


public class FaiscaIMG extends Particula {

	BufferedImage img = null;
	static Random rnd = new Random();
	double angulo = 0;
	
	/*
	 * 
	 * 	


					for (int j = 0; j < 10; j++) {
						FaiscaIMG pr = new FaiscaIMG(X, Y,500, Constantes.faisca, angulo*-1);
						CanvasMain.listaDeParticulas.add(pr);
					}
	 */
	public FaiscaIMG(float x, float y,
			int tempoDeVida, BufferedImage img, double  angulo) {
		super(x, y, 0, 0, tempoDeVida + rnd.nextInt(40), Color.white);
		// TODO Auto-generated constructor stub
		this.img = img;
		vel = 100;
		 this.angulo =  angulo + rnd.nextDouble();
	}

	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		//super.SimulaSe(diftime);
		  
		tempoDeVida-=diftime;
		if(tempoDeVida<=0) {
			vivo = false;
		}
 
		double velX = vel * Math.cos(angulo);
		double velY = vel * Math.sin(angulo);
 

		X += velX * diftime / 1000.0f;
		Y += velY * diftime / 1000.0f;
		
	}
	
	@Override
	public void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo) {
		// TODO Auto-generated method stub
 
		AffineTransform trans = dbg.getTransform();

		dbg.translate((int)(X - xMundo),(int)(Y - yMundo));

		dbg.rotate(10);
  
		dbg.drawImage(img, - img.getWidth()/2,- img.getHeight()/2, null);
 
		 
		dbg.setTransform(trans);
//		dbg.setColor(Color.black);
//		dbg.fillOval((int)(X-raio-xMundo), (int)(Y-raio-yMundo), (int)(raio*2), (int)(raio*2));

	 /*
		float prop = 1.0f - (timerParticula/(float)tempoDeVida);
		
		float propinv = (1.0f-prop)+0.05f;

		
		AffineTransform trans = dbg.getTransform();
		Composite com = dbg.getComposite();
		
		Composite cp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, prop);
		dbg.setComposite(cp);
		
		dbg.translate((int)X-xMundo, (int)Y-yMundo);
		dbg.scale(propinv, propinv);
		dbg.drawImage(img, -16, -16,null);
		
		dbg.setTransform(trans);
		dbg.setComposite(com);
		//dbg.fillRect((int)X-xMundo-1, (int)Y-yMundo-1, 3, 3);
 */
  
	}
}
