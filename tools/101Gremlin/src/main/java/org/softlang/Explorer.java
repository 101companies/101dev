package org.softlang;

import java.util.List;
import java.util.Vector;

import org.softlang.gremlinWrapper.Wrapper;

import com.google.gson.Gson;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.impls.sail.SailEdge;

public class Explorer {

	/**
	 * Convert List of SailEdges to JSON string
	 * 
	 * @param edges
	 * @return JSON String
	 */
	private static List<Edge> toEdges(Direction direction, List<SailEdge> sailEdges) {
		Direction opp = null;
		if (direction == Direction.IN)
			opp = Direction.OUT;
		else
			opp = Direction.IN;
		Vector<Edge> edges = new Vector<Edge>();
		System.out.print(sailEdges.size());
		for (int i = 0; i < sailEdges.size(); i++) {
			SailEdge e = sailEdges.get(i);
			edges.add(new Edge(direction, e.getLabel(), e.getVertex(opp).getId().toString()));
		}
		return edges;
	}

	/**
	 * Get all triples for a given 101resource id, for instance
	 * 'Contribution-3Axslt'
	 * 
	 * @param resourceID
	 * @return JSON string
	 */
	public static String getResourceTriplesIn(String resourceID) {
		return (new Gson()).toJson(toEdges(Direction.IN, Wrapper.exploreIn(resourceID)));
	}
	
	public static String getResourceTriplesOut(String resourceID) {
		return (new Gson()).toJson(toEdges(Direction.OUT, Wrapper.exploreOut(resourceID)));
	}
	
	public static String getResourceTriples(String resourceID) {
		List<Edge> in = toEdges(Direction.IN, Wrapper.exploreIn(resourceID));
		List<Edge> out = toEdges(Direction.OUT, Wrapper.exploreOut(resourceID));
		out.addAll(in);
		return (new Gson()).toJson(out);
	}
	
}
