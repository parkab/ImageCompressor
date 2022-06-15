import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Decompress {
	public int[][] y = new int[1][];
	public int[][] cr= new int[1][];
	public int[][] cb= new int[1][];
	public int[][] r = new int[1][];
	public int[][] g = new int[1][];
	public int[][] b = new int[1][];

	public Decompress() throws IOException {
		DecompressProcess decompressProcess = new DecompressProcess();
		ArrayList<ArrayList<BlockY>> y_processed = decompressProcess.getBlockYArrayList();
		ArrayList<ArrayList<BlockCb>> cb_processed = decompressProcess.getBlockCbArrayList();
		ArrayList<ArrayList<BlockCr>> cr_processed = decompressProcess.getBlockCrArrayList();
		y = new int[y_processed.size() * 8][y_processed.get(0).size() * 8];
		cb = new int[cb_processed.size() * 8][cb_processed.get(0).size() * 8];
		cr = new int[cr_processed.size() * 8][cr_processed.get(0).size() * 8];
		r = new int[y_processed.size() * 8][y_processed.get(0).size() * 8];
		g = new int[y_processed.size() * 8][y_processed.get(0).size() * 8];
		b = new int[y_processed.size() * 8][y_processed.get(0).size() * 8];
		initializeYArr();
		initializeCbArr();
		initializeCrArr();
		initializeR();
		initializeG();
		initializeB();
		ExportImage.createImage(r, g, b);
	}

	public void initializeR() {
		for(int i = 0; i < r.length; i++) {
			for(int j = 0; j < r[0].length; j++) {
				r[i][j] = YCbCr.YCbCrtoRGB(y[i][j], cb[i][j], cr[i][j])[0];
			}
		}
	}

	public void initializeG() {
		for(int i = 0; i < g.length; i++) {
			for(int j = 0; j < g[0].length; j++) {
				g[i][j] = YCbCr.YCbCrtoRGB(y[i][j], cb[i][j], cr[i][j])[1];
			}
		}
	}

	public void initializeB() {
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				b[i][j] = YCbCr.YCbCrtoRGB(y[i][j], cb[i][j], cr[i][j])[2];
			}
		}
	}

	public void initializeYArr() throws FileNotFoundException {
		DecompressProcess decompressProcess = new DecompressProcess();
		ArrayList<ArrayList<BlockY>> y_processed = decompressProcess.getBlockYArrayList();
		for(int i = 0; i < y_processed.size(); i++) {
			for(int j = 0; j < y_processed.get(i).size(); j++) {
				int[][] block = y_processed.get(i).get(j).block;
				for(int k = 0; k < 8; k++) {
					for(int l = 0; l < 8; l++) {
						y[(8 * i) + k][(8 * j) + l] = block[k][l];
					}
				}
			}
		}
	}



	public void initializeCbArr() throws FileNotFoundException {
		DecompressProcess decompressProcess = new DecompressProcess();
		ArrayList<ArrayList<BlockCb>> y_processed = decompressProcess.getBlockCbArrayList();
		for(int i = 0; i < y_processed.size(); i++) {
			for(int j = 0; j < y_processed.get(i).size(); j++) {
				int[][] block = y_processed.get(i).get(j).block;
				for(int k = 0; k < 8; k++) {
					for(int l = 0; l < 8; l++) {
						cb[(8 * i) + k][(8 * j) + l] = block[k][l];
					}
				}
			}
		}
	}

	public void initializeCrArr() throws FileNotFoundException {
		DecompressProcess decompressProcess = new DecompressProcess();
		ArrayList<ArrayList<BlockCr>> y_processed = decompressProcess.getBlockCrArrayList();
		for(int i = 0; i < y_processed.size(); i++) {
			for(int j = 0; j < y_processed.get(i).size(); j++) {
				int[][] block = y_processed.get(i).get(j).block;
				for(int k = 0; k < 8; k++) {
					for(int l = 0; l < 8; l++) {
						cr[(8 * i) + k][(8 * j) + l] = block[k][l];
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Decompress decompress = new Decompress();
	}
}
