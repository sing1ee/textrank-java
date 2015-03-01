package textrank.pagerank;


public class Edge implements Comparable<Edge> {

	String start, end;
	float weight;
	
	private String label() {
		return start + "->" + end;
	}
	
	
	
	@Override
	public String toString() {
		return label();
	}



	@Override
	public int compareTo(Edge o) {
		return this.label().compareTo(o.label());
	}
}