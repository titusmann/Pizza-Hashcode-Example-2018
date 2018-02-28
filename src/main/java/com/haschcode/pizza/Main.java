package com.haschcode.pizza;

import com.haschcode.pizza.model.Pizza;
import com.haschcode.pizza.model.Slice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.haschcode.pizza.Main.calcularCortes;

public class Main {

    public static List<String> comb;

    public static Pizza pizza;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        pizza = null;

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

        System.out.println(pizza.toString());
        HashMap<Integer, Integer> weightCells = new HashMap<>();

        long size = pizza.getMapIngredient().size();
        for(int i = 0; i < size; i++) {
            Integer weight = weightOfCell(i);
            weightCells.put(i,weight);
            System.out.println(weight);
        }

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
                    System.out.println(x+"-"+y);
                }
            }
        }

        System.out.println(result.size());

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
}
