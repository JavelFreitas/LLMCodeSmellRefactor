package org.example.studyregistry;

public class RegistryProperties {
    public final Integer id;
    public final String name;
    public final Integer priority;
    public final boolean isActive;

    private RegistryProperties(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.priority = builder.priority;
        this.isActive = builder.isActive;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Integer priority;
        private boolean isActive;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public RegistryProperties build() {
            return new RegistryProperties(this);
        }
    }
}