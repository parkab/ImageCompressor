public class Decompress {
	public static void main(String[] args) {
		BitInputStream inputStream = new BitInputStream("file.compressed");
//		while(true) {
//			try {
//				System.out.println(inputStream.readBits(1));
//			}
//			catch(Exception exception) {
//				break;
//			}
//		}

		String s = "";
		for(int i = 0; i < 90; i++) {
			s += inputStream.readBits(1);
		}

	}
}
