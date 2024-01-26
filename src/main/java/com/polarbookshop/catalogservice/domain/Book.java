package com.polarbookshop.catalogservice.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(

    @Id
    Long id,

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
    Double price,

    @CreatedDate
    Instant createdDate,

    @LastModifiedDate
    Instant lastModifiedDate,

    @Version
    int  version
){
    @NotNull
    public static Book of(
        String isbn, String title, String author, Double price
    ) {
        return new Book(
            null, isbn, title, author, price, null, null, 0
        );
    }
}
