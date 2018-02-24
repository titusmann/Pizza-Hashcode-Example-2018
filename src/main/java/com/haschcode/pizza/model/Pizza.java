package com.haschcode.pizza.model;

import java.util.HashMap;

public class Pizza {

    private int maxColumn;
    private int maxRows;
    private int maxCellsSlice;
    private int minIngredientSlice;

    private HashMap<Integer, Boolean> mapIngredient;

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
