package org.example.studyplanner;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HabitTrackerTest {
    HabitTracker habitTracker = HabitTracker.getHabitTracker();
    List<Integer> habitIds = new ArrayList<>();

    @BeforeEach
    void setUp() {
        habitIds.add(
                habitTracker.addHabit(
                        new HabitTracker.HabitBuilder(habitTracker.getTrackerKeys().size() + 1)
                                .withName("Test Search Name Habit")
                                .withMotivation("Test Search Habit Motivation")
                                .withIsConcluded(false)
                )
        );
    }

    boolean verifyCardStringInList(String searching, List<String> response) {
        for (String habit : response) {
            if (habit.contains(searching)) {
                return true;
            }
        }
        return false;
    }

    @Test
    @Order(1)
    @DisplayName("Search Name In Habits Test")
    void searchNameInHabits() {
        List<String> response = habitTracker.searchInHabits("Search Name Habit");
        assertTrue(verifyCardStringInList("Search Name Habit", response));
    }

    @Test
    @Order(2)
    @DisplayName("Search Motivation In Habits Test")
    void searchMotivationInHabits() {
        List<String> response = habitTracker.searchInHabits("Search Habit Motivation");
        assertTrue(verifyCardStringInList("Search Habit Motivation", response));
    }

    void verifyAddHabitProperties(Habit habit) {
        assertEquals("Add Habit Name Test", habit.getName());
        assertEquals("Add Habit Motivation Test", habit.getMotivation());
        assertEquals(30, habit.getDailyDedicationTime().getMinute());
        assertEquals(2, habit.getDailyDedicationTime().getHour());
        assertEquals(2024, habit.getStartDate().getYear());
        assertEquals(2, habit.getStartDate().getMonthValue());
        assertEquals(4, habit.getStartDate().getDayOfMonth());
        assertEquals(8, habit.getStartDate().getHour());
        assertEquals(16, habit.getStartDate().getMinute());
        assertEquals(32, habit.getStartDate().getSecond());
    }

    @Test
    @Order(3)
    @DisplayName("Add Habit Test")
    void addHabitTest() {
        // Extracted methods improve readability
        int id = createAndAddHabit();
        Habit habit = retrieveAndVerifyHabit(id);
        verifyAddHabitProperties(habit);
    }

    private int createAndAddHabit() {
        String name = "Add Habit Name Test";
        String motivation = "Add Habit Motivation Test";
        int dailyMinutesDedication = 30;
        int dailyHoursDedication = 2;
        int year = 2024, month = 2, day = 4, hour = 8, minute = 16, second = 32;
        boolean isConcluded = false;

        return habitTracker.addHabit(
                new HabitTracker.HabitBuilder(habitTracker.getTrackerKeys().size() + 1)
                        .withName(name)
                        .withMotivation(motivation)
                        .withDailyDedicationTime(dailyHoursDedication, dailyMinutesDedication)
                        .withStartDate(year, month, day, hour, minute, second)
                        .withIsConcluded(isConcluded)
        );
    }

    private Habit retrieveAndVerifyHabit(int id) {
        Habit habit = habitTracker.getHabitById(id);
        if (habit == null) {
            fail("Habit was not added successfully.");
        }
        assertFalse(habit.getIsConcluded());
        return habit;
    }
}
