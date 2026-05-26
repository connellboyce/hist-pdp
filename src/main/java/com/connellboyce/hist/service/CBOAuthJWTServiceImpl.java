package com.connellboyce.hist.service;

import com.connellboyce.hist.exception.TokenIntrospectionException;
import com.connellboyce.hist.model.Identity;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class CBOAuthJWTServiceImpl implements SubjectTokenService {
	private RSAPublicKey publicKey;

	@Override
	public Identity introspect(String token) throws TokenIntrospectionException {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);

			JWSHeader header = signedJWT.getHeader();
			if (!JWSAlgorithm.RS256.equals(header.getAlgorithm())) {
				throw new SecurityException("Unexpected JWT algorithm");
			}

			JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
			RSASSAVerifier verifier = new RSASSAVerifier(getPublicKey(claims.getIssuer(), header.getKeyID()));

			boolean valid = signedJWT.verify(verifier);

			if (!valid) {
				throw new SecurityException("Invalid JWT signature");
			}

			Date expiration = claims.getExpirationTime();
			if (expiration != null && expiration.before(new Date())) {
				throw new SecurityException("JWT expired");
			}

			return new Identity(claims.getSubject(), List.of(claims.getStringArrayClaim("amr")));
		} catch (ParseException | SecurityException | JOSEException e) {
			throw new TokenIntrospectionException("Failed to parse JWT: " + e.getMessage());
		}
	}

	// TODO: Update this with a proper caching strategy, support for multiple active public keys, etc.
	// This will not function in the event of a key rotation and will not support multiple valid issuers
	// This should also validate against an approved pre-defined list of issuers
	private RSAPublicKey getPublicKey(String iss, String kid) throws ParseException, JOSEException {
		if (publicKey != null) {
			return publicKey;
		}

		RestClient restClient = RestClient.create();

		String uri = iss + "/oauth2/jwks";
		String jwksJson = restClient.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(String.class);

		if (jwksJson == null || jwksJson.isEmpty()) {
			throw new IllegalStateException(
					"Failed to retrieve JWKS from: " + uri
			);
		}

		JWKSet jwkSet = JWKSet.parse(jwksJson);

		JWK jwk = jwkSet.getKeyByKeyId(kid);

		if (jwk == null) {
			throw new IllegalStateException(
					"No key found for kid: " + kid
			);
		}

		RSAKey rsaKey = (RSAKey) jwk;

		RSAPublicKey rsaPublicKey = rsaKey.toRSAPublicKey();

		this.publicKey = rsaPublicKey;

		return rsaPublicKey;
	}
}
