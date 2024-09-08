package org.llan.loggedrl.framework.save;

import org.llan.loggedrl.framework.util.Matrix;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSaver {

    private static final String location = System.getProperty("user.dir") + "\\src\\main\\resources\\org\\llan\\loggedrl\\data\\";

    public static void saveMatrixToFile(Matrix matrix, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(location + filename))) {
            writer.write(matrix.toDataString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Matrix loadMatrixFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(location + filename))) {
            List<double[]> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                double[] row = Arrays.stream(values).mapToDouble(Double::parseDouble).toArray();
                rows.add(row);
            }
            int numRows = rows.size();
            int numCols = rows.get(0).length;
            Matrix matrix = new Matrix(numRows, numCols);
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    matrix.set(i, j, rows.get(i)[j]);
                }
            }
            return matrix;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

