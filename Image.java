import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Image {
	public ArrayList<ArrayList<BlockY>> blockYArrayList = new ArrayList<>();
	public ArrayList<ArrayList<BlockCr>> blockCrArrayList = new ArrayList<>();
	public ArrayList<ArrayList<BlockCb>> blockCbArrayList = new ArrayList<>();
	int height;
	int width;
	int[][] y;
	int[][] cb;
	int[][] cr;

	public Image(ProcessImage processImage) {
		y = processImage.getY_array();
		cb = processImage.getCb_array();
		cr = processImage.getCr_array();
		generateBlocks();
	}

	private void generateBlocks() {
		for(int i = 0; i < y.length/8; i++) {
			ArrayList<BlockY> blockYArrayList1 = new ArrayList<>();
			ArrayList<BlockCb> blockCbArrayList1 = new ArrayList<>();
			ArrayList<BlockCr> blockCrArrayList1 = new ArrayList<>();
			for(int j = 0; j < y[0].length/8; j++) {
				int[][] block_arr_y = new int[8][8];
				int[][] block_arr_cb = new int[8][8];
				int[][] block_arr_cr = new int[8][8];
				for(int k = 0; k < 8; k++) {
					for(int l = 0; l < 8; l++) {
						block_arr_y[k][l] = y[(8*i)+k][(8*j)+l];
						block_arr_cb[k][l] = cb[(8*i)+k][(8*j)+l];
						block_arr_cr[k][l] = cr[(8*i)+k][(8*j)+l];
					}
				}
				blockYArrayList1.add(new BlockY(block_arr_y));
				blockCrArrayList1.add(new BlockCr(block_arr_cr));
				blockCbArrayList1.add(new BlockCb(block_arr_cb));
			}
			blockYArrayList.add(blockYArrayList1);
			blockCrArrayList.add(blockCrArrayList1);
			blockCbArrayList.add(blockCbArrayList1);
		}
	}

	public void compressImage(String filepath) throws IOException {
		String y = "";
		for(int i = 0; i < blockYArrayList.size(); i++) {
			for(int j = 0; j < blockYArrayList.get(i).size(); j++) {
				y += blockYArrayList.get(i).get(j).compressBlock() + "_";
			}
			y += "|";
		}
		String cb = "";
		for(int i = 0; i < blockCbArrayList.size(); i++) {
			for(int j = 0; j < blockCbArrayList.get(i).size(); j++) {
				cb += blockCbArrayList.get(i).get(j).compressBlock() + "_";
			}
			cb += "|";
		}
		String cr = "";
		for(int i = 0; i < blockCrArrayList.size(); i++) {
			for(int j = 0; j < blockCrArrayList.get(i).size(); j++) {
				cr += blockCrArrayList.get(i).get(j).compressBlock() + "_";
			}
			cr += "|";
		}

		File output = new File("hello.file");
		FileWriter fileWriter = new FileWriter(output);
		String write = y + ">" + cb + ">" + cr + ">";

		fileWriter.write(write);
		fileWriter.flush();
		fileWriter.close();

		String answer = HuffMan.encode(y + ">" + cb + ">" + cr + ">");

		BitOutputStream outputStream = new BitOutputStream(filepath);
		BitInputStream inputStream = new BitInputStream("hello.file");

		HuffProcessor processor = new HuffProcessor();
		processor.compress(inputStream, outputStream);
//
//		for(int i = 0; i < answer.length(); i++) {
//			outputStream.writeBits(1, Integer.valueOf(answer.charAt(i)));
//		}
//
//		outputStream.close();
	}

	public static void main(String[] args) throws IOException {
		BufferedImage bi = ImageIO.read(new File("based.png"));
		Image image = new Image(new ProcessImage(bi));
		image.compressImage("file.compressed");
	}
}
