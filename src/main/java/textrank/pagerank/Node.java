package textrank.pagerank;

public class Node implements Comparable<Node> {

	public String label;
	public float rank;
	
	
	
	@Override
	public String toString() {
		return label + ":" + rank;
	}

	@Override
	public int compareTo(Node o) {
		return label.compareTo(o.label);
	}
}