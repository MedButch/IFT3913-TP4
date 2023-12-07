package test;

import currencyConverter.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void convert(){
        assertEquals(Currency.convert());
    }
}