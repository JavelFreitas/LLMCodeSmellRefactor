package org.example.studyregistry;

import java.util.List;

public class WeekSetupValidator {
    private static final int REQUIRED_PROPERTIES = 11;

    public static void validateProperties(List<String> properties) {
        if (properties == null) {
            throw new IllegalArgumentException("A lista de propriedades não pode ser nula");
        }
        if (properties.size() != REQUIRED_PROPERTIES) {
            throw new IllegalArgumentException(
                    String.format("A lista deve conter exatamente %d propriedades, mas contém %d",
                            REQUIRED_PROPERTIES,
                            properties.size())
            );
        }
        validateNonNullElements(properties);
    }

    private static void validateNonNullElements(List<String> properties) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i) == null) {
                throw new IllegalArgumentException(
                        String.format("A propriedade no índice %d não pode ser nula", i)
                );
            }
        }
    }
}