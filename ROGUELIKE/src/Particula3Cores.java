import java.awt.Color;
import java.awt.Graphics2D;


public class Particula3Cores extends Particula {

	int r2,g2,b2;
	int r3,g3,b3;
	
	public Particula3Cores(float x, float y, float velX, float velY,
			int tempoDeVida, Color cor1,Color cor2,Color cor3) {
		super(x, y, velX, velY, tempoDeVida, cor1);
		// TODO Auto-generated constructor stub
		
		r2 = cor2.getRed();
		g2 = cor2.getGreen();
		b2 = cor2.getBlue();
		
		r3 = cor3.getRed();
		g3 = cor3.getGreen();
		b3 = cor3.getBlue();
	}
	
	@Override
	public void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo) {
		// TODO Auto-generated method stub
		
		float prop = 1.0f - (timerParticula/(float)tempoDeVida);
		
		Color newcor = null;
		
		if(prop>0.75){
			float prop2 = (1.0f-prop)*4;
//			System.out.println("a "+prop2 );
			newcor = new Color((int)(r*prop2+r2*(1-prop2)),(int)(g*prop2+g2*(1-prop2)),(int)(b*prop2+b2*(1-prop2)),(int)(255*prop));
		}else if(prop>0.50){
			float prop2 = (0.75f-prop)*4;
//			System.out.println("b "+prop2 );
			newcor = new Color((int)(r2*prop2+r3*(1-prop2)),(int)(g2*prop2+g3*(1-prop2)),(int)(b2*prop2+b3*(1-prop2)),(int)(255*prop));
		}else{
			newcor = new Color(r3,g3,b3,(int)(255*prop));
		}
		
		dbg.setColor(newcor);
		dbg.fillRect((int)X-xMundo-1, (int)Y-yMundo-1, 3, 3);
	}

}
