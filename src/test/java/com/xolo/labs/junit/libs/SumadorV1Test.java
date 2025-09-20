package com.xolo.labs.junit.libs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Sumador.
 * Estas pruebas utilizan únicamente la biblioteca JUnit, sin Mockito ni otras herramientas de mockeo.
 */
class SumadorV1Test {

    /**
     * Prueba básica de suma de dos enteros.
     * Verifica que la suma de dos números enteros sea correcta.
     */
    @Test
    void sumar() {
        Sumador sumador = new Sumador();
        assertEquals(10, sumador.sumar(5, 5));
        assertEquals(12, sumador.sumar(6, 6));
    }

    /**
     * Prueba que sumar lance NullPointerException si alguno de los argumentos es null.
     */
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

    /**
     * Prueba que el método printSuma se ejecuta correctamente con argumentos válidos.
     * Nota: Al no usar Mockito, no es posible verificar las llamadas al método ni los argumentos utilizados.
     * Para ejemplos con Mockito, consultar SumadorV2Test.java.
     */
    @Test
    void printSuma() {
        Sumador sumador = new Sumador();
        sumador.printSuma(5, 5);
        assert true;
    }

}
