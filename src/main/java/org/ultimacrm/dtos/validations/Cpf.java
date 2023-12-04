package org.ultimacrm.dtos.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfValidation.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cpf {
    String message() default "CPF Inválido!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] palyload() default {};
}
