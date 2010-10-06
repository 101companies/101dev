package org.softlang.client.util;

import java.io.Serializable;

public class ResultPair<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4412198087367818482L;
	private T t;
	private double newSalary;

	public ResultPair() {
	}

	public ResultPair(T t, double salary) {
		this.t = t;
		this.newSalary = salary;
	}

	public T getT() {
		return t;
	}

	public double getNewSalary() {
		return newSalary;
	}

}
