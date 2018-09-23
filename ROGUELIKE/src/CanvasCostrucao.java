import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.FileWriter;

public class CanvasCostrucao extends MyCanvas {

	public MyCanvas canvasOrigem = null;

	public Color cor = null;

	private BufferedImage fundo;

	float mouseX,mouseY;
	int NTileX, NTileY;
	int base[][] = new int[31][31];
	int armas[][] = new int[31][31];

	int posXArma = 50 + 16;
	int posYArma = 10 + 16;

	int posXBase = 70 + 34;
	int posYBase = 10 + 16;

	Arma my_arma = new Arma();
	Base my_base = new Base();

	boolean isSelectedSalvar = false;
	boolean isSelectedCancelar = false;
	boolean semBase = false;
	boolean semGrana = false;
	boolean semVizinhos = false;
	float counterTooltipGrana = 0;
	float counterTooltipBase = 0;
	float counterTooltipVizinhos = 0;
	
	public CanvasCostrucao(MyCanvas canvasOrigem, Color cor) {
		fundo = GamePanel.instance.carregaImagem("fundo.png");

		this.canvasOrigem = canvasOrigem;
		this.cor = cor;
		NTileX = NTileY = 32; 
		for (int j = 0; j < base.length; j++) {
			for (int i = 0; i < base[j].length; i++) {
				base[j][i] = Constantes.heroi.layer0[j][i]; 
			} 
		}
 
		for (int j = 0; j < armas.length; j++) {
			for (int i = 0; i < armas[j].length; i++) {
				armas[j][i] = Constantes.heroi.layer1[j][i]; 
			} 
		}
		
		
		
		
		Constantes.bases.get(0).isSelected = true;
		my_base = Constantes.bases.get(0);
		
	}

	@Override
	public void SimulaSe(int diftime) {
		// TODO Auto-generated method stub
		
 
		if(semBase) {
			counterTooltipBase += diftime/1000f;
		}
		
		if(semGrana) {
			counterTooltipGrana += diftime/1000f;
		}
		
		if(semVizinhos) {
			counterTooltipVizinhos += diftime/1000f;
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		
		dbg.setFont(Constantes.font);
		 
		dbg.drawImage(Constantes.fundohangar, 0, 0, null);
	 
		dbg.setColor(Color.white);
		dbg.drawString("Clique nos icones para construção", 10, 15);

		
		dbg.drawString("Planta de Construção", 384, 48);
		 
		
		// DESENHA GRID
		for (int j = 0; j < base.length; j++) {
			for (int i = 0; i < base[j].length; i++) {
				if (base[j][i] == 0) {
					dbg.drawImage(fundo, i * 16 + 384, j * 16 + 50, 16, 16, null);
				} else {
					dbg.drawImage(getImageBase(base[j][i]), i * 16 + 384, j * 16 + 50, 16, 16, null);
				}
			}
		}

		for (int j = 0; j < armas.length; j++) {
			for (int i = 0; i < armas[j].length; i++) {
				if (armas[j][i] != 0) {
					dbg.drawImage(getImageArma(armas[j][i]), i * 16 + 384, j * 16 + 50, 16, 16, null);
				}
			}
		}

		dbg.setFont(Constantes.font.deriveFont(Font.BOLD, 10));
	  
		dbg.setColor(Color.WHITE);
		dbg.drawString("Bases", 165 + posXBase, 45);
		
		dbg.setColor(Color.WHITE);
		dbg.drawString("Armas", 162 + posXArma, 45);
		
		// DESENHA MENU
		for (int j = 0; j < Constantes.bases.size(); j++) {
			if (Constantes.bases.get(j).isSelected) {
				dbg.setColor(Color.YELLOW);
				dbg.drawRect(170 + posXBase, 50 + posYBase * j, 17, 17);
			}

			dbg.drawImage(Constantes.bases.get(j).imagem, 170 + posXBase, 50 + posYBase * j, null);
		}

		for (int j = 0; j < Constantes.armas.size(); j++) {
			if (Constantes.armas.get(j).isSelected) {
				dbg.setColor(Color.YELLOW);
				dbg.drawRect(170 + posXArma, 50 + posYArma * j, 17, 17);
			}
			dbg.drawImage(Constantes.armas.get(j).imagem, 170 + posXArma, 50 + posYArma * j, null);
		}

		
		dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 20));
		dbg.setColor(Color.WHITE);
		
