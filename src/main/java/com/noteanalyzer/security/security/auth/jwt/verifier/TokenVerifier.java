package com.noteanalyzer.security.security.auth.jwt.verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
