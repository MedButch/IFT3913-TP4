package test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowTest {
    private static ArrayList<Currency> currencies;

    @BeforeAll
    ArrayList<Currency> currencies = Currency.init();

    @Test
    void convert(){
        assertEquals(-1, MainWindow.convert("USD", "CAD", currencies, -5000.00));
        assertEquals(-1, MainWindow.convert("CAD", "GBP" ,currencies, -1.00));
        assertEquals(0.00, MainWindow.convert("GBP", "EUR", currencies, 0.00));
        assertEquals(472_197.50, MainWindow.convert("EUR", "CHF", currencies, 500_000.00));
        assertEquals(1_728_866.67, MainWindow.convert("CHF", "AUD", currencies, 1_000_000.00));
        assertEquals(-1, MainWindow.convert("AUD", "USD", currencies, 1_000_001.00));
        assertEquals(-1, MainWindow.convert("CAD", "USD", currencies, 1_234_567_890.00));
        assertEquals(-1, MainWindow.convert("PES", "NZD", currencies, 7_890.00));
        assertEquals(-1, MainWindow.convert("FRC", "USD", currencies, 567.00));
        assertEquals(-1, MainWindow.convert("CAD", "MRK", currencies, 12_345.00));
        assertEquals(12_435.00, MainWindow.convert("CAD", "CAD", currencies, 12_345.00));

    }

    @Test
    void convertValidCurrencies() {
        assertEquals(472_197.50, MainWindow.convert("EUR", "CHF", currencies, 500_000.00));
        assertEquals(1_728_866.67, MainWindow.convert("CHF", "AUD", currencies, 1_000_000.00));
        // Autres combinaisons valides
        assertEquals(1000.00, MainWindow.convert("USD", "USD", currencies, 1000.00));
        assertEquals(0.00, MainWindow.convert("EUR", "GBP", currencies, 0.00));
    }

    @Test
    void convertInvalidCurrencies() {
        assertEquals(-1, MainWindow.convert("XXX", "YYY", currencies, 1000.00));
        assertEquals(-1, MainWindow.convert("EUR", "ZZZ", currencies, 500.00));
    }

    @Test
    void convertVariousAmounts() {
        assertEquals(-1, MainWindow.convert("USD", "CAD", currencies, -5000.00));
        assertEquals(-1, MainWindow.convert("CAD", "GBP", currencies, 1_000_001.00));
        assertEquals(0.00, MainWindow.convert("USD", "EUR", currencies, 0.00));
        assertEquals(500.00, MainWindow.convert("GBP", "CHF", currencies, 500.00));
    }

    @Test
    void convertExceptionalCases() {
        ArrayList<Currency> emptyCurrencies = new ArrayList<>();
        assertEquals(-1, MainWindow.convert("USD", "EUR", emptyCurrencies, 1000.00));
    }
}