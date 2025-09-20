package com.xolo.labs.junit.libs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;

/**
 * Pruebas para sumar dos enteros usando Mockito con anotaciones.
 * 
 * Aquí usamos las anotaciones @Mock y @ExtendWith para simplificar la configuración de los mocks.
 * Los comentarios explican cada acción como si fueras principiante.
 */

// Usamos @ExtendWith para habilitar el uso de anotaciones de mockito en JUnit 5.
// Esto permite que los mocks se inicialicen automáticamente antes de cada prueba.
@ExtendWith(MockitoExtension.class)
class SumadorV4Test {

    // 1. Usamos la anotación @Mock para crear un mock de la clase Sumador.
    // No necesitas inicializarlo manualmente, Mockito lo hace por ti.
    @Mock
    private Sumador sumador;

    /**
     * Prueba básica: Verifica que el método sumar fue llamado con los argumentos correctos.
     */
    @Test
    void sumar_verificarUsoDelMetodo() {
        // 2. Llamamos al método sumar del mock con los valores 5 y 5.
        sumador.sumar(5, 5);

        // 3. Verificamos que sumar fue llamado con los argumentos correctos.
        Mockito.verify(sumador).sumar(5, 5);
    }

    /**
     * Prueba de "stubbing": Le decimos al mock qué debe devolver o cuándo debe lanzar una excepción.
     */
    @Test
    void sumar_withStumbbing_value() {
        // Caso 1: Si se llama sumador.sumar(5, 5), regresa 10.
        Mockito.when(sumador.sumar(5, 5)).thenReturn(10);

        // Caso 2: Si se llama sumador.sumar(10, 7), lanza RuntimeException.
        Mockito.when(sumador.sumar(10, 7)).thenThrow(new RuntimeException());

        // Caso 3: Si se llama sumador.sumar(7, 14), primero regresa 21, luego lanza excepción.
        Mockito.when(sumador.sumar(7, 14)).thenReturn(21).thenThrow(new RuntimeException());

        // Probamos cada caso:
        assertEquals(10, sumador.sumar(5, 5)); // Debe regresar 10
        assertThrows(RuntimeException.class, () -> sumador.sumar(10, 7)); // Debe lanzar excepción
        assertEquals(21, sumador.sumar(7, 14)); // Primer llamada: regresa 21
        assertThrows(RuntimeException.class, () -> sumador.sumar(7, 14)); // Segunda llamada: excepción
    }

    /**
     * Prueba de stubbing usando cualquier argumento (anyInt).
     * No importa qué números se usen, siempre regresa 25.
     */
    @Test
    void sumar_withSttumbbing_Args() {
        Mockito.when(sumador.sumar(anyInt(), anyInt())).thenReturn(25);

        // Probamos con distintos argumentos, siempre debe regresar 25.
        assertEquals(25, sumador.sumar(7, 15));
        assertEquals(25, sumador.sumar(71, 34));
    }

    /**
     * Prueba el comportamiento cuando alguno de los argumentos es null.
     * Simulamos que el método lanza NullPointerException si recibe null.
     */
    @Test
    void sumar_null_withStumbbing() {
        // Definimos que si algún argumento es null, debe lanzar excepción.
        Mockito.when(sumador.sumar(null, 7)).thenThrow(new NullPointerException());
        Mockito.when(sumador.sumar(7, null)).thenThrow(new NullPointerException());
        Mockito.when(sumador.sumar(null, null)).thenThrow(new NullPointerException());

        // Probamos cada caso: debe lanzar NullPointerException.
        assertThrows(NullPointerException.class, () -> sumador.sumar(null, 7));
        assertThrows(NullPointerException.class, () -> sumador.sumar(7, null));
        assertThrows(NullPointerException.class, () -> sumador.sumar(null, null));
    }

    /**
     * Verifica que el método printSuma fue llamado con los argumentos correctos.
     */
    @Test
    void printSuma_mockitoVerifyMethod() {
        // Llamamos al método printSuma del mock.
        sumador.printSuma(5, 5);

        // Verificamos que se llamó con los argumentos correctos.
        Mockito.verify(sumador).printSuma(5, 5);
    }

    /**
     * Prueba que el método printSuma lanza una excepción y verifica que sumar no fue llamado.
     */
    @Test
    void printSuma_withStumbbing() {
        // Forzamos que printSuma lance una excepción si se llama con 10 y 7.
        doThrow(new RuntimeException()).when(sumador).printSuma(10, 7);

        // Verificamos que el método sumar no fue llamado con esos argumentos.
        Mockito.verify(sumador, never()).sumar(10, 7);

        // Verificamos que printSuma lanza excepción cuando se llama con 10 y 7.
        assertThrows(RuntimeException.class, () -> sumador.printSuma(10, 7));
    }
}
