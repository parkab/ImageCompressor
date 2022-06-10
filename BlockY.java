public class BlockY {
	public int[][] block;

	public BlockY(int[][] block) {
		this.block = block;
	}

	public BlockY(String block) {
		int[] arr = RLE.unRLE(block);
		int[][] zigZag = ZigZag.unZigZag(arr);
		int[][] quantized = Quantization.decodeQuantizeLuma(zigZag);
		DCT dct = new DCT();
		dct.initMatrix(1);
		this.block = dct.inverseDCT(quantized);
	}

	public String compressBlock() {
		Chroma chroma = new Chroma();
		chroma.subSample(block);
		DCT dct = new DCT();
		dct.initMatrix(1);
		int[][] arr = dct.forwardDCT(block);
		arr = Quantization.quantizeLuma(arr);
		int[] zigzag = ZigZag.zigZagMatrix(arr, 8, 8);
		String bits = RLE.rleArr(zigzag);
		return bits;
	}

	public String toString() {
		String s = "";
		for(int i = 0; i < block.length; i++) {
			for(int j = 0; j < block[0].length; j++) {
				s += block[i][j] + " ";
			}
			s += "\n";
		}

		return s;
	}
}
