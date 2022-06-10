public class DCT {

	int N = 8;
	int[][] cosines = new int[N][N];
	double c[][] = new double[N][N];
	double cT[][]= new double[N][N];

	public DCT() {

	}

	public void initMatrix(int quality)
	{
		int i;
		int j;

		for (j = 0; j < N; j++)
		{
			double nn = (double)(N);
			c[0][j]  = 1.0 / Math.sqrt(nn);
			cT[j][0] = c[0][j];
		}

		for (i = 1; i < 8; i++)
		{
			for (j = 0; j < 8; j++)
			{
				double jj = (double)j;
				double ii = (double)i;
				c[i][j]  = Math.sqrt(2.0/8.0) * Math.cos(((2.0 * jj + 1.0) * ii * Math.PI) / (2.0 * 8.0));
				cT[j][i] = c[i][j];
			}
		}
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

	public int[][] inverseDCT(int input[][])
	{
		int output[][] = new int[N][N];
		double temp[][] = new double[N][N];
		double temp1;
		int i;
		int j;
		int k;

		for (i=0; i<N; i++)
		{
			for (j=0; j<N; j++)
			{
				temp[i][j] = 0.0;

				for (k=0; k<N; k++)
				{
					temp[i][j] += input[i][k] * c[k][j];
				}
			}
		}

		for (i=0; i<N; i++)
		{
			for (j=0; j<N; j++)
			{
				temp1 = 0.0;

				for (k=0; k<N; k++)
				{
					temp1 += cT[i][k] * temp[k][j];
				}

				if (temp1 < 0)
				{
					output[i][j] = 0;
				}
				else if (temp1 > 255)
				{
					output[i][j] = 255;
				}
				else
				{
					output[i][j] = (int)Math.round(temp1);
				}
			}
		}

		return output;
	}

	public static void main(String[] args) {
		int[][] test = {{16, 11, 10, 16, 24, 40, 51, 61}, {12, 12, 14, 19, 26, 58, 60, 55}, {14, 13, 16, 24, 40, 57, 69, 56}, {14, 17, 22, 29, 51, 87, 80, 62}, {18, 22, 37, 56, 68, 109, 103, 77}, {24, 35, 55, 64, 81, 104, 113, 92}, {49, 64, 78, 87, 103, 121, 120, 101}, {72, 92, 95, 98, 112, 100, 103, 99}};
		DCT classs = new DCT();
		classs.initMatrix(1);
//		int[][] dct = classs.forwardDCT(test);
//		int[][] reversed = classs.inverseDCT(dct);
//
//		for (int i = 0; i < reversed.length; i++) {
//			for (int j = 0; j < reversed[0].length; j++) {
//				System.out.println(reversed[i][j]);
//			}
//		}
	}
}
