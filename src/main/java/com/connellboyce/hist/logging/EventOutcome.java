package com.connellboyce.hist.logging;

public enum EventOutcome {
	SUCCESS("success"),
	FAILURE("failure"),
	UNKNOWN("unknown");

	private final String name;

	EventOutcome(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
