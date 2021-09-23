package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        //Scan column and row
        String columnAndRow = "3 3";

        int[] matrizInputs = {5, 8, 7, 6, 9, 1, 4, 3, 2};

        String finalOutputSequence = getBiggerSequenceAdjacMatrix(columnAndRow, matrizInputs);

        System.out.println();
        System.out.println("Output: ");
        System.out.println(finalOutputSequence);
        System.out.println();
    }

    public static String getBiggerSequenceAdjacMatrix(String columnAndRow, int[] matrizInputs ) {

        String[] columnAndRowSplit = columnAndRow.split(" ");

        int columnSize = Integer.parseInt(columnAndRowSplit[0]);
        int rowSize = Integer.parseInt(columnAndRowSplit[1]);

        int[][] inputs = new int[columnSize][rowSize];



        int count = 0;

        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                inputs[row][column] = matrizInputs[count];
                count++;
            }
        }

        List<int[]> adjacentsCoordinates = getAdjacentsCoordinatesOnInput(inputs);

        ArrayList<ArrayList<Integer>> listSequences = new ArrayList<>();
        for (int i = 0; i < adjacentsCoordinates.size(); i++) {
            listSequences.add(getSequenceAdjacentsCoordinates(adjacentsCoordinates.get(i)));
        }

        ArrayList<ArrayList<Integer>> finalListVector = getFinalSequence(listSequences);

        String finalOutputSequence = getFinalOutputSequence(finalListVector);

        return finalOutputSequence;

    }

    private static String getFinalOutputSequence(ArrayList<ArrayList<Integer>> finalListVector) {
        String finalOutput = "";
        for (int i = 0; i < finalListVector.size(); i++) {
            for (int j = 0; j < finalListVector.get(i).size(); j++) {

                finalOutput = finalOutput + (finalListVector.get(i).get(j) + " ");
            }
        }

        return finalOutput;
    }

    private static List<int[]> getAdjacentsCoordinatesOnInput(int[][] inputs) {
        List<int[]> adjacentsCoordinates = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                adjacentsCoordinates.add(getAdjacentsCoordinates(i, j, inputs));
            }
        }

        return adjacentsCoordinates;
    }

    private static ArrayList<ArrayList<Integer>> getFinalSequence(ArrayList<ArrayList<Integer>> listSequences) {
        ArrayList<ArrayList<Integer>> aux = new ArrayList<>();
        for (int i = 0; i < listSequences.size(); i++) {
            if (!aux.isEmpty()) {
                if (aux.get(0).size() < listSequences.get(i).size()) {
                    aux.clear();
                    aux.add(listSequences.get(i));
                }
            } else {
                aux.add(listSequences.get(i));
            }
        }

        return aux;
    }

    private static ArrayList<Integer> getSequenceAdjacentsCoordinates(int[] adjacentsCoordinates) {
        ArrayList<Integer> listSequence = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < adjacentsCoordinates.length; i++) {
            int aux = adjacentsCoordinates[i];
            if (aux > 0) {
                listSequence.add(aux);
                if (i + 1 <= adjacentsCoordinates.length - 1) {
                    if (listSequence.get(count) > adjacentsCoordinates[i + 1]
                            || listSequence.get(count) + 1 != adjacentsCoordinates[i + 1]) {
                        listSequence.clear();
                        count = 0;
                    } else {
                        count++;
                    }
                }
            }

        }

        return listSequence;
    }

    public static int[] getAdjacentsCoordinates(int row, int column, int[][] inputs) {

        int[] adjacentsCoordinates = new int[5];
        int count = 0;
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                if (i == row || j == column) {
                    reverseRow(inputs[i]);
                    adjacentsCoordinates[count] = inputs[i][j];
                    count++;
                }
            }
        }
        return adjacentsCoordinates;
    }

    private static void reverseRow(int[] v) {
        int initRow = 0;
        int finalRow = v.length - 1;

        while (initRow < finalRow) {
            tradeValues(v, initRow, finalRow);
            initRow++;
            finalRow--;
        }
    }

    private static void tradeValues(int[] v, int initRow, int finalRow) {
        int aux = v[initRow];
        v[initRow] = v[finalRow];
        v[finalRow] = aux;
    }

}
