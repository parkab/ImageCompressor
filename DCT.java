public class DCT {

	int N = 8;
	int[][] cosines = new int[N][N];

	public DCT() {

	}

	public int[][] forwardDCT(int[][] input) {

		double i_mult;
		double j_mult;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {

				if(i == 0) {
					i_mult = 1/Math.sqrt(N);
				}
				else {
					i_mult = Math.sqrt(2)/ Math.sqrt(N);
				}

				if(j == 0) {
					j_mult = 1/Math.sqrt(N);
				}
				else {
					j_mult = Math.sqrt(2)/ Math.sqrt(N);
				}

				double temp_var = 0;
				for(int k = 0; k < N; k++) {
					for(int l = 0; l < N; l++) {
						double temp_2 = input[k][l] * Math.cos((2 * k + 1) * i * Math.PI / (2 * N)) * Math.cos((2 * l + 1) * j * Math.PI / (2 * N));
						temp_var += temp_2;
					}
				}

				cosines[i][j] = (int) (temp_var * i_mult * j_mult);
			}
		}

		return cosines;
	}

	public void printCosines() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.println(cosines[i][j]);
			}
		}
	}

	public static void main(String[] args) {
		double[][] test = {{16, 11, 10, 16, 24, 40, 51, 61}, {12, 12, 14, 19, 26, 58, 60, 55}, {14, 13, 16, 24, 40, 57, 69, 56}, {14, 17, 22, 29, 51, 87, 80, 62}, {18, 22, 37, 56, 68, 109, 103, 77}, {24, 35, 55, 64, 81, 104, 113, 92}, {49, 64, 78, 87, 103, 121, 120, 101}, {72, 92, 95, 98, 112, 100, 103, 99}};
		DCT classs = new DCT();
	}
}
