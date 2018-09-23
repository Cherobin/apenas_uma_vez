import java.awt.image.BufferedImage;

public class Base {

	public int id;
	public String nome;
	public BufferedImage imagem;
	public int tipo_base; //1 - bateria // 2 - Energia // 3-Propulsão
	public float custo;
	public float bateria;
	public float energia;
	public float propulsao;
	public float vida;
	public Boolean isSelected = false;
	
}
