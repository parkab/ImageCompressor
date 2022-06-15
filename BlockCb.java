public class BlockCb {
	public int[][] block;

	public BlockCb(int[][] block) {
		this.block = block;
	}

	public BlockCb(String b) {
		String block = b;
		if(block.charAt(0) == ' ') {
			block = block.substring(1);
		}

		if (block.charAt(block.length() - 1) == ' ') {
			block = block.substring(0, block.length()-1);
		}

		int[] arr = RLE.unRLE(block);
		int[][] zigZag = ZigZag.unZigZag(arr);
		int[][] quantized = Quantization.decodeQuantizeChroma(zigZag);
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
		arr = Quantization.quantizeChroma(arr);
		int[] zigzag = ZigZag.zigZagMatrix(arr, 8, 8);
		String bits = RLE.rleArr(zigzag);

		return bits;
//		BitOutputStream outputStream = new BitOutputStream(filepath);
//
//		for(int i = 0; i < answer.length(); i++) {
//			outputStream.writeBits(1, Integer.valueOf(answer.charAt(i)));
//		}
//
//		outputStream.close();
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
