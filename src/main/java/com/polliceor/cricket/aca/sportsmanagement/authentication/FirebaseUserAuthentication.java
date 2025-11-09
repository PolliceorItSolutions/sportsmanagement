package com.polliceor.cricket.aca.sportsmanagement.authentication;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class FirebaseUserAuthentication extends AbstractAuthenticationToken {

    private final FirebaseToken firebaseToken;

    public FirebaseUserAuthentication(FirebaseToken token) {
        super(null);
        this.firebaseToken = token;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return firebaseToken;
    }

    @Override
    public Object getPrincipal() {
        return firebaseToken.getEmail();
    }
}

