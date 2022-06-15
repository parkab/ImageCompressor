public class HuffNode implements Comparable<HuffNode> {

	private int myValue, myWeight;
	private HuffNode myLeft, myRight;

	public HuffNode(int value, int weight) {
		this(value, weight, null, null);
	}

	public HuffNode(int value, int weight, HuffNode left, HuffNode right) {
		myValue = value;
		myWeight = weight;
		myLeft = left;
		myRight= right;
	}

	public int compareTo(HuffNode other) {
		return myWeight - other.myWeight;
	}

	public int value() {
		return myValue;
	}

	public int weight() {
		return myWeight;
	}

	public HuffNode left() {
		return myLeft;
	}

	public HuffNode right() {
		return myRight;
	}

	public String toString() {
		return myValue + "";
	}

	public boolean isLeaf() {
		return (myLeft == null && myRight == null);
	}
}