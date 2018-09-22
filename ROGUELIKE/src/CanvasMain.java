import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class CanvasMain extends MyCanvas {
	


	private BufferedImage imgFundo;
	private BufferedImage imgCharset;
	private BufferedImage tileset;

	public static BufferedImage fumaca = null;
	
	BufferedImage imagefundo;

	boolean LEFT, RIGHT,UP,DOWN,FIRE;


	int MouseX,MouseY;

	float x,y;
	float x2,y2;
	int tempomovimento = 0;
	int tempomovimento2 = 0;

	boolean scale = false;

	double fatorDeEscala = 0.5;

	double posx = 0;
	double posy = 0;

	double rotx = 0;

	public static Personagem heroi;

	public static ArrayList<Sprite> listaDePersonagens = new ArrayList<Sprite>();

	public static ArrayList<Sprite> listaDeProjeteis = new ArrayList<Sprite>();

	public static ArrayList<Sprite> listaDeParticulas = new ArrayList<Sprite>();

	public static GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
	
	int timerfps = 0;
	int somacor = 0;
	int timertiro = 0;
	
	int MapX = 0;
	int MapY = 0;
	
	
	
	public CanvasMain() {
		imgFundo = GamePanel.instance.carregaImagem("Imagem 675.jpg");
		imgCharset = GamePanel.instance.carregaImagem("rmxp004tw4.png");
		tileset = GamePanel.instance.carregaImagem("Bridge.png");
		fumaca = GamePanel.instance.carregaImagem("fumaca.png");
		
		x = 0;
		y = 50; 
		
		x2 = 0;
		y2 = 200;

		heroi = new Personagem(100, 100, imgCharset,0);

		listaDePersonagens.add(heroi);
		for(int i = 0; i < 10;i++){
			int posx = 0;
			int posy = 0;
			
			int bx = 0;
			int by = 0;
			
			boolean colidiu = false;
				
			do{
				
					
				colidiu = false;
				posx = GamePanel.rnd.nextInt(600);
				posy = GamePanel.rnd.nextInt(400);
				
				bx = (int)((posx+16)/16);
				by = (int)((posy+40)/16);
				
				for(int j = 0; j < listaDePersonagens.size();j++){
					Personagem pers = (Personagem)listaDePersonagens.get(j);

					if(pers.ColisaoRetangular(new Personagem(posx, posy, null, 0))){
						colidiu = true;
						continue;
					}
				}
				
			}while(colidiu);

			
			Personagem pers = new Personagem(posx, posy, imgCharset, GamePanel.rnd.nextInt(7)+1);
			pers.velX = -50+GamePanel.rnd.nextInt(100);
			pers.velY = -50+GamePanel.rnd.nextInt(100);
			
			pers.vel = (int)Math.sqrt(pers.velX*pers.velX + pers.velY*pers.velY);
			
			listaDePersonagens.add(pers);
		}
		
		
		
		gerenciadorEventos.carregaEventos(this.getClass().getResourceAsStream("ecentos.csv"));
		
		
	}
	@Override
	public void SimulaSe(int diftime) {
		timertiro+=diftime;
		
		int vel = 200;
		if(LEFT){
			heroi.velX = -vel;
			heroi.anim = 1;
		}else if(RIGHT){
			heroi.velX = vel;
			heroi.anim = 2;
		}else{
			heroi.velX = 0;
		}
		if(UP){
			heroi.velY = -vel;
			heroi.anim = 3;
		}else if(DOWN){
			heroi.velY = vel;
			heroi.anim = 0;
		}else{
			heroi.velY = 0;
		}
		
		if(FIRE&&timertiro>400){
			float dx = (MouseX+MapX)-(heroi.X+16);
			float dy = (MouseY+MapY)-(heroi.Y+24);
			
			double ang = Math.atan2(dy, dx);
			
			float velo = 400;
			
			Projetil proj = new Projetil(heroi.X+16, heroi.Y+24, (float)(velo*Math.cos(ang)), (float)(velo*Math.sin(ang)),heroi);
			
			listaDeProjeteis.add(proj);
			
			timertiro = 0;
		}

//		heroi.SimulaSe((int)diftime);
		for(int i = 0; i < listaDePersonagens.size();i++){
			Sprite sp = listaDePersonagens.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDePersonagens.remove(i);
				i--;
			}
		}
		
		
		for(int i = 0; i < listaDeProjeteis.size();i++){
			Sprite sp = listaDeProjeteis.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDeProjeteis.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < listaDeParticulas.size();i++){
			Sprite sp = listaDeParticulas.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDeParticulas.remove(i);
				i--;
			}
		}	
		
		gerenciadorEventos.testaEventos(heroi);
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// clear the background
		dbg.setColor(Color.white);
		dbg.fillRect (0, 0, Constantes.telaW, Constantes.telaH);

//		dbg.drawImage(imgFundo, null,0,0);
		
		for(int i = 0; i < listaDePersonagens.size();i++){
			listaDePersonagens.get(i).DesenhaSe(dbg,MapX,MapY);
		}
		
		for(int i = 0; i < listaDeProjeteis.size();i++){
			listaDeProjeteis.get(i).DesenhaSe(dbg,MapX,MapY);
		}
		
		for(int i = 0; i < listaDeParticulas.size();i++){
			listaDeParticulas.get(i).DesenhaSe(dbg,MapX,MapY);
		}	
//		heroi.DesenhaSe(dbg);
		
		gerenciadorEventos.desenhase(dbg, MapX,MapY);
		
		dbg.setColor(Color.BLUE);	
//		dbg.drawString("FPS: "+FPS+" MouseX: "+MouseX+" MouseY: "+MouseY+" LEFT "+LEFT+" RIGHT "+RIGHT+" UP "+UP+" DOWN "+DOWN, 10, 10);	
		dbg.drawString("FPS: "+GamePanel.FPS+" TMov1: "+tempomovimento+" TMov2: "+tempomovimento2  , 10, 10);			
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT){
			LEFT = true;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			RIGHT = true;
		}
		if(keyCode == KeyEvent.VK_UP){
			UP = true;
		}
		if(keyCode == KeyEvent.VK_DOWN){
			DOWN = true;
		}	
		if(keyCode == KeyEvent.VK_SPACE){
			scale=!scale;
		}
		if(keyCode == KeyEvent.VK_M){
			GamePanel.telaAtiva = new CanvasMenu(this,Color.blue);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT){
			LEFT = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			RIGHT = false;
		}
		if(keyCode == KeyEvent.VK_UP){
			UP = false;
		}
		if(keyCode == KeyEvent.VK_DOWN){
			DOWN = false;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		FIRE = false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		FIRE = true;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
//		double oldpox = MouseX/fatorDeEscala;
//		double oldpoy = MouseY/fatorDeEscala;
		
		if(arg0.getWheelRotation()>0){
			rotx+=0.1;
		}else{
			rotx-=0.1;
		}
		
//		double mouseposnewx = MouseX/fatorDeEscala;
//		double mouseposnewy = MouseY/fatorDeEscala;
		
//		posx += -(oldpox-mouseposnewx);
//		posy += -(oldpoy-mouseposnewy);
	}

}
