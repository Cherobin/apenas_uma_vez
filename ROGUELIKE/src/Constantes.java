import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Constantes {
	public static int telaW = 1024;
	public static int telaH = 640;
	public static List<Arma> armas = new ArrayList<>();
	public static List<Base> bases = new ArrayList<>();
	
	public static Rectangle telaRect = new Rectangle();
	
	public static Personagem heroi;

	public static Quadrante quadrantes[][] = new Quadrante[40][40];
	
	public static double gold = 1000000;
	
	public static List<NaveBase> navesBase = new ArrayList<>();
	
	public static NaveBase navePLayer;
	
	public static BufferedImage expolsao[] = new BufferedImage[5];
	
	public static BufferedImage fragmento[] = new BufferedImage[2];
	
	public static BufferedImage bilboards[] = new BufferedImage[7];
	
	public static void carregaImagens() {
		expolsao[0] = GamePanel.instance.carregaImagem("exp01.png");
		expolsao[1] = GamePanel.instance.carregaImagem("exp02.png");
		expolsao[2] = GamePanel.instance.carregaImagem("exp03.png");
		expolsao[3] = GamePanel.instance.carregaImagem("exp04.png");
		expolsao[4] = GamePanel.instance.carregaImagem("exp05.png");
		
 

		fragmento[0] = GamePanel.instance.carregaImagem("fragmento_1.png");
		fragmento[1] = GamePanel.instance.carregaImagem("fragmento_2.png");
		
		goldImage = GamePanel.instance.carregaImagem("gold.png");
		lifeImage = GamePanel.instance.carregaImagem("life.png");

		
		bilboards[0] = GamePanel.instance.carregaImagem("Bilboard_1.png");
		bilboards[1] = GamePanel.instance.carregaImagem("Bilboard_2.png");
		bilboards[2] = GamePanel.instance.carregaImagem("Bilboard_3.png");
		bilboards[3] = GamePanel.instance.carregaImagem("Bilboard_4.png");
		bilboards[4] = GamePanel.instance.carregaImagem("Bilboard_5.png");
		bilboards[5] = GamePanel.instance.carregaImagem("Bilboard_6.png");
		bilboards[6] = GamePanel.instance.carregaImagem("Bilboard_7.png");
		
		hudImage = GamePanel.instance.carregaImagem("hud.png");

		fundohangar = GamePanel.instance.carregaImagem("fundohangar.png");

		faisca  = GamePanel.instance.carregaImagem("faisca.png");
	}

	
	public static List<BufferedImage> imgBigAsteroide = new ArrayList<>();
	public static List<BufferedImage> imgSmallAsteroide = new ArrayList<>(); 
 
	public static BufferedImage goldImage;
	public static BufferedImage lifeImage;
	public static BufferedImage hudImage;
	public static BufferedImage fundohangar;
	public static BufferedImage faisca;
	
	public static Font font;
	    
 

}
