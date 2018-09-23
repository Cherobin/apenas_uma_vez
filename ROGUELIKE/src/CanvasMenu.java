import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


public class CanvasMenu extends MyCanvas {
	
	public MyCanvas canvasOrigem = null;
	
	public Color cor = null;
	
	boolean isStart = false;
	String[] split;
	String name = "B u l l e t of h e l l";
	public CanvasMenu(MyCanvas canvasOrigem,Color cor) {
		this.canvasOrigem = canvasOrigem;
		this.cor = cor;
		
		split= name.split(" ");
	}
	
	long somatime = 0;
	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		somatime += diftime;
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.setColor(cor);
		dbg.fillRect(0, 0, Constantes.telaW, Constantes.telaH);
		
		 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 70));
		 dbg.setColor(Color.WHITE);
		 dbg.drawString("Bullet of Hell", Constantes.telaW/2 - 250, 70);
		 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 69));
		
		 
		 dbg.setColor(Color.RED);
		if(somatime%30==0) {
			 dbg.setColor(Color.BLACK);
			 somatime=0;
		}

	
		 dbg.drawString("          Hell", Constantes.telaW/2 - 250+3, 70);
		 
		 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 65));
		 if(isStart) {
		 dbg.setColor(Color.RED);
		 }else {
			 dbg.setColor(Color.YELLOW);
		 }
		 dbg.drawString("CLICK TO START", Constantes.telaW/2 - 220, Constantes.telaH/2);
		
		  
 
		 
		 dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 18));
		 dbg.setColor(Color.WHITE);
		 
		 dbg.drawString("Controles:", 50, 470);
		 
		 //lado esquerdo
		 dbg.drawString("MOUSE: MIRA", 150, 500);  
		 dbg.drawString("TECLA A: ROTACIONA PARA ESQUERDA", 150, 530);
		 dbg.drawString("TECLA D: ROTACIONA PARA DIREITA", 150, 560);
		 dbg.drawString("TECLA W: AUMENTA VELOCIDADE", 150, 590);
		 dbg.drawString("TECLA S: DIMINUI VELOCIDADE", 150, 620);
		 
		 //lado direito
		 dbg.drawString("MOUSE SCROLL: ZOOM IN/OUT",540, 500);
		 dbg.drawString("TECLA ALT: CURA",540, 530);
		 dbg.drawString("TECLA BARRRA DE ESPAÇO: DASH",540, 560);
		 dbg.drawString("TECLA H: HANGER",540, 590);
		 
		 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ESCAPE){
			GamePanel.telaAtiva = canvasOrigem;
		}
		if(keyCode == KeyEvent.VK_G){
			GamePanel.telaAtiva = new CanvasMenu(this,Color.GREEN);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
 
		
		Rectangle rect = new Rectangle( Constantes.telaW/2 - 220, Constantes.telaH/2-70, 440, 70);
		if (rect.contains(e.getX(), e.getY())) {
			isStart = true;
		} else {
			isStart = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

		if (isStart) {
			GamePanel.telaAtiva = new CanvasMain();
		}

		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

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

	}

}
