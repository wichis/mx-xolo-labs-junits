/**
 * GreetingsTest.java
 *
 * Simple JUnit 5 test class for verifying the Greetings class.
 * 
 * This repository demonstrates basic usage of JUnit 5 for simple unit tests.
 * 
 * - The Greetings class provides a method to return greetings in different languages.
 * - We use JUnit 5's @Test annotation and assertions to test that Greetings returns the correct string for each LenguageType.
 * - The tests are straightforward and don't cover complex scenarios.
 * 
 * To run the tests, use your IDE or 'mvn test' if using Maven.
 * 
 * For more information, see the README of this repository.
 */

package com.xolo.labs.junit.libs;

import com.xolo.labs.junit.libs.types.LenguageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Greetings class.
 */
class GreetingsTest {

    /**
     * Test the saludar method for different language types.
     * Uses simple assertions to verify expected results.
     */
    @Test
    void saludar() {
        Greetings greetings = new Greetings();
        assertEquals("hello", greetings.saludar(LenguageType.ENGLISH));
        assertEquals("hola", greetings.saludar(LenguageType.SPANISH));
        assertEquals("bonjour", greetings.saludar(LenguageType.FRENCH));
        assertEquals("dont know", greetings.saludar(LenguageType.UNKNOWN));
    }
}
