package ua.com.foxminded.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomSizeValid.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomSizeConstraint {
    String message() default "Size must be more than 2 and less then 10";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
