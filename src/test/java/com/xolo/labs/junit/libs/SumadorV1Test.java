package com.xolo.labs.junit.libs;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;

/**
 * Test para sumar dos enteros
 * Utilizando sin usar mockito
 */
class SumadorV1Test {


    /**
     * Simple basic test.
     */
    @Test
    void sumar() {
        Sumador sumador = new Sumador();
        assertEquals(10, sumador.sumar(5, 5));
        assertEquals(12, sumador.sumar(6, 6));
    }

    @Test
    void sumar_null(){
        Sumador sumador = new Sumador();
        assertThrows(NullPointerException.class, () -> {
            sumador.sumar(null, 7);
        });
        assertThrows(NullPointerException.class, () -> {
            sumador.sumar(7, null);
        });
        assertThrows(NullPointerException.class, () -> {
            sumador.sumar(null, null);
        });
    }


    @Test
    void printSuma() {
        Sumador sumador = new Sumador();
        sumador.printSuma(5, 5);
        assert true;
    }

}