package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
    private double[][] rows;
    private double[][] columns;
    private double determinante;
    ArrayList<Double> subdet = new ArrayList<>();

    public Matrix(double[][] matrix) throws Exception{
        if(!isMatrix(matrix)){
            throw new Exception("not a matrix");
        }
        setRows(matrix);
    }
    private boolean isMatrix(double[][] matrix){
        for(double[] row : matrix){
            if(row.length != matrix[0].length){
                return false;
            }
        }
        return true;
    }
    public void setDeterminante() throws Exception {
        this.determinante = determinante();
    }
    public double getDeterminante() {
        return determinante;
    }
    public double[][] getRows() {
        return rows;
    }
    public void setRows(double[][] matrix){
        this.rows = matrix;
        setColumnsAfterRows();
    }
    private double[][] convertRowsColumns(double[][] matrix){
        double[][] converted = new double[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix[0].length; i++){
            for(int j = 0; j < matrix.length; j++){
                converted[i][j] = matrix[j][i];
            }
        }
        return converted;
    }
    public double[][] getColumns() {
        return columns;
    }
    public void setColumns(double[][] columns) {
        this.columns = columns;
        setRowsAfterColumns();
    }
    private void setColumnsAfterRows(){
        this.columns = convertRowsColumns(this.rows);
    }
    private void setRowsAfterColumns() {
        this.rows = convertRowsColumns(this.columns);
    }
    public double determinante() throws Exception {
        double[] det = {0};
        subdeterminanten(this, det, 1, 1);
        return det[0];
    }
    private void subdeterminanten(Matrix matrix, double[] det, int carriedsign, double erzeuger) throws Exception {
        int localsign;
        if(matrix.rows.length == 2 && matrix.rows[1].length == 2){
            det[0] += erzeuger * carriedsign * (matrix.rows[0][0] * matrix.rows[1][1] - matrix.rows[0][1] * matrix.rows[1][0]);
        }
        else{
            double subdet1 = det[0];
            for(int i = 0; i < matrix.rows.length; i++){
                localsign = (int) Math.pow(-1, i) * carriedsign; // Vorzeichenformel für die Anwendung vereinfacht
                subdeterminanten(matrix.subMatrix(0, i, matrix.rows[0][i]), det, localsign, matrix.rows[0][i]);
            }
            double subdet2 = det[0];
            double subdet = subdet2-subdet1;
            det[0] += subdet * (erzeuger-1);

        }
    }
    public Matrix subMatrix(int rowIndex, int colIndex, double erzeuger) throws Exception {
        Matrix subMatrix = new Matrix(this.rows.clone());
        subMatrix.deleteRow(subMatrix, rowIndex);
        subMatrix.deleteCol(subMatrix, colIndex);
        return subMatrix;
    }
    public void deleteRow(Matrix matrix, int rowIndex){
        if (rowIndex > matrix.rows.length-1){
            throw new ArrayIndexOutOfBoundsException("Matrix Rang kleiner als Zeilenindex");
        }
        double[][] newRows = new double[matrix.rows.length-1][matrix.rows[1].length];
        System.arraycopy(matrix.rows, 0, newRows, 0, rowIndex);
        System.arraycopy(matrix.rows, rowIndex+1, newRows, rowIndex, matrix.rows.length-(rowIndex+1));

        matrix.rows = newRows;
    }
    public void deleteCol(Matrix matrix, int colIndex){
        if(colIndex > matrix.rows[0].length-1){
            throw new ArrayIndexOutOfBoundsException("Matrix Spaltenrang ist kleiner als Spaltenindex: " + "Rang: " + (matrix.rows[0].length-1) + ", Spaltenindex: " + colIndex);
        }
        double[][] newRows = new double[matrix.rows.length][matrix.rows[0].length-1];
        for(int i = 0; i < matrix.rows.length; i++){
            if(colIndex > 0){
                System.arraycopy(matrix.rows[i], 0, newRows[i], 0, colIndex);
            }
            if(colIndex < matrix.rows[0].length-1){
                System.arraycopy(matrix.rows[i], colIndex+1, newRows[i], colIndex, matrix.rows[0].length - (colIndex+1));
            }
        }
        matrix.rows = newRows;
    }
    public void printMatrix(){
        for(int i = 0; i < rows.length; i++){
            printArray(rows[i]);
        }
        System.out.println();
    }
    public void printArray(double[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
