public class ZigZag {

	static int[][] zigZag = {{0, 1, 5, 6, 14, 15, 27, 28},
			{2, 4, 7, 13, 16, 26, 29, 42},
			{3, 8, 12, 17, 25, 30, 41, 43},
			{9, 11, 18, 24, 31, 40, 44, 53},
			{10, 19, 23, 32, 39, 45, 52, 54},
			{20, 22, 33, 38, 46, 51, 55, 60},
			{21, 34, 37, 47, 50, 56, 59, 61},
			{35, 36, 48, 49, 57, 58, 62, 63}};
	public static int[] zigZagMatrix(int arr[][], int n, int m) {
		int row = 0, col = 0;
		int[] zigZag_arr = new int[n * m];
		int iterator = 0;

		boolean row_inc = false;

		int mn = Math.min(m, n);
		for (int len = 1; len <= mn; ++len) {
			for (int i = 0; i < len; ++i) {
				zigZag_arr[iterator] = arr[row][col];
				iterator++;

				if (i + 1 == len)
					break;
				if (row_inc) {
					++row;
					--col;
				} else {
					--row;
					++col;
				}
			}

			if (len == mn)
				break;

			if (row_inc) {
				++row;
				row_inc = false;
			} else {
				++col;
				row_inc = true;
			}
		}

		if (row == 0) {
			if (col == m - 1)
				++row;
			else
				++col;
			row_inc = true;
		} else {
			if (row == n - 1)
				++col;
			else
				++row;
			row_inc = false;
		}

		int MAX = Math.max(m, n) - 1;
		for (int len, diag = MAX; diag > 0; --diag) {

			if (diag > mn)
				len = mn;
			else
				len = diag;

			for (int i = 0; i < len; ++i) {
				zigZag_arr[iterator] = arr[row][col];
				iterator++;

				if (i + 1 == len)
					break;

				if (row_inc) {
					++row;
					--col;
				} else {
					++col;
					--row;
				}
			}

			if (row == 0 || col == m - 1) {
				if (col == m - 1)
					++row;
				else
					++col;

				row_inc = true;
			}

			else if (col == 0 || row == n - 1) {
				if (row == n - 1)
					++col;
				else
					++row;

				row_inc = false;
			}
		}

		return zigZag_arr;
	}

	public static int[][] unZigZag(int[] arr) {
		int[][] array = new int[8][8];

		for(int i = 0; i < zigZag.length; i++) {
			for(int j = 0; j < zigZag[i].length; j++) {
				array[i][j] = arr[zigZag[i][j]];
			}
		}

		return array;
	}



	public static void main(String[] args) {
		int[] arr = new int[64];

		for(int i = 0; i < 64; i++) {
			arr[i] = i;
		}

		int[][] zig = unZigZag(arr);

		for(int i = 0; i < zig.length; i++) {
			for(int j = 0; j < zig[0].length; j++) {
				System.out.print(zig[i][j]);
			}
			System.out.println(" ");
		}
	}
}
