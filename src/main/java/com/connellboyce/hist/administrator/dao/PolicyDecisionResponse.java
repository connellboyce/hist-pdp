package com.connellboyce.hist.administrator.dao;

import com.connellboyce.hist.model.Advice;
import com.connellboyce.hist.model.Decision;
import com.connellboyce.hist.model.Obligation;

import java.util.List;

public record PolicyDecisionResponse(Decision decision, List<Obligation> obligations, List<Advice> advice) {

	public PolicyDecisionResponse(Decision decision) {
		this(decision, List.of(), List.of());
	}

}
