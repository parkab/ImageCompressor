public class test {
	public static void main(String[] args) {
//		int[][] test = {{16, 11, 10, 16, 24, 40, 51, 61}, {12, 12, 14, 19, 26, 58, 60, 55}, {14, 13, 16, 24, 40, 57, 69, 56}, {14, 17, 22, 29, 51, 87, 80, 62}, {18, 22, 37, 56, 68, 109, 103, 77}, {24, 35, 55, 64, 81, 104, 113, 92}, {49, 64, 78, 87, 103, 121, 120, 101}, {72, 92, 95, 98, 112, 100, 103, 99}};
//		int[][] test = {{52, 55, 61, 66, 70, 61, 64, 73},
//				{63, 59, 55, 90, 109, 85, 69, 72},
//				{62, 59, 68, 113, 144, 104, 66, 73},
//				{63, 58, 71, 122, 154, 106, 70, 69},
//				{67, 61, 68, 104, 126, 88, 68, 58, 75},
//				{79, 65, 60, 70, 77, 68, 58, 75},
//				{85, 71, 64, 49, 55, 61, 65, 83},
//				{87, 79, 69, 68, 65, 76, 78, 94}};
		int[][] test = {{16, 0, 0, 0, 0, 0, 0, 0}, {16, 0, 0, 0, 0, 0, 0, 0}, {16, 0, 0, 0, 0, 0, 0, 0}, {16, 0, 0, 0, 0, 0, 0, 0},
				{16, 0, 0, 0, 0, 0, 0, 0}, {16, 0, 0, 0, 0, 0, 0, 0}, {16, 0, 0, 0, 0, 0, 0, 0}, {16, 0, 0, 0, 0, 0, 0, 0}};

		Chroma chroma = new Chroma();
		chroma.subSample(test);
		DCT dct = new DCT();
		dct.initMatrix(1);
		int[][] arr = dct.forwardDCT(test);
		arr = Quantization.quantizeChroma(arr);
		int[] zigzag = ZigZag.zigZagMatrix(arr, 8, 8);
		String bits = RLE.rleArr(zigzag);
		String answer  = HuffMan.encode(bits);
//		BitOutputStream outputStream = new BitOutputStream("file.compressed");
//
//		for(int i = 0; i < answer.length(); i++) {
//			outputStream.writeBits(1, Integer.valueOf(answer.charAt(i)));
//		}
//
//		outputStream.close();

		int[][] unzigZag = ZigZag.unZigZag(zigzag);
		arr = Quantization.decodeQuantizeChroma(unzigZag);
		arr = dct.inverseDCT(arr);
//
//		for(int i = 0; i < zigzag.length; i++) {
//			System.out.println(zigzag[i]);
//		}

		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
