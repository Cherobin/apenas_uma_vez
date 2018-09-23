import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Item extends Sprite {
	BufferedImage charset = null;

	static int basex = 496;
	static int basey = 496;

	int animTimer = 0;
	int animSpeed = 200;
	int frame = 0;
	int anim = 0;

	double angulo = 0;
	double vel = 0;

	int raio = 55;
 
	double gold;
	double xy[] = new double[2];
	int charw = 36; 
	int charh = 55;
	int qtSprites = 0;
	int life = 0;
	boolean isAnimate = false;
	public Item( BufferedImage charset, float x, float y,int goldMax, int lifeMax, int animSpeed, int qtSprites,int charw, int charh, int raio, boolean isAnimate) {
		this.X = x;
		this.Y = y;
		this.charset = charset;
		gold = goldMax;
		this.animSpeed = animSpeed;
		this.qtSprites = qtSprites;
		this.charw = charw;
		this.charh = charh;
		this.raio =  raio;
		this.life = lifeMax;
		this.isAnimate = isAnimate;
	}

	@Override
	public void SimulaSe(int diftime) {
	
		if(isAnimate) {
			animTimer += diftime; 
		
			frame = (animTimer / animSpeed) % qtSprites;
			if(gold > 100) {
				anim = 0;
			}else {
				anim = 1;
			}

		}
		
 
		if (testaColisao(Constantes.heroi.X,Constantes.heroi.Y,Constantes.heroi.raio,Constantes.heroi)) {
			vivo = false;
			Constantes.gold += gold;
			Constantes.heroi.life += life;
			if(Constantes.heroi.life > Constantes.heroi.lifeMax) {
				Constantes.heroi.life = Constantes.heroi.lifeMax;
			}
		}

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		
 
		 dbg.drawImage(charset,(int)X-xMundo, (int)Y-yMundo,(int)X+charw-xMundo,
		 (int)Y+charh-yMundo,charw*frame,charh*anim,charw*frame+charw,charh*anim+charh,null);
		
		//AffineTransform trans = dbg.getTransform();

		//dbg.translate((int)(X - xMundo),(int)(Y - yMundo));

		//dbg.rotate(angulo);

	 

		//dbg.setTransform(trans);
	}

	public boolean testaColisao(Item pers) {
		float dx = (pers.X + 16) - (X + 16);
		float dy = (pers.Y + 24) - (Y + 24);

		float sr = pers.raio + raio;

		if (sr * sr > (dx * dx + dy * dy)) {
			return true;
		}

		return false;
	}

	public boolean testaColisao(float x, float y, float r, Personagem proj) {
		float dx = x - X;
		float dy = y - Y;
		float sr = r + raio;

		if (sr * sr > (dx * dx + dy * dy)) {
			
			for (int i = 0; i < 31; i++) {
				for (int j = 0; j < 31; j++) {
					if (proj.layer0[i][j] != 0) {
						
						proj.basetToXY(j, i, xy);
						double dx2 = X - (x+xy[0]);
						double dy2 = Y - (y+xy[1]);
						double sr2 = raio + 16;
						
						if (sr2 * sr2 > (dx2 * dx2 + dy2* dy2)) { 
							return true;
						}
					}
				}
			}
			
			
		}

		return false;
	}

	public boolean ColisaoRetangular(Personagem pers) {
		float p1x1 = X + 2;
		float p1x2 = X + 30;
		float p2x1 = pers.X + 2;
		float p2x2 = pers.X + 30;

		float p1y1 = Y + 2;
		float p1y2 = Y + 46;
		float p2y1 = pers.Y + 2;
		float p2y2 = pers.Y + 46;

		if ((p1x1 < p2x2) && (p1x2 > p2x1) && (p1y1 < p2y2) && (p1y2 > p2y1)) {
			return true;
		}
		return false;
	}
 

	public void basetToXY(int j, int i, double ret[]) {
		int pi = i - 15;
		int pj = j - 15;

		int ix = pi * 32;
		int jx = pj * 32;

		double x = jx * Math.cos(angulo) - ix * Math.sin(angulo);
		double y = jx * Math.sin(angulo) + ix * Math.cos(angulo);
		ret[0] = x;
		ret[1] = y;
	}
}
