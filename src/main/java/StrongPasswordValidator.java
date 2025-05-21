/*
 * (c) Midland Software Limited 2025
 * Name     : StrongPasswordValidator.java
 * Author   : RoodbarakyK
 * Date     : 21 May 2025
 */

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@ApplicationScoped
public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    private StrongPassword strongPassword;

    @Override
    public void initialize(final StrongPassword strongPassword) {
        ConstraintValidator.super.initialize(strongPassword);
    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        boolean upperFlag = false;
        boolean lowerFlag = false;
        boolean digitFlag = false;
        boolean specialFlag = false;
        Set<Character> chars = password.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.toSet());
        for (Character c : chars) {
            if (Character.isUpperCase(c)) {
                upperFlag = true;
            }
            if (Character.isLowerCase(c)) {
                lowerFlag = true;
            }
            if (Character.isDigit(c)) {
                digitFlag = true;
            }
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                specialFlag = true;
            }
        }
        return upperFlag && lowerFlag && digitFlag && specialFlag;
    }
}
