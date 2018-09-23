import java.awt.image.BufferedImage;

public class Bilboard {
	float x;
	float y;
	float z;
	
	BufferedImage img = null;
	
	public Bilboard(float x, float y, float z, BufferedImage img) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.img = img;
	}
	
	
}
