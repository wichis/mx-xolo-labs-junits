package com.xolo.labs.junit.libs;


import com.xolo.labs.junit.libs.types.LenguageType;

public class Greetings {

    public String saludar(LenguageType len){
        switch (len){
            case ENGLISH -> {
                return "hello";
            }
            case SPANISH -> {
                return "hola";
            }
            case FRENCH -> {
                return "bonjour";
            }
            default -> {
                return "dont know";
            }
        }
    }
}
