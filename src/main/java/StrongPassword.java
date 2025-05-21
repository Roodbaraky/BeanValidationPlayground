/*
 * (c) Midland Software Limited 2025
 * Name     : StrongPassword.java
 * Author   : RoodbarakyK
 * Date     : 21 May 2025
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ReportAsSingleViolation
@NotNull
@NotBlank
@Size(min = 6, max = 16)
@Constraint(validatedBy = {StrongPasswordValidator.class})
@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String propertyName() default "password";

    String message() default "Invalid password";

    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

}
