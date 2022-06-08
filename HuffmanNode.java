public class HuffmanNode implements Comparable<HuffmanNode> {
	int frequency;
	char data;
	HuffmanNode left, right;

	public HuffmanNode(int frequency, char data, HuffmanNode left, HuffmanNode right) {
		this.frequency = frequency;
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public int compareTo(HuffmanNode node) {
		return frequency - node.frequency;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}
}