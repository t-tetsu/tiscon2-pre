package net.unit8.sigcolle.auth;

import enkan.security.UserPrincipal;
import lombok.Data;

/**
 * ユーザーのログイン情報を保持する.
 * セッションに格納したい情報が増えた時は本クラスにフィールドを追加すること.
 * @author blackawa
 */
@Data
public class LoginUserPrincipal implements UserPrincipal {
    private Long userId;
    private String userName;

    public LoginUserPrincipal(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public boolean hasPermission(String s) {
        return true;
    }

    @Override
    public String getName() {
        return "user";
    }
}
