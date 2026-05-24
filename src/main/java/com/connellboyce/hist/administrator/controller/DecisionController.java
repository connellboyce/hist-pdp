package com.connellboyce.hist.administrator.controller;

import com.connellboyce.hist.administrator.dao.PolicyDecisionRequest;
import com.connellboyce.hist.administrator.dao.PolicyDecisionResponse;
import com.connellboyce.hist.engine.Decision;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class DecisionController {

	@PostMapping("/decision")
	public ResponseEntity<PolicyDecisionResponse> makeDecision(@RequestBody PolicyDecisionRequest request) {
		return ResponseEntity.ok().body(new PolicyDecisionResponse(Decision.PERMIT));
	}
}
