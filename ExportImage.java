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

	public static void test_fun(double[][] red_arr, double[][] blue_arr, double[][] green_arr) throws IOException {
		BufferedImage img = new BufferedImage(red_arr[0].length, red_arr.length, BufferedImage.TYPE_INT_RGB);
		File test = new File("test.png");
		for(int i = 0; i < red_arr.length; i++) {
			for(int j = 0; j < red_arr[0].length; j++) {
				int col = ((int) red_arr[i][j] << 16) | ((int) green_arr[i][j] << 8) | (int) blue_arr[i][j];
				img.setRGB(j, i, col);
			}
		}

		ImageIO.write(img, "PNG", test);
	}

	public static void main(String[] args) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File("Image_created_with_a_mobile_phone.png"));
		ProcessImage processImage = new ProcessImage(bufferedImage);
	}
}
