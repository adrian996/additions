package com.adrian.assignment.validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdditionObjectValidatorTest {
    private AdditionObjectValidator validator;

    @BeforeEach
    public void setup() {
        validator = new AdditionObjectValidator();
    }

    @Test
    public void testValidateAddablesValid() {
        assertTrue(validator.validateAddables(2, 3));
    }

    @Test
    public void testValidateAddablesInvalidNull() {
        assertFalse(validator.validateAddables(null, null));
    }

    @Test
    public void testValidateAddablesInvalidNegativeValue() {
        assertFalse(validator.validateAddables(-1, 5));
    }

    @Test
    public void testValidateAddablesInvalidOutOfRange() {
        assertFalse(validator.validateAddables(101, 50));
    }

    @Test
    public void testValidateAddablesInvalidMixed() {
        assertFalse(validator.validateAddables(2, -3));
    }
}
