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
 * Pruebas para sumar dos enteros usando Mockito.
 * En esta clase vamos a usar "spy" para monitorear y modificar el comportamiento de un objeto real.
 * Los comentarios explican cada acción como si no tuvieras experiencia previa.
 */
class SumadorV3Test {

    // 1. Declaramos una variable tipo Sumador que será nuestro "spy".
    // Un spy es una copia especial de un objeto real, que nos permite observar y modificar su comportamiento.
    private Sumador sumadorSpy;

    /**
     * Este método se ejecuta antes de cada prueba.
     * Aquí creamos el spy a partir de un objeto real Sumador.
     * Así podemos monitorear lo que hace y cambiar sus respuestas si queremos.
     */
    @BeforeEach
    void setUp() {
        // 2. Inicializamos sumadorSpy usando Mockito.spy y un objeto real de Sumador.
        // Esto nos permite observar llamadas y modificar el resultado si es necesario.
        sumadorSpy = Mockito.spy(new Sumador());
    }

    /**
     * Prueba básica usando spy:
     * Verificar que el método sumar fue llamado con los argumentos correctos.
     * Así sabemos que se usó correctamente.
     */
    @Test
    void sumar_usaSpy_v1() {
        // 3. Llamamos al método sumar de nuestro spy con los valores 5 y 5.
        sumadorSpy.sumar(5, 5);

        // 4. Verificamos que el método sumar sí se llamó con los argumentos correctos.
        // Si no fue así, la prueba falla.
        Mockito.verify(sumadorSpy).sumar(5, 5);
    }

    /**
     * Prueba de "stubbing" (modificando el resultado del método sumar usando el spy):
     * Aquí le decimos al spy qué debe responder para ciertos valores.
     */
    @Test
    void sumar_withStumbbing_value() {
        // Caso 1: Cuando sumadorSpy.sumar(5, 5) se llama, regresa 10.
        Mockito.when(sumadorSpy.sumar(5, 5)).thenReturn(10);

        // Caso 2: Cuando sumadorSpy.sumar(10, 7) se llama, lanza una excepción.
        Mockito.when(sumadorSpy.sumar(10, 7)).thenThrow(new RuntimeException());

        // Caso 3: Cuando sumadorSpy.sumar(7, 14) se llama la primera vez, regresa 21.
        // La segunda vez con los mismos argumentos, lanza una excepción.
        Mockito.when(sumadorSpy.sumar(7, 14)).thenReturn(21).thenThrow(new RuntimeException());

        // Prueba Caso 1: Debe regresar 10.
        assertEquals(10, sumadorSpy.sumar(5, 5));
        // Prueba Caso 2: Debe lanzar RuntimeException.
        assertThrows(RuntimeException.class, () -> sumadorSpy.sumar(10, 7));
        // Prueba Caso 3: Primera llamada regresa 21.
        assertEquals(21, sumadorSpy.sumar(7, 14));
        // Prueba Caso 3: Segunda llamada lanza RuntimeException.
        assertThrows(RuntimeException.class, () -> sumadorSpy.sumar(7, 14));
    }

    /**
     * Prueba de stubbing usando cualquier argumento:
     * Usamos anyInt() para indicar que sin importar qué enteros usemos, el método regresa 25.
     */
    @Test
    void sumar_withSttumbbing_Args() {
        // Le decimos al spy: para cualquier par de enteros, regresa siempre 25.
        Mockito.when(sumadorSpy.sumar(anyInt(), anyInt())).thenReturn(25);

        // Prueba 1: Debe regresar 25.
        assertEquals(25, sumadorSpy.sumar(7, 15));
        // Prueba 2: Debe regresar 25.
        assertEquals(25, sumadorSpy.sumar(71, 34));
    }

    /**
     * Prueba el comportamiento cuando alguno de los argumentos es null.
     * Simulamos que sumar lanza NullPointerException si algún argumento es null.
     * IMPORTANTE: Este tipo de pruebas con spy sobre un objeto real pueden fallar con Mockito.
     * El spy está pensado para monitorear, no para modificar el comportamiento con nulls.
     */
    @Test
    void sumar_null_withStumbbing() {
        // Le decimos al spy: si algún argumento es null, lanza NullPointerException.
        Mockito.when(sumadorSpy.sumar(null, 7)).thenThrow(new NullPointerException());
        Mockito.when(sumadorSpy.sumar(7, null)).thenThrow(new NullPointerException());
        Mockito.when(sumadorSpy.sumar(null, null)).thenThrow(new NullPointerException());

        // Prueba 1: Debe lanzar NullPointerException.
        assertThrows(NullPointerException.class, () -> sumadorSpy.sumar(null, 7));
        // Prueba 2: Debe lanzar NullPointerException.
        assertThrows(NullPointerException.class, () -> sumadorSpy.sumar(7, null));
        // Prueba 3: Debe lanzar NullPointerException.
        assertThrows(NullPointerException.class, () -> sumadorSpy.sumar(null, null));
    }

    // ... aquí podrían seguir más pruebas, con el mismo nivel de detalle en los comentarios.
}
