import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProcessImage {
	public static void main(String[] args) throws IOException {
		BufferedImage bi = ImageIO.read(new File("Image_created_with_a_mobile_phone.png"));
		getPixel(bi);
	}

	public static void getPixel(BufferedImage image) {
		for(int i = 0; i < image.getWidth(); i++) {
			for(int j = 0; j < image.getHeight(); j++) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				System.out.println("red: " + red + ", green: " + green + ", blue: " + blue);
			}
		}
	}
}
