package com.connellboyce.hist.administrator.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PolicyDecisionRequest(

		Subject subject,

		@JsonProperty("authorization_details")
		List<AuthorizationDetail> authorizationDetails

) {

	public record Subject(String token) {}

	public record AuthorizationDetail(String type,
	                                  List<String> actions,
	                                  Resources resources) {}

	public record Resources(@JsonProperty("minecraft_server_ip") String minecraftServerIP,
	                       @JsonProperty("minecraft_username") String minecraftServerUsername) {}

}
