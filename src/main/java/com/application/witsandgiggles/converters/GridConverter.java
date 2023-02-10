package com.application.witsandgiggles.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class GridConverter implements AttributeConverter<int[][], String> {

    @Override
    public String convertToDatabaseColumn(int[][] grid) {
        if (grid.length == 0) {
            return "";
        }

        return Arrays.deepToString(grid);
    }

    @Override
    public int[][] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        String[] strings = dbData.replace("[", "").replace("]", "").split(", ");

        int gridSize = (int) Math.sqrt(strings.length);
        int result[][] = new int[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++)
            result[i][j] = Integer.parseInt(strings[(gridSize * i) + j]);
        }
        return result;
    }
}
