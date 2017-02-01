package net.unit8.sigcolle.form;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import enkan.component.doma2.DomaProvider;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author takahashi
 */
@Data
public class UserForm extends FormBase {
    @Inject
    private DomaProvider domaProvider;

    @DecimalMin("1")
    @DecimalMax("9999")
    private String userId;

    @NotBlank
    @Length(max = 20)
    private String firstName;

    @NotBlank
    @Length(max = 20)
    private String lastName;

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
