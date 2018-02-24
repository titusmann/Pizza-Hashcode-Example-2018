package com.haschcode.pizza;

import com.haschcode.pizza.model.Pizza;
import com.haschcode.pizza.model.Slice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static List<String> comb;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Pizza pizza = null;

        HashMap<Integer, Integer> weightCell = new HashMap<>();
        try {
            pizza = ReadFile.muestraContenido(args[0]);

            comb = combinaciones(pizza);

            //for(int i = 0; i<pizza.getMapIngredient().size(); i++){
            //    int weight = 0;
            //    weight = weightOfCell(i,pizza);

            //    weightCell.put(i,weight);
            //}

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(pizza.toString());
    }

    public Integer weightOfCell(Integer i, Pizza pizza){
        Integer result = 0;

        for(String combinatoria : comb){
            Integer xmax = Integer.parseInt(combinatoria.split("-")[0]);
            Integer ymax = Integer.parseInt(combinatoria.split("-")[1]);




        }
        return result;
    }

    public static List<String> combinaciones(Pizza pizza){
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

        //System.out.println(result.size());

        return result;
    }

}
