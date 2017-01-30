package net.unit8.sigcolle.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.transaction.Transactional;

import enkan.collection.Multimap;
import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.auth.LoginPrincipal;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.LoginForm;
import net.unit8.sigcolle.model.User;
import org.seasar.doma.jdbc.NoResultException;

import static enkan.util.BeanBuilder.builder;
import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;

/**
 * Created by tie303856 on 2016/12/14.
 */
public class LoginController {
    @Inject
    TemplateEngine templateEngine;

    @Inject
    DomaProvider domaProvider;

    private static final String INVALID_USERNAME_OR_PASSWORD = "ユーザー名とパスワードが間違っています。もう一度やり直してください。";

    // ログイン画面表示
    @Transactional
    public HttpResponse index() throws IOException {
        return templateEngine.render("login", "login", new LoginForm());
    }

    // ログイン処理
    @Transactional
    public HttpResponse login(LoginForm form, Session session) throws IOException {

        UserDao userDao = domaProvider.getDao(UserDao.class);
        User user;
        Multimap errors = Multimap.empty();

        // メールアドレス存在チェック
        try {
            user = userDao.selectByEmail(form.getEmail());
        } catch (NoResultException e) {
            errors.add("error", INVALID_USERNAME_OR_PASSWORD);
            form.setErrors(errors);
            return templateEngine.render("login",
                    "login", form
            );
        }

        // パスワードチェック
        if (!form.getPass().equals(user.getPass())) {
            errors.add("error", INVALID_USERNAME_OR_PASSWORD);
            form.setErrors(errors);
            return templateEngine.render("login",
                    "login", form
            );
        }
        if (session == null) {
            session = new Session();
        }
        session.put("name", user.getLastName() + " " + user.getFirstName());
        session.put("userId", user.getUserId());
        session.put("principal", new LoginPrincipal());

        return builder(redirect("/", SEE_OTHER))
                .set(HttpResponse::setSession, session)
                .build();
    }

    // ログアウト処理
    @Transactional
    public HttpResponse logout(Session session) throws IOException {
        session.clear();
        return builder(redirect("/", SEE_OTHER))
                .set(HttpResponse::setSession, session)
                .build();
    }
}
