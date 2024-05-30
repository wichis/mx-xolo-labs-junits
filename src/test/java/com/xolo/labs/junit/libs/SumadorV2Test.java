package com.xolo.labs.junit.libs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;

/**
 * Test para sumar dos enteros
 * Utilizando 100% mockito
 */
class SumadorV2Test {

    // 1. Preperamos el mock para el test
    @Mock
    private Sumador sumador;

    @BeforeEach
    void setUp() {
        // 2. Inicializamos el mock, OJO: no se está haciendo o usando elmetodo real.
        // PD: Mockito lo hace por nosotros para poder controlar el objeto mockeado
        sumador = Mockito.mock(Sumador.class);
    }

    /**
    /**
     * Vamos a probar que el metodo sumar se llame con los argumentos validos
     */
    @Test
    void sumar_verificarUsoDelMetodo() {
        // 3. Utilizamos el mock como si fuera el objeto real, pero con el control de mockito
        sumador.sumar(5, 5);
        // 4. Verificamos que el metodo sumar fue llamado con los argumentos 5, 5
        Mockito.verify(sumador).sumar(5, 5);
    }

    @Test
    void sumar_withStumbbing_value(){
        // case 1
        // stumbbing result to 10
        Mockito.when(sumador.sumar(5, 5)).thenReturn(10);
        // case 2
        // stumbbing result to throwing exception
        Mockito.when(sumador.sumar(10, 7)).thenThrow(new RuntimeException());
        // case 3
        // stumbbing result both
        Mockito.when(sumador.sumar(7, 14)).thenReturn(21).thenThrow(new RuntimeException());

        // case 1
        assertEquals(10, sumador.sumar(5, 5));
        // case 2
        assertThrows(RuntimeException.class, () -> sumador.sumar(10, 7));
        // case 3
        assertEquals(21, sumador.sumar(7, 14));
        assertThrows(RuntimeException.class, () -> sumador.sumar(7, 14));

    }


    @Test
    void sumar_withSttumbbing_Args(){
        Mockito.when(sumador.sumar(anyInt(), anyInt())).thenReturn(25);

        assertEquals(25, sumador.sumar(7, 15));
        assertEquals(25, sumador.sumar(71, 34));
    }

    @Test
    void sumar_null_withStumbbing(){
        //Defining values to return
        Mockito.when(sumador.sumar(null, 7)).thenThrow(new NullPointerException());
        Mockito.when(sumador.sumar(7, null)).thenThrow(new NullPointerException());
        Mockito.when(sumador.sumar(null, null)).thenThrow(new NullPointerException());

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
    void printSuma_mockitoVerifyMethod() {
        sumador.printSuma(5, 5);
        Mockito.verify(sumador).printSuma(5, 5);
    }

    @Test
    void printSuma_withStumbbing(){
        doThrow(new RuntimeException()).when(sumador).printSuma(10, 7);
        // Verificamos que otro metodo fuera del contexto de la prueba no se haya llamado
        Mockito.verify(sumador, never()).sumar(10, 7);
        assertThrows(RuntimeException.class, () -> sumador.printSuma(10, 7));
    }
}