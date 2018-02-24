package com.haschcode.pizza.model;

import java.util.HashMap;
import java.util.List;

public class Pizza {

    private int maxColumn;
    private int maxRows;
    private int maxCellsSlice;
    private int minIngredientSlice;

    private HashMap<Integer, Boolean> mapIngredient;

    private List<Slice> sliceList;

    public Pizza(int maxColumn, int maxRows, int maxCellsSlice, int minIngredientSlice, HashMap<Integer, Boolean> mapIngredient) {
        this.maxColumn = maxColumn;
        this.maxRows = maxRows;
        this.maxCellsSlice = maxCellsSlice;
        this.minIngredientSlice = minIngredientSlice;
        this.mapIngredient = mapIngredient;
    }

    public int getMaxColumn() {
        return maxColumn;
    }


    public int getMaxRows() {
        return maxRows;
    }


    public int getMaxCellsSlice() {
        return maxCellsSlice;
    }


    public int getMinIngredientSlice() {
        return minIngredientSlice;
    }

    public HashMap<Integer, Boolean> getMapIngredient() {
        return mapIngredient;
    }

    public Boolean getIngredient(Integer pos) {
        return mapIngredient.get(pos);
    }

    public Boolean getIngredient(Integer y, Integer x) {
        return mapIngredient.get(y*maxColumn+x);
    }

    public Integer getXpos(Integer i){
        return i%maxColumn;
    }

    public Integer getYpos(Integer i){
        return i/maxColumn;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "maxColumn=" + maxColumn +
                ", maxRows=" + maxRows +
                ", maxCellsSlice=" + maxCellsSlice +
                ", minIngredientSlice=" + minIngredientSlice +
                ", mapIngredient=" + mapIngredient +
                '}';
    }
}
