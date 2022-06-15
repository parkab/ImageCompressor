public class YCbCr {
	public static int[] RGBtoYCbCr(double r, double g, double b) {
		double y = 0;
		double cb = 0;
		double cr = 0;

		y = (double)(0.299 * r + 0.587 * g + 0.114 * b);
		cb = (double)(-0.169 * r - 0.331 * g + 0.500 * b);
		cr = (double)(0.500 * r - 0.419 * g - 0.081 * b);

		int[] answer = new int[3];
		answer[0] = (int) y;
		answer[1] = (int) cb;
		answer[2] = (int) cr;

		return answer;
	}

	public static int[] YCbCrtoRGB(int y, int cb, int cr) {
		double Y = (double) y;
		double Cb = (double) cb;
		double Cr = (double) cr;

		int r = (int)(y + 0.000 * cb + 1.403 * cr);
		int g = (int)(y - 0.344 * cb - 0.714 * cr);
		int b = (int)(y + 1.773 * cb + 0.000 * cr);

		int[] answer = new int[3];
		answer[0] = r;
		answer[1] = g;
		answer[2] = b;

		return answer;
	}
}
