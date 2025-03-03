package org.example;

import java.util.Arrays;
import java.util.Random;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        // Aufgabe 1
        Matrix matrix1 = new Matrix(new double[][] {
                {1,1,3,2,-1},
                {1,1,1,0,2},
                {-1,2,1,2,-3},
                {4,-2,1,1,-1},
                {2,0,2,4,1}
        });
        matrix1.setDeterminante();
        System.out.println("Determinante: " + matrix1.getDeterminante());
        //Aufgabe 2

        Vector b = new Vector(new double[] {4,7,3,-2,4});
        Vector x = Mathe.lgs_loesen(matrix1, b);
        System.out.println(Arrays.toString(x.getValues()));

    }
    public static class Mathe{
        public static Vector lgs_loesen(Matrix A, Vector b) throws Exception {
            if(A.getRows().length != A.getRows()[0].length || b.values.length != A.getRows().length){
                throw new Exception("This LGS can't have a solution");
            }
            double[] x = new double[b.values.length];
            A.setDeterminante();
            Matrix Ai = new Matrix(A.getRows());
            for(int i = 0; i < b.values.length; i++){
                Ai.setRows(A.getRows());
                double[][] Ai_columns = Ai.getColumns();
                Ai_columns[i] = b.values;
                Ai.setColumns(Ai_columns);
                Ai.setDeterminante();
                x[i] = Ai.getDeterminante()/A.getDeterminante();
            }
            return new Vector(x);
        }
    }
}