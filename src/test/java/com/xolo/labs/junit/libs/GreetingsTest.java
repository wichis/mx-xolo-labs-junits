package com.xolo.labs.junit.libs;

import com.xolo.labs.junit.libs.types.LenguageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingsTest {

    @Test
    void saludar() {
        Greetings greetings = new Greetings();
        assertEquals("hello", greetings.saludar(LenguageType.ENGLISH));
        assertEquals("hola", greetings.saludar(LenguageType.SPANISH));
        assertEquals("bonjour", greetings.saludar(LenguageType.FRENCH));
        assertEquals("dont know", greetings.saludar(LenguageType.UNKNOWN));
    }
}