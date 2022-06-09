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

	}
}
