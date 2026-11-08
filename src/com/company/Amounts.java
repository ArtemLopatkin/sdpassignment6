package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class Amounts {
    private Amounts() {
    }

    static BigDecimal positive(BigDecimal amount, String name) {
        BigDecimal result = nonNegative(amount, name);
        if (result.signum() == 0) {
            throw new IllegalArgumentException(name + " must be positive.");
        }
        return result;
    }

    static BigDecimal nonNegative(BigDecimal amount, String name) {
        if (amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException(name + " must be non-negative.");
        }
        try {
            return amount.setScale(2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException(name + " must have at most two decimal places.", exception);
        }
    }
}
