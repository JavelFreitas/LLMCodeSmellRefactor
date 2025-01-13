package org.example.studyregistry;

import java.util.ArrayList;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private List<Registry> registryList;
    private List<String> weekResponsibilities;

    // Record para encapsular os dados do setup da semana
    public record WeekSetup(
            String planName,
            String objectiveTitle,
            String objectiveDescription,
            String materialTopic,
            String materialFormat,
            String goal,
            String reminderTitle,
            String reminderDescription,
            String mainTaskTitle,
            String mainHabit,
            String mainCardStudy
    ) {}

    // Construtor privado para Singleton
    private StudyTaskManager() {
        this.registryList = new ArrayList<>();
        this.weekResponsibilities = new ArrayList<>();
    }

    // Método para acessar a instância Singleton
    public static StudyTaskManager getStudyTaskManager() {
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    // Métodos de acesso para weekResponsibilities
    public List<String> getWeekResponsibilities() {
        return new ArrayList<>(weekResponsibilities); // Retorna uma cópia imutável da lista
    }

    // Configuração da semana com WeekSetup
    public void setUpWeek(WeekSetup weekSetup) {
        this.weekResponsibilities = List.of(
                weekSetup.planName(),
                weekSetup.objectiveTitle(),
                weekSetup.objectiveDescription(),
                weekSetup.materialTopic(),
                weekSetup.materialFormat(),
                weekSetup.goal(),
                weekSetup.reminderTitle(),
                weekSetup.reminderDescription(),
                weekSetup.mainTaskTitle(),
                weekSetup.mainHabit(),
                weekSetup.mainCardStudy()
        );
    }

    // Método que converte uma lista de strings para WeekSetup e configura a semana
    public void handleSetUpWeek(List<String> stringProperties) {
        if (stringProperties.size() == 11) {
            WeekSetup weekSetup = new WeekSetup(
                    stringProperties.get(0),
                    stringProperties.get(1),
                    stringProperties.get(2),
                    stringProperties.get(3),
                    stringProperties.get(4),
                    stringProperties.get(5),
                    stringProperties.get(6),
                    stringProperties.get(7),
                    stringProperties.get(8),
                    stringProperties.get(9),
                    stringProperties.get(10)
            );
            setUpWeek(weekSetup);
        } else {
            throw new IllegalArgumentException("A lista deve conter exatamente 11 elementos.");
        }
    }

    // Métodos para gerenciar registros
    public void addRegistry(Registry registry) {
        registryList.add(registry);
    }

    public void removeRegistry(Registry registry) {
        registryList.remove(registry);
    }

    public List<Registry> getRegistryList() {
        return new ArrayList<>(registryList); // Retorna uma cópia imutável da lista
    }

    // Método de busca nos registros
    public List<String> searchInRegistries(String text) {
        List<String> response = new ArrayList<>();
        for (Registry registry : registryList) {
            String name = (registry.getName() != null) ? registry.getName() : "";
            if (name.toLowerCase().contains(text.toLowerCase())) {
                response.add(name);
            }
        }
        return response;
    }

    // Método para facilitar a busca (delegação para searchInRegistries)
    public List<String> handleSearch(String text) {
        return searchInRegistries(text);
    }
}
