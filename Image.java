import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Image {
	ArrayList<ArrayList<Block>> blockArrayList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		int[][] image = {{123, 45, 23, 0, 5, 12, 85, 3}, {45, 12, 134, 76, 23, 12, 54, 12}, {43, 12, 65, 87, 12, 12, 14, 8},
		{12, 76, 45, 12, 12, 54, 75, 12}, {23, 12, 12, 76, 34, 23, 34, 12}, {4, 23, 7, 87, 12, 23, 10, 13},
				{23, 12, 12, 76, 34, 23, 34, 12}, {4, 23, 7, 87, 12, 23, 10, 13}};

		Chroma chroma = new Chroma();
		chroma.subSample(image);

		DCT dct = new DCT();
		image = dct.forwardDCT(image);

		Quantization quantization = new Quantization();
		int[][] quantized = quantization.quantizeLuma(image);

		ZigZag zigzag = new ZigZag();

		int[] zigzaged = zigzag.zigZagMatrix(quantized, 8, 8);

		Helpers.loop1D(zigzaged);

		String bits = RLE.rleArr(zigzaged);

		String answer  = HuffMan.encode(bits);

		BitOutputStream outputStream = new BitOutputStream("file.compressed");
//
//		for(int i = 0; i < 11; i++) {
//			outputStream.writeBits(1, 0);
//		}

		System.out.println(answer);

		for(int i = 0; i < answer.length(); i++) {
			outputStream.writeBits(1, Integer.valueOf(answer.charAt(i)));
		}

		outputStream.close();
	}
}
