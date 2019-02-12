package chorss.apartment.monitoring.service;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsUniqueEmailValidator.class)
public @interface IsUniqueEmail {

    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}