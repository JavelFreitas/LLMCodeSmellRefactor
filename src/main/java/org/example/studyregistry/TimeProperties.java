package org.example.studyregistry;

public class TimeProperties {
    public final Integer practicedDays;
    public final int day;
    public final int month;
    public final int year;
    public final Double duration;

    private TimeProperties(Builder builder) {
        this.practicedDays = builder.practicedDays;
        this.day = builder.day;
        this.month = builder.month;
        this.year = builder.year;
        this.duration = builder.duration;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer practicedDays;
        private int day;
        private int month;
        private int year;
        private Double duration;

        public Builder practicedDays(Integer practicedDays) {
            this.practicedDays = practicedDays;
            return this;
        }

        public Builder day(int day) {
            this.day = day;
            return this;
        }

        public Builder month(int month) {
            this.month = month;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder duration(Double duration) {
            this.duration = duration;
            return this;
        }

        public TimeProperties build() {
            return new TimeProperties(this);
        }
    }
}