import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportImage {
	public static void createImage(int[][] r, int[][] g, int b[][]) throws IOException {
		BufferedImage img = new BufferedImage(r[0].length, r.length, BufferedImage.TYPE_INT_RGB);
		File f = new File("otherbased.png");
		for(int i = 0; i < r[0].length; i++) {
			for(int j = 0; j < r.length; j++) {
				int col = (r[j][i] << 16) | (g[j][i] << 8) | b[j][i];
				img.setRGB(j, i, col);
			}
		}

		ImageIO.write(img, "PNG", f);
	}

	public static void createeImage() throws IOException {
		BufferedImage img = new BufferedImage(304, 304, BufferedImage.TYPE_INT_RGB);
		File f = new File("based.png");
		int r = 5;
		int g = 25;
		int b = 255;
		int col = (r << 16) | (g << 8) | b;
		for(int i = 0; i < 304; i++) {
			for(int j = 0; j < 304; j++) {
				img.setRGB(j, i, col);
			}
		}

		ImageIO.write(img, "PNG", f);
	}

	public static void main(String[] args) throws IOException {
		createeImage();
	}
}
