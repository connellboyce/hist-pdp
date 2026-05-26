package com.connellboyce.hist.logging;

public enum EventResult {
	SUCCESS("success"),
	FAILURE("failure"),
	INDETERMINATE("indeterminate"),
	UNDECIDED("undecided");

	private final String name;

	EventResult(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
