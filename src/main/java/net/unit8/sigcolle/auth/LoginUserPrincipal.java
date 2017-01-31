package net.unit8.sigcolle.auth;

import enkan.security.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザーのログイン情報を保持する.
 * セッションに格納したい情報が増えた時は本クラスにフィールドを追加すること.
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
