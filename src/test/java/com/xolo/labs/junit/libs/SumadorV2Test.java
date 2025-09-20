package com.xolo.labs.junit.libs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;

/**
 * Test para sumar dos enteros usando 100% mockito.
 * 
 * Esta clase te enseña cómo probar el comportamiento de una clase usando "mocking" (simulación de objetos) con Mockito.
 * Los comentarios están explicados paso a paso para principiantes, para que puedas entender qué hace cada línea.
 */
class SumadorV2Test {

    // 1. Declaramos una variable de tipo Sumador que usaremos como "mock"
    private Sumador sumador;

    /**
     * Este método se ejecuta antes de cada prueba.
     * Aquí creamos un "mock" de la clase Sumador usando Mockito.
     * Un mock es una copia simulada de la clase real, que nos permite controlar su comportamiento para probarlo.
     */
    @BeforeEach
    void setUp() {
        // 2. Inicializamos el mock de Sumador.
        // No usamos el método real, solo la simulación que nos da Mockito.
        sumador = Mockito.mock(Sumador.class);
    }

    /**
     * Prueba básica: Verificar que el método sumar fue llamado con los argumentos correctos.
     */
    @Test
    void sumar_verificarUsoDelMetodo() {
        // 3. Usamos el mock como si fuera el objeto real. 
        // Llamamos al método sumar con los números 5 y 5.
        sumador.sumar(5, 5);

        // 4. Verificamos que el método sumar sí fue llamado con los valores 5 y 5.
        // Si no fue llamado así, la prueba falla.
        Mockito.verify(sumador).sumar(5, 5);
    }

    /**
     * Prueba de "stubbing": Simular diferentes respuestas del método sumar.
     * "Stubbing" es decirle al mock qué debe responder cuando se le llama con ciertos valores.
     */
    @Test
    void sumar_withStumbbing_value() {
        // Caso 1: Cuando sumador.sumar(5, 5) se llama, regresa 10.
        Mockito.when(sumador.sumar(5, 5)).thenReturn(10);

        // Caso 2: Cuando sumador.sumar(10, 7) se llama, lanza una excepción.
        Mockito.when(sumador.sumar(10, 7)).thenThrow(new RuntimeException());

        // Caso 3: Cuando sumador.sumar(7, 14) se llama la primera vez, regresa 21.
        // La segunda vez que se llama con los mismos valores, lanza una excepción.
        Mockito.when(sumador.sumar(7, 14)).thenReturn(21).thenThrow(new RuntimeException());

        // Prueba caso 1: Debe regresar 10.
        assertEquals(10, sumador.sumar(5, 5));

        // Prueba caso 2: Debe lanzar RuntimeException.
        assertThrows(RuntimeException.class, () -> sumador.sumar(10, 7));

        // Prueba caso 3: Primera llamada regresa 21.
        assertEquals(21, sumador.sumar(7, 14));

        // Prueba caso 3: Segunda llamada lanza RuntimeException.
        assertThrows(RuntimeException.class, () -> sumador.sumar(7, 14));
    }

    /**
     * Prueba "stubbing" con cualquier argumento: 
     * Usamos anyInt() para indicar que no importa qué enteros usemos, siempre regresa 25.
     */
    @Test
    void sumar_withSttumbbing_Args() {
        // Sea cuales sean los enteros, el método regresa 25.
        Mockito.when(sumador.sumar(anyInt(), anyInt())).thenReturn(25);

        // Prueba 1: Debe regresar 25.
        assertEquals(25, sumador.sumar(7, 15));
        // Prueba 2: Debe regresar 25.
        assertEquals(25, sumador.sumar(71, 34));
    }

    /**
     * Prueba el comportamiento cuando uno de los argumentos es null.
     * Simulamos que sumar lanza NullPointerException si algún argumento es null.
     */
    @Test
    void sumar_null_withStumbbing() {
        // Si el primer argumento es null, lanza NullPointerException.
        Mockito.when(sumador.sumar(null, 7)).thenThrow(new NullPointerException());
        // Si el segundo argumento es null, igual.
        Mockito.when(sumador.sumar(7, null)).thenThrow(new NullPointerException());
        // Si ambos son null, igual.
        Mockito.when(sumador.sumar(null, null)).thenThrow(new NullPointerException());

        // Prueba 1: Debe lanzar NullPointerException.
        assertThrows(NullPointerException.class, () -> sumador.sumar(null, 7));
        // Prueba 2: Debe lanzar NullPointerException.
        assertThrows(NullPointerException.class, () -> sumador.sumar(7, null));
        // Prueba 3: Debe lanzar NullPointerException.
        assertThrows(NullPointerException.class, () -> sumador.sumar(null, null));
    }

    /**
     * Verificamos que el método printSuma fue llamado correctamente con los argumentos 5 y 5.
     */
    @Test
    void printSuma_mockitoVerifyMethod() {
        // Llamamos al método printSuma del mock.
        sumador.printSuma(5, 5);

        // Verificamos que printSuma se llamó con los argumentos 5 y 5.
        Mockito.verify(sumador).printSuma(5, 5);
    }

    /**
     * Simulamos que el método printSuma lanza una excepción y verificamos que sumar no fue llamado.
     */
    @Test
    void printSuma_withStumbbing() {
        // Simulamos que printSuma lanza una excepción si se llama con 10 y 7.
        doThrow(new RuntimeException()).when(sumador).printSuma(10, 7);

        // Verificamos que el método sumar no fue llamado con esos argumentos.
        Mockito.verify(sumador, never()).sumar(10, 7);

        // Verificamos que printSuma lanza una excepción cuando se llama con 10 y 7.
        assertThrows(RuntimeException.class, () -> sumador.printSuma(10, 7));
    }
}
