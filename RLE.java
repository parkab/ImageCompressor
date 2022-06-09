import javax.swing.*;
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

	public static String rleArr(int[] arr) {
		String encoded = "";

		for(int i = 0; i < arr.length; i++) {

			int num = 1;
			while(i < arr.length-1 && arr[i+1] == arr[i]) {
				i++;
				num++;
			}

			encoded += arr[i] + " " + num + " ";
		}

		return encoded;
	}

	public static int[] unRLE(String RLE) {
		String[] split = RLE.split(" ");
		ArrayList<Integer> total = new ArrayList<>();
		for(String s: split) {
			total.add(Integer.valueOf(s));
		}

		int total_val = 0;
		for(int i = 1; i < total.size(); i+=2) {
			total_val += total.get(i);
		}

		int[] final_arr = new int[total_val];
		int iterator = 0;

		for(int i = 0; i < total.size(); i+=2) {
			for(int j = 0; j < total.get(i+1); j++) {
				final_arr[iterator] = total.get(i);
				iterator++;
			}
		}

		return final_arr;
	}

	public static void main(String[] args) {
//		int[] arr_test = {1, 0, 0, 0, 0, 0, 11, 14, 74, 0, 0, 0, 0, 4, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0};
//		String new_arr = RLE.rleArr(arr_test);
//		System.out.println(new_arr);
		String s = "1 1 0 5 11 1 14 1 74 1 0 4 4 1 0 4 19 1 0 5";
		unRLE(s);
	}
}
