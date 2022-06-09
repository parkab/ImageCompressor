public class Block {
	public int[][] block;

	public Block(int[][] block) {
		this.block = block;
	}

	public Block(String block) {
		int[] arr = RLE.unRLE(block);
		int[][] zigZag = ZigZag.unZigZag(arr);
		int[][] quantized = Quantization.decodeQuantizeLuma(zigZag);
		DCT dct = new DCT();
		dct.initMatrix(1);
		this.block = dct.inverseDCT(quantized);
	}

	public void compressBlock(String filepath) {
		Chroma chroma = new Chroma();
		chroma.subSample(block);
		DCT dct = new DCT();
		dct.initMatrix(1);
		int[][] arr = dct.forwardDCT(block);
		arr = Quantization.quantizeLuma(arr);
		int[] zigzag = ZigZag.zigZagMatrix(arr, 8, 8);
		String bits = RLE.rleArr(zigzag);

		System.out.println(bits);

		String answer  = HuffMan.encode(bits);
		BitOutputStream outputStream = new BitOutputStream(filepath);

		for(int i = 0; i < answer.length(); i++) {
			outputStream.writeBits(1, Integer.valueOf(answer.charAt(i)));
		}

		outputStream.close();
	}

	public static void main(String[] args) {
		int[][] test = {{52, 55, 61, 66, 70, 61, 64, 73},
				{63, 59, 55, 90, 109, 85, 69, 72},
				{62, 59, 68, 113, 144, 104, 66, 73},
				{63, 58, 71, 122, 154, 106, 70, 69},
				{67, 61, 68, 104, 126, 88, 68, 58, 75},
				{79, 65, 60, 70, 77, 68, 58, 75},
				{85, 71, 64, 49, 55, 61, 65, 83},
				{87, 79, 69, 68, 65, 76, 78, 94}};
	}
}
