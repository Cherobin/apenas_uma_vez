import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


public class CanvasMenu extends MyCanvas {
	
	public MyCanvas canvasOrigem = null;
	
	public Color cor = null;
	
	
	
	
	public CanvasMenu(MyCanvas canvasOrigem,Color cor) {
		this.canvasOrigem = canvasOrigem;
		this.cor = cor;
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
		
		dbg.setColor(Color.white);
		dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 20));
		dbg.drawString("MENU DO JOGO PRESSIONE ESC PARA SAIR", 10, 10);
		dbg.drawString(""+somatime,300,250);
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

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
