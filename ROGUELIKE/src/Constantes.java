import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
	
	public static BufferedImage expolsao[] = new BufferedImage[5];
	
	public static void carregaImagens() {
		expolsao[0] = GamePanel.instance.carregaImagem("exp01.png");
		expolsao[1] = GamePanel.instance.carregaImagem("exp02.png");
		expolsao[2] = GamePanel.instance.carregaImagem("exp03.png");
		expolsao[3] = GamePanel.instance.carregaImagem("exp04.png");
		expolsao[4] = GamePanel.instance.carregaImagem("exp05.png");
		
		goldImage = GamePanel.instance.carregaImagem("gold.png");
		lifeImage = GamePanel.instance.carregaImagem("life.png");
	}

	
	public static List<BufferedImage> imgBigAsteroide = new ArrayList<>();
	public static List<BufferedImage> imgSmallAsteroide = new ArrayList<>(); 
 
	public static BufferedImage goldImage;
	public static BufferedImage lifeImage;

	

}
