package com.connellboyce.hist.service;

import com.connellboyce.hist.exception.TokenIntrospectionException;
import com.connellboyce.hist.model.Identity;

public interface SubjectTokenService {
	Identity introspect(String token) throws TokenIntrospectionException;
}
