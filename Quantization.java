public class Quantization {
	int[][] quant_table_luma = {{16,11,10,16,24,40,51,61},
			{12,12,14,19,26,58,60,55},
			{14,13,16,24,40,57,69,56},
			{14,17,22,29,51,87,80,62},
			{18,22,37,56,68,109,103,77},
			{24,35,55,64,81,104,113,92},
			{49,64,78,87,103,121,120,101},
			{72,92,95,98,112,100,103,99}};

	int[][] quant_table_chroma = {{17,18,24,47,99,99,99,99},
			{18,21,26,66,99,99,99,99},
			{24,26,56,99,99,99,99,99},
			{47,66,99,99,99,99,99,99},
			{99,99,99,99,99,99,99,99},
			{99,99,99,99,99,99,99,99},
			{99,99,99,99,99,99,99,99},
			{99,99,99,99,99,99,99,99}};

	public int[][] quantizeLuma(int[][] input) {
		int[][] quantizedArray = new int[input.length][input[0].length];
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[0].length; j++) {
				quantizedArray[i][j] = input[i][j] / quant_table_luma[i % 8][j % 8];
			}
		}

		return quantizedArray;
	}

	public int[][] quantizeChroma(int[][] input) {
		int[][] quantizedArray = new int[input.length][input[0].length];
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[0].length; j++) {
				quantizedArray[i][j] = input[i][j] / quant_table_chroma[i % 8][j % 8];
			}
		}

		return quantizedArray;
	}
}
