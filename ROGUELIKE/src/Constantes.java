import java.awt.Rectangle;
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

}
