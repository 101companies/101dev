package org.softlang;

import com.tinkerpop.blueprints.Direction;

/**
 * Extremely simplified triple model
 * 
 */
public class Edge {
	Direction direction;
	String predicate;
	String node;
	
	public Edge(Direction direction, String predicate, String node) {
		this.direction = direction;
		this.predicate = predicate;
		this.node = node;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public String getPredicate() {
		return predicate;
	}
	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}

	

}
