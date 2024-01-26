package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds(){
        var book = Book.of("1122334455", "My New Book", "Amy Author", 25.44);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails(){
        var book = Book.of("a123456789", "My New Book", "Amy Author", 25.44);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN number must be formatted properly (10 or 13 digits)");
    }
}
