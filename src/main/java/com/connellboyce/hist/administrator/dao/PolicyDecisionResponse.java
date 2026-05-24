package com.connellboyce.hist.administrator.dao;

import com.connellboyce.hist.engine.Decision;
import com.connellboyce.hist.engine.model.Advice;
import com.connellboyce.hist.engine.model.Obligation;

import java.util.List;

public record PolicyDecisionResponse(Decision decision, List<Obligation> obligations, List<Advice> advice) {

	public PolicyDecisionResponse(Decision decision) {
		this(decision, List.of(), List.of());
	}

}
