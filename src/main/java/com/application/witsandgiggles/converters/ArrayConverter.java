package com.application.witsandgiggles.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class ArrayConverter implements AttributeConverter<int[], String> {

    @Override
    public String convertToDatabaseColumn(int[] array) {
        if (array.length == 0) {
            return "";
        }

        return Arrays.toString(array);
    }

    @Override
    public int[] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new int[0];
        }

        String[] strings = dbData.replace("[", "").replace("]", "").split(", ");

        int gridSize = strings.length;
        int[] result = new int[gridSize];

        for (int i = 0; i < gridSize; i++) {
                result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
}
