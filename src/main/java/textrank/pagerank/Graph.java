package textrank.pagerank;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class Graph {
	
	Map<Node, List<Edge>> graph;
	float d = 0.85f;
	int iters = 10;
	
	public abstract Graph addNode(String start, String end, float weight);
	public abstract void rank() throws Exception;
	
	public Node node(String label) {
		Node n = new Node();
		n.label = label;
		return n;
	}

	public List<Node> topk(int k) {
		List<Node> nodes = Lists.newLinkedList( graph.keySet());
		Collections.sort(nodes, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.rank > o2.rank) return -1;
				if (o1.rank < o2.rank) return 1;
				return 0;
			}
		});
		
		return nodes.subList(0, Math.min(k, nodes.size()));
	}

}