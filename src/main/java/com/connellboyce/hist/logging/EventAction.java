package com.connellboyce.hist.logging;

public enum EventAction {
	DECISION_REQUEST_RECEIVED("decision_request_received");

	private final String name;

	EventAction(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
