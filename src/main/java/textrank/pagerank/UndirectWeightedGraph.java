package textrank.pagerank;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UndirectWeightedGraph extends Graph {

	public UndirectWeightedGraph() {
		graph = Maps.newTreeMap();
	}
	
	@Override
	public Graph addNode(String start, String end, float weight) {
		{
			Node n = new Node();
			n.label = start;
			Edge e = new Edge();
			e.start = start;
			e.end = end;
			e.weight = weight;
			if (graph.containsKey(n)) {
				graph.get(n).add(e);
			} else {
				List<Edge> edgs = Lists.newLinkedList();
				edgs.add(e);
				graph.put(n, edgs);
			}
		}
		{
			String tmp = start;
			start = end;
			end = tmp;
			Node n = new Node();
			n.label = start;
			Edge e = new Edge();
			e.start = start;
			e.end = end;
			e.weight = weight;
			if (graph.containsKey(n)) {
				graph.get(n).add(e);
			} else {
				List<Edge> edgs = Lists.newLinkedList();
				edgs.add(e);
				graph.put(n, edgs);
			}
		}
		return this;
	}

	@Override
	public void rank() throws Exception {
		
		Map<String, Float> ws = Maps.newTreeMap();
		Map<String, Float> outSum = Maps.newTreeMap();

		for (Node n : graph.keySet()) {
//			System.out.println(n.label + "\t" + IDF.df(n.label));
			ws.put(n.label, 1.0f / graph.size());
		}
		
		for (Node n : graph.keySet()) {
			float os = 0;
			List<Edge> out = graph.get(n);
			for (Edge oute : out) {
				os += oute.weight;
			}
			outSum.put(n.label, os);
		}
		
		
		for (int i = 0; i < iters; ++i) {
			
			for (Node n : graph.keySet()) {
				
				List<Edge> in = graph.get(n);
				float sum = 0;
				for (Edge e : in) {
					float ij = e.weight;
					sum += ij / outSum.get(e.end) * ws.get(e.end);
				}
				ws.put(n.label, (1 - d) + d * sum);
			}
			
			for (Node n : graph.keySet()) {
				n.rank = ws.get(n.label);
			}
		}
		
		// rank normalize
		float min = Float.MAX_VALUE, max = Float.MIN_VALUE;
		
		for (Node n : graph.keySet()) {
			if (n.rank < min) min = n.rank;
			if (n.rank > max) max = n.rank;
		}
		
		for (Node n : graph.keySet()) {
			n.rank = (n.rank - min / 10f) / (max - min / 10f) * 100;
		}
	}
}