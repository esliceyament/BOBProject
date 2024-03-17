package com.example.bobproject.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final LocalDate MIN_DATE = LocalDate.of(1930,1,1);
    private static final LocalDate MAX_DATE = LocalDate.of(2011,12,31);

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null values
        }
        try {
            String formattedDate = value.format(DATE_FORMATTER);
            if (!formattedDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                return false;
            }
            return value.isEqual(MIN_DATE) || value.isAfter(MIN_DATE) && (value.isEqual(MAX_DATE) || value.isBefore(MAX_DATE));
        } catch (Exception e) {
            return false;
        }
    }
}
