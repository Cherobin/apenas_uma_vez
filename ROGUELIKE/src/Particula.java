import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Particula extends Sprite {
	
	float vel = 0;
	float velX = 0;
	float velY = 0;

	//float raio = 2;

	int tempoDeVida = 0;
	Color cor = null;
	
	int timerParticula = 0;
	
	int r,g,b;
	
	public Particula(float x,float y,float velX,float velY,int tempoDeVida,Color cor){
		this.X = x;
		this.Y = y;
		
		this.velX = velX;
		this.velY = velY;
		
		this.tempoDeVida = tempoDeVida;
		
		this.cor = cor;
		
		r = cor.getRed();
		g = cor.getGreen();
		b = cor.getBlue();
	}
	
	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		timerParticula+=diftime;
		
		X+=velX*diftime/1000.0f;
		Y+=velY*diftime/1000.0f;
		
		if(timerParticula >= tempoDeVida){
			vivo = false;
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo) {
		// TODO Auto-generated method stub
		
//		dbg.setColor(Color.black);
//		dbg.fillOval((int)(X-raio-xMundo), (int)(Y-raio-yMundo), (int)(raio*2), (int)(raio*2));

		float prop = 1.0f - (timerParticula/(float)tempoDeVida);
		
		Color newcor = new Color(r,g,b,(int)(255*prop));
		
		dbg.setColor(newcor);
		dbg.fillRect((int)X-xMundo-1, (int)Y-yMundo-1, 3, 3);
	}
}
