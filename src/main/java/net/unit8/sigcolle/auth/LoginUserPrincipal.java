package net.unit8.sigcolle.auth;

import enkan.security.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザーのログイン認証情報.
 * enkanの認証機構と連携するために {@link UserPrincipal} を拡張しており、
 * ログイン認証情報はすべてこのクラスで保持する.
 * (see also: http://enkan.github.io/guide/authentication.html )
 * @author blackawa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserPrincipal implements UserPrincipal {
    private Long userId;
    private String userName;
    @Override
    public boolean hasPermission(String s) {
        return true;
    }

    @Override
    public String getName() {
        return "user";
    }
}