		if (my_base != null) {
			dbg.drawString("Nome: " + my_base.nome, 10, 60);
			dbg.drawString("R$: " + my_base.custo, 10, 90);
			dbg.drawString("Tipo Base: " + my_base.tipo_base, 10, 120);
			dbg.drawString("Vida: " + my_base.vida, 10, 150); 
			dbg.drawString("Bateria: " + my_base.bateria, 10, 180);
			dbg.drawString("Energia: " + my_base.energia, 10, 210);
			dbg.drawString("Propulsão: " + my_base.propulsao, 10, 240);
			
		} else  if (my_arma != null) {	
			dbg.drawString("Nome: " + my_arma.nome, 10, 60);
			dbg.drawString("R$: " + my_arma.custo, 10, 90);
			dbg.drawString("Dano: " + my_arma.dano, 10, 120);
			dbg.drawString("Custo de Energia: " + my_arma.energia, 10, 150);
			dbg.drawString("Candêcia tiro: " + my_arma.candecia_tiro, 10, 180);
			dbg.drawString("Velocidade tiro: " + my_arma.velocidade_tiro, 10, 210);
			dbg.drawString("Tiro automático: " + my_arma.tiro_automatico, 10, 240);
			dbg.drawString("Range: " + my_arma.range, 10, 270);
		}   
		
		
		dbg.setFont(Constantes.font.deriveFont(Font.BOLD, 40));
		
		dbg.setColor(Color.GREEN);
		dbg.drawString("$: " + Constantes.gold, 10, 310);

		dbg.setColor(Color.black);
		dbg.drawString("Salvar", Constantes.telaW - 149, Constantes.telaH - 49);
		dbg.setColor(Color.WHITE);
		dbg.drawString("Salvar", Constantes.telaW - 150, Constantes.telaH - 50);

		if (isSelectedSalvar) {
			dbg.setColor(Color.YELLOW);
			dbg.drawString("Salvar", Constantes.telaW - 150, Constantes.telaH - 50);
		}

		dbg.setColor(Color.BLACK);
		dbg.drawString("Cancelar", 75, Constantes.telaH - 49);
		dbg.setColor(Color.WHITE);
		dbg.drawString("Cancelar", 76, Constantes.telaH - 50);

		if (isSelectedCancelar) {
			dbg.setColor(Color.YELLOW);
			dbg.drawString("Cancelar", 76, Constantes.telaH - 50);
		}
		
		

