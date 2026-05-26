package com.connellboyce.hist.logging;

import com.connellboyce.hist.administrator.dao.PolicyDecisionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class EventLogger {
	static final Logger LOG = LoggerFactory.getLogger(EventLogger.class);

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static void log(EventAction eventAction, EventOutcome eventOutcome, PolicyDecisionRequest.Subject subject, List<PolicyDecisionRequest.AuthorizationDetail> authorizationDetails) {
		Map<String, Object> data = Map.of(
				"event", Map.of("action", eventAction,
									"result", eventOutcome),
				"subject", subject,
				"authorizationDetails", authorizationDetails
		);
		LOG.atInfo().log(objectMapper.writeValueAsString(data));
	}
}
