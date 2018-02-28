package com.haschcode.pizza;

import com.haschcode.pizza.model.Pizza;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WriteFile {


    public static void imprimePesos(Pizza pizza) throws FileNotFoundException, IOException {
        String file = "C:\\Users\\fjbelaza\\Documents\\Pizza-Hashcode-Example-2018-develop\\Pizza-Hashcode-Example-2018\\src\\main\\java\\resources\\output.out";

        FileWriter f = new FileWriter(file);
        BufferedWriter b = new BufferedWriter(f);

        int columnas = pizza.getMaxColumn();
        HashMap<Integer,Integer> pesos = pizza.getWeightOfCells();

        int i = 0;
        for (Integer peso : pesos.values()) {
            b.write(peso + "\t");
            i++;
            if(i >= columnas){
                b.newLine();
                i = 0;
            }
        }
        b.close();
    }

}
