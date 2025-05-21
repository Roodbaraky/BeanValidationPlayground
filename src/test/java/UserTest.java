import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Validator validator;

    @BeforeEach
    void setUp() {
        validator = factory.getValidator();
    }

    @Test
    public void createUser() {
        User user = new User("john@example.com", "Hell0123!");
        assertNotNull(user);
        assertEquals("john@example.com", user.getEmail());
        assertEquals("Hell0123!", user.getPassword());
    }

    @Test
    public void createUser__InvalidPassword() {
        User user = new User("john@example.com", "");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(3, violations.size());
        List<String> violationMessages = violations.stream().map(ConstraintViolation::getMessage).toList();
        assertTrue(violationMessages.contains("Invalid password"));
        assertTrue(violationMessages.contains("must not be blank"));
        assertTrue(violationMessages.contains("size must be between 6 and 16"));
    }

}