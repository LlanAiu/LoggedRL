package org.llan.loggedrl.framework.util;

public class Matrix {
    private double[][] _matrix;

    public Matrix(int rows, int cols){
        _matrix = new double[rows][cols];
    }

    public void fill(double min, double max){
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                double value = min + Math.random() * (max - min);
                set(i, j, value);
            }
        }
    }

    public double get(int row, int col){
        return _matrix[row][col];
    }

    public void set(int row, int col, double value){
        _matrix[row][col] = value;
    }

    public int getRows(){
        return _matrix.length;
    }

    public int getCols(){
        return _matrix[0].length;
    }

    public Matrix multiply(Matrix other){
        if(getCols() != other.getRows()){
            throw new IllegalArgumentException("Matrix dimensions do not match");
        }

        Matrix result = new Matrix(getRows(), other.getCols());

        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < other.getCols(); j++){
                double sum = 0;
                for(int k = 0; k < getCols(); k++){
                    sum += get(i, k) * other.get(k, j);
                }
                result.set(i, j, sum);
            }
        }

        return result;
    }

    public Matrix transpose(){
        Matrix result = new Matrix(getCols(), getRows());

        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                result.set(j, i, get(i, j));
            }
        }

        return result;
    }

    public Matrix add(Matrix other){
        if(getRows() != other.getRows() || getCols() != other.getCols()){
            throw new IllegalArgumentException("Matrix dimensions do not match");
        }

        Matrix result = new Matrix(getRows(), getCols());

        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                result.set(i, j, get(i, j) + other.get(i, j));
            }
        }

        return result;
    }
}
