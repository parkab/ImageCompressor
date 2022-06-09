import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportImage {
	public static void createImage() throws IOException {
		BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
		File f = new File("based.png");
		int r = 5;
		int g = 25;
		int b = 255;
		int col = (r << 16) | (g << 8) | b;
		for(int i = 0; i < 300; i++) {
			for(int j = 0; j < 300; j++) {
				img.setRGB(i, j, col);
			}
		}

		ImageIO.write(img, "PNG", f);
	}

	public static void exportImage(String filepath, BufferedImage bi) {
		try {
			File output = new File(filepath);
			ImageIO.write(bi, "png",output);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws IOException {
		createImage();
	}
}
