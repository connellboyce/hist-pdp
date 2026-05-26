package com.connellboyce.hist.administrator.controller;

import com.connellboyce.hist.administrator.dao.PolicyDecisionRequest;
import com.connellboyce.hist.administrator.dao.PolicyDecisionResponse;
import com.connellboyce.hist.exception.TokenIntrospectionException;
import com.connellboyce.hist.logging.EventAction;
import com.connellboyce.hist.logging.EventLogger;
import com.connellboyce.hist.logging.EventOutcome;
import com.connellboyce.hist.model.Decision;
import com.connellboyce.hist.model.Identity;
import com.connellboyce.hist.service.SubjectTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class DecisionController {

	@Autowired
	SubjectTokenService subjectTokenService;

	@PostMapping("/decision")
	public ResponseEntity<PolicyDecisionResponse> makeDecision(@RequestBody PolicyDecisionRequest request, @RequestHeader(value = "Authorization", required = false) String invokingAuthorization) {
		EventLogger.log(
				EventAction.DECISION_REQUEST_RECEIVED,
				EventOutcome.UNKNOWN,
				request.subject(),
				request.authorizationDetails()
		);

		try {
			Identity subject = subjectTokenService.introspect(request.subject().token());

			return ResponseEntity.ok().body(new PolicyDecisionResponse(Decision.PERMIT));
		} catch (TokenIntrospectionException e) {
			return ResponseEntity.ok().body(new PolicyDecisionResponse(Decision.DENY));
		}
	}
}
