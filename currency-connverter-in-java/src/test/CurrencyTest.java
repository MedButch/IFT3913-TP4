package test;

import currencyConverter.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void convert(){
        assertEquals(-1, Currency.convert(-5000.00, 1.25));
        assertEquals(-1, Currency.convert(-1.00, 1.25));
        assertEquals(0.00, Currency.convert(0.00, 1.25));
        assertEquals(625_000.00, Currency.convert(500_000.00, 1.25));
        assertEquals(1_250_000.00, Currency.convert(1_000_000.00, 1.25));
        assertEquals(-1, Currency.convert(1_000_001.00, 1.25));
        assertEquals(-1, Currency.convert(1_234_567_890.00, 1.25));
    }
}