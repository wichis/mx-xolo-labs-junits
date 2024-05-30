package com.xolo.labs.junit.libs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;

/**
 * Test para sumar dos enteros
 * Utilizando 100% mockito
 * - Vamos a utilizar spy para poder monitorear un objeto real
 */
class SumadorV3Test {


    private Sumador sumadorSpy;

    @BeforeEach
    void setUp() {
        sumadorSpy = Mockito.spy(new Sumador());
    }

    /**
    /**
     * Vamos a probar que el metodo sumar se llame con los argumentos validos
     */
    @Test
    void sumar_usaSpy_v1() {
        sumadorSpy.sumar(5, 5);

        Mockito.verify(sumadorSpy).sumar(5, 5);
    }

    @Test
    void sumar_usaSpy_v2() {
        sumadorSpy.sumar(5, 5);

        Mockito.verify(sumadorSpy).sumar(5, 5);
    }

    @Test
    void sumar_withStumbbing_value(){
        // case 1
        // stumbbing result to 10
        Mockito.when(sumadorSpy.sumar(5, 5)).thenReturn(10);
        // case 2
        // stumbbing result to throwing exception
        Mockito.when(sumadorSpy.sumar(10, 7)).thenThrow(new RuntimeException());
        // case 3
        // stumbbing result both
        Mockito.when(sumadorSpy.sumar(7, 14)).thenReturn(21).thenThrow(new RuntimeException());

        // case 1
        assertEquals(10, sumadorSpy.sumar(5, 5));
        // case 2
        assertThrows(RuntimeException.class, () -> sumadorSpy.sumar(10, 7));
        // case 3
        assertEquals(21, sumadorSpy.sumar(7, 14));
        assertThrows(RuntimeException.class, () -> sumadorSpy.sumar(7, 14));

    }


    @Test
    void sumar_withSttumbbing_Args(){
        Mockito.when(sumadorSpy.sumar(anyInt(), anyInt())).thenReturn(25);

        assertEquals(25, sumadorSpy.sumar(7, 15));
        assertEquals(25, sumadorSpy.sumar(71, 34));
    }

    /**
     * Este medotodo esta fallando porque el spy hacia el objeto real no esta funcionando
     * El utilizar un spy no es recomendable para este tipo de pruebas
     * El spy es para monitorear un objeto real y no para hacer stumbbing
     */
    @Test
    void sumar_null_withStumbbing(){
        //Defining values to return
        Mockito.when(sumadorSpy.sumar(null, 7)).thenThrow(new NullPointerException());
        Mockito.when(sumadorSpy.sumar(7, null)).thenThrow(new NullPointerException());
        Mockito.when(sumadorSpy.sumar(null, null)).thenThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () -> {
            sumadorSpy.sumar(null, 7);
        });
        assertThrows(NullPointerException.class, () -> {
            sumadorSpy.sumar(7, null);
        });
        assertThrows(NullPointerException.class, () -> {
            sumadorSpy.sumar(null, null);
        });
    }

    @Test
    void printSuma_mockitoVerifyMethod() {
        sumadorSpy.printSuma(5, 5);
        Mockito.verify(sumadorSpy).printSuma(5, 5);
    }

    @Test
    void printSuma_withStumbbing(){
        doThrow(new RuntimeException()).when(sumadorSpy).printSuma(10, 7);
        // Verificamos que otro metodo fuera del contexto de la prueba no se haya llamado
        Mockito.verify(sumadorSpy, never()).sumar(10, 7);
        assertThrows(RuntimeException.class, () -> sumadorSpy.printSuma(10, 7));
    }
}