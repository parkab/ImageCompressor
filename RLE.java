import java.util.ArrayList;

public class RLE {
	private static int[] helper1(int[] input) {
		int i = input.length - 1;

		while(i >= 0) {
			if(input[i] != 0) {
				break;
			}

			i--;
		}

		int[] array = new int[i + 1];

		for(int j = 0; j < array.length; j++) {
			array[j] = input[j];
		}

		return array;
	}

	public static String getRLE(int[] image) {
		int i = 0;
		int skip = 0;
		String bitstream = "";

		while(i < image.length) {
			if (image[i] != 0) {
				bitstream = bitstream + String.valueOf(image[i]) + " " + String.valueOf(skip) + " ";
				skip = 0;
				i++;
			}
        	else {
					skip = skip + 1;
					i = i + 1;
				}
			}

		return bitstream;
	}

	public static String rle2(String text) {
		String encodedString = "";

		for (int i = 0, count = 1; i < text.length(); i++) {
			if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1))
				count++;
			else {
				encodedString = encodedString.concat(Integer.toString(count))
						.concat(Character.toString(text.charAt(i)));
				count = 1;
			}
		}
		return encodedString;
	}

	public static void main(String[] args) {
		int[] arr_test = {1, 0, 0, 0, 0, 0, 11, 14, 74, 0, 0, 0, 0, 4, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0};
		int[] new_arr = RLE.helper1(arr_test);
		String rle = getRLE(arr_test);
		System.out.println(rle);
	}
}
