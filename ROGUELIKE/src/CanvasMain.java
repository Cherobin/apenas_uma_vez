import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class CanvasMain extends MyCanvas {
	
 
	public static BufferedImage fumaca = null;
	
	BufferedImage imagefundo;

	boolean LEFT, RIGHT,UP,DOWN,FIRE;


	int MouseX,MouseY;

	float x,y;
	float x2,y2;
	int tempomovimento = 0;
	int tempomovimento2 = 0;



	double posx = 0;
	double posy = 0;

	double rotx = 0;
	public Random rnd;


	public static ArrayList<Sprite> listaDePersonagens = new ArrayList<Sprite>();

	public static ArrayList<Sprite> listaDeProjeteis = new ArrayList<Sprite>();

	public static ArrayList<Sprite> listaDeParticulas = new ArrayList<Sprite>();

	public static ArrayList<Sprite> listaDeAsteroides = new ArrayList<Sprite>();
	
	public static ArrayList<Sprite> listaDeItens = new ArrayList<Sprite>();
	
	public static GerenciadorDeEventos gerenciadorEventos = new GerenciadorDeEventos();
	
	int timerfps = 0;
	int somacor = 0;
	int timertiro = 0;
	
	int MapX = 0;
	int MapY = 0;
	
	double zoom;
	double newzoom;
	
	
	public Font fonteMini = new Font("", Font.BOLD,8);
	
	public Font fonte = new Font("", Font.BOLD,16);
	
	public Font fonteG1 = new Font("", Font.BOLD,22);
	
	public Color lifebarcolor = new Color(0, 155, 0,155) ;
	
	public CanvasMain() {
		//imgFundo = GamePanel.instance.carregaImagem("nebulosa.jpg");
		//imgCharset = GamePanel.instance.carregaImagem("rmxp004tw4.png");
		//tileset = GamePanel.instance.carregaImagem("Bridge.png");
		//fumaca = GamePanel.instance.carregaImagem("fumaca.png");
		
		rnd = new Random();
		x = 0;
		y = 50; 
		
		x2 = 0;
		y2 = 200;

		Constantes.heroi = new Personagem(100, 100,5000, Constantes.navesBase.get(0));
		Constantes.heroi.vel = 200;


		listaDePersonagens.add(Constantes.heroi);
		
		for(int i = 0; i < 10;i++){
			int posx = 0;
			int posy = 0;
			
			int bx = 0;
			int by = 0;
			
			boolean colidiu = false;
				
			do{
				
					
				colidiu = false;
				posx = GamePanel.rnd.nextInt(16000)-8000;
				posy = GamePanel.rnd.nextInt(16000)-8000;
				
				bx = (int)((posx+16)/16);
				by = (int)((posy+40)/16);
				
				for(int j = 0; j < listaDePersonagens.size();j++){
					Personagem pers = (Personagem)listaDePersonagens.get(j);
					
//					if(pers.ColisaoRetangular(new Personagem(posx, posy))){
//						colidiu = true;
//						continue;
//					}
				}
				
			}while(colidiu); 
			Personagem pers = new Personagem(posx, posy,500,Constantes.navesBase.get(rnd.nextInt(Constantes.navesBase.size()-1)));
			pers.vel = 50+GamePanel.rnd.nextInt(50);
			pers.angulo = (Math.PI*2)*GamePanel.rnd.nextDouble();
			pers.rodaia = true;
			//pers.vel = (int)Math.sqrt(pers.velX*pers.velX + pers.velY*pers.velY);
			
			listaDePersonagens.add(pers);
		}
		
		 
		
		for(int i = 0; i < 100;i++){
			int posx = 0;
			int posy = 0;
			
			int bx = 0;
			int by = 0;
			
			boolean colidiu = false;
				
			do{
				
					
				colidiu = false;
				posx = GamePanel.rnd.nextInt(16000)-8000;
				posy = GamePanel.rnd.nextInt(16000)-8000;
				
				bx = (int)((posx+16)/16);
				by = (int)((posy+40)/16);
				
			 
				
			}while(colidiu);
 
			Asteroides pers = new Asteroides(posx, posy,500,Constantes.imgBigAsteroide.get(rnd.nextInt(Constantes.imgBigAsteroide.size()-1)), true);
			pers.vel = 50+GamePanel.rnd.nextInt(50);
			pers.angulo = (Math.PI*2)*GamePanel.rnd.nextDouble();
			pers.rodaia = true;
			//pers.vel = (int)Math.sqrt(pers.velX*pers.velX + pers.velY*pers.velY);
			
			listaDeAsteroides.add(pers);
		}
		
		zoom = 0.5;
		newzoom = 0.5;
		
//		gerenciadorEventos.carregaEventos(this.getClass().getResourceAsStream("ecentos.csv"));
		
		
	}
	@Override
	public void SimulaSe(int diftime) {
		timertiro+=diftime;
		zoom = newzoom;

		if(LEFT){
			Constantes.heroi.angulo-=Math.PI*diftime/1000.0f;

		}else if(RIGHT){
			Constantes.heroi.angulo+=Math.PI*diftime/1000.0f;

		}
		
		if(UP){
			Constantes.heroi.vel += 50*diftime/1000.0f;
			
			System.out.println("Constantes.heroi.vel "+Constantes.heroi.vel);
			
			if(Constantes.heroi.vel>=400) {
				Constantes.heroi.vel=400;
			}
		}else if(DOWN){
			Constantes.heroi.vel-=50*diftime/1000.0f;
			
			//System.out.println("DDD Constantes.heroi.vel "+Constantes.heroi.vel);
			
			if(Constantes.heroi.vel<=0) {
				Constantes.heroi.vel=0;
			}
		}
		

		Constantes.heroi.FIRE = FIRE;


		
		Constantes.heroi.xAlvo = (float)(MouseX/zoom+MapX);
		Constantes.heroi.yAlvo = (float)(MouseY/zoom+MapY);


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
		
		for(int i = 0; i < listaDeAsteroides.size();i++){
			Sprite sp = listaDeAsteroides.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDeAsteroides.remove(i);
				i--;
			}
		}	
		
		for(int i = 0; i < listaDeItens.size();i++){
			Sprite sp = listaDeItens.get(i);
			sp.SimulaSe((int)diftime);
			if(!sp.vivo){
				listaDeItens.remove(i);
				i--;
			}
		}
		
		
		
//		gerenciadorEventos.testaEventos(heroi);
		
		MapX = (int)(Constantes.heroi.X-(Constantes.telaW/zoom)/2);
		MapY = (int)(Constantes.heroi.Y-(Constantes.telaH/zoom)/2);
		
		Constantes.telaRect.setBounds(MapX, MapY, (int)(Constantes.telaW/zoom), (int)(Constantes.telaH/zoom));
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// clear the background
		dbg.setColor(Color.BLACK);
		dbg.fillRect (0, 0, Constantes.telaW, Constantes.telaH);
		
		for(int i = 0; i < 40; i++) {
			for(int j = 0; j < 40; j++) {
				Rectangle bquadrante = new Rectangle((j-20)*8000, (i-20)*8000, 8000, 8000);
				
				if(Constantes.telaRect.intersects(bquadrante)) {
					if(Constantes.quadrantes[i][j]!=null) {
						//System.out.println(Constantes.telaRect+" "+bquadrante);
						Constantes.quadrantes[i][j].DesenhaSe(dbg, MapX, MapY);
					}else {
						Constantes.quadrantes[i][j] = new Quadrante(j-20, i-20);
						Constantes.quadrantes[i][j].DesenhaSe(dbg, MapX, MapY);
					}
				}
			}
		}
		
		//dbg.drawImage(imgFundo,(int)(-500+(heroi.X/100)),(int)(-500+(heroi.Y/100)),null);
		
		AffineTransform trans = dbg.getTransform();
		
		dbg.scale(zoom, zoom);

//		dbg.drawImage(imgFundo, null,0,0);
		
		for(int i = 0; i < listaDeItens.size();i++){
			listaDeItens.get(i).DesenhaSe(dbg,MapX,MapY);
		}
		
		for(int i = 0; i < listaDePersonagens.size();i++){
			listaDePersonagens.get(i).DesenhaSe(dbg,MapX,MapY);
		}
		
		for(int i = 0; i < listaDeProjeteis.size();i++){
			listaDeProjeteis.get(i).DesenhaSe(dbg,MapX,MapY);
		}
		
		for(int i = 0; i < listaDeParticulas.size();i++){
			listaDeParticulas.get(i).DesenhaSe(dbg,MapX,MapY);
		}	
		
		for(int i = 0; i < listaDeAsteroides.size();i++){
			listaDeAsteroides.get(i).DesenhaSe(dbg,MapX,MapY);
		}	
		
	
		
//		heroi.DesenhaSe(dbg);
		
		//gerenciadorEventos.desenhase(dbg, MapX,MapY);
		
		dbg.setTransform(trans);
		
		//Font f = dbg.getFont();
		
		//dbg.setFont(fonte);
		//dbg.setColor(Color.YELLOW);	
		//dbg.drawString("FPS: "+GamePanel.FPS, 10, 20);
		
		//dbg.setFont(fonteMini);	
	//	dbg.drawString("Velocidade", 10, Constantes.telaH-26);
		//dbg.drawString(""+(int)(Constantes.heroi.vel), 10, Constantes.telaH-15);
		
		 dbg.setFont(fonteMini);	 
		 dbg.setColor(Color.YELLOW);
		 dbg.drawString(""+(int)(Constantes.gold), 60, 32);
		
		dbg.setColor(lifebarcolor);
	
		dbg.fillRect(51, 41, (int)(587*(Constantes.heroi.life/(float)Constantes.heroi.lifeMax)), 21);

	
	
		//dbg.drawString("Life", 105, Constantes.telaH-28);
		
		//dbg.setFont(f);
		
		dbg.drawImage(Constantes.hudImage, 0, 0, null);
		 
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			LEFT = true;
		}
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			RIGHT = true;
		}
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			UP = true;
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
			DOWN = true;
		}	
		if(keyCode == KeyEvent.VK_SPACE){

		}
		if(keyCode == KeyEvent.VK_M){
			GamePanel.telaAtiva = new CanvasCostrucao(this,Color.blue);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
			LEFT = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
			RIGHT = false;
		}
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W){
			UP = false;
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S){
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
			newzoom = zoom*1.1;
		}else{
			newzoom = zoom*0.9;
		}
		
		if(newzoom>2) {
			newzoom = 2;
		}
		
		if(newzoom<0.05) {
			newzoom = 0.05;
		}
		
//		double mouseposnewx = MouseX/fatorDeEscala;
//		double mouseposnewy = MouseY/fatorDeEscala;
		
//		posx += -(oldpox-mouseposnewx);
//		posy += -(oldpoy-mouseposnewy);
	}

}
