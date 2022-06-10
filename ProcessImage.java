import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProcessImage {
	private int[][] red_array;
	private int[][] blue_array;
	private int[][] green_array;
	private int[][] y_array;
	private int[][] cb_array;
	private int[][] cr_array;

	public ProcessImage(BufferedImage image) {
		red_array = new int[image.getHeight()][image.getWidth()];
		blue_array = new int[image.getHeight()][image.getWidth()];
		green_array = new int[image.getHeight()][image.getWidth()];
		y_array = new int[image.getHeight()][image.getWidth()];
		cb_array = new int[image.getHeight()][image.getWidth()];
		cr_array = new int[image.getHeight()][image.getWidth()];
		initializeArrays(image);
		arrayToYCbCr();
	}
	public static void main(String[] args) throws IOException {
		BufferedImage bi = ImageIO.read(new File("Image_created_with_a_mobile_phone.png"));

	}

	private void arrayToYCbCr() {
		for(int i = 0; i < red_array.length; i++) {
			for(int j = 0; j < red_array[0].length; j++) {
				y_array[i][j] = YCbCr.RGBtoYCbCr(red_array[i][j], green_array[i][j], blue_array[i][j])[0];
				cb_array[i][j] = YCbCr.RGBtoYCbCr(red_array[i][j], green_array[i][j], blue_array[i][j])[1];
				cr_array[i][j] = YCbCr.RGBtoYCbCr(red_array[i][j], green_array[i][j], blue_array[i][j])[2];
			}
		}
	}

	private void initializeArrays(BufferedImage image) {
		for(int i = 0; i < image.getWidth(); i++) {
			for(int j = 0; j < image.getHeight(); j++) {
				int pixel = image.getRGB(i, j);
				red_array[j][i] = (pixel >> 16) & 0xff;
				green_array[j][i] = (pixel >> 8) & 0xff;
				blue_array[j][i] = (pixel) & 0xff;
			}
		}
	}

	public int[][] getY_array() {
		return y_array;
	}

	public int[][] getCb_array() {
		return cb_array;
	}

	public int[][] getCr_array() {
		return cr_array;
	}
}
