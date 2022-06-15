import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DecompressProcess {

	ArrayList<ArrayList<BlockY>> blockYArrayList = new ArrayList<>();
	ArrayList<ArrayList<BlockCb>> blockCbArrayList = new ArrayList<>();
	ArrayList<ArrayList<BlockCr>> blockCrArrayList = new ArrayList<>();

	public DecompressProcess() throws FileNotFoundException {
		initializeBlockY(processY());
		initializeBlockCb(processCb());
		initializeBlockCr(processCr());
	}

	public static String getFile() throws FileNotFoundException {
		String line = "";
		Scanner input = new Scanner(new File("hello.file"));
		while (input.hasNextLine()) {
			line = input.nextLine();
		}
		input.close();

		return line;
	}

	public static ArrayList<ArrayList<String>> processY() throws FileNotFoundException {
		String total = getY();
		ArrayList<String> y = seperateY(total);
		ArrayList<ArrayList<String>> final_arr = new ArrayList<>();

		for(int i = 0; i < y.size(); i++) {
			final_arr.add(seperateX(y.get(i)));
		}

		return final_arr;
	}

	public static ArrayList<ArrayList<String>> processCb() throws FileNotFoundException {
		String total = getCb();
		ArrayList<String> y = seperateY(total);
		ArrayList<ArrayList<String>> final_arr = new ArrayList<>();

		for(int i = 0; i < y.size(); i++) {
			final_arr.add(seperateX(y.get(i)));
		}

		return final_arr;
	}
	public static ArrayList<ArrayList<String>> processCr() throws FileNotFoundException {
		String total = getCr();
		ArrayList<String> y = seperateY(total);
		ArrayList<ArrayList<String>> final_arr = new ArrayList<>();

		for(int i = 0; i < y.size(); i++) {
			final_arr.add(seperateX(y.get(i)));
		}

		return final_arr;
	}


	public static ArrayList<String> seperateY(String total) {
		ArrayList<String> y = new ArrayList<>();
		String temp = "";
		for(int i = 0; i < total.length(); i++) {
			if(total.charAt(i) == '|') {
				y.add(temp);
				temp = "";
				i++;
			}
			else {
				temp += total.charAt(i);
			}
		}

		return y;
	}

	public static ArrayList<String> seperateX(String s) {
		ArrayList<String> xs = new ArrayList<>();
		String temp = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '_') {
				xs.add(temp);
				temp = "";

			}
			else {
				temp += s.charAt(i);
			}
		}

		return xs;
	}

	public static String getY() throws FileNotFoundException {
		String s = getFile();
		ArrayList<String> xs = new ArrayList<>();
		String temp = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '>') {
				xs.add(temp);
				temp = "";
			}
			else {
				temp += s.charAt(i);
			}
		}

		return xs.get(0);
//		String[] parts = getFile().split(">");
//		return parts[0];
	}

	public static String getCb() throws FileNotFoundException {
		String s = getFile();
		ArrayList<String> xs = new ArrayList<>();
		String temp = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '>') {
				xs.add(temp);
				temp = "";
			}
			else {
				temp += s.charAt(i);
			}
		}

		return xs.get(1);
//		String[] parts = getFile().split(">");
//		return parts[1];
	}

	public static String getCr() throws FileNotFoundException {
		String s = getFile();
		ArrayList<String> xs = new ArrayList<>();
		String temp = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '>') {
				xs.add(temp);
				temp = "";
			}
			else {
				temp += s.charAt(i);
			}
		}

		return xs.get(2);
//		String[] parts = getFile().split(">");
//		return parts[2];
	}

	public void initializeBlockY(ArrayList<ArrayList<String>> y) {
		ArrayList<ArrayList<BlockY>> blocky = new ArrayList<>();
		for(int i = 0; i < y.size(); i++) {
			ArrayList<BlockY> temp = new ArrayList<>();
			for(int j = 0; j < y.get(i).size();j++) {
				temp.add(new BlockY(y.get(i).get(j)));
			}
			blocky.add(temp);
		}

		this.blockYArrayList = blocky;
	}

	public void initializeBlockCb(ArrayList<ArrayList<String>> y) {
		ArrayList<ArrayList<BlockCb>> blocky = new ArrayList<>();
		for(int i = 0; i < y.size(); i++) {
			ArrayList<BlockCb> temp = new ArrayList<>();
			for(int j = 0; j < y.get(i).size();j++) {
				temp.add(new BlockCb(y.get(i).get(j)));
			}
			blocky.add(temp);
		}

		this.blockCbArrayList = blocky;
	}

	public void initializeBlockCr(ArrayList<ArrayList<String>> y) {
		ArrayList<ArrayList<BlockCr>> blocky = new ArrayList<>();
		for(int i = 0; i < y.size();i++) {
			ArrayList<BlockCr> temp = new ArrayList<>();
			for(int j = 0; j < y.get(i).size();j++) {
				temp.add(new BlockCr(y.get(i).get(j)));
			}
			blocky.add(temp);
		}

		this.blockCrArrayList = blocky;
	}

	public ArrayList<ArrayList<BlockY>> getBlockYArrayList() {
		return blockYArrayList;
	}

	public ArrayList<ArrayList<BlockCb>> getBlockCbArrayList() {
		return blockCbArrayList;
	}

	public ArrayList<ArrayList<BlockCr>> getBlockCrArrayList() {
		return blockCrArrayList;
	}

	public static void main(String[] args) throws FileNotFoundException {
		DecompressProcess decompressProcess = new DecompressProcess();
		System.out.println(DecompressProcess.processCb());
	}
}
