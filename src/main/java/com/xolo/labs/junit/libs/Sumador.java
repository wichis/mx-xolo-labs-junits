package com.xolo.labs.junit.libs;

public class Sumador {
    public Integer sumar(Integer a, Integer b){
        return a + b;
    }

    public void printSuma(int a, int b){
        System.out.println("La suma de " + a + " + " + b + " es: " + sumar(a, b));
    }
}
