package com.connellboyce.hist.engine;

public enum Decision {
	PERMIT("permit"),
	DENY("deny");

	private final String name;

	Decision(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
