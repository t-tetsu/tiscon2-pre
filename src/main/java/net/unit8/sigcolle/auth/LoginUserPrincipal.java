package net.unit8.sigcolle.auth;

import enkan.security.UserPrincipal;

public class LoginUserPrincipal implements UserPrincipal {
    @Override
    public boolean hasPermission(String s) {
        return true;
    }

    @Override
    public String getName() {
        return "user";
    }
}
