public class Image {
    public static void main(String[] args) {
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

		String bits = RLE.getRLE(zigzaged);

		System.out.println(bits);
	}
}
