package com.polarbookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public record Book(
    @NotBlank(message = "The ISBN number must be defined.")
    @Pattern(
        regexp = "^([0-9]{10}|[0-9]{13})$",
        message = "The ISBN number must be formatted properly (10 or 13 digits)"
    )
    String isbn,

    @NotBlank(message = "The title of the book must be defined")
    String title,

    @NotBlank(message = "The author of the book must be defined")
    String author,

    @NotNull(message = "The price of the book must be defined")
    @Positive(message = "The price must be greater than 0")
    Double price

){}
