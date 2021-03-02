package ua.com.foxminded.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomSizeValid implements ConstraintValidator<CustomSizeConstraint,String> {
    @Override
    public void initialize(CustomSizeConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cvc) {
        return contactField != null && (contactField.length()>2 && contactField.length()<10);
    }
}
