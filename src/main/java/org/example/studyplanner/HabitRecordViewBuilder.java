package org.example.studyplanner;

import java.util.List;
import java.util.stream.Collectors;

class HabitRecordViewBuilder {
    List<HabitRecordView> buildHabitRecordViews(List<Habit> habits, HabitTracker ht) {
        return habits.stream()
                .map(habit -> new HabitRecordView(
                        habit.getName(),
                        ht.getHabitRecords(habit.getId())
                                .stream()
                                .map(ht::formatHabitDate)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}