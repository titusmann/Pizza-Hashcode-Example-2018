package com.haschcode.pizza;

import com.haschcode.pizza.model.Pizza;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Pizza pizza = null;
        try {
            pizza = ReadFile.muestraContenido(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(pizza.toString());
    }
}