		dbg.setFont(Constantes.font.deriveFont(Font.PLAIN, 10));
		
		
		//toooltip kkkk
		if(semBase && counterTooltipBase < 3) {
			dbg.setColor(Color.white);
			dbg.fillRect((int)mouseX, (int)mouseY-12, 270, 20);
			dbg.setColor(Color.RED);
			dbg.drawString("  Para Construir uma arma, é necessário ter uma base", mouseX, mouseY);
		}else {
			semBase = false;
			counterTooltipBase = 0;
		}
		
		
		if(semGrana&& counterTooltipGrana < 3) { 
			dbg.setColor(Color.white);
			dbg.fillRect((int)mouseX, (int)mouseY-12, 160, 20);
			dbg.setColor(Color.RED);
			dbg.drawString("  Você não possuí R$ suficiente", mouseX, mouseY);
		}else {
			semGrana = false;
			counterTooltipGrana = 0;
		}
		
		
		if(semVizinhos&& counterTooltipVizinhos < 3) { 
			dbg.setColor(Color.white);
			dbg.fillRect((int)mouseX, (int)mouseY-12, 200, 20);
			dbg.setColor(Color.RED);
			dbg.drawString("  Um bloco deve estar conectado ao outro!", mouseX, mouseY);
		}else {
			semVizinhos = false;
			counterTooltipVizinhos = 0;
		}
		
		
		
	}

	BufferedImage getImageBase(int id) {

		for (int j = 0; j < Constantes.bases.size(); j++) {
			if (Constantes.bases.get(j).id == id) {
				return Constantes.bases.get(j).imagem;
			}
		}
		return fundo;
	}

	BufferedImage getImageArma(int id) {

		for (int j = 0; j < Constantes.armas.size(); j++) {
			if (Constantes.armas.get(j).id == id) {
				return Constantes.armas.get(j).imagem;
			}
		}
		return fundo;
	}
	
	boolean podeCriar(int x, int y) {
		 
		 for (int j = x-1; j <= x+1; j++) {
			for (int i = y-1; i <= y+1; i++) {
				if(j<31 && i < 31 && i >0 && j >0) { // não pegar null
					if(base[j][i]!=0){
						return true;
					}
				}
			}
		}
		
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ESCAPE) {
			GamePanel.telaAtiva = canvasOrigem;
		}
		if (keyCode == KeyEvent.VK_G) {
			GamePanel.telaAtiva = new CanvasCostrucao(this, Color.GREEN);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		 
        
		if (keyCode == KeyEvent.VK_S) {
			System.out.println("#Layer1");
			for (int j = 0; j < base.length; j++) {
				for (int i = 0; i < base[j].length; i++) {
					base[j][i] = Constantes.heroi.layer0[j][i];
					System.out.print(base[j][i]+";");
				}
				System.out.println();
			}

			System.out.println("#Layer2");
			for (int j = 0; j < armas.length; j++) {
				for (int i = 0; i < armas[j].length; i++) {
					armas[j][i] = Constantes.heroi.layer1[j][i];
					System.out.print(armas[j][i]+";");
				}
				System.out.println();
			}
			
		}
		
		 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		mouseX = e.getX();
		mouseY = e.getY();
		
		Rectangle rect = new Rectangle(Constantes.telaW - 150, Constantes.telaH - 78, 128, 38);
		if (rect.contains(e.getX(), e.getY())) {
			isSelectedSalvar = true;
		} else {
			isSelectedSalvar = false;
		}

		rect = new Rectangle(75, Constantes.telaH - 78, 158, 38);
		if (rect.contains(e.getX(), e.getY())) {
			isSelectedCancelar = true;
		} else {
			isSelectedCancelar = false;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

		if (isSelectedCancelar) {
			GamePanel.telaAtiva = canvasOrigem;
		}

		if (isSelectedSalvar) {
			for (int j = 0; j < base.length; j++) {
				for (int i = 0; i < base[j].length; i++) {
					Constantes.heroi.layer0[j][i] = base[j][i];
				}
			}

			for (int j = 0; j < armas.length; j++) {
				for (int i = 0; i < armas[j].length; i++) {
					Constantes.heroi.layer1[j][i] = armas[j][i];
				}
			}
			GamePanel.telaAtiva = canvasOrigem;
		}

		if (my_base != null) {
				for (int j = 0; j < base.length; j++) {
					for (int i = 0; i < base[j].length; i++) {
						Rectangle rect = new Rectangle(i * 16 + 384, j * 16 + 50, 16, 16);
						if (rect.contains(arg0.getX(), arg0.getY())) {
							if (Constantes.gold >= my_base.custo) {
								if(podeCriar(j,i) && base[j][i] == 0) {
									Constantes.gold -= my_base.custo;
									base[j][i] = my_base.id;
								}else {
									semVizinhos = true;
								}
							}else {
								semGrana = true;
							}
							break;
						}
					}
				}

			}
	 

		if (my_arma != null) {
				for (int j = 0; j < armas.length; j++) {
					for (int i = 0; i < armas[j].length; i++) {
						Rectangle rect = new Rectangle(i * 16 + 384, j * 16 + 50, 16, 16);
						if (rect.contains(arg0.getX(), arg0.getY())) {
							if (Constantes.gold >= my_arma.custo) {
								if( base[j][i] !=0 && armas[j][i] ==0) {
									Constantes.gold -= my_arma.custo;
									armas[j][i] = my_arma.id;
								}else {
									semBase = true;
								}
							}else {
								semGrana = true;
							}
							
							break;
						}
					}
				}
			}
		 

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

		for (int j = 0; j < Constantes.armas.size(); j++) {
			Rectangle rect = new Rectangle(170 + posXArma, 50 + posYArma * j, 16, 16);
			if (rect.contains(arg0.getX(), arg0.getY())) {

				Constantes.armas.get(j).isSelected = true;

				for (int i = 0; i < Constantes.armas.size(); i++) {
					if (!Constantes.armas.get(j).equals(Constantes.armas.get(i))) {
						Constantes.armas.get(i).isSelected = false;
					}
				}
				for (int i = 0; i < Constantes.bases.size(); i++) {
					Constantes.bases.get(i).isSelected = false;
				}
				my_base = null;
				my_arma = Constantes.armas.get(j);
				break;
			}
		}

		for (int j = 0; j < Constantes.bases.size(); j++) {
			Rectangle rect = new Rectangle(170 + posXBase, 50 + posYBase * j, 16, 16);
			if (rect.contains(arg0.getX(), arg0.getY())) {
				Constantes.bases.get(j).isSelected = true;

				for (int i = 0; i < Constantes.bases.size(); i++) {
					if (!Constantes.bases.get(j).equals(Constantes.bases.get(i))) {
						Constantes.bases.get(i).isSelected = false;
					}
				}
				for (int i = 0; i < Constantes.armas.size(); i++) {
					Constantes.armas.get(i).isSelected = false;
				}
				my_arma = null;
				my_base = Constantes.bases.get(j);
				break;
			}
		}
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
