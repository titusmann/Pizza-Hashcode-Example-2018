package com.haschcode.pizza;

import com.haschcode.pizza.model.Pizza;
import com.haschcode.pizza.model.Slice;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.haschcode.pizza.Main.calcularCortes;

public class Main {

    public static List<String> comb;

    public static Pizza pizza;

    public static List<Slice> slices;

    public static AtomicInteger contador = new AtomicInteger(0);

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        pizza = null;
        slices = new ArrayList<>();

        HashMap<Integer, Integer> weightCell = new HashMap<>();
        try {
            pizza = ReadFile.muestraContenido(args[0]);

            comb = combinaciones();

            //for(int i = 0; i<pizza.getMapIngredient().size(); i++){
            //    int weight = 0;
            //    weight = weightOfCell(i,pizza);

            //    weightCell.put(i,weight);
            //}

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(pizza.toString());
        HashMap<Integer, Integer> weightCells = new HashMap<>();

        long size = pizza.getMapIngredient().size();
        int posMenorPeso = -1;
        do {
            int menorPeso = 1000;
            posMenorPeso = -1;
            for (Integer i : pizza.getMapIngredient().keySet()) {
                Integer weight = weightOfCell(i);
                weightCells.put(i, weight);
                if (weight < menorPeso && weight > 0) {
                    menorPeso = weight;
                    posMenorPeso = i;
                }
                //System.out.println(weight);
            }
            pizza.setWeightOfCells(weightCells);

            // - Seleccionar la celda con menor peso.
            // - Seleccionar el corte mas grande.
            // - En caso de haber mas de un corte, seleccionar el corte cuya suma de pesos sea menor.
            Integer actualSize = 0;
            for (String combi : comb) {
                Integer xmax = Integer.parseInt(combi.split("-")[0]);
                Integer ymax = Integer.parseInt(combi.split("-")[1]);

                if (calcularCortes(xmax, ymax, pizza.getXpos(posMenorPeso), pizza.getYpos(posMenorPeso)) > 0) {
                    //Esta combinatoria tiene cortes posibles. Se realiza el primero.
                    realizarPrimerCorte(xmax, ymax, pizza.getXpos(posMenorPeso), pizza.getYpos(posMenorPeso));
                }
            }
        } while (posMenorPeso != -1);


//        try {
//            WriteFile.imprimeCortes(slices);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public static List<String> combinaciones(){
        List<String> result = new ArrayList<>();
        Integer max = pizza.getMaxCellsSlice();
        Integer min = pizza.getMinIngredientSlice()*2;

        int x;
        int y;

        for(x = 1; x <= max ; x++) {
            for (y = 1; y <= max; y++) {
                if(x*y <= max && x*y >= min){
                    result.add(x+"-"+y);
                    //System.out.println(x+"-"+y);
                }
            }
        }

        Collections.sort(result, Comparator.comparingInt(c -> Integer.parseInt(c.split("-")[0]) * Integer.parseInt(c.split("-")[1])));
        Collections.reverse(result);

        return result;
    }

    public static Integer weightOfCell(Integer i){
        Integer result = 0;

        Integer coorx = pizza.getXpos(i);
        Integer coory = pizza.getYpos(i);

        for(String combinatoria : comb){
            Integer xmax = Integer.parseInt(combinatoria.split("-")[0]);
            Integer ymax = Integer.parseInt(combinatoria.split("-")[1]);

            result += calcularCortes(xmax,ymax,coorx,coory);
        }
        return result;
    }



    /**
     * Devuelve el peso con respecto a esa combinatoria.
     * @param avancex
     * @param avancey
     * @param coorx
     * @param coory
     * @return
     */
    public static Integer calcularCortes(Integer avancex, Integer avancey, Integer coorx, Integer coory){
        Integer resultado = 0;
        Slice slice;
        for(int x=0; x<avancex; x++) {
            for(int y=0; y<avancey; y++) {
                slice = new Slice(coorx-x, coory-y, coorx-x+(avancex-1), coory-y+(avancey-1));
                if ( slice.validateSlice(pizza) ) {
                    resultado++;
                }
            }
        }
        return resultado;
    }

    public static void realizarPrimerCorte(Integer avancex, Integer avancey, Integer coorx, Integer coory){
        Integer resultado = 0;
        Slice slice;
        for(int x=0; x<avancex; x++) {
            for(int y=0; y<avancey; y++) {
                slice = new Slice(coorx-x, coory-y, coorx-x+(avancex-1), coory-y+(avancey-1));
                if ( slice.validateSlice(pizza) ) {
                    //Realizamos el corte.
                    slices.add(slice);
                    System.out.println("Cortecillo num: " + contador.getAndIncrement());

                    //Anulamos las casillas.
                    for(int x2 = coorx-x; x2 <= coorx-x+(avancex-1); x2++ ){
                        for(int y2 = coory-y; y2 <= coory-y+(avancey-1); y2++ ){
                            pizza.cutPizza(y2,x2);

                        }
                    }

                    if(contador.get()%50==0) {
                        try {
                            WriteFile.imprimeCortes(slices);
                            slices.clear();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
