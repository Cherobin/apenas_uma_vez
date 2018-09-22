import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Personagem extends Sprite {
	BufferedImage charset = null;

	static int basex = 496;
	static int basey = 496;

	int animTimer = 0;
	int animSpeed = 200;
	int frame = 0;
	int anim = 0;

	double angulo = 0;
	int vel = 0;

	int raio = 496;

	int life = 100;

	Rectangle boundingbox;

	int layer0[][] = new int[31][31];
	int layer1[][] = new int[31][31];

	boolean FIRE = false;
	float xAlvo = 0;
	float yAlvo = 0;
	int timertiro = 0;

	public Personagem(float x, float y) {
		this.X = x;
		this.Y = y;

		boundingbox = new Rectangle(0, 0, 100, 100);

		layer0[15][15] = 1;
		layer0[15][16] = 2;
		layer0[15][17] = 2;
		layer0[14][15] = 2;
		layer0[16][15] = 2;
	}

	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		animTimer += diftime;
		timertiro += diftime;

		frame = (animTimer / animSpeed) % 3;

		// if(this!=GamePanel.heroi){
		// float difx = GamePanel.heroi.X - X;
		// float dify = GamePanel.heroi.Y - Y;
		//
		// double ang = Math.atan2(dify, difx);
		//
		// velX = (int)(vel*Math.cos(ang));
		// velY = (int)(vel*Math.sin(ang));
		// }

		double velX = vel * Math.cos(angulo);
		double velY = vel * Math.sin(angulo);

		float xold = X;
		float yold = Y;

		X += velX * diftime / 1000.0f;
		Y += velY * diftime / 1000.0f;

		angulo = Math.atan2(velY, velX);

		// if(Math.abs(velX)>Math.abs(velY)){
		// if(velX>0){
		// anim = 2;
		// }else{
		// anim = 1;
		// }
		// }else{
		// if(velY>0){
		// anim = 0;
		// }else if(velY<0){
		// anim = 3;
		// }else{
		// anim = 0;
		// }
		// }

		// if(X<0){
		// X = 0;
		// velX = -velX;
		// }
		// if(Y<0){
		// Y = 0;
		// velY = -velY;
		// }

		// if(X>CanvasMain.tilemap.Largura*16-charw){
		// X = CanvasMain.tilemap.Largura*16-charw;
		// velX = -velX;
		// }
		// if(Y>CanvasMain.tilemap.Altura*16-charh){
		// Y = CanvasMain.tilemap.Altura*16-charh;
		// velY = -velY;
		// }

		// if(CanvasMain.tilemap.mapa2[by][bx]!=0){
		// X = xold;
		// Y = yold;
		// velY = -velY;
		// velX = -velX;
		// }

		for (int i = 0; i < CanvasMain.listaDePersonagens.size(); i++) {
			Personagem pers = (Personagem) CanvasMain.listaDePersonagens.get(i);

			if (pers != this) {
				if (ColisaoRetangular(pers)) {
					X = xold;
					Y = yold;
					velY = -velY;
					velX = -velX;
					break;
				}
			}
		}

		if (FIRE && timertiro > 100) {
			float xy[] = new float[2];
			for (int i = 0; i < 31; i++) {
				for (int j = 0; j < 31; j++) {

					basetToXY(j, i, xy);
					
					double dx = xAlvo - (xy[0]+X);
					double dy = yAlvo - (xy[1]+Y);

					double ang = Math.atan2(dy, dx);

					float velo = 1200;

					Projetil proj = new Projetil(xy[0]+X, xy[1]+Y, (float) (velo * Math.cos(ang)), (float) (velo * Math.sin(ang)),
							this);

					CanvasMain.listaDeProjeteis.add(proj);

					timertiro = 0;
				}
			}
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		// dbg.drawImage(charset,(int)X-xMundo, (int)Y-yMundo,(int)X+charw-xMundo,
		// (int)Y+charh-yMundo,charposx+charw*frame,charposy+charh*anim,charposx+charw*frame+charw,charposy+charh*anim+charh,null);

		AffineTransform trans = dbg.getTransform();

		dbg.translate(X - xMundo, Y - yMundo);

		dbg.rotate(angulo);

		dbg.setColor(Color.white);
		dbg.drawOval((int) -raio, (int) -raio, raio * 2, raio * 2);

		// dbg.setColor(Color.red);
		// dbg.drawRect((int)X-xMundo-boundingbox.width/2,
		// (int)Y-yMundo-boundingbox.height/2,boundingbox.width,boundingbox.height);

		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 31; j++) {
				dbg.setColor(Color.red);
				dbg.drawRect((int) -basex + j * 32, (int) -basey + i * 32, 32, 32);
				if (layer0[i][j] == 1) {
					dbg.setColor(Color.red);
					dbg.fillRect((int) -basex + j * 32, (int) -basey + i * 32, 32, 32);
				}
				if (layer0[i][j] == 2) {
					dbg.setColor(Color.blue);
					dbg.fillRect((int) -basex + j * 32, (int) -basey + i * 32, 32, 32);
				}
			}
		}

		dbg.setColor(Color.green);
		dbg.drawLine(0, -basey, basex, 0);
		dbg.drawLine(0, basey, basex, 0);

		dbg.setTransform(trans);
	}

	public boolean testaColisao(Personagem pers) {
		float dx = (pers.X + 16) - (X + 16);
		float dy = (pers.Y + 24) - (Y + 24);

		float sr = pers.raio + raio;

		if (sr * sr > (dx * dx + dy * dy)) {
			return true;
		}

		return false;
	}

	public boolean testaColisao(float x, float y, float r) {
		float dx = (x + 16) - (X + 16);
		float dy = (y + 24) - (Y + 24);

		float sr = r + raio;

		if (sr * sr > (dx * dx + dy * dy)) {
			return true;
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

	public void levaDano(int dano) {
		life -= dano;
		if (life <= 0) {
			vivo = false;
		}
	}

	public void basetToXY(int j, int i, float ret[]) {
		int pi = i - 15;
		int pj = j - 15;

		int ix = pi * 32;
		int jx = pj * 32;

		double x = jx * Math.cos(angulo) - ix * Math.sin(angulo);
		double y = jx * Math.sin(angulo) + ix * Math.cos(angulo);
		ret[0] = (float)x;
		ret[1] = (float)y;
	}

}
