package org.example.studyplanner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TimelineView {

    public String habitDateViewAll(HabitTracker ht) {
        List<HabitRecordView> habitRecordViews = new HabitRecordViewBuilder().buildHabitRecordViews(ht.getHabits(), ht);
        return habitRecordViews.stream()
                .map(HabitRecordView::toString)
                .collect(Collectors.joining());
    }
}
