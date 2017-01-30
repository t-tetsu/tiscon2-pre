package net.unit8.sigcolle.form;

import java.util.List;

import javax.inject.Inject;

import enkan.component.doma2.DomaProvider;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author takahashi
 */
@Data
public class LoginForm extends FormBase {
    @Inject
    DomaProvider domaProvider;

    @NotBlank
    @Length(max = 50)
    private String email;

    @Length(min = 4, max = 20)
    private String pass;

    @Override
    public boolean hasErrors() {
        return super.hasErrors();
    }

    @Override
    public boolean hasErrors(String name) {
        return super.hasErrors(name);
    }

    @Override
    public List<String> getErrors(String name) {
        return super.getErrors(name);
    }
}
