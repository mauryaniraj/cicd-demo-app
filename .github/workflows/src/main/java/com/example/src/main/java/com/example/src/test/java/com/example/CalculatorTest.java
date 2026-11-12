package com.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
class CalculatorTest {
    private final Calculator calculator = new Calculator();
    @Test
    void addsTwoNumbers() {
        assertEquals(5, calculator.add(2, 3));
    }
    @Test
    void dividesTwoNumbers() {
        assertEquals(4, calculator.divide(8, 2));
    }
}
    @Test
    void divideByZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(1, 0));
    }
}