package test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowTest {

    private static final ArrayList<Currency> currencies = Currency.init();

    //Tests boîte noire
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

    //Tests boîte blanche


    //Couverture des instructions: séparament
    @Test
    void convertValidCurrencies() {
        assertEquals(472_197.50, MainWindow.convert("Euro", "Swiss Franc", currencies, 500_000.00));
        assertEquals(1_728_866.67, MainWindow.convert("CHF", "AUD", currencies, 1_000_000.00));
        assertEquals(1000.00, MainWindow.convert("USD", "USD", currencies, 1000.00));
        assertEquals(0.00, MainWindow.convert("EUR", "GBP", currencies, 0.00));
    }

    @Test
    void convertInvalidCurrencies() {
        assertEquals(-1, MainWindow.convert("XXX", "YYY", currencies, 1000.00));
        assertEquals(-1, MainWindow.convert("EUR", "ZZZ", currencies, 500.00));
    }

    @Test
    void testMainWindowConvertZeroAmount() {
        assertEquals(0.0, MainWindow.convert("US Dollar", "Euro", currencies, 0.0));
    }

    @Test
    void testMainWindowConvertNegativeAmount() {
        assertEquals(-125.0, MainWindow.convert("US Dollar", "Euro", currencies, -100.0));
    }


    // 2 - couverture des arcs du graphe de flot de contrôle
    @Test
    void testArcGraph() {
        assertNotNull(MainWindow.convert("US Dollar", "Euro", currencies, 100.0));
        assertNull(MainWindow.convert("nimportedequoi", "Euro", currencies, 100.0));
        assertNull(MainWindow.convert("nimportedequoiXXX", "nimportedequoiYYY", currencies, 100.0));
    }

    // couverture des chemins indépendants du graphe de flot de contrôle
    @Test
    void testIndependentPath() {
        assertNotNull(MainWindow.convert("US Dollar", "Euro", currencies, 100.0));
        assertNull(MainWindow.convert("nimportedequoi", "Euro", currencies, 100.0));
        assertNull(MainWindow.convert("nimportedequoi1", "nimportedequoi2", currencies, 100.0));
    }


    // couverture des conditions
    @Test
    void testConversionWithZeroAmount() {
        double result = MainWindow.convert("US Dollar", "Euro", currencies, 0.0);
        assertEquals(0.0, result);
    }



    // couverture des i-chemins
    @Test
    void testIChemin() {
        assertNotNull(MainWindow.convert("US Dollar", "Euro", currencies, 100.0));
        assertNull(MainWindow.convert("nimportedequoi", "Euro", currencies, 100.0));
    }

}