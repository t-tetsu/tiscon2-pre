package net.unit8.sigcolle.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;

import enkan.collection.Multimap;
import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.UserForm;
import net.unit8.sigcolle.model.User;

import static enkan.util.BeanBuilder.builder;
import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;

/**
 * Created by tie303856 on 2016/12/14.
 */
public class RegisterController {
    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private DomaProvider domaProvider;

    private static final String EMAIL_ALREADY_EXISTS = "このメールアドレスは既に登録されています。";

    // 登録画面表示
    @Transactional
    public HttpResponse index() {
        return templateEngine.render("register", "user", new UserForm());
    }

    // ユーザ登録処理
    @Transactional
    public HttpResponse register(UserForm form, Session session) {

        if (form.hasErrors()) {
            return templateEngine.render("register",
                    "user", form
            );
        }

        UserDao userDao = domaProvider.getDao(UserDao.class);

        // メールアドレス重複チェック
        if (userDao.countByEmail(form.getEmail()) != 0) {
            Multimap errors = Multimap.empty();
            errors.add("email", EMAIL_ALREADY_EXISTS);
            form.setErrors(errors);
            return templateEngine.render("register",
                    "user", form
            );
        }

        User user = builder(new User())
                .set(User::setLastName, form.getLastName())
                .set(User::setFirstName, form.getFirstName())
                .set(User::setEmail, form.getEmail())
                .set(User::setPass, form.getPass())
                .build();
        userDao.insert(user);

        if (session == null) {
            session = new Session();
        }
        String name = form.getLastName() + " " + form.getFirstName();
        session.put("name", name);

        User loginUser = userDao.selectByEmail(form.getEmail());
        session.put("userId", loginUser.getUserId());

        return builder(redirect("/", SEE_OTHER))
                .set(HttpResponse::setSession, session)
                .build();
    }
}
