package com.haschcode.pizza.model;

public class Slice {

    private int x1;
    private int y1;
    private int x2;
    private int y2;


    public Slice(int x1, int y1, int x2, int y2) {
        this.x1 = x1; // Fila
        this.y1 = y1; // Columna
        this.x2 = x2;
        this.y2 = y2;
    }

    public Slice(int esq1, int esq2) {
        //this.x1 = esq1/maxColumn; // Fila
        //this.y1 = esq1%maxColumn; // Columna
        //this.x2 = esq2/maxColumn;
        //this.y2 = esq2%maxColumn;
    }


    public boolean validateSlice(Pizza pizza) {
        //Todas las coordenadas dentro del mapa
        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0) {
            return false;
        }
        if (x1 >= pizza.getMaxRows() || x2 >= pizza.getMaxRows()
            || y1 >= pizza.getMaxColumn() || y2 >= pizza.getMaxColumn()) {
            return false;
        }

        //El tamaño del corte menor que el maximo
        if ((x2-x1)*(y2-y1) > pizza.getMaxCellsSlice()) {
            return false;
        }

        //Numero de ingredientes de cada tipo superior al minimo y ningun nulo
        int numTomatoes = 0;
        int numMushrooms = 0;
        for (int x = x1;x<=x2;x++){
            for (int y = y1; y<=y2; y++){
                Boolean ingredient = pizza.getIngredient(y,x);
                if (ingredient == null) {
                    return false;
                }
                if (ingredient) {
                    numTomatoes++;
                } else {
                    numMushrooms++;
                }

            }
        }
        if (numMushrooms < pizza.getMinIngredientSlice() || numTomatoes < pizza.getMinIngredientSlice()){
            return false;
        }

        return true;
    }
}
