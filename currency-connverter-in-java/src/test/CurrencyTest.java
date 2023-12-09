package test;

import currencyConverter.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    //Tests boîte noire
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

    //Tests boîte blanche
    @Test
    void convertPositiveAmounts() {
        assertEquals(1250.00, Currency.convert(1000.00, 1.25));
        assertEquals(2500.00, Currency.convert(2000.00, 1.25));
    }

    @Test
    void convertNegativeAmounts() {
        assertEquals(-1250.00, Currency.convert(-1000.00, 1.25));
        assertEquals(-2500.00, Currency.convert(-2000.00, 1.25));
    }

    @Test
    void convertZeroAmount() {
        assertEquals(0.00, Currency.convert(0.00, 1.25));
    }

    @Test
    void convertVariousExchangeRates() {
        assertEquals(1000.00, Currency.convert(1000.00, 1.00));  
        assertEquals(0.00, Currency.convert(1000.00, 0.00));     
        assertEquals(2000.00, Currency.convert(1000.00, 2.00)); 
        assertEquals(-1000.00, Currency.convert(1000.00, -1.00));
    }

    @Test
    void convertExceptionalCases() {
        // Tests pour des valeurs extrêmes ou non valides
        assertEquals(Double.POSITIVE_INFINITY, Currency.convert(Double.MAX_VALUE, 2.00));
        assertEquals(Double.NEGATIVE_INFINITY, Currency.convert(-Double.MAX_VALUE, 2.00));
    }
}