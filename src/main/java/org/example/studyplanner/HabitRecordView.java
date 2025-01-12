package org.example.studyplanner;

import java.util.List;

class HabitRecordView {
    private final String habitName;
    private final List<String> recordDates;

    HabitRecordView(String habitName, List<String> recordDates) {
        this.habitName = habitName;
        this.recordDates = recordDates;
    }

    @Override
    public String toString() {
        return "[ Habit: " + habitName + ". Records: " + recordDates + "]";
    }
}
