import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"Hell0123!", "V@lidP455"})
    public void createUser__ValidPasswords(String password) {
        User user = new User("john@example.com", password);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "hi", "Hell0", "hello!", "HELLO!", "hellOO!", "Hell 0123!"})
    public void createUser__InvalidPasswords(String password) {
        User user = new User("john@example.com", password);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("Invalid password", violations.iterator().next().getMessage());
    }

}