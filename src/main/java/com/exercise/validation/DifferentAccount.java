package com.exercise.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DifferentAccountValidator.class)
@Documented
public @interface DifferentAccount {

    String message () default "Account numbers cannot be identical";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};

}
