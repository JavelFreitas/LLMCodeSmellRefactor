package org.example.studyregistry;

import java.util.List;
import java.util.Arrays;

public class WeekSetup {
    private static final int REQUIRED_PROPERTIES = 11;

    private final Plan plan;
    private final Objective objective;
    private final Material material;
    private final Reminder reminder;
    private final MainItems mainItems;

    private WeekSetup(Builder builder) {
        this.plan = builder.plan;
        this.objective = builder.objective;
        this.material = builder.material;
        this.reminder = builder.reminder;
        this.mainItems = builder.mainItems;
    }

    public static class Plan {
        private final String name;
        public Plan(String name) {
            this.name = name;
        }
    }

    public static class Objective {
        private final String title;
        private final String description;
        public Objective(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }

    public static class Material {
        private final String topic;
        private final String format;
        public Material(String topic, String format) {
            this.topic = topic;
            this.format = format;
        }
    }

    public static class Reminder {
        private final String title;
        private final String description;
        public Reminder(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }

    public static class MainItems {
        private final String taskTitle;
        private final String habit;
        private final String cardStudy;
        private final String goal;
        public MainItems(String taskTitle, String habit, String cardStudy, String goal) {
            this.taskTitle = taskTitle;
            this.habit = habit;
            this.cardStudy = cardStudy;
            this.goal = goal;
        }
    }

    public static class Builder {
        private Plan plan;
        private Objective objective;
        private Material material;
        private Reminder reminder;
        private MainItems mainItems;

        public Builder plan(String name) {
            this.plan = new Plan(name);
            return this;
        }

        public Builder objective(String title, String description) {
            this.objective = new Objective(title, description);
            return this;
        }

        public Builder material(String topic, String format) {
            this.material = new Material(topic, format);
            return this;
        }

        public Builder reminder(String title, String description) {
            this.reminder = new Reminder(title, description);
            return this;
        }

        public Builder mainItems(String taskTitle, String habit, String cardStudy, String goal) {
            this.mainItems = new MainItems(taskTitle, habit, cardStudy, goal);
            return this;
        }

        public WeekSetup build() {
            return new WeekSetup(this);
        }
    }

    public static WeekSetup fromList(List<String> properties) {
        WeekSetupValidator.validateProperties(properties);
        return new Builder()
                .plan(properties.get(0))
                .objective(properties.get(1), properties.get(2))
                .material(properties.get(3), properties.get(4))
                .mainItems(properties.get(8), properties.get(9), properties.get(10), properties.get(5))
                .reminder(properties.get(6), properties.get(7))
                .build();
    }

    public List<String> toList() {
        return Arrays.asList(
                plan.name,
                objective.title,
                objective.description,
                material.topic,
                material.format,
                mainItems.goal,
                reminder.title,
                reminder.description,
                mainItems.taskTitle,
                mainItems.habit,
                mainItems.cardStudy
        );
    }
}